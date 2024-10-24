/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work.huawei;

import java.util.HashSet;
import java.util.Set;

/**
 * Demo3
 *
 * @author liuzhen
 * @version 1.0.0 2022/8/10 23:02
 */
public class Demo3 {

    public static void main(String[] args) {
        int[][] arr = {{2, 1, 0, 3}, {0, 1, 2, 1}, {0, 3, 0, 0}, {0, 0, 0, 0}};

        System.out.println(demo(arr));
    }

    private static int demo(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;

        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> set = new HashSet<>();
                
                if (arr[i][j] == 3) {
                    res += findSucces(arr, i, j, set) >= 2 ? 1 : 0;
                }
            }
        }

        return res;
    }

    private static int findSucces(int[][] arr, int i, int j, Set<Integer> set) {
        int m = arr.length;
        int n = arr[0].length;
        if (i >= m || i < 0 || j >= n || j < 0) {
            return 0;
        }

        int point = arr[i][j];
        if (point == 1) {
            return 0;
        }

        int x = i * j + j;
        if (point == 2 && !set.contains(x)) {
            set.add(x);
            return 1;
        }

        int a = findSucces(arr, i + 1, j, set);
        int c = findSucces(arr, i, j + 1, set);
        int b = findSucces(arr, i - 1, j, set);
        int d = findSucces(arr, i, j - 1, set);

        return a + b + c + d;
    }

}
