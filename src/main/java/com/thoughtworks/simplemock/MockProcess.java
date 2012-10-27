package com.thoughtworks.simplemock;

public class MockProcess {
    private OngoingStubbing currentOngoingStubbing;

    public void setCurrentOngoingStubbing(OngoingStubbing currentOngoingStubbing) {
        this.currentOngoingStubbing = currentOngoingStubbing;
    }

    public OngoingStubbing getCurrentOngoingStubbing() {
        return currentOngoingStubbing;
    }
}
