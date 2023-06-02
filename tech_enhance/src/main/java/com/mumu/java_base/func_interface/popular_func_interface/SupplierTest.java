/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.func_interface.popular_func_interface;

import java.util.function.Supplier;

/**
 * SupplierTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/19 22:58
 */
public class SupplierTest {

    /**
     * java.util.function.Supplier<T> 接口仅包含一个无参的方法： T get() 。用来获取一个泛型参数指定类型的对象数据。
     * 由于这是一个函数式接口，这也就意味着对应的Lambda表达式需要“对外提供”一个符合泛型类型的对象数据。
     */

    /**
     * 题目：使用 Supplier 接口作为方法参数类型，通过Lambda表达式求出int数组中的最大值。提示：接口的泛型请使用 java.lang.Integer 类。
     */
    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 4, 52, 333, 23};

        // 调用getMax方法,参数传递Lambda
        int maxValue = getMax(() -> {
            int max = 0;
            for (int i : arr) {
                max = Math.max(max, i);
            }
            return max;
        });
        System.out.println("最大值为：" + maxValue);
    }

}
