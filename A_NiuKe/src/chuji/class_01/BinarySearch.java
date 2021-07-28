/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package chuji.class_01;

/**
 * DichotomyArray
 * 二分法解题
 * @author liuzhen
 * @version 1.0.0 2021/7/8 17:07
 */
public class BinarySearch {

    /**
     * 一个有序数组，找出target的起止位置
     *  nums = [5,7,7,8,8,10], target = 8
     *  输出 [3,4]
     */

    /**
     * 二分法查找
     * @param arr
     * @param target
     * @return
     */
    public static int[] binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        boolean flag = false;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (target < arr[middle]) {
                right = middle - 1;
            } else if (target > arr[middle]) {
                left = middle + 1;
            } else {
                flag = true;
                break;
            }
        }

        int minPos = -1;
        int maxPos = -1;
        if (flag) {
            for (int i = middle; i >= 0; i--) {
                if (arr[i] != target) {
                    break;
                }
                minPos = i;
            }
            for (int i = middle; i < arr.length; i++) {
                if (arr[i] != target) {
                    break;
                }
                maxPos = i;
            }
        }

        return new int[] {minPos, maxPos};
    }


    public static int[] findIndex(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        int minPos = right;
        int maxPos = left;

        int middle = (right - left) / 2;

        while (middle != -1) {
            int num = arr[middle];

            if (target > num) {
                left = middle;
                middle = middle == right ? -1 : (right + middle + 1) / 2;
            } else if (target < num) {
                right = middle;
                middle = middle == left ? -1 : (left + middle - 1) / 2;
            } else {
                minPos = minPos < middle ? minPos : middle;
                maxPos = maxPos > middle ? maxPos : middle;
                break;
            }
        }

        // 没有找到target值
        if (minPos > maxPos) {
            return new int[] {-1, -1};
        }

        // 向左找
        for (int i = minPos; i >= left; i--) {
            int num = arr[i];
            if (num != target) {
                break;
            }
            minPos = minPos < i ? minPos : i;
        }
        // 向右找
        for (int i = maxPos; i < right; i++) {
            int num = arr[i];
            if (num != target) {
                break;
            }
            maxPos = maxPos > i ? maxPos : i;
        }

        return new int[] {minPos, maxPos};
    }

    /**
     * 二分法查找
     * @param arr
     * @param key
     * @return
    */
    public static int search(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (key < arr[middle]) {
                right = middle - 1;
            } else if (key > arr[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 7, 8, 8, 10};
        int target = 7;
//        int[] result = findIndex(arr, target);
        int[] result = binarySearch(arr, target);

        System.out.println("minPos: " + result[0]);
        System.out.println("maxPos: " + result[1]);
    }

}
