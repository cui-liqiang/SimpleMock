package com.thoughtworks.simplemock;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Invocation {
    private final Method method;
    private final Object[] objects;

    public Invocation(Method method, Object[] objects) {
        this.method = method;
        this.objects = objects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invocation that = (Invocation) o;

        if (method != null ? !method.equals(that.method) : that.method != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(objects, that.objects)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = method != null ? method.hashCode() : 0;
        result = 31 * result + (objects != null ? Arrays.hashCode(objects) : 0);
        return result;
    }

    @Override
    public String toString() {
        return method.toString();
    }
}
