package com.mumu.jdk_api.javaCompiler;

import java.util.ArrayList;

/**
 * HelloWorld
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/23 16:00
 */
public class HelloWorld {
    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
        System.out.println("Hello, World!");
    }

    @Deprecated
    private int add() {
        return 1;
    }
}
