package com.thoughtworks.simplemock;

import org.junit.Test;

import static com.thoughtworks.simplemock.SimpleMock.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StubTest {
    @Test
    public void returnDefaultValueWhenNotStubbed() {
        InterfaceForTest mock = SimpleMock.mock(InterfaceForTest.class);
        assertNull(mock.objFun());
        assertEquals(0, mock.intFun());
    }

    @Test
    public void returnSpecifiedValueWhenStubbed() {
        InterfaceForTest mock = SimpleMock.mock(InterfaceForTest.class);
        when(mock.intFun()).thenReturn(4);
        assertEquals(4, mock.intFun());

        when(mock.objFun()).thenReturn("string");

        assertEquals("string", mock.objFun());
    }

}
