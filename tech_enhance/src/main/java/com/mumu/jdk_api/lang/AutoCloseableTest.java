/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * AutoCloseableTest
 * AutoCloseable
 * @author liuzhen
 * @version 1.0.0 2025/7/12 17:18
 */
public class AutoCloseableTest {

    /**
     * AutoCloseable 是 Java 7 引入的一个核心接口，位于 java.lang 包中，用于支持 try-with-resources 语句，实现资源的自动管理。
     * 1. 主要特点:
     *      资源自动释放：与 try-with-resources 配合使用，确保资源被正确关闭
     *      异常处理：close() 方法可以抛出异常
     *      广泛实现：JDK 中所有需要关闭的资源类都实现了此接口
     *
     * 2. 使用方式
     *    try (ResourceType resource = new ResourceType()) {
     *         // 使用资源
     *    } // 自动调用 close()
     *
     *
     *
     */

    public static void main(String[] args) {
        // 使用
        try (MyResource res = new MyResource()) {
            res.doSomething();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static class MyResource implements AutoCloseable {
        public void doSomething() {
            System.out.println("Doing something...");
        }

        @Override
        public void close() throws Exception {
            System.out.println("Resource is being closed");
            // 释放资源的逻辑
        }
    }

    
    /**
     * 现代方式 (try-with-resources)  与 传统方式 (try-finally) 对比:
     * 优势：
     *   代码更简洁
     *   自动处理多个资源的关闭
     *   更好的异常处理（支持抑制异常）
     * @return void
     * @author liuzhen
     * @date 2025/7/12 17:26
     */
    public void streamDemo() {
        // 1. 传统方式 (try-finally)
        InputStream input = null;
        try {
            input = new FileInputStream("file.txt");
            // 使用输入流
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // 处理关闭异常
                }
            }
        }

        // 2.
        try (InputStream input2 = new FileInputStream("file.txt")) {
            // 使用输入流
        } catch (Exception e) {
            throw new RuntimeException(e);
        } // 自动关闭

    }


}
