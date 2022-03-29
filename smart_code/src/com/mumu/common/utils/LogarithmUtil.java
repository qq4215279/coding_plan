package com.mumu.common.utils;

import com.mumu.nowcoder.chuji.class_01.Code_00_BubbleSort;

import java.util.Arrays;

/**
 * LogarithmUtil
 * 对数器: 开始写绝对正确的数组（写5个方法）
 * @author liuzhen
 * @version 1.0.0 2021/8/12 14:50
 */
public class LogarithmUtil {

    /** 测试次数 */
    private static final int testTimes = 500000;
    /** 数组最大长度 */
    private static final int maxSize = 100;
    /** 数组中的最大值 */
    private static final int maxValue = 100;


    /**
     * 1.绝对正确的数组排序方法rightMethod
     * @author liuzhen
     * @date 2021/8/12 14:51
     * @param arr
     * @return void
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 2.随机数组发生器
     * @author liuzhen
     * @date 2021/8/12 14:51
     * @param maxSize
     * @param maxValue
     * @return int[]
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];    // 生成长度随机的数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 3.数组复制，把随机产生的数组复制给一个新的数组res[]
     * @author liuzhen
     * @date 2021/8/12 14:51
     * @param arr
     * @return int[]
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 4.比较两个数组是否一样 ，一样就返回true,不一样就返回false
     * @author liuzhen
     * @date 2021/8/12 14:52
     * @param arr1
     * @param arr2
     * @return boolean
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        // 1.空计较false情况
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        // 2.空比较true情况
        if (arr1 == null && arr2 == null) {
            return true;
        }
        // 3.长度是否相等
        if (arr1.length != arr2.length) {
            return false;
        }
        // 4.值之间是否相等比较(最关键)
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 5.打印Array数组方法
     * @author liuzhen
     * @date 2021/8/12 14:52
     * @param arr
     * @return void
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        // 以字符串进行打印
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            Code_00_BubbleSort.bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        Code_00_BubbleSort.bubbleSort(arr);
        printArray(arr);
    }

}
