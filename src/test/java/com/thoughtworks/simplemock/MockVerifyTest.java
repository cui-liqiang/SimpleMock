package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.exceptions.WrongInvocationTimesException;
import org.junit.Test;

import static com.thoughtworks.simplemock.SimpleMock.verify;
import static com.thoughtworks.simplemock.SimpleMock.when;
import static org.junit.Assert.fail;

public class MockVerifyTest {
    private InterfaceForTest mock = SimpleMock.mock(InterfaceForTest.class);
    @Test
    public void shouldVerifyOneInvocation() {
        when(mock.objFun()).thenReturn(new Object());
        mock.objFun();
        verify(mock).objFun();
    }

    @Test
    public void shouldThrowWhenVerifyNotPass() {
        when(mock.objFun()).thenReturn(new Object());
        mock.objFun();
        mock.objFun();
        try{
            verify(mock).objFun();
            fail();
        } catch (WrongInvocationTimesException e) {
            System.out.println(e.getMessage());
        }
    }
}
