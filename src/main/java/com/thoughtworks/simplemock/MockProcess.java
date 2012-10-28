package com.thoughtworks.simplemock;

import java.util.ArrayList;
import java.util.List;

public class MockProcess {
    private OngoingStubbing currentOngoingStubbing;
    private MockVerification mockVerification;
    static MockProcess process;
    private List<ArgMatcher> matchers = new ArrayList<ArgMatcher>();

    public static MockProcess mockProcess() {
        if(process == null) {
            process = new MockProcess();
        }
        return process;
    }

    public void setCurrentOngoingStubbing(OngoingStubbing currentOngoingStubbing) {
        this.currentOngoingStubbing = currentOngoingStubbing;
    }

    public OngoingStubbing getCurrentOngoingStubbing() {
        return currentOngoingStubbing;
    }

    public void startVerify(MockVerification mockVerification) {
        this.mockVerification = mockVerification;
    }

    public boolean onVerifying() {
        return mockVerification != null;
    }

    public MockVerification getVerification() {
        return mockVerification;
    }

    public void stopVerifying() {
        mockVerification = null;
    }

    public <T> T addParamMatcher(ArgMatcher<T> argMatcher) {
        matchers.add(argMatcher);
        return null;
    }

    public List<ArgMatcher> getAllMatchers() {
        ArrayList<ArgMatcher> argMatchers = new ArrayList<ArgMatcher>();
        argMatchers.addAll(matchers);
        matchers.clear();
        return argMatchers;
    }
}
