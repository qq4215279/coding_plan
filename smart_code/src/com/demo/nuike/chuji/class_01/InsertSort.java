package com.demo.nuike.chuji.class_01;

/**
 * InsertSort
 * 插入排序
 * @author liuzhen
 * @version 1.0.0 2021/7/11 23:23
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j -1]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void printArray(int[] arr) {	// 5.打印Array数组方法
        if (arr == null) {
            return ;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");		//以字符串进行打印
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 22, 3, 4, 77, 876, 33, 32};

        insertSort(arr);
        printArray(arr);

    }

}
