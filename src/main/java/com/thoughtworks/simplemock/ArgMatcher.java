package com.thoughtworks.simplemock;

public interface ArgMatcher<T> {
    public boolean match(T actual);
}
