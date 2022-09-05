package com.gobestsoft.java_base.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PutApis
 *
 * @author liuzhen
 * @version 1.0.0 2021/7/30 16:34
 */
public class PutApis {

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
     * computeIfAbsent(): 和putIfAbsent类似但是，在返回值上不一样，value值不存在的时候，
     * 返回的是新的value值，同时可以通过自定义一些条件进行过滤。
     */
    @Test
    public void computeIfAbsentTest() {
        Map<String, String> map = new HashMap<>();

        String value = map.get("key");
        if (value == null) {
            value = "test";
            map.put("key", value);
        }

        // java8之后。上面的操作可以简化为一行，若key对应的value为空，会将第二个参数的返回值存入并返回
        String value2 = map.computeIfAbsent("key2", k -> "hehehe"); // k 为key值
        System.out.println("value2:" + value2);

        System.out.println("----------------->");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }

        // 用法2：================================>
        Map<String, List<Integer>> strIdsMap = new HashMap<>();
        List<Integer> idList = strIdsMap.computeIfAbsent("lihua", k -> new ArrayList<>());
        idList.add(1);
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
        map.computeIfPresent("0", (k,v) -> v * 100);
//        int value1 = map.computeIfPresent("0", (k,v) -> v * 100);
//        System.out.println("value1: " + value1);
        int value2 = map.computeIfPresent("2", (k,v) -> v * 100);
        System.out.println("value2: " + value2);

    }

}
