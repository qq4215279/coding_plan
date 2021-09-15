package com.demo.geek.c16_bitwise;

/**
 * LeetCode190
 * 颠倒二进制位
 * @author liuzhen
 * @version 1.0.0 2021/8/26 21:28
 */
public class LeetCode190 {

    /**
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     * 提示：
     * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
     * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
     *  
     * 进阶:
     * 如果多次调用这个函数，你将如何优化你的算法？
     *
     * 示例 1：
     * 输入: 00000010100101000001111010011100
     * 输出: 00111001011110000010100101000000
     * 解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     *
     * 示例 2：
     * 输入：11111111111111111111111111111101
     * 输出：10111111111111111111111111111111
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     *
     * 示例 1：
     * 输入：n = 00000010100101000001111010011100
     * 输出：964176192 (00111001011110000010100101000000)
     * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
     *      因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
     *
     * 示例 2：
     *
     * 输入：n = 11111111111111111111111111111101
     * 输出：3221225471 (10111111111111111111111111111111)
     * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
     *      因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
     *  
     * 提示：
     * 输入是一个长度为 32 的二进制字符串
     *
     * 思路：
     * 1. int -> 转 String -> reverse -> int   不推荐，特别慢，相当于拖了裤子放屁
     * 2. int -> 位运算 ->
     */

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            ans = (ans << 1) + (n & 1);
            n >>= 1;
        }
        return ans >>> 0;
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

        System.out.println("-1左移两位：" + (-1 << 2));
        System.out.println("-2左移两位：" + (-2 << 2)); // -2 * 2^2
        System.out.println("3左移4位：" + (3 << 4)); // 3 * 2^4

        System.out.println((-1 << 2) & 15);
        System.out.println("-1 & 1：" + (-1 & 1));
        System.out.println("------------------------------------------->");

        System.out.println("0b111(7) 右移1位：" + (0b111 >> 1));
        System.out.println("0b111(7) 右移2位：" + (0b111 >> 2));
        System.out.println("0b111(7) 右移3位：" + (0b111 >> 3));

        System.out.println("0b111(7) 无符号右移1位：" + (0b111 >>> 1));
        System.out.println("0b111(7) 无符号右移2位：" + (0b111 >>> 2));
        System.out.println("0b111(7) 无符号右移3位：" + (0b111 >>> 3));


        System.out.println("-7 右移1位：" + (-7 >> 1));
        System.out.println("-7 右移2位：" + (-7 >> 2));
        System.out.println("-7 右移3位：" + (-7 >> 3));
        System.out.println("-7 右移4位：" + (-7 >> 4));

        System.out.println("-7 无符号右移1位：" + (-7 >>> 1));
        System.out.println("-7 无符号右移2位：" + (-7 >>> 2));
        System.out.println("-7 无符号右移3位：" + (-7 >>> 3));
        System.out.println("-7 无符号右移4位：" + (-7 >>> 4));

        System.out.println("Integer max value：" + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("Integer min value：" + Integer.toBinaryString(Integer.MIN_VALUE));

        System.out.println("   Long max value：" + Long.toBinaryString(Long.MAX_VALUE));
        System.out.println("   Long min value：" + Long.toBinaryString(Long.MIN_VALUE));

        System.out.println("------------------------------------------->");

        System.out.println(" 1二进制：" + Integer.toBinaryString(1));
        System.out.println(" 0二进制：" + Integer.toBinaryString(0));
        System.out.println("-1二进制：" + Integer.toBinaryString(-1));
        System.out.println("-2二进制：" + Integer.toBinaryString(-2));
        System.out.println("-3二进制：" + Integer.toBinaryString(-3));
    }

}
