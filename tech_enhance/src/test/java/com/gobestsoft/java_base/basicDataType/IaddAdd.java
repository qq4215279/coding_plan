package com.gobestsoft.java_base.basicDataType;

/**
 * IaddAdd
 * i++ 与 ++i 问题
 * @author liuzhen
 * @version 1.0.0 2021/6/18 21:40
 */
public class IaddAdd {

    /**
     * i++改变的只有i，++i既改变i，又改变赋值变量；
     */
    public static void condition01() {
        int i = 1;
        int s = ++i;
        int x = i++;
        System.out.println(i); // 3
        System.out.println(s); // 2
        System.out.println(x); // 2
    }

    /**
     * 计算: int s = (i++) + (++i) + (i--) + (--i)
     * 计算技巧：先计算 i 的值，在把这部分的计算逻辑赋值给 s ；重复上一步骤
     * 1. i++ => i=6; s'=5;
     * 2. ++i => i=7; s'=7;
     * 3. i-- =>  i=6; s'=7;
     * 4. --i =>  i=5; s'=5;
     */
    public static void condition02() {
        int i = 5;
        int s = (i++) + (++i) + (i--) + (--i);
        System.out.println(i); // 5
        System.out.println(s); // 24
    }

    /**
     *
     */
    public static void condition03() {
        // 1.基础：
        int a = 1, b = 0;
        b = a++;
        System.out.println("b = a++  ===>  " + b); // 1
        b = ++a;
        System.out.println("b = ++a  ===>  " + b); // 2

        // 2.深入：
        int y = 0;
        int i = 0;
        y = ++y;
        System.out.println("y=" + y); // 1

        /*
        * 解析: int temp = i++ // 0   jvm在处理 i = i++ 时， 会建立一个临时变量来接收 i++ 的的值，然后返回这个临时变量的值，返回的值再被等号左边的变量接收了
        *       首先等号左边 i 的值会因为 i++ 变为1；后面又因为等号右边是一个临时变量temp的值0，再次覆盖掉1，变为0.
        *
        */
        i = i++;
        System.out.println("i=" + i); // 0
    }

    public static void main(String[] args) {

    }

}
