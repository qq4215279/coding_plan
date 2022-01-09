/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

/**
 * 异常
 */
package com.gobestsoft.java_base.throwable;

/**
 * 知识点：
 * 1. 异常概念：就是不正常的意思。在生活中:医生说,你的身体某个部位有异常,该部位和正常相比有点不同,该部位的功能将 受影响.在程序中的意思就是：
 * 异常 ：指的是程序在执行过程中，出现的非正常的情况，最终会导致JVM的非正常停止。
 * 在Java等面向对象的编程语言中，异常本身是一个类，产生异常就是创建异常对象并抛出了一个异常对象。Java处 理异常的方式是中断处理。
 * 异常指的并不是语法错误,语法错了,编译不通过,不会产生字节码文件,根本不能运行.
 *
 * 2. 异常体系：
 * 异常机制其实是帮助我们找到程序中的问题，java.lang.Throwable: 是所有异常的根类，所有的异常根类都由它继承
 * 其下有两个子类： java.lang.Error 与 java.lang.Exception ，平常所说的异常指 java.lang.Exception 。
 * Throwable体系：
 *      Error: 严重错误Error，无法通过处理的错误，只能事先避免，好比绝症。（错误是不能处理的，因为这是系统内部的错误，运行时报错，系统问题。）
 *          eg: 内存溢出、系统崩溃
 *      Exception: 表示异常，异常产生后程序员可以通过代码的方式纠正，使程序继续运行，是必须要处理的。好比感冒、阑尾炎。（是程序员根据问题描述可以处理的。）
 *              1. IOException、ClassNotFoundException、ParseException...  这些异常必须被处理。
 *              2. RuntimeException：这类异常是可以被处理的，但不一定处理，一般不处理。
 * Throwable中的常用方法：
 *      public void printStackTrace(): 打印异常的详细信息。 包含了异常的类型,异常的原因,还包括异常出现的位置,在开发和调试阶段,都得使用printStackTrace。
 *      public String getMessage(): 获取发生异常的原因。 提示给用户的时候,就提示错误原因。
 *      public String toString(): 获取异常的类型和异常描述信息(不用)。
 *
 * 3. 异常分类：
 * 我们平常说的异常就是指Exception，因为这类异常一旦出现，我们就要对代码进行更正，修复程序。
 * 异常(Exception)的分类: 根据在编译时期还是运行时期去检查异常?
 *      编译时期异常: checked 异常。在编译时期,就会检查,如果没有处理异常,则编译失败。(如日期格式化异常)
 *      运行时期异常: runtime 异常。在运行时期,检查异常.在编译时期,运行异常不会编译器检测(不报错)。(如数学异常)
 *
 * 异常的处理：
 * Java异常处理的五个关键字：try、catch、finally、throw、throws
 */
