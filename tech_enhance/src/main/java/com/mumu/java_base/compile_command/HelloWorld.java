/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.compile_command;

/**
 * HelloWorld
 * 原生命令启动项目
 * @author liuzhen
 * @version 1.0.0 2021/3/19 23:20
 */
public class HelloWorld {

    /**
     * 1.1. 编译字节码。javac 编译 Java 源文件。格式: javac [options] [source files]
     * eg：- 定位到src/main/java目录下。
     *     - 输入命令: javac -d out -encoding UTF-8  com\mumu\java_base\compile_command\HelloWorld.java
     *          -encoding UTF-8    指定了编码为 UTF-8
     *          -d out    指定了编译输出目录为 out 文件夹
     *          com\mumu\java_base\compile_command\HelloWorld.java    要编译的 Java 源文件。
     *          其他参数：
     *              -d directory: 指定编译输出的目录。编译后的 .class 文件将存储在指定的目录中。
     *              -classpath path 或 -cp path: 指定类路径，用于寻找依赖的类和包。路径可以是目录或 JAR 文件。
     *              -sourcepath path: 指定源代码的搜索路径。
     *              -encoding encoding: 指定源文件的编码。
     *              -verbose: 显示详细的编译信息。
     *              -version: 显示版本信息。
     *              -help: 显示帮助信息。
     *
     * 1.2. 运行字节码
     * eg: java -cp out com.mumu.java_base.compile_command.HelloWorld
     *      -cp 参数来设置类路径，指向你的编译输出目录 out。然后，它会运行主类 com.mumu.java_base.compile_command.HelloWorld
     *
     *
     * 2.1. 打jar包命令
     * eg: - 先编译。
     *     - 再执行打包命令: jar cfe HelloWorld.jar com.mumu.java_base.compile_command.HelloWorld -C out .
     *          c    创建新的 JAR 文件。
     *          v 表示在标准输出中生成详细输出。
     *          f    指定 JAR 文件的名称。
     *          m 后面跟着MANIFEST.MF文件的路径。
     *          e    指定主类。
     *          HelloWorld.jar    指定要创建的 JAR 文件的名称。
     *          com.mumu.java_base.compile_command.HelloWorld       指定主类的完整类名。
     *          -C out .    进入 out 目录，将其中的文件包含在 JAR 中，. 表示当前目录
     *
     * 2.2. 执行jar包。格式: java -jar jar文件名.jar
     * eg: 执行命令: java -jar HelloWorld.jar
     *
     */
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <= 100; i++) {
            System.out.println("Hello World - " + i);
        }

        Thread.sleep(100000);
    }

}
