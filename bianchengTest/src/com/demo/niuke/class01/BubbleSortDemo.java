/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.niuke.class01;

import java.util.Arrays;
import java.util.Random;

public class BubbleSortDemo { // 冒泡排序

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            //System.out.println("该数组不需要进行排序");
            return;

        }
        for (int e = arr.length - 1; e > 0; e--) {
            for (int i = 0; i < e; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //对数器：
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] arraysRandom(int maxSize, int maxValues) {
        Random r = new Random();
        int ms = r.nextInt(maxSize) + 1;
        int arr[] = new int[ms];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(maxValues);
        }
        return arr;
    }

    public static int[] arraysCopy(int[] arr) {
        if (arr == null) {
            //System.out.println("数组太短！");
            return null;
        }
        int res[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEquals(int[] arr, int[] res) {
        if (arr == null && res == null) {
            return true;
        }
        if ((arr == null && res != null) || (arr != null && res == null)) {
            return false;
        }
        if (arr.length != res.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != res[i]) {
                return false;
            }
        }
        return true;
    }


    public static void printArrays(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        int timeTest = 500000;
        int ms = 100;
        int mv = 100;
        boolean succeed = true;
        for (int i = 0; i < timeTest; i++) {

            int[] arr = arraysRandom(ms, mv);
            int[] res = arraysCopy(arr);
            sort(arr);
            comparator(res);
            if (!isEquals(arr, res)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        int[] arr = arraysRandom(ms, mv);
        printArrays(arr);
        sort(arr);
        printArrays(arr);

        /*System.out.println("排序方法没有问题，请打印输出一次结果：");

        int[] arr1 = arraysRandom(ms, mv);
        sort(arr);
        System.out.println(Arrays.toString(arr));*/

        /*int arr2[] = {2, 999, 15, 156, 1, 65};
        sort(arr);
        System.out.println(Arrays.toString(arr));*/
    }
}


