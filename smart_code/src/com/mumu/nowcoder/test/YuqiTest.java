/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.nowcoder.test;

public class YuqiTest {

    /**
     * 给一个不重复的整数数组A，同时给一个定值K，找出A中所有能组成和为K的数的集合
     * 例：
     * 输入：[1,2,3,4,5,6], 6
     * 输出：[1,2,3],[2,4],[1,5],[6]
     *
     * 输入：[1,2,3,4,5], 100
     * 输出：None
     */

    public static void binaryCal(int[] arr, int k) {
        int n = arr.length;
        // 最大的数为2的n次方
        int max = 1 << n;
        for (int i = 1; i < max; i++) {
            // 转成二进制数
            String binaryNum = Integer.toBinaryString(i);
            // 转成相同的位数，不足n位的在前补0
            binaryNum = toSameLen(binaryNum, n);
            char[] bitNum = binaryNum.toCharArray();
            int sum = 0;
            for (int j = 0; j < bitNum.length; j++) {
                // 二进制数当前位置为1，则加起来
                if (bitNum[j] == '1') {
                    sum += arr[j];
                }
            }
            // 和为m了，输出
            if (sum == k) {
                output(bitNum, arr);
            }
        }
    }

    private static String toSameLen(String binaryNum, int len) {
        // 数的长度
        int numLen = binaryNum.length();
        if (numLen == len) {
            return binaryNum;
        }
        StringBuilder sb = new StringBuilder();
        // 差几位补几个0
        for (int i = 0; i < len - numLen; i++) {
            sb.append(0);
        }
        return sb.append(binaryNum).toString();
    }

    private static void output(char[] bitNum, int[] a) {
        if (bitNum == null) {
            System.out.println("None");
        }

        for (int i = 0; i < bitNum.length; i++) {
            if (bitNum[i] == '1') {
                System.out.print(a[i] + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int k = 6;
        binaryCal(arr, k); // 6    2 4     1 5     1 2 3
    }

}
