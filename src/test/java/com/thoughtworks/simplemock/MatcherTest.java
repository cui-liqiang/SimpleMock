package com.thoughtworks.simplemock;

import org.junit.Ignore;
import org.junit.Test;

import static com.thoughtworks.simplemock.SimpleMock.when;
import static org.junit.Assert.assertEquals;

public class MatcherTest {
    private InterfaceForTest mock = SimpleMock.mock(InterfaceForTest.class);

    @Test
    public void useMatcherTest() {
        Object value = new Object();
        int expectedA = 4;
        when(mock.funWithParameter(Matchers.argThat(new FieldAMatcher(expectedA)))).thenReturn(value);

        Data data = new Data();
        data.a = expectedA;
        data.b = 1;
        assertEquals(value, mock.funWithParameter(data));
        data.a = expectedA;
        data.b = 0;
        assertEquals(value, mock.funWithParameter(data));
    }

    private static class FieldAMatcher implements ArgMatcher<Data> {
        final private int expectedA;

        private FieldAMatcher(int expectedA) {
            this.expectedA = expectedA;
        }

        @Override
        public boolean match(Data data) {
            return expectedA == data.a;
        }
    }
}
