/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.java_base.basic_data_type;

import org.junit.Test;

/**
 * CharTest01
 * char 类型
 * @author liuzhen
 * @version 1.0.0 2021/6/20 18:24
 */
public class CharTest01 {

    @Test
    public void testType() {
        byte aa = 1;
        short bb = 1;
        boolean cc = true;

        // char类型
        char dd = '!';
        char ee = 33;
        char ff = (char)(dd + ee);
        int gg = ff;

        System.out.println("aa: " + aa); // aa: 1
        System.out.println("bb: " + bb); // bb: 1
        System.out.println("cc: " + cc); // cc: true
        System.out.println("dd: " + dd); // dd: !
        System.out.println("ee: " + ee); // ee: !
        System.out.println("ff: " + ff); // ff: B
        System.out.println("gg: " + gg); // gg: 66

        System.out.println("--------------->");

        int a = 1;
        long b = 1L;
        float c = 1F;
        double d = 1D;

        System.out.println("a: " + a); // a: 1
        System.out.println("b: " + b); // b: 1
        System.out.println("c: " + c); // c: 1.0
        System.out.println("d: " + d); // d: 1.0

    }

}
