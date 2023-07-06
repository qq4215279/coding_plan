/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.math;

import org.junit.Test;

import java.math.BigInteger;

/**
 * BigIntegerTest
 * 在java中经常会遇到比较大的数，甚至超过了long型，那么该如何处理这些“大数据”呢？
 * 在java中有两个类BigInteger和BigDecimal分别表示大整数类和大浮点数类，从原则上是可以表示“天文单位”一样大的数字咯，但有一个缺点就是比较费内存！
 * @author liuzhen
 * @version 1.0.0 2023/7/6 11:19
 */
public class BigIntegerTest {
    /**
     * BigInteger是Java中的一个类，用于进行任意精度的整数运算。它提供了一系列的API方法，用于执行常见的整数操作。以下是BigInteger类的一些常用API：
     *
     * 创建 BigInteger 对象：
     * BigInteger(String val): 使用字符串值创建一个 BigInteger 对象。
     * BigInteger(byte[] val): 使用字节数组创建一个 BigInteger 对象。
     * BigInteger(int signum, byte[] magnitude): 使用符号和字节数组创建一个 BigInteger 对象。
     *
     * 基本运算操作：
     * add(BigInteger val): 将当前 BigInteger 对象与指定的 BigInteger 对象相加。
     * subtract(BigInteger val): 从当前 BigInteger 对象中减去指定的 BigInteger 对象。
     * multiply(BigInteger val): 将当前 BigInteger 对象与指定的 BigInteger 对象相乘。
     * divide(BigInteger val): 将当前 BigInteger 对象除以指定的 BigInteger 对象。
     * mod(BigInteger val): 计算当前 BigInteger 对象除以指定的 BigInteger 对象的余数。
     * remainder()方法，这个函数的作用是将大整数取余
     * pow(exponent): 这个函数的作用是将大整数取exponent的指数，例如 a.pow(b)==a^b;
     * gcd(): 这个函数的作用是将两个大整数取最大公约数，例如 a.gcd(b)；
     * abs(): 这个函数的作用是取绝对值
     * max(), min(): 分别是比较两个数的大小，例如 a.max(b); 取a,b中的最大值
     *
     * 比较和判断操作：
     * compareTo(BigInteger val): 比较当前 BigInteger 对象与指定的 BigInteger 对象的大小关系。
     * equals(Object obj): 检查当前 BigInteger 对象是否与指定的对象相等。
     * isProbablePrime(int certainty): 检查当前 BigInteger 对象是否为概率上的质数。
     *
     * 转换和格式化：
     * toString(): 将当前 BigInteger 对象转换为字符串表示。
     * toByteArray(): 将当前 BigInteger 对象转换为字节数组表示。
     * intValue(), longValue(), floatValue(), doubleValue(): 将当前 BigInteger 对象转换为相应的基本数据类型。
     *
     * 这些只是BigInteger类的一些常用API方法。BigInteger还提供了许多其他方法，如位操作、幂运算、取模运算等。
     * 请注意，由于BigInteger执行的是任意精度的整数运算，因此它的计算速度可能相对较慢，比基本数据类型的运算要慢得多。在处理大整数时，请考虑性能和内存消耗。
     */

    /**
     * 权限控制：setBit(),testBit()
     * @date 2023/7/6 14:57
     * @param
     * @return void
     */
    @Test
    public void testSetAndTest() {
        // 1.封装数据(setBit的值需 >= 0，否则出现异常：ArithmeticException("Negative bit address"))
        BigInteger permission = new BigInteger("0");
        BigInteger numBig = permission.setBit(2);
        System.out.println(numBig);

        numBig = numBig.setBit(5);
        System.out.println(numBig);

        numBig = numBig.setBit(13);
        numBig = numBig.setBit(66);

        // 原理：73786976294838214692 = 0 + 2^2 + 2^5 + 2^13 + 2^66 次方的和；
        // 看！！即使这么大的数也不会溢出，而int最大值只有2147483647；
        System.out.println("原理：" + numBig);


        // 2.取值验证（返回Boolean型）
        System.out.println(numBig.testBit(2));
        System.out.println(numBig.testBit(5));
        System.out.println(numBig.testBit(13));
        System.out.println(numBig.testBit(66));
        System.out.println(numBig.testBit(27));
    }

}
