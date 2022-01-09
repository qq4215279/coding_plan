/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.jclass.code_block;

class SubCodeBlock extends SuperCodeBlock {
    /** 子类静态代码块 */
    static {
        System.out.println("静 - 子类静态代码块");
    }

    /** 子类构造代码块 */
    {
        System.out.println("子 - 子类构造代码块");
    }

    public SubCodeBlock() {
        System.out.println("子 - 子类无参构造函数执行~");
    }

    public SubCodeBlock(String str) {
        System.out.println("子 - 有参构造函数执行~" + str);
    }

    /**
     * 普通代码块 -- 方法调用时执行
     */
    public void normalBlock(){
        // 定义普通代码块
        {
            System.out.println("普通 - 子类普通代码块");
        }
    }

    public static void main(String[] args) {
        System.out.println("开始执行子类main方法====>");

        SubCodeBlock subCodeBlock = new SubCodeBlock();
        subCodeBlock.normalBlock();

        // 结果
       /*
            静 - 父类静态代码块
            静 - 子类静态代码块
            开始执行子类main方法====>
            父 - 父类构造代码块
            父 - 父类无参构造函数执行~
            子 - 子类构造代码块
            子 - 子类无参构造函数执行~
            普通 - 子类普通代码块
        */
    }

}