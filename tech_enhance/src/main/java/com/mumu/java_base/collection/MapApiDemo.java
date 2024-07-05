/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapApiDemo
 *
 * @author liuzhen
 * @version 1.0.0 2021/7/30 16:34
 */
public class MapApiDemo {


    /**
     * put(): 只要key存在，value值就会被覆盖,注意put方法返回的是put之前的值，如果无put之前的值返回null
     */
    @Test
    public void putTest() {
        Map<String, Integer> map = new HashMap<>();

        map.put("1", 1);
//        int num1 = map.put("1", 1);
//        System.out.println("num1: " + num1);

        int num2 = map.put("1", 2);
        System.out.println("num2: " + num2);
    }

    /**
     * putIfAbsent(): 只有在key不存在或者key为null的时候，value值才会被覆盖。返回put之前的值。 
     * 使用场景：如果我们要变更某个key的值，我们又不知道key是否存在的情况下，而又不希望增加key的情况使用。
     */
    @Test
    public void putIfAbsentTest() {
        Map<String, Integer> map = new HashMap<>();

        map.put("t1", 123);
        int value = map.putIfAbsent("t1", 11);
        System.out.println("value: " + value);

        // 等价于
        if (map.containsKey("t1") && map.get("t1") != null) {
            map.put("t2", 22);
        }
    }

    /**
     * compute：V compute(K key, BiFunction < ? super K, ? super V, ? extends V> remappingFunction)
     * compute的方法,指定的key在map中的值进行操作 不管存不存在，操作完成后保存到map中
     * 注：
     *  - 使用 compute() 需要进行对 V 值进行判空，若V为null会报空指针异常！eg: integer4
     *  - compute() 返回为最新的 V 值
     *
     * @return void
     * @date 2024/5/20 16:04
     */
    @Test
    public void computeTest() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        Integer integer = map.compute("3", (k, v) -> v + 1);
        // v 为null，需要对v 进行判空，如下写法error
        // Integer integer4 = map.compute("4", (k, v) -> v + 1);
        // key不管存在不在都会执行后面的函数，并保存到map中
        Integer integer5 = map.compute("5", (k, v) -> {
            if (v == null) {
                return 0;
            }
            return v + 1;
        });

        System.out.println(integer); // 4
        System.out.println(integer5); // 0
        System.out.println(map.toString()); // {1=1, 2=2, 3=4, 4=0}
    }

    /**
     * computeIfAbsent(): 和putIfAbsent类似但是，在返回值上不一样，value值不存在的时候，
     * 返回的是新的value值，同时可以通过自定义一些条件进行过滤。
     */
    @Test
    public void computeIfAbsentTest() {
        Map<String, List<Integer>> map = new HashMap<>();

        List<Integer> list = map.get("key");
        if (list == null) {
            list = new ArrayList<>();
            list.add(1);
            map.put("key", list);
        }

        // java8之后。上面的操作可以简化为一行，若key对应的value为空，会将第二个参数的返回值存入并返回
        List<Integer> list2 = map.computeIfAbsent("key2", k -> new ArrayList<>()); // k 为key值
        System.out.println("value2:" + list2);

        System.out.println("----------------->");

        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        // 用法2：================================>
        Map<String, List<Integer>> strIdsMap = new HashMap<>();
        List<Integer> idList = strIdsMap.computeIfAbsent("lihua", k -> new ArrayList<>());
        idList.add(1);

        // 用法3：================================>
        System.out.println("用法3：================================>");
        Map<Integer, String> map3 = new HashMap<>();
        String s1 = map3.computeIfAbsent(1, k -> "computeIfAbsent11111");
        System.out.println("s1: " + s1);
        if ("computeIfAbsent11111".equals(s1)) {
            map3.put(1, "computeIfAbsent22222");
        }

        String s3 = map3.computeIfAbsent(1, k -> "computeIfAbsent3333");
        System.out.println("s3: " + s3);

    }

    /**
     * computeIfPresent(): 和computeIfAbsent方法正好相反，只有当key存在的时候才执行操作。
     * 如果value值不存在，返回的是新的value值
     */
    @Test
    public void computeIfPresentTest() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        //如果value值不存在，返回的是新的value值
        map.computeIfPresent("0", (k, v) -> v * 100);
//        int value1 = map.computeIfPresent("0", (k,v) -> v * 100);
//        System.out.println("value1: " + value1);
        int value2 = map.computeIfPresent("2", (k,v) -> v * 100);
        System.out.println("value2: " + value2);

    }

}
