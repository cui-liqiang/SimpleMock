package com.thoughtworks.simplemock;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MockInvocationHandler implements InvocationHandler {

    private Map<Invocation, Object> invocationReturnValueMap = new HashMap<Invocation, Object>();
    private MockProcess mockProcess = new MockProcess();
    private static Map<Class<?>, Object> primitiveDefaultValue;

    public MockInvocationHandler(MockProcess mockProcess) {
        this.mockProcess = mockProcess;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Invocation invocation = new Invocation(method, objects);
        Object returnVal = invocationReturnValueMap.get(invocation);
        if (returnVal != null) {
            return returnVal;
        }
        OngoingStubbing ongoingStubbing = new OngoingStubbing(invocation, invocationReturnValueMap);
        mockProcess.setCurrentOngoingStubbing(ongoingStubbing);
        return defaultValueFor(method);
    }

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

    private Object defaultValueFor(Method method) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        if(method.getReturnType().isPrimitive()) {
            return primitiveDefaultValue.get(method.getReturnType());
        } else {
            return null;
        }
    }
}
