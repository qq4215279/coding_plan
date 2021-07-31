/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.collection;

import java.util.*;

/**
 * List2Set
 * List与Set相互转化
 * @author liuzhen
 * @version 1.0.0 2021/3/21 16:21
 */
public class List2Set {

    private static List<Integer> list = Arrays.asList(1, 1, 1,2,43,2);
    private static Set<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3));

    /***
     * 构造方法
     * @return
     */
    public static Set<Integer> list2Set01(List<Integer> list) {
        Set<Integer> set = new HashSet<>(list);
        return set;
    }

    /***
     * addAll()
     * @return
     */
    public static Set<Integer> list2Set02(List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        set.addAll(list);
        return set;
    }


    public static void main(String[] args) {
        Set<Integer> set = List2Set.list2Set01(list);
        System.out.println(set);

    }

}
