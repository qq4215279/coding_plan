package com.demo.nuike.chuji.class_01;

import com.demo.common.utils.ArrayUtil;
import com.demo.common.utils.LogarithmUtil;

/**
 * InsertSort
 * 直接插入排序
 * @author liuzhen
 * @version 1.0.0 2021/7/11 23:23
 */
public class InsertSort { // 直接插入排序（插入排序） 时间复杂度O(N^2) 空间复杂度O(1)

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j -1]) {
                    ArrayUtil.swap(arr, j - 1, j);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {4, 1, 22, 3, 4, 77, 876, 33, 32};

        insertSort(arr);
        LogarithmUtil.printArray(arr);

        
    }

}
