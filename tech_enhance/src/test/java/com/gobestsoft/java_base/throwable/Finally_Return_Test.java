/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

/**
 * Finally_Return_Test
 *
 * @author liuzhen
 * @version 1.0.0 2022/1/9 17:23
 */
public class Finally_Return_Test {

    public static void main(String[] args) {
        int a = getA();
        System.out.println(a);
    }

    // 定义一个方法,返回变量a的值

    /**
     * finally一定会执行，即使是catch中return了，catch中的return会等finally中的代码执行完之后，才会执行。
     * 总结：
     * 1. return优先级：finally > catch > try > finally块下面代码
     * 2. try块里代码发生异常，try块里发生异常后的代码就不执行了，包括return
     * 3. 没有发生异常，catch块里的代码不会执行
     * 4. catch、finally里有return， finally块下面就不能有别的代码了，编译都不能通过
     * 5. catch、finally没有return，finally块下面有别的代码，按顺序执行，且finally块下面的代码最后执行
     * @return
     */
    public static int getA() {
        int a = 100;
        int b = 0;
        try {
            System.out.println("start try...");
            int c = a / b;
            System.out.println("return try");
            return 1;
        } catch (Exception e) {
            System.out.println("return catch");
            System.out.println(e);
            // return 2;
        } finally {
            System.out.println("return finally");
            // 一定会执行的代码
            // return 3;
        }

        // 注：catch、finally 里有了return，这里加代码没有意义，不会执行
        System.out.println("return last");
        return 9999;
    }

}
