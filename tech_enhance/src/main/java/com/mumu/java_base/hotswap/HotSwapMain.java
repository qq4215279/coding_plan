/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_base.hotswap;

/**
 * HotSwapMain
 *
 * @author liuzhen
 * @version 1.0.0 2025/5/6 15:08
 */
public class HotSwapMain {


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println("hotSwap start " + i);
            Thread.sleep(1000);
        }
    }

}
