package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;

import java.util.List;
import java.util.Map;

public class OngoingStubbing {
    private final Invocation invocation;
    private final List<Invocation> invocations;

    public OngoingStubbing(Invocation invocation, List<Invocation> invocations) {
        this.invocation = invocation;
        this.invocations = invocations;
    }

    public void thenReturn(final Object value) {
        Answer answer = new Answer() {
            @Override
            public Object answer() {
                return value;
            }
        };
        thenReturn(answer);
    }

    public void thenReturn(Answer answer) {
        invocation.setAnswer(answer);
        this.invocations.add(this.invocation);
    }

}
