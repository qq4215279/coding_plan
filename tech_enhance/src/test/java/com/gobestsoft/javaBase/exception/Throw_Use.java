/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.exception;

/**
 * Throw_Use
 *
 * @author liuzhen
 * @version 1.0.0 2021/3/19 22:16
 */
public class Throw_Use {


    public static int method(int type) {
        switch (type) {
            case 1:
                System.out.println("哈哈哈");
                break;
            case 2:
                System.out.println("呵呵呵");
                break;
            default:
                throw new RuntimeException("类型错误，嘤嘤嘤！");
        }
        return type;
    }

    public static void main(String[] args) {

        System.out.println("开始");
        int type = Throw_Use.method(2);
        System.out.println("type: " + type);
        System.out.println("结束~~~~~");
    }

}
