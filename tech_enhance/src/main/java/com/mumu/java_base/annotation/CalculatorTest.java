/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation;

import com.mumu.java_base.annotation.anno.Check;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * CalculatorTest
 * 定义的计算器类
 * @author liuzhen
 * @version 1.0.0 2023/7/13 14:36
 */
public class CalculatorTest {

    /**
     * 加法
     * @date 2023/7/13 15:51
     * @param
     * @return void
     */
    @Check
    public void add() {
        String str = null;
        // bug!
        str.toString();
        System.out.println("1 + 0 =" + (1 + 0));
    }

    /**
     * 减法
     * @date 2023/7/13 15:51
     * @param
     * @return void
     */
    @Check
    public void sub() {
        System.out.println("1 - 0 =" + (1 - 0));
    }

    /**
     * 乘法
     * @date 2023/7/13 15:51
     * @param
     * @return void
     */
    @Check
    public void mul() {
        System.out.println("1 * 0 =" + (1 * 0));
    }

    /**
     * 除法 bug
     * @date 2023/7/13 15:52
     * @param
     * @return void
     */
    @Check
    public void div() {
        // bug!
        System.out.println("1 / 0 =" + (1 / 0));
    }

    public void show() {
        System.out.println("永无bug...");
    }

    /**
     * 简单的测试框架
     * 当主方法执行后，会自动自行被检测的所有方法(加了Check注解的方法)，判断方法是否有异常，记录到文件中
     * @date 2023/7/13 16:36
     * @param args
     * @return void
     */
    public static void main(String[] args) throws IOException {
        // 1. 创建计算器对象
        CalculatorTest c = new CalculatorTest();
        // 2. 获取字节码文件对象
        Class cls = c.getClass();
        // 3. 获取所有方法
        Method[] methods = cls.getMethods();

        // 出现异常的次数
        int number = 0;
        BufferedWriter bw = new BufferedWriter(new FileWriter("bug.txt"));

        for (Method method : methods) {
            // 4. 判断方法上是否有Check注解
            if (method.isAnnotationPresent(Check.class)) {
                // 5.有，执行
                try {
                    method.invoke(c);
                } catch (Exception e) {
                    // 6. 捕获异常

                    // 记录到文件中
                    number++;

                    e.printStackTrace();

                    bw.write(method.getName() + " 方法出异常了");
                    bw.newLine();
                    bw.write("异常根本原因的名称:" + e.getCause().getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常根本原因的原因:" + e.getCause().getMessage());
                    bw.newLine();

                    bw.write("异常的名称:" + e.getClass().getSimpleName());
                    bw.newLine();
                    bw.write("异常的原因:" + e.getMessage());
                    bw.newLine();

                    bw.write("--------------------------");
                    bw.newLine();

                }
            }
        }

        bw.write("本次测试一共出现 " + number + " 次异常");
        bw.newLine();

        bw.flush();
        bw.close();

    }
}
