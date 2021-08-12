package com.demo.common.utils;

/**
 * ArrayUtil
 * 数组工具类
 * @author liuzhen
 * @version 1.0.0 2021/8/12 14:57
 */
public class ArrayUtil {

    /**
     * 交换数组的两个数
     * @author liuzhen
     * @date 2021/8/12 14:58
     * @param arr
     * @param i
     * @param j
     * @return void
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
