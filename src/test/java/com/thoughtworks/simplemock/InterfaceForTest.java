package com.thoughtworks.simplemock;

interface InterfaceForTest {
    int intFun();

    Object objFun();

    Object funWithParameter(Data data);
}

class Data {
    public int a;
    public int b;
}
