package com.thoughtworks.simplemock;

import java.util.Map;

public class MockVerification {
    private final MockVerificationMode mockVerificationMode;

    public <T> MockVerification(MockVerificationMode mockVerificationMode) {
        this.mockVerificationMode = mockVerificationMode;
    }

    public void verify(VerificationData data) {
        mockVerificationMode.verify(data);
    }
}
