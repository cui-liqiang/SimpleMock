package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.verificationmodes.Times;

import java.lang.reflect.Proxy;

public class SimpleMock {
    private static MockProcess mockProcess = MockProcess.mockProcess();

    public static <T> T mock(Class<T> interfaze) {
        return (T) Proxy.newProxyInstance(SimpleMock.class.getClassLoader(), new Class[]{interfaze}, new MockInvocationHandler());
    }

    public static <T> OngoingStubbing when(T whatEver) {
        return mockProcess.getCurrentOngoingStubbing();
    }

    public static <T> T verify(T mock) {
        MockVerificationMode times = new Times(1);
        MockVerification mockVerification = new MockVerification(times);
        mockProcess.startVerify(mockVerification);
        return mock;
    }

}
