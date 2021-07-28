/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.niuke.class01;

import java.util.Scanner;

public class testDemo02 {   // 累乘

    public static int addAll1(int n){ // 非递归形式：

        int sum = 1 ;
        for (int i = 1;i <= n;i++){
            sum = sum * i;
        }

    return sum;

    }

    public static int addAll2(int n){   // 递归的方式实现
        int sum = 1;
        if (n < 1){
            return sum ;
        }


        sum = n * addAll2( n - 1 );
        n--;

        return sum;
    }

    public static int addAll(int n){

        int sumAll = 0;
        for (int i=1;i<=n;i++){
            sumAll += addAll2( i );
        }
        return sumAll;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner( System.in );
        int n = scanner.nextInt();

        /*Random random = new Random( );
        int nn = random.nextInt(50);*/

        int result = addAll1( n );
        System.out.println(result);
        System.out.println("============================");

        int res = addAll2( 3 );
        int resAll = addAll( 3 );
        System.out.println(res);
        System.out.println(resAll);
        System.out.println(125%10000);
    }


}
