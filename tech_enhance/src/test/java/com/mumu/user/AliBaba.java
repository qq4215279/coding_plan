/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

/**
 * AliBaba
 *
 * @author liuzhen
 * @version 1.0.0 2020/12/23 16:42
 */
public class AliBaba {

    /**
     * 不要在 foreach 循环里进行元素的 remove/add 操作。
     * remove 元素请使用 Iterator 方式，如果并发操作，需要对 Iterator 对象加锁。
     */
    @Test
    public void test01() { // [2] [2]

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }

            System.out.println(list.toString());
        }
    }

    @Test
    public void test02() { // [2]
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }

            System.out.println(list.toString());
        }
    }

    @Test
    public void test11() { // [1,2]  [1]
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }

            System.out.println(list.toString());
        }
    }

    @Test
    public void test12() { // [1,2] [1]  fail: java.util.ConcurrentModificationException
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }

            System.out.println(list.toString());
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("1".equals(item)) {
                iterator.remove();
            }

            System.out.println(list.toString());
        }

//        for (String item : list) {
//            if ("1".equals(item)) {
//                list.remove(item);
//            }
//
//            System.out.println(list.toString());
//        }
        // 说明：以上代码的执行结果肯定会出乎大家的意料，那么试一下把“1”换成“2”，会是同样的结果吗？
    }
}
