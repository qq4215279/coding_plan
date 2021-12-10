/*
 * Copyright 2020-2022, 上海木木网络科技有限公司.
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
     *
     */

    @Test
    public void apiTest() {
        int maxValue = Integer.MAX_VALUE;
        int minValue = Integer.MIN_VALUE;
        System.out.println("Integer获取最大值和最小值：" + maxValue + " " + minValue);
    }

    /**
     * 静态api：
     * bitCount​(int i) 返回指定的 int值的二进制补码表示形式中的 int 。
     * compare​(int x, int y) 以数字方式比较两个 int值。x大于y返回1，当x等于y返回0，当x小于y返回-1
     * compareUnsigned​(int x, int y) 比较两个 int值，将数值视为无符号。
     * decode​(String nm) 将 String解码为 Integer 。
     */
    @Test
    public void staticApiTest() {
        System.out.println(Integer.bitCount(100));

        System.out.println(Integer.compare(100, 100));


    }

}
