/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.basicDataType;

import org.junit.Test;

/**
 * Basic8DataType
 * 8种基本数据类型
 * @author liuzhen
 * @version 1.0.0 2021/4/19 10:28
 */
public class Basic8DataType {

    /**
     * 8种基本数据类型及取值范围：
     * 1.整型：
     * byte：-2^7 ~ 2^7-1，即-128 ~ 127。1字节。Byte。
     * short：-2^15 ~ 2^15-1，即-32768 ~ 32767。2字节。Short。
     * 有符号int：-2^31 ~ 2^31-1，即-2147483648 ~ 2147483647。4字节。Integer。
     * 无符号int：0~2^32-1。
     * long：-2^63 ~ 2^63-1，即-9223372036854774808 ~ 9223372036854774807。8字节。Long。末尾加L。（也可以不加L） 9.2亿亿亿
     *
     * 2.浮点型：
     * float：4字节。Float。末尾加F。
     * double：8字节。Double。末尾加D
     *
     * 3.字符型：
     * char：2字节。Character。
     *
     * 4.布尔型：
     * boolean：Boolean。
     *
     * 类型转换：
     * boolean类型与其他基本类型不能进行类型的转换（既不能进行自动类型的提升，也不能强制类型转换）， 否则，将编译出错
     */

    private static final int RUN_NUM_PRECISION = 100;
    /** 10亿 */
    private static final int TEN_YI = 1000000000;

    /**
     * 自动类型转换
     * 报错原因：
     * byte类型内存占有1个字节，int类型内存占有4个字节.根据运算时“变量会提升”原理，
     * 所以 byte 在和 int 类型运算时会提升为 int 类型 ，自动补充3个字节，因此计算后的结果还是 int 类型。
     *
     * 转换规则：范围小的类型向范围大的类型提升， byte、short、char 运算时直接提升为 int 。
     * byte、short、char‐‐>int‐‐>long‐‐>float‐‐>double
     */
    @Test
    public void test01() {
        int i = 1;
        byte b = 2;
        // byte x = b + i; // 报错!!! int类型和byte类型运算，结果是int类型
        int j = b + i;
        System.out.println(j);

        System.out.println("----------------------->");

        // 同理，当一个 int 类型变量和一个 double 变量运算时， int 类型将会自动提升为 double 类型进行运算。
        int i2 = 1;
        double d = 2.5;
        // int类型和double类型运算，结果是double类型
        // int类型会提升为double类型
        double e = d + i2;
        System.out.println(e);
    }

    /**
     * 强制类型转换：
     *
     * 强转注意：浮点转成整数，直接取消小数点，可能造成数据损失精度。 int 强制转成 short 砍掉2个字节，可能造成数据丢失。
     */
    @Test
    public void test02() {
        // short类型变量，内存中2个字节
        short s = 1;
        /*
           出现编译失败 s和1做运算的时候，1是int类型，s会被提升为int类型
           s+1后的结果是int类型，将结果在赋值会short类型时发生错误
           short内存2个字节，int类型4个字节，4个字节int强制转成2个字节short砍掉前面两个字节
           必须将int强制转成short才能完成赋值
        */
//        s = s + 1; // 编译失败 short + int -> 左边应变为int类型，此时得进行强制类型转换
        s = (short)(s + 1); // 编译成功

        System.out.println("----------------------->");

        // 同理：当一个double类型变int类型时，也需要强转
//        int i2 = 3.14; // 报错
        int i3 = (int)3.14;

        System.out.println("----------------------->");

        // 另：+=符号的扩展
        /**
         *  s += 1 逻辑上看作是 s = s + 1 计算结果被提升为int类型，再向short类型赋值时发生错误，因为不能将取值范围大的类型赋值到取值范围小的类型。
         *  但是， s=s+1进行两次运算，+= 是一个运算符，只运算一次，并带有强制转换的特点，也就是说 s += 1 就是 s = (short)(s + 1) ，
         *  因此程序没有问题编译通过，运行结果是2.
         */
        short s2 = 1;
        s2 += 1; // += 操作不需要进行强制类型转换！！！
        System.out.println(s2);

    }

    /**
     * byte型不能自动类型提升到char，short和char直接也不会发生自动类型提升（因为负数的问题），同时，byte当然可以直接提升到short型
     * 当对小于int的数据类型（byte, char, short）进行运算时，首先会把这些类型的变量值强制转为int类型进行计算，最后会得到int类型的值。
     * 因此，如果把2个short类型的值相加，最后得到的结果是int类型，如果需要得到short类型的结果，就必须显示地运算结果转为short类型。
     */
    @Test
    public void test03() {
        short s1 = 1;
        short s2 = 2;
        short s3 = (short)(s1 + s2); // s1 + s2 会转成int类型进行计算

        // 同理 byte 和 char
        byte b1 = 1;
        byte b2 = 2;
        byte b3 = (byte)(b1 + b2); // b1 + b2 会转成int类型进行计算
    }

    /**
     * 分析：
     * b3 = 1 + 2 ， 1 和 2 是常量，为固定不变的数据，在编译的时候（编译器javac），已经确定了 1+2 的结果并没有超过byte类型的取值范围，可以赋值给变量 b3，
     * 因此 b3 = 1 + 2 是正确的。反之， b4 = b2 + b3，b2和b3是变量，变量的值是可能变化的，在编译的时候，编译器javac不确定b2+b3的结果是什么，
     * 因此会将结果以int类型进行处理，所以int类型不能赋值给byte类型，因此编译失败。
     */
    @Test
    public void test04() {
        byte b1 = 1;
        byte b2 = 2;
        byte b3 = 1 + 2; // 不需要强转，因为1和2都是常量，...
        byte b4 = (byte)(b1 + b2); // 需进行强转，因为b1和b2是变量，...
        System.out.println(b3);
        System.out.println(b4);
    }

    /**
     * 定义一个超过int范围的long类型的整数：
     * 规律: Java中直接输入整数则默认为 int 类型，带有小数点的实数默认为 double 类型！！！
     * 如果你直接声明 long a = 123；这是没有错的，因为123是int类型，可以赋值给范围更大的long类型，但是如果你的数字超过int范围那得“告诉编译器”，
     * 你的数字已经大于int范围，要换成更大范围的long类型表示，所以数字后面加上L(或小写l)。
     * 比如 long a = 2147483648;(这样写会报错，因为int类型最大表示范围是2147483647) 得这样 long a = 2147483648L;
     */
    @Test
    public void test05() {
        //              10亿
        long num1 = TEN_YI * Long.valueOf(RUN_NUM_PRECISION);
        long num2 = TEN_YI * 100L;
        long num3 = (long)TEN_YI * 100;
        long num4 = 100000000000L;

        System.out.println("num1: " + num1);
        System.out.println("num2: " + num2);
        System.out.println("num3: " + num3);
        System.out.println("num4: " + num4);
    }

}
