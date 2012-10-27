package com.thoughtworks.simplemock;

import java.util.Map;

public class OngoingStubbing {
    private final Invocation invocation;
    private final Map<Invocation, Object> invocationReturnValueMap;

    public OngoingStubbing(Invocation invocation, Map<Invocation, Object> invocationReturnValueMap) {
        this.invocation = invocation;
        this.invocationReturnValueMap = invocationReturnValueMap;
    }

    public void thenReturn(Object value) {
        this.invocationReturnValueMap.put(this.invocation, value);
    }

}
