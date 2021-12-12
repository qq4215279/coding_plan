/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.jdk_api.lang;

import org.junit.Test;

/**
 * IntegerTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/6 22:56
 */
public class IntegerTest {

    /**
     * 使用: eg: integer.intVale
     * byteValue() 返回此值 Integer为 byte的基本收缩转换后。
     * shortValue() 返回此值 Integer为 short的基本收缩转换后。
     * intValue() 返回此值 Integer为 int 。
     * longValue() 在扩展基元转换后，将此 Integer的值作为 long返回。
     * floatValue() 返回此值 Integer为 float一个宽元转换后。
     * doubleValue() 返回此值 Integer为 double一个宽元转换后。
     * <p>
     * compareTo​(Integer anotherInteger) 以数字方式比较两个 Integer对象。
     */
    @Test
    public void apiTest() {
        int maxValue = Integer.MAX_VALUE;
        int minValue = Integer.MIN_VALUE;
        System.out.println("Integer获取最大值和最小值：" + maxValue + " " + minValue);

        Integer integer = Integer.valueOf("188");
        System.out.println("byteValue: " + integer.byteValue());
        System.out.println("shortValue: " + integer.shortValue());
        System.out.println("byteValue: " + integer.intValue());
        System.out.println("floatValue: " + integer.floatValue());
        System.out.println("doubleValue: " + integer.doubleValue());

        Integer integer2 = Integer.valueOf("500");
        System.out.println(integer.compareTo(integer2));
    }

    /**
     * 静态api：eg: Integer.valueOf(int i)
     * valueOf(int i) 返回表示指定的 int值的 Integer实例。 或 new Integer(int i) 返回Integer实例，但已过时！
     * parseInt(String s) 将字符串参数解析为带符号的十进制整数。
     * toString(int i) 返回表示指定整数的 String对象。
     *
     * bitCount(int i) 返回指定的 int值的二进制补码表示形式中的 int 。
     * compare(int x, int y) 以数字方式比较两个 int值。x大于y返回1，当x等于y返回0，当x小于y返回-1
     * decode(String nm) 将 String解码为 Integer 。
     *
     * max(int a, int b) 返回两个 int值中较大的一个， int调用 Math.max一样 。
     * min(int a, int b) 返回两个 int值中较小的一个， int调用 Math.min一样 。
     * sum(int a, int b) 根据+运算符将两个整数相加。
     *
     * toBinaryString​(int i) 返回整数参数的字符串表示形式，作为base 2中的无符号整数。
     * toHexString​(int i) 返回整数参数的字符串表示形式，作为基数为16的无符号整数。
     * toOctalString​(int i) 返回整数参数的字符串表示形式，作为基数为8的无符号整数。
     */
    @Test
    public void staticApiTest() {
        System.out.println(Integer.bitCount(100));

        System.out.println(Integer.compare(100, 100)); // 0

        System.out.println(Integer.decode("1223")); // 1223

        System.out.println(Integer.getInteger("234"));

        System.out.println(Integer.toBinaryString(16));
    }

}
