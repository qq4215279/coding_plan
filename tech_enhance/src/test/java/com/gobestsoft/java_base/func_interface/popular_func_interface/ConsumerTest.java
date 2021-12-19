/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface.popular_func_interface;

import java.util.function.Consumer;

/**
 * ConsumerTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/19 23:05
 */
public class ConsumerTest {

    /**
     * java.util.function.Consumer<T> 接口则正好与Supplier接口相反，它不是生产一个数据，而是消费一个数据， 其数据类型由泛型决定。
     * 抽象方法：accept() Consumer 接口中包含抽象方法 void accept(T t) ，意为消费一个指定泛型的数据。
     * 默认方法：andThen() 如果一个方法的参数和返回值全都是 Consumer 类型，那么就可以实现效果：消费数据的时候，首先做一个操作， 然后再做一个操作，
     *     实现组合。而这个方法就是 Consumer 接口中的default方法 andThen 。
     */

    /**
     * 题目下面的字符串数组当中存有多条信息，请按照格式“ 姓名：XX。性别：XX。 ”的格式将信息打印出来。
     * 要求将打印姓名的动作作为第一个 Consumer 接口的Lambda实例，将打印性别的动作作为第二个 Consumer 接口的Lambda实例，将两个 Consumer 接口按照顺序“拼接”到一起。
     */
    private static void printInfo(Consumer<String> one, Consumer<String> two, String[] array) {
        for (String info : array) {
            // 姓名：迪丽热巴。性别：女。
            one.andThen(two).accept(info);
        }
    }

    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男"};

        printInfo(s -> System.out.print("姓名：" + s.split(",")[0]), s -> System.out.println("性别：" + s.split(",")[1] + "。 "), array);
    }
}