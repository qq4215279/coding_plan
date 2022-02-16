/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

/**
 * 函数式接口介绍
 */
package com.gobestsoft.java_base.func_interface;

/**
 * 知识点：
 * 函数式接口：有且仅有一个抽象方法的接口。
 * 语法糖：是指使用更加方便，但是原理不变的代码语法。例如在遍历集合时使用的for-each语法，其实底层的实现原理仍然是迭代器，这便是“语法糖”。
 *      从应用层面来讲，Java中的Lambda可以被当做是匿名内部 类的“语法糖”，但是二者在原理上是不同的。
 *  好处：
 *  1. Lambda的延迟执行：有些场景的代码执行后，结果不一定会被使用，从而造成性能浪费。而Lambda表达式是延迟执行的，这正好可以作为解决方案，提升性能。
 *
 * @FunctionalInterface注解：与 @Override 注解的作用类似，Java 8中专门为函数式接口引入了一个新的注解： @FunctionalInterface 。该注 解可用于一个接口的定义上
 * 一旦使用该注解来定义接口，编译器将会强制检查该接口是否确实有且仅有一个抽象方法，否则将会报错。需要注意的是，即使不使用该注解，只要满足函数式接口的定义，这仍然是一个函数式接口，使用起来都一样。
 *
 * 函数式接口，可以进行函数式编程，
 * 函数式编程的体现，lambda表达式！
 */