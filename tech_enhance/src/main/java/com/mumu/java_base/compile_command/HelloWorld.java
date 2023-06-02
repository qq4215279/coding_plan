//package com.gobestsoft.java_base.compile_command;

/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */


/**
 * HelloWorld
 * 原生命令启动项目
 * @author liuzhen
 * @version 1.0.0 2021/3/19 23:20
 */
public class HelloWorld {

    /**
     * 用java原生命令启动class文件：
     * 1. 将文件里的package... 去掉
     * 2. 生成.class文件: javac -encoding utf-8 HelloWorld.java   // 注: 多个java文件用空格隔开。查看帮助: javac -help
     * 3. 运行Main方法: java HelloWorld   // (注: 多个class文件用空格隔开)
     *
     * 用java原生命令启动jar包：
     * 1. 将文件里的package... 去掉
     * 2. 生成.class文件: javac -encoding utf-8 HelloWorld.java   // 注: 多个java文件用空格隔开。查看帮助: javac -help
     * 3. 生成jar包: jar cvf test.jar HelloWorld.class  // 注: 多个class文件用空格隔开
     * 4. 添加指定程序入口: 用解压软件打开test.jar，进入MANIFEST.MF文件夹下，增加: Main-Class: HelloWorld
     * 5.
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            System.out.println("Hello World" + i);
        }
    }

}
