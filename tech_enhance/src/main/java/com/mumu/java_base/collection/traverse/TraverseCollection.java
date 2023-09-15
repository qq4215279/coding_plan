/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection.traverse;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.util.*;

/**
 * TraverseCollectionDemo
 * 集合遍历
 * @author liuzhen
 * @version 1.0.0 2021/3/21 15:28
 */
public class TraverseCollection {

    /**
     * 迭代器和for循环效率差异
     * ⼀万个元素两者之间都相差⼀倍多的时间，如果是⼗万，百万个元素，那么两者之间相差的速度会越来越⼤。下⾯通过图形来解释：
     * 普通for循环：每次遍历⼀个索引的元素之前，都要访问之间所有的索引。
     * 迭代器：每次访问⼀个元素后，都会⽤游标记录当前访问元素的位置，遍历⼀个元素，记录⼀个位置。
     * @author liuzhen
     * @date 2022/4/9 22:41
     * @param
     * @return void
     */
    @Test
    public void TraverseEffect() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {// 向链表中添加⼀万个元素
            linkedList.add(i);
        }
        long beginTimeFor = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            System.out.print(linkedList.get(i));
        }
        System.out.println();
        long endTimeFor = System.currentTimeMillis();
        System.out.println("使⽤普通for循环遍历10000个元素需要的时间：" + (endTimeFor - beginTimeFor));
        long beginTimeIte = System.currentTimeMillis();
        Iterator<Integer> it = linkedList.listIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        long endTimeIte = System.currentTimeMillis();
        System.out.println("使⽤迭代器遍历10000个元素需要的时间：" + (endTimeIte - beginTimeIte));
    }

    /**
     * 集合初始化集中方式：
     * 0. add() / addAll()
     * 1.使用Arrays.asList方法，如下：List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
     * 2.导入Guava依赖包，使用Google Guava工具集Lists方法，如下： List<Integer> list = Lists.newArrayList(1, 2, 3);
     * 3.使用Stream，不过要求JDK版本在8以上，如下：List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
     * 4.使用Lists，不过要求JDK版本在9以上，如下：List<Integer> list = Lists.of(1,2,3);
     * 5.匿名对象：这里的双括号”{{}}”到底什么意思，什么用法呢？双括号”{{}}”,用来初始化，使代码简洁易读。第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，
     * 第二层括弧实际上是一个实例初始化块 (instance initializer block)，这个块在内部匿名类构造时被执行。推而广之，可初始化ArrayList、Set、HashMap ...
     *
     */
    private static List<String> arrayList = new ArrayList<String>() {
        {
            add("刘亦菲");
            add("刘翔");
            add("刘星");
        }
    };

    private static final List<Map<String, Object>> list = new ArrayList<>();

    /**
     * 匿名对象
     */
//    private static Map<String, Object> map1 = new HashMap<String, Object>() {
//        {
//            map1.put("username", "zhangsan");
//            map1.put("password", 123);
//            map1.put("age", 20);
//            list.add(map1);
//        }
//    };

    /**
     * 静态代码块初始化
     */
    private static Map<String, Object> map2;

    static {
        map2 = new HashMap<>();

        map2.put("username", "lisi");
        map2.put("password", "root");
        map2.put("age", 30);
        list.add(map2);
    }


    /**
     * for循环
     * @return
     */
    public static <T> void traverseListMethod01(List<T> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            T name = arrayList.get(i);
            System.out.println("用户名：" + name);
        }
        System.out.println("==========1");
    }

    /**
     * 加强for循环
     * @return
     */
    public static <T> void traverseListMethod02(List<T> arrayList) {
        for (T name : arrayList) {
            System.out.println("用户名2：" + name);
        }
        System.out.println("==========2");
    }

    /**
     * forEach
     * @return
     */
    public static void traverseListMethod03(List<T> arrayList) {
        arrayList.forEach(name -> {
            System.out.println("用户名3：" + name);
        });
        System.out.println("==========3");
    }

    /**
     * Iterator
     * @return
     */
    public static void traverseListMethod04(List<T> arrayList) {
        Iterator<T> iteratorList = arrayList.iterator();
        while (iteratorList.hasNext()) {
            System.out.println("用户名4：" + iteratorList.next());
        }
        System.out.println("==========4");
    }

    /**
     * 遍历Set集合
     * Iterator
     * @return
     * @param set
     */
    public static void traverseSetMethod01(Set<Integer> set) {
        Iterator<Integer> iteratorList = set.iterator();
        while (iteratorList.hasNext()) {
            System.out.println("用户名5：" + iteratorList.next());
        }
        System.out.println("==========4");
    }

    /**
     * map遍历方式1：
     * 通过Set
     * @return
     */
    public static void traverseMapMethod01(Map<T, Object> map1) {
        //
        Set<T> set = map1.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            Object value = map1.get(key);
            System.out.println("key:" + key + "  value:" + value);
        }
        System.out.println("==================================1");
    }

    /**
     * map遍历方式2：
     * 通过Set + forEach
     * @return
     */
    public static void traverseMapMethod02(Map<T, Object> map1) {
        Set<T> set1 = map1.keySet();
        set1.forEach(key -> {
            System.out.println("key:" + key);
        });
        Collection<Object> values = map1.values();
        values.forEach(value -> {
            System.out.println("value:" + value);
        });
        System.out.println("==================================2");
    }

    /**
     * map遍历方式3：
     * Map.Entry entry : map.entrySet()
     * @return
     */
    public static void traverseMapMethod03(Map<T, Object> map2) {
        //map遍历方式3： ！！！！！
        for (Map.Entry<T, Object> entry : map2.entrySet()) {
            System.out.println("key:" + entry.getKey() + "  value:" + entry.getValue());
        }
        System.out.println("==================================3");
    }

    /**
     * map遍历方式4: Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
     * Iterator
     * @return
     */
    public static void traverseMapMethod04(Map<T, Object> map1) {
        Iterator<Map.Entry<T, Object>> it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<T, Object> entry = it.next();
            System.out.println("key:" + entry.getKey() + " value:" + entry.getValue());
        }
        System.out.println("==================================4");
    }

    /**
     * map遍历方式4: Iterator遍历获取，然后获取到Map.Entry<String, String>，再得到getKey()和getValue()
     * Iterator
     * @return
     */
    public static void traverseMapMethod05(Map<String, Object> map1) {
        map1.forEach((key, val) -> {
            System.out.println("key:" + key + " value:" + val);
        });

        System.out.println("==================================5");
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "lihua");
        map.put("age", 20);

        traverseMapMethod05(map);
    }
}
