/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * HashMapUnsafeDemo HashMap不安全Demo
 * 
 * @author liuzhen
 * @version 1.0.0 2022/7/23 21:10
 */
public class HashMapUnsafeDemo {

    /**
     * 证明 HashMap 是非线程安全的。
     * HashMap 本身默认的容量不是很大，如果不停地往 map 中添加新的数据，它便会在合适的时机进行扩容。
     * 而在扩容期间，它会新建一个新的空数组，并且用旧的项填充到这个新的数组中去。
     * 那么，在这个填充的过程中，如果有线程获取值，很可能会取到 null 值，而不是我们所希望的、原来添加的值。如下演示：
     * @date 2022/7/23 21:14
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        final Integer targetKey = 0b1111_1111_1111_1111; // 65535
        final String targetValue = "v";
        map.put(targetKey, targetValue);
        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> {
                map.put(key, "someValue");
            });
        }).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }

}
