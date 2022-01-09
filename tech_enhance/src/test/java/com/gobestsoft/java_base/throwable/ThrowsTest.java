/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ThrowsTest
 * 声明异常throws
 * @author liuzhen
 * @version 1.0.0 2022/1/9 16:52
 */
public class ThrowsTest {

    /**
     * 声明异常throws:
     * 声明异常：将问题标识出来，报告给调用者。如果方法内通过throw抛出了编译时异常，而没有捕获处理（稍后讲 解该方式），那么必须通过throws进行声明，让调用者去处理。
     * 关键字throws运用于方法声明之上,用于表示当前方法不处理异常,而是提醒该方法的调用者来处理异常(抛出异常).
     *
     *
     * 声明异常格式：修饰符 返回值类型 方法名(参数) throws 异常类名1,异常类名2…{ }
     */

    public static void main(String[] args) throws FileNotFoundException, IOException {
        read("a.txt");
    }

    // 如果定义功能时有问题发生需要报告给调用者。可以通过在方法上使用throws关键字进行声明

    /**
     * throws用于进行异常类的声明，若该方法可能有多种异常情况产生，那么在throws后面可以写多个异常类，用逗号隔开。
     */
    public static void read(String path) throws FileNotFoundException, IOException {
        // 如果不是 a.txt这个文件
        if (!path.equals("a.txt")) {
            // 我假设 如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常 throw
            throw new FileNotFoundException("文件不存在");
        }
        if (!path.equals("b.txt")) {
            throw new IOException();
        }
    }

}
