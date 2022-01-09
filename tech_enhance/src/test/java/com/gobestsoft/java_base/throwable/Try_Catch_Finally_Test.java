/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

import java.io.FileNotFoundException;

/**
 * Try_Catch_Finally_Test
 * 捕获异常try…catch
 * @author liuzhen
 * @version 1.0.0 2022/1/9 16:57
 */
public class Try_Catch_Finally_Test {

    /**
     * 捕获异常try…catch：
     * 如果异常出现的话,会立刻终止程序,所以我们得处理异常:
     *      1. 该方法不处理,而是声明抛出,由该方法的调用者来处理(throws)。
     *      2. 在方法中使用try-catch的语句块来处理异常。
     *
     * try-catch的方式就是捕获异常。
     *      try：该代码块中编写可能产生异常的代码。
     *      catch：用来进行某种异常的捕获，实现对捕获到的异常进行处理。
     * 捕获异常：Java中对异常有针对性的语句进行捕获，可以对出现的异常进行指定方式的处理。
     * 捕获异常语法如下：
     *      try {
     *          编写可能会出现异常的代码
     *      } catch(异常类型 e) {
     *          处理异常的代码
     *          // 记录日志/打印异常信息/继续抛出异常
     *      }
     *
     * 如何获取异常信息：
     * Throwable类中定义了一些查看方法:
     *      public String getMessage(): 获取异常的描述信息,原因(提示给用户的时候,就提示错误原因。
     *      public String toString(): 获取异常的类型和异常描述信息(不用)。
     *      public void printStackTrace(): 打印异常的跟踪栈信息并输出到控制台。
     * 包含了异常的类型,异常的原因,还包括异常出现的位置,在开发和调试阶段,都得使用printStackTrace。
     *
     * finally代码块：
     * finally：有一些特定的代码无论异常是否发生，都需要执行。另外，因为异常会引发程序跳转，导致有些语句执行不到。而finally就是解决这个问题的，在finally代码块中存放的代码都是一定会被执行的。
     * 什么时候的代码必须最终执行？
     *  当我们在try语句块中打开了一些物理资源(磁盘文件/网络连接/数据库连接等),我们都得在使用完之后,最终关闭打开的资源。
     * finally的语法: try...catch....finally:自身需要处理异常,最终还得关闭资源。
     * 注意:finally不能单独使用。
     *
     * 当只有在try或者catch中调用退出JVM的相关方法,此时finally才不会执行,否则finally永远会执行。
     *
     * 异常注意事项：
     * a.多个异常使用捕获又该如何处理呢？
     *      1. 多个异常分别处理。
     *      2. 多个异常一次捕获，多次处理。
     *      3. 多个异常一次捕获一次处理。
     * 一般我们是使用一次捕获多次处理方式，格式如下：
     *      try {
     *          编写可能会出现异常的代码
     *      } catch(异常类型A e) { // 当try中出现A类型异常,就用该catch来捕获.
     *          处理异常的代码
     *          // 记录日志/打印异常信息/继续抛出异常
     *      } catch(异常类型B e) { // 当try中出现B类型异常,就用该catch来捕获.
     *          处理异常的代码
     *          // 记录日志/打印异常信息/继续抛出异常
     *      }
     * 注意:这种异常处理方式，要求多个catch中的异常不能相同，并且若catch中的多个异常之间有子父类异 常的关系，那么子类异常要求在上面的catch处理，父类异常在下面的catch处理。
     *
     * b. 运行时异常被抛出可以不处理。即不捕获也不声明抛出。
     * c. 如果finally有return语句,永远返回finally中的结果,避免该情况.
     * d. 如果父类抛出了多个异常,子类重写父类方法时,抛出和父类相同的异常或者是父类异常的子类或者不抛出异常。
     * e. 父类方法没有抛出异常，子类重写父类该方法时也不可抛出异常。此时子类产生该异常，只能捕获处理，不 能声明抛出
     */

    public static void main(String[] args) {
        try {
            // 当产生异常时，必须有处理方式。要么捕获，要么声明。
            read("b.txt");
        } catch (FileNotFoundException e) {
            // 括号中需要定义什么呢？ //try中抛出的是什么异常，在括号中就定义什么异常类型
            System.out.println(e);

             /*
                Throwable类中定义了3个异常处理的方法
                 String getMessage() 返回此 throwable 的简短描述。
                 String toString() 返回此 throwable 的详细消息字符串。
                 void printStackTrace()  JVM打印异常对象,默认此方法,打印的异常信息是最全面的
             */
            // 文件不存在
            System.out.println(e.getMessage());
            // 重写Object类的toString java.io.IOException: 文件不存在
            System.out.println(e.toString());
            // java.io.IOException: 文件不存在
            System.out.println(e);

        } finally {
            System.out.println("不管程序怎样，这里都将会被执行。");
        }
        System.out.println("over");
    }

    /**
     * 我们 当前的这个方法中 有异常 有编译期异常
     */
    public static void read(String path) throws FileNotFoundException {
        if (!path.equals("a.txt")) {
            // 如果不是 a.txt这个文件 // 我假设 如果不是 a.txt 认为 该文件不存在 是一个错误 也就是异常 throw
            throw new FileNotFoundException("文件不存在");
        }
    }

}
