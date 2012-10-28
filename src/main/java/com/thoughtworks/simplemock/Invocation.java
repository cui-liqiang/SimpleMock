package com.thoughtworks.simplemock;

import com.thoughtworks.simplemock.answers.Answer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Invocation {
    private final Method method;
    private final Object[] args;
    private List<ArgMatcher> matchers;
    private Answer answer;

    public Invocation(Method method, Object[] args, List<ArgMatcher> matchers) {
        this.method = method;
        this.args = args;
        this.matchers = matchers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invocation that = (Invocation) o;

        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if(!matchers.isEmpty()) {
            Iterator<ArgMatcher> iterator = matchers.iterator();
            for (Object arg : that.args) {
                if(!iterator.hasNext()) throw new RuntimeException("when using matcher, please all use matchers");
                if(!iterator.next().match(arg)) return false;
            }
        } else {
            if (!Arrays.equals(args, that.args)) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (args != null ? Arrays.hashCode(args) : 0);
        return result;
    }

    @Override
    public String toString() {
        return method.toString();
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Answer getAnswer() {
        return answer;
    }
}
