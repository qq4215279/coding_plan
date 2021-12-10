package com.mumu.dp3_10_templatemethod.test1.hookmethod;

public class Client {
    public static void main(String args[]) {
        DataViewer dv;
        dv = new XMLDataViewer();
        dv.process();
    }
}
