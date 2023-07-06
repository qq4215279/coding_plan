/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.math;

/**
 * BigDecimalTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/6 11:20
 */
public class BigDecimalTest {

    /**
     * BigDecimal类提供了丰富的API方法，用于进行高精度的十进制数值计算。以下是BigDecimal类的一些常用API方法：
     *
     * 创建 BigDecimal 对象：
     * BigDecimal(String val): 使用字符串值创建一个 BigDecimal 对象。
     * BigDecimal(double val): 使用双精度浮点数值创建一个 BigDecimal 对象。
     * BigDecimal(int val): 使用整数值创建一个 BigDecimal 对象。
     *
     * 基本运算操作：
     * add(BigDecimal augend): 将当前 BigDecimal 对象与指定的 BigDecimal 对象相加。
     * subtract(BigDecimal subtrahend): 从当前 BigDecimal 对象中减去指定的 BigDecimal 对象。
     * multiply(BigDecimal multiplicand): 将当前 BigDecimal 对象与指定的 BigDecimal 对象相乘。
     * divide(BigDecimal divisor): 将当前 BigDecimal 对象除以指定的 BigDecimal 对象。
     * remainder(BigDecimal divisor): 计算当前 BigDecimal 对象除以指定的 BigDecimal 对象的余数。
     *
     * 比较和判断操作：
     * compareTo(BigDecimal val): 比较当前 BigDecimal 对象与指定的 BigDecimal 对象的大小关系。
     * equals(Object obj): 检查当前 BigDecimal 对象是否与指定的对象相等。
     * signum(): 返回当前 BigDecimal 对象的符号。
     *
     * 舍入操作：
     * setScale(int newScale): 设置当前 BigDecimal 对象的小数位数。
     * setScale(int newScale, RoundingMode roundingMode): 设置当前 BigDecimal 对象的小数位数，并指定舍入模式。
     *
     * 转换和格式化：
     * toString(): 将当前 BigDecimal 对象转换为字符串表示。
     * toBigInteger(): 将当前 BigDecimal 对象转换为 BigInteger 对象。
     * intValue(), longValue(), floatValue(), doubleValue(): 将当前 BigDecimal 对象转换为相应的基本数据类型。
     *
     * 这些只是BigDecimal类的一些常用API方法。BigDecimal还提供了许多其他方法，如取绝对值、取对数、开平方等。
     * 请注意，由于BigDecimal执行的是任意精度的十进制数值计算，因此它的计算速度可能相对较慢，比基本数据类型的运算要慢得多。在处理大量数据时，请考虑性能和内存消耗。
     */
}
