package com.mumu.nowcoder.chuji.class_02;

import java.util.LinkedList;

public class Code_08_AllLessNumSubArray {
    /**
     * 最大值减最小值小于或等于num的子数组数量
     * 题目：给定数组arr和整数num，共返回有多少个 子数组满足如下情况：
     * max(arr[i..j]) - min(arr[i..j]) <= num
     * max(arr[i..j])表示子数组arr[i..j]中的最大值，min(arr[i..j])表示子数组arr[i..j]中的最小值
     * 要求：如果数组长度为N，请实现时间复杂度为O(N)的解法
     */

    //1.暴力解法：
    public static int getNum1(int[] arr, int num){
        int res = 0;
        for (int start = 0; start < arr.length ; start++) {
            for (int end = start; end < arr.length ; end++) {
                if (isValid(arr, start, end, num)){
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean isValid(int[] arr, int start, int end, int num){
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }

    //2.最优解
    public static int getNum(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (left < arr.length){
            while (right < arr.length){
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[right]) {
                    qmin.pollLast();
                }
                qmin.addLast(right);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[right]){
                    qmax.pollLast();
                }
                qmax.addLast(right);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break;
                }
                right++;
            }
            if (qmin.peekFirst() == left){
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == left){
                qmax.pollFirst();
            }
            res += right - left;
            left++;
        }
        return res;
    }

    // for test



    public static void main(String[] args) {

    }
}
