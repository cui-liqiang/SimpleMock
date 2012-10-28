package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;

import java.util.Map;

public class OngoingStubbing {
    private final Invocation invocation;
    private final Map<Invocation, Answer> invocationReturnValueMap;

    public OngoingStubbing(Invocation invocation, Map<Invocation, Answer> invocationReturnValueMap) {
        this.invocation = invocation;
        this.invocationReturnValueMap = invocationReturnValueMap;
    }

    public void thenReturn(final Object value) {
        this.invocationReturnValueMap.put(this.invocation, new Answer() {
            @Override
            public Object answer() {
                return value;
            }
        });
    }

    public void thenReturn(Answer answer) {
        this.invocationReturnValueMap.put(this.invocation, answer);
    }

}
