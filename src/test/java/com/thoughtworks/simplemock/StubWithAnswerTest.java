package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;
import org.junit.Test;

import static com.thoughtworks.simplemock.SimpleMock.when;
import static junit.framework.Assert.assertEquals;

public class StubWithAnswerTest {
    private InterfaceForTest mock = SimpleMock.mock(InterfaceForTest.class);
    private int increaseNum = 0;

    @Test
    public void shouldReturnFromTheAnswer() {
        increaseNum = 0;
        when(mock.objFun()).thenReturn(new Answer() {
            @Override
            public Object answer() {
                return nextNum();
            }
        });
        assertEquals(0, mock.objFun());
        assertEquals(1, mock.objFun());
        assertEquals(2, mock.objFun());
    }

    private Integer nextNum() {
        return increaseNum++;
    }
}
