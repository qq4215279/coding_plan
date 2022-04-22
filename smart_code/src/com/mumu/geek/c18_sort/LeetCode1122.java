/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c18_sort;


/**
 * LeetCode1122
 * 数组的相对排序
 * @author liuzhen
 * @version 1.0.0 2021/8/28 23:18
 */
public class LeetCode1122 {

    /**
     * 给你两个数组，arr1 和 arr2，
     * arr2 中的元素各不相同
     * arr2 中的每个元素都出现在 arr1 中
     * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
     *
     * 示例：
     * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
     * 输出：[2,2,2,1,4,3,3,9,6,7,19]
     *  
     * 提示：
     * 1 <= arr1.length, arr2.length <= 1000
     * 0 <= arr1[i], arr2[i] <= 1000
     * arr2 中的元素 arr2[i] 各不相同
     * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
     */

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length];
        int[] a = new int[1001];
        // 统计arr1中各个数的个数
        for (int i = 0; i < arr1.length; i++) {
            a[arr1[i]]++;
        }
        // 定义填写新数组的下标位置
        int index = 0;
        // 按arr2的顺序，填写新数组
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < a[arr2[i]]; j++) {
                result[index++] = arr2[i];
            }
            a[arr2[i]] = 0;
        }
        // 剩余数据，按顺序填入新数组
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i]; j++) {
                result[index++] = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        int[] result = relativeSortArray(arr1, arr2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
        System.out.println("------------->");

        int[] array = {3, 534, 22, 234, 1, 3};
        // heapSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

}
