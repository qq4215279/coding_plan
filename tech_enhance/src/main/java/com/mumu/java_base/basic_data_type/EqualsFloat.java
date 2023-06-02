/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.basic_data_type;

import java.math.BigDecimal;

/**
 * EqualsFloat
 * 浮点数比较大小
 * 浮点数之间的等值判断，基本数据类型不能用==来比较，包装数据类型不能用 equals来判断。
 * 说明：浮点数采用“尾数+阶码”的编码方式，类似于科学计数法的“有效数字+指数”的表示方式。
 * 二进制无法精确表示大部分的十进制小数，具体原理参考《码出高效》。
 * @author liuzhen
 * @version 1.0.0 2022/5/1 17:56
 */
public class EqualsFloat {

    /**
     * 浮点数比较方式1
     * 指定一个误差范围，两个浮点数的差值在此范围之内，则认为是相等的。
     * @date 2022/5/1 17:58
     * @param
     * @return void
     */
    public void equalsTest01() {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        float diff = 1e-6F;

        if (Math.abs(a - b) < diff) {
            System.out.println("true");
        }
    }

    /**
     * 浮点数比较方式2
     * 使用 BigDecimal 来定义值，再进行浮点数的运算操作。
     * BigDecimal 的等值比较应使用 compareTo()方法，而不是 equals()方法。
     * 说明：equals()方法会比较值和精度（1.0 与 1.00 返回结果为 false），而 compareTo()则会忽略精度。
     * @date 2022/5/1 17:58
     * @param
     * @return void
     */
    public void equalsTest02() {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.compareTo(y) == 0) {
            System.out.println("true");
        }
    }

    /**
     * Float比较，反例示范
     * @date 2022/5/1 17:59
     * @param
     * @return void
     */
    public void mistakeEqualsTest() {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        if (a == b) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 a==b 的结果为 false
        }
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            // 预期进入此代码块，执行其它业务逻辑
            // 但事实上 equals 的结果为 false
        }
    }
    
}
