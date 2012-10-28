package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockInvocationHandler implements InvocationHandler {
    private static Map<Class<?>, Object> primitiveDefaultValue;
    static {
        primitiveDefaultValue = new HashMap<Class<?>, Object>();
        primitiveDefaultValue.put(boolean.class, false);
        primitiveDefaultValue.put(char.class, '\0');
        primitiveDefaultValue.put(byte.class, (byte) 0);
        primitiveDefaultValue.put(short.class, (short) 0);
        primitiveDefaultValue.put(int.class, 0);
        primitiveDefaultValue.put(long.class, 0L);
        primitiveDefaultValue.put(float.class, 0f);
        primitiveDefaultValue.put(double.class, 0d);
    }

    private List<Invocation> storedInvocations = new ArrayList<Invocation>();

    private MockProcess mockProcess = MockProcess.mockProcess();
    private Map<Invocation, Integer> invocationTimesMap = new HashMap<Invocation, Integer>();

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Invocation invocation = new Invocation(method, objects, mockProcess.getAllMatchers());
        if(mockProcess.onVerifying()) {
            verifyInvocation(invocation);
        } else {
            Answer answer = getAnswerFromInvocation(invocation);
            if (answer != null) {
                recordInvocations(invocation);
                return answer.answer();
            }
            OngoingStubbing ongoingStubbing = new OngoingStubbing(invocation, storedInvocations);
            mockProcess.setCurrentOngoingStubbing(ongoingStubbing);
        }
        return defaultValueFor(method);
    }

    private Answer getAnswerFromInvocation(Invocation invocation) {
        for (Invocation storeInvocation : storedInvocations) {
            if(storeInvocation.equals(invocation)) {
                return storeInvocation.getAnswer();
            }
        }
        return null;
    }

    private void recordInvocations(Invocation invocation) {
        Integer times = invocationTimesMap.get(invocation);
        if(times == null) {
            invocationTimesMap.put(invocation, 1);
        } else {
            invocationTimesMap.put(invocation, times + 1);
        }
    }

    private void verifyInvocation(Invocation invocation) {
        MockVerification verification = mockProcess.getVerification();
        Integer times = invocationTimesMap.get(invocation);
        VerificationData data = new VerificationData(invocation, times == null ? 0 : times);
        try {
            verification.verify(data);
        } finally {
            mockProcess.stopVerifying();
        }
    }

    private Object defaultValueFor(Method method) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if(method.getReturnType().isPrimitive()) {
            return primitiveDefaultValue.get(method.getReturnType());
        } else {
            return null;
        }
    }
}
