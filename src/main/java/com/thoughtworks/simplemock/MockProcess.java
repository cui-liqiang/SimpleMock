package com.thoughtworks.simplemock;

public class MockProcess {
    private OngoingStubbing currentOngoingStubbing;
    private MockVerification mockVerification;

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
}
