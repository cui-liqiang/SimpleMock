package com.thoughtworks.simplemock;

public class VerificationData {
    private Invocation invocation;
    private Integer times;

    public VerificationData(Invocation invocation, Integer times) {
        this.invocation = invocation;
        this.times = times;
    }

    public Integer getInvocationTimes() {
        return times;
    }

    public Invocation getInvocation() {
        return invocation;
    }
}
