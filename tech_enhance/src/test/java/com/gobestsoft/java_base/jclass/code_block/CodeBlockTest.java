/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.jclass.code_block;

class CodeBlockTest {
    public static void main(String[] args) {
        System.out.println("开始执行Test main方法====>");

        SubCodeBlock subCodeBlock = new SubCodeBlock();
        subCodeBlock.normalBlock();

        /*
            开始执行Test main方法====>
            静 - 父类静态代码块
            静 - 子类静态代码块
            父 - 父类构造代码块
            父 - 父类无参构造函数执行~
            子 - 子类构造代码块
            子 - 子类无参构造函数执行~
            普通 - 子类普通代码块
        */

    }
}