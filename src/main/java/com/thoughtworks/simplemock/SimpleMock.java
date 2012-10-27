package com.thoughtworks.simplemock;

import java.lang.reflect.Proxy;

public class SimpleMock {
    private static MockProcess mockProcess = new MockProcess();

    public static <T> T mock(Class<T> interfaze) {
        return (T) Proxy.newProxyInstance(SimpleMock.class.getClassLoader(), new Class[]{interfaze}, new MockInvocationHandler(mockProcess));
    }

    public static <T> OngoingStubbing when(T whatEver) {
        return mockProcess.getCurrentOngoingStubbing();
    }
}
