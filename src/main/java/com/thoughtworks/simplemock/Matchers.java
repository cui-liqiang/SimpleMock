package com.thoughtworks.simplemock;

public class Matchers {
    private static MockProcess mockProcess = MockProcess.mockProcess();

    public static <T> T argThat(ArgMatcher<T> argMatcher) {
        return mockProcess.addParamMatcher(argMatcher);
    }
}
