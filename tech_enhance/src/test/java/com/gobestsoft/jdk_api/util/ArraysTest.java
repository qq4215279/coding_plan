/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.jdk_api.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * ArraysTest01
 * Arrays静态类常用api
 * @author liuzhen
 * @version 1.0.0 2021/6/20 22:41
 */
public class ArraysTest {

    @Test
    public void apiTest() {
        // 1. 数组转集合asList(T... a)   （此方法与 Collection.toArray(T[] a) 结合使用）
        List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");

        // 2.1 排序 sort(T... a)  注：数字从小到大排序，字母先大写后小写
        int[] intArray = new int[] {4, 1, 3, -23};
        Arrays.sort(intArray); // 输出： [-23, 1, 3, 4]
        // 2.2 忽略大小写排序 Case-insensitive sort
        String[] strArray = new String[] { "z", "a", "C" };
        Arrays.sort(strArray, String.CASE_INSENSITIVE_ORDER);

        // 3. 打印数组内容 toString()
        String str = Arrays.toString(strArray);
        System.out.print(str); // 输出：[3, 2, 1, 5, 4]

        // 4. 比较数组元素是否相等 equals()
        int[] arr1 = {1,2,3};
        int[] arr2 = {1,2,3};
        System.out.println(Arrays.equals(arr1,arr2)); // 输出：true 因为Arrays.equals()重写了equals，所以，这里能比较元素是否相等。
        System.out.println(arr1.equals(arr1)); // 输出：false 因为equals()比较的是两个对象的地址，不是里面的数。

        // 5. 二分查找法找指定元素的索引值（下标） binarySearch(T... a, key)  注：数组一定是排好序的，否则会出错！！！找到元素，只会返回最后一个位置
        int[] arr = {10,20,30,40,50};
        System.out.println(Arrays.binarySearch(arr, 30)); // 输出：2 （下标索引值从0开始）
        System.out.println(Arrays.binarySearch(arr, 36)); // 输出：-4 （找不到元素，返回-x，从-1开始数，如题，返回-4）
        System.out.println(Arrays.binarySearch(arr, 0,3,30)); // 输出：2 （从0到3位（不包括）找30，找到了，在第2位，返回2）
        System.out.println(Arrays.binarySearch(arr, 0,3,40)); // 输出：-4 （从0到3位（不包括）找40，找不到，从-1开始数，返回-4）

        // 6.1 复制指定长度的数组 copeOf(T... original, newLength)
        int[] arrCopy01 = Arrays.copyOf(arr, 3); // [10,20,30]
        // 6.2 数组复制 copyOfRange(T... original, int from, int to)
        int[] arrCopy02 = Arrays.copyOfRange(arr,1,3); // [20,30]

        // 7. 返回流对象 stream​(T[] array)
        IntStream stream = Arrays.stream(arr);

        // 8. 填充数组：fill(T... a, num)
        int[] fillArr = new int[5];
        Arrays.fill(fillArr, 2); // [2,2,2,2,2]
    }

}
