/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.nowcoder.test;

public class AddMultiAll {

    /**
     * 累加 + 累乘
     * eg：
     *  输入3：
     *  输出：6 = 1 + 2 * 1 + 3 * 2 * 1
     */

    /**
     * 非递归实现累乘
     * @author liuzhen
     * @date 2021/8/9 13:49
     * @param n
     * @return int
     */
    public static int multiAll(int n) {
        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }

        return sum;
    }

    /**
     * 递归实现累乘
     * @author liuzhen
     * @date 2021/8/9 13:49
     * @param n
     * @return int
     */
    public static int multiAllByRecur(int n) {
       if (n == 1) {
           return n;
       }

        return n * multiAllByRecur(n - 1);
    }

    /**
     * 累加 + 累乘
     * @author liuzhen
     * @date 2021/8/9 13:48
     * @param n
     * @return int
     */
    public static int addMultiAll(int n) {
        int sumAll = 0;
        for (int i = 1; i <= n; i++) {
            sumAll += multiAllByRecur(i);
        }
        return sumAll;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();

        /*Random random = new Random( );
        int nn = random.nextInt(50);*/

        int multiAllByRecur = multiAllByRecur(5);
        int result = addMultiAll(5);
        System.out.println("multiAllByRecur: " + multiAllByRecur);
        System.out.println("result: " + result);

    }

}
