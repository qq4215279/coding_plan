/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.basic_data_type;

import org.junit.Test;

/**
 * BitOperation
 * 位运算
 * @author liuzhen
 * @version 1.0.0 2021/9/15 14:39
 */
public class BitOperation {

    /**
     * 左移
     */
    @Test
    public void shlTest() {
        System.out.println("-1左移两位：" + (-1 << 2)); // -4 = -1 * 2^2
        System.out.println("-2左移两位：" + (-2 << 2)); // -8 = -2 * 2^2
        System.out.println("3左移4位：" + (3 << 4)); // 48 = 3 * 2^4
    }

    /**
     * 右移 & 无符号右移
     */
    @Test
    public void shrTest() {
        // 正数右移与无符号右移没区别
        System.out.println("7：" + Integer.toBinaryString(7)); // 7：111
        System.out.println("7 / 2：" + (7 / 2)); // 7 / 2：3
        System.out.println("7(0b111) 右移1位：" + Integer.toBinaryString((0b111 >> 1)) + " 十进制：" + (7 >> 1)); // 7(0b111) 右移1位：11 十进制：3
        System.out.println("7(0b111) 右移2位：" + Integer.toBinaryString((0b111 >> 2)) + " 十进制：" + (7 >> 2));
        System.out.println("7(0b111) 右移3位：" + Integer.toBinaryString((0b111 >> 3)) + " 十进制：" + (7 >> 3));
        System.out.println("7(0b111) 无符号右移1位：" + Integer.toBinaryString((0b111 >>> 1)) + " 十进制：" + (7 >>> 1));
        System.out.println("7(0b111) 无符号右移2位：" + Integer.toBinaryString((0b111 >>> 2)) + " 十进制：" + (7 >>> 2));
        System.out.println("7(0b111) 无符号右移3位：" + Integer.toBinaryString((0b111 >>> 3)) + " 十进制：" + (7 >>> 3));

        /*
        * 当一个数为负数且为奇数时，除2 与 这个数右移一位结果不一致（右移结果比除2的小1）？
        * 解释：仅仅是做一个右移运算，所以如果最低位是1的话，就会被抹去。
        * 总结：
        *      除法运算，结果都向0取整；位运算结果向下取整
        *      所以对于正数来说位运算和除法结果是一样的，因为正数的向下取整也就是向0取整；而对于负数来说，向下取整要比向0取整小1.
        * */
        System.out.println("-7 ：" + Integer.toBinaryString((-7)));
        System.out.println("-7 / 2：" + (-7 / 2)); // -3
        System.out.println("     -7 右移1位：" + Integer.toBinaryString((-7 >> 1)) + " 十进制：" + (-7 >> 1)); // -4
        // 负偶数除2 与 右移结果一致
        System.out.println("-8 / 2：" + (-8 / 2)); // -4
        System.out.println("     -8 右移1位：" + Integer.toBinaryString((-8 >> 1)) + " 十进制：" + (-8 >> 1)); // -4

        System.out.println("     -7 右移2位：" + Integer.toBinaryString((-7 >> 2)) + " 十进制：" + (-7 >> 2));
        System.out.println("     -7 右移3位：" + Integer.toBinaryString((-7 >> 3)) + " 十进制：" + (-7 >> 3));
        System.out.println("     -7 右移4位：" + Integer.toBinaryString((-7 >> 4)) + " 十进制：" + (-7 >> 4));

        System.out.println("-7 无符号右移1位：" + Integer.toBinaryString((-7 >>> 1)) + " 十进制：" + (-7 >>> 1));
        System.out.println("-7 无符号右移2位：" + Integer.toBinaryString((-7 >>> 2)) + " 十进制：" + (-7 >>> 2));
        System.out.println("-7 无符号右移3位：" + Integer.toBinaryString((-7 >>> 3)) + " 十进制：" + (-7 >>> 3));
        System.out.println("-7 无符号右移4位：" + Integer.toBinaryString((-7 >>> 4)) + " 十进制：" + (-7 >>> 4));

        System.out.println("--------------------------------------------->");
        System.out.println("Integer max value：" + Integer.toBinaryString(Integer.MAX_VALUE)); // 2^31 - 1
        System.out.println("Integer min value：" + Integer.toBinaryString(Integer.MIN_VALUE)); // -2^31
        System.out.println("   Long max value：" + Long.toBinaryString(Long.MAX_VALUE)); // 2^63 - 1
        System.out.println("   Long min value：" + Long.toBinaryString(Long.MIN_VALUE)); // 2^63
    }

    /**
     * XOR
     */
    @Test
    public void xorTest() {
        System.out.println("~1：" + Integer.toBinaryString(~1) + "  十进制：" + (~1)); // ~1：11111111111111111111111111111110  十进制：-2
        System.out.println("~0：" + Integer.toBinaryString(~0) + "  十进制：" + (~0)); // ~0：11111111111111111111111111111111  十进制：-1
        System.out.println("~-1：" + Integer.toBinaryString(~-1) + "  十进制：" + (~-1)); // ~-1：0  十进制：0
        System.out.println("~5：" + Integer.toBinaryString(~5) + "  十进制：" + (~5)); // ~5：11111111111111111111111111111010  十进制：-6
        System.out.println("~-6：" + Integer.toBinaryString(~-6) + "  十进制：" + (~-6)); // ~-6：101  十进制：5
        System.out.println("1s：" + Integer.toBinaryString(0b1) + "  十进制：" + (0b1)); // 1s：1  十进制：1

        System.out.println("------------------------------------->");

        int x = 0b1111;
        int y = 0b1111;
        System.out.println("x：" + Integer.toBinaryString(x) + " y：" + Integer.toBinaryString(y));

        System.out.println("x ^ 0 = x：" + Integer.toBinaryString((x ^ 0)) + "  十进制：" + (x ^ 0));
        System.out.println("x ^ x = 0：" + Integer.toBinaryString((x ^ x)) + "  十进制：" + (x ^ x));
        System.out.println("x ^ 1s = ~x：" + Integer.toBinaryString((x ^ y)) + "  十进制：" + (x ^ y));
        System.out.println("x ^ (~x) = 1s：" + Integer.toBinaryString((x ^ (~x))) + "  十进制：" + (x ^ (~x)));
        System.out.println("~x：" + Integer.toBinaryString(~(x)) + " 十进制：" + (~x));

    }

    /**
     * & 与运算
     */
    @Test
    public void andTest() {

    }

    public static void main(String[] args) {
        int a = 0b1101101;
        System.out.println(a);
        int b = a & 7;
        System.out.println("7：" + Integer.toBinaryString(15));
        System.out.println("b: " + Integer.toBinaryString(b));

        System.out.println("-------->");
        System.out.println(1);
        System.out.println(1 << 1);
        System.out.println(1 << 2);
        System.out.println(1 << 3);
        System.out.println(1 << 4);
        System.out.println(1 << 5);
        System.out.println(1 << 6);

        System.out.println("========>");
        System.out.println(0 ^ 0);
        System.out.println(~-1);

        System.out.println((-1 << 2) & 15);
        System.out.println("-1 & 1：" + (-1 & 1));
        System.out.println("------------------------------------------->");


        System.out.println("------------------------------------------->");

        System.out.println(" 1二进制：" + Integer.toBinaryString(1));
        System.out.println(" 0二进制：" + Integer.toBinaryString(0));
        System.out.println("-1二进制：" + Integer.toBinaryString(-1));
        System.out.println("-2二进制：" + Integer.toBinaryString(-2));
        System.out.println("-3二进制：" + Integer.toBinaryString(-3));

    }

}
