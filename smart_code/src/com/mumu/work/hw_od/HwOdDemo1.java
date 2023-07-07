/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.work.hw_od;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * HwOdDemo1
 *
 * @author liuzhen
 * @version 1.0.0 2023/3/14 21:55
 */
public class HwOdDemo1 {

    /**
     * 羊、狼、农夫都在岸边，当羊的数量小于狼的数量时，狼会攻击羊，农夫则会损失羊。农夫有一艘容量固定的船，能够承载固定数量的动物。
     * 要求求出不损失羊情况下将全部羊和狼运到对岸需要的最小次数。只计算农夫去对岸的次数，回程时农夫不会运送羊和狼。 备注：农夫在或农夫离开后羊的数量大于狼的数量时狼不会攻击羊。 农夫自身不占用船的容量。输入描述
     * 第一行输入为M，N，X，分别代表羊的数量，狼的数量，小船的容量。输出描述 输出不损失羊情况下将全部羊和狼运到对岸需要的最小次数。（若无法满足条件则输出0）
     *
     * 示例1： 输入：533 输出：3 说明：第一次运2只狼 第二次运3只羊第三次运2只羊和1只狼示例2：
     *
     * 输入： 541 输出：0 说明：如果找不到不损失羊的运送方案，输出0
     *
     * 思路： 1：首先几个边界条件要清楚 第一、羊的数量不能小于狼的数量 第二、农夫自身不占用船的容量。 第三、回程时农夫不会运送羊和狼。 2：不管在河的两岸都需要满足羊的数量不能小于狼的数量。
     * 3：这样的话明显一个DFS就可以解决问题，因为有重复的子问题就是运送多少只动物的情况下是否满足不损失动物的条件。
     */

    public static int min_times;

    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        // 转为数组
        List<Integer> nums =
            Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        int M = nums.get(0);
        int N = nums.get(1);
        int X = nums.get(2);

        min_times = (M + N) * X;

        // 表示已运输到对岸的羊、狼个数
        int m_temp = 0;
        int n_temp = 0;

        transport(M, N, X, m_temp, n_temp, 0);

        if (min_times == (M + N) * X) {
            System.out.println(0);
        } else {
            System.out.println(min_times);
        }

    }

    // m0, n0 分别表示剩余的羊、狼个数， x为船容量
    // m1, n1 分别表示运输到对岸的羊、狼个数，times为次数
    public static int transport(int m0, int n0, int x, int m1, int n1, int times) {
        // 若可以一次性运走，结束了，注意等于号。。。
        if (x >= m0 + n0) {
            if (times + 1 < min_times) {
                min_times = times + 1;
            }
            return times + 1;
        }
        // 尝试运一部分狼一部分羊
        // 要上船的羊数量不可以超过岸上数量、也不可以超过船的容量
        for (int i = 0; i <= m0 && i <= x; i++) {
            // 要上船的狼的数量不可以超过岸上数量、也不可以超过船装了羊后的剩余的容量
            for (int j = 0; j <= n0 && i + j <= x; j++) {
                // 不可以不运
                if (i + j == 0) {
                    continue;
                }
                // 船离岸后，原来这岸，要么没有羊，要么羊比狼多，才可以运；对岸也要检查，不考虑回程带动物
                if ((m0 - i == 0 || m0 - i > n0 - j) && (m1 + i == 0 || m1 + i > n1 + j)) {
                    // 运一次
                    int result = transport(m0 - i, n0 - j, x, m1 + i, n1 + j, times + 1);
                    // 如果获取了结果，和minTime比较，但是不结束，继续检查
                    if (result < min_times && result != 0) {
                        min_times = result;
                    }
                }
            }
        }
        // 没有方案了。。返回0
        return 0;
    }

}
