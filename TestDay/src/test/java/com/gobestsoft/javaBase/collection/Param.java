/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.collection;

import org.junit.Test;

/**
 * Param
 *
 * @author liuzhen
 * @version 1.0.0 2021/4/19 10:28
 */
public class Param {

    private static final int RUN_NUM_PRECISION = 100;

    private long iron;

    public long getIron() {
        return iron;
    }

    public void setIron(long iron) {
        this.iron = iron;
    }

    @Override
    public String toString() {
        return "Param{" + "iron=" + iron + '}';
    }

    public static void main(String[] args) {
        Param param = new Param();


        long iron1 = 1000000000 * Long.valueOf(RUN_NUM_PRECISION);
        long iron2 = 1000000000 * 100L;
//        long iron = Long.valueOf(iron0);
        System.out.println("iron1: " + iron1);
        System.out.println("iron2: " + iron2);
        param.setIron(iron1);

        System.out.println(iron1);
        System.out.println(iron2);
    }

    @Test
    public void testType() {
        byte aa = 1;
        short bb = 1;
        boolean cc = true;
        char dd = 1;

        System.out.println("aa: " + aa);
        System.out.println("bb: " + bb);
        System.out.println("cc: " + cc);
        System.out.println("dd: " + dd);

        System.out.println("--------------->");

        int a = 1;
        long b = 1L;
        float c = 1F;
        double d = 1D;

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("d: " + d);

    }
}
