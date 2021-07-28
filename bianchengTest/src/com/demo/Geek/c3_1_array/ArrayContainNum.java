/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c3_1_array;

import java.util.Scanner;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class ArrayContainNum {
    public static void main(String[] args) {
        int[][] arr = {{1, 5, 10, 16}, {6, 15, 20, 30}, {11, 18, 35, 40}, {50, 60, 70, 100}};
        /*
          row行， clo列
        * 1  5  10 15
        * 5  15 20 30
        * 10 18 30 40
        * 50 60 70 100
        * */
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int row= arr.length - 1;
        int clo=0;
        while (row >= 0 && clo < arr.length){
            if (num < arr[row][clo]) {
                row--;
                continue;
            }
            if (num > arr[row][clo]) {
                clo++;
                continue;
            }
            if (num == arr[row][clo]) {
                System.out.println("数组中存在这个数：" + num);
                break;
            }
           /* else if (num!=arr[row][clo]){
                System.out.println("没有这个元素");
            }*/
        }
        /*for (row = arr.length - 1, clo = 0; row >= 0 && clo < arr.length; ) {
            if (num < arr[row][clo]) {
                row--;
                continue;
            }
            if (num > arr[row][clo]) {
                clo++;
                continue;
            }
            if (num == arr[row][clo]) {
                System.out.println("数组中存在这个数：" + num);
                break;
            }
            System.out.println("没有这个元素");
        }*/
    }
}
