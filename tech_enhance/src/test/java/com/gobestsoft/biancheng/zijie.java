package com.gobestsoft.biancheng;

public class zijie {

    public static void test(String s){
        byte[] bytes = s.getBytes();
        for (int i = 0; i < s.length() ; i++) {
            int a = bytes[i];
            int b = bytes[i+1];
            int c = bytes[i+2];
            int d = bytes[i+3];
            if (a == b && b == c){

            }
        }

    }

    public static void main(String[] args) {

    }

}
