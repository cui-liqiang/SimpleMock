package com.thoughtworks.simplemock.verificationmodes;

import com.thoughtworks.simplemock.Invocation;
import com.thoughtworks.simplemock.MockVerificationMode;
import com.thoughtworks.simplemock.VerificationData;
import com.thoughtworks.simplemock.exceptions.WrongInvocationTimesException;

public class Times implements MockVerificationMode {
    private int times;

    public Times(int times) {
        this.times = times;
    }

    @Override
    public void verify(VerificationData data) {
        if(times != data.getInvocationTimes()) {
            Invocation invocation = data.getInvocation();
            throw new WrongInvocationTimesException("Wrong invocation times:\n" +
                    "expected invoke " + invocation + " " + times + " times,\n" +
                    "but actually " + data.getInvocationTimes() + " times");
        }
    }
}
