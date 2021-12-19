/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface.popular_func_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * PredicateTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/19 23:19
 */
public class PredicateTest {

    /**
     * Predicate接：有时候我们需要对某种类型的数据进行判断，从而得到一个boolean值结果。这时可以使用 java.util.function.Predicate<T> 接口。
     * 抽象方法：test() Predicate 接口中包含一个抽象方法： boolean test(T t) 。用于条件判断的场景
     * 默认方法：and 既然是条件判断，就会存在与、或、非三种常见的逻辑关系。其中将两个 Predicate 条件使用“与”逻辑连接起来实 现“并且”的效果时，可以使用default方法 and 。
     * 默认方法：or 与 and 的“与”类似，默认方法 or 实现逻辑关系中的“或”。
     * 默认方法：negate “与”、“或”已经了解了，剩下的“非”（取反）也会简单。
     */

    /**
     * 题目：数组当中有多条“姓名+性别”的信息如下，请通过 Predicate 接口的拼装将符合要求的字符串筛选到集合 ArrayList 中，
     * 需要同时满足两个条件： 1. 必须为女生； 2. 姓名为4个字。
     */
    public static List<String> filter(String[] array, Predicate<String> one, Predicate<String> two) {
        List<String> list = new ArrayList<>();
        for (String info : array) {
            if (one.and(two).test(info)) {
                list.add(info);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
        List<String> list = filter(array, s -> "女".equals(s.split(",")[1]), s -> s.split(",")[0].length() >= 4);
        System.out.println(list);
    }

}
