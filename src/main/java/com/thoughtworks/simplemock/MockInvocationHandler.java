package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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

    private Map<Invocation, Answer> invocationReturnValueMap = new HashMap<Invocation, Answer>();

    private MockProcess mockProcess = new MockProcess();
    private Map<Invocation, Integer> invocationTimesMap = new HashMap<Invocation, Integer>();

    public MockInvocationHandler(MockProcess mockProcess) {
        this.mockProcess = mockProcess;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Invocation invocation = new Invocation(method, objects);
        if(mockProcess.onVerifying()) {
            verifyInvocation(invocation);
        } else {
            Answer returnVal = invocationReturnValueMap.get(invocation);
            if (returnVal != null) {
                recordInvocations(invocation);
                return returnVal.answer();
            }
            OngoingStubbing ongoingStubbing = new OngoingStubbing(invocation, invocationReturnValueMap);
            mockProcess.setCurrentOngoingStubbing(ongoingStubbing);
        }
        return defaultValueFor(method);
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
