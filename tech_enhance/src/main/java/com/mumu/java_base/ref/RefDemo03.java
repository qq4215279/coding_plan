package com.mumu.java_base.ref;

/**
 * RefDemo03
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/7 20:45
 */
public class RefDemo03 {

    // String、Integer等是不可变类型
    public static void main(String[] args) {
        String str = "张三";
        call(str);
        System.out.println(str);
    }

    public static void call(String i) {
        i = "李四";
    }

}
