/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection.list;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * IntArray2List
 * int数组转list
 * @author liuzhen
 * @version 1.0.0 2023/7/22 16:42
 */
public class IntArray2List {

    /**
     * int[] 转 list
     * @date 2023/7/22 16:43
     * @param
     * @return void
     */
    @Test
    public void intArray2ListTest(){
		int[] array = { 1, 2, 5, 5, 5, 5, 6, 6, 7, 2, 9, 2 };

        // 方法一：需要导入apache commons-lang3 jar
        List<Integer> list = Arrays.asList(ArrayUtils.toObject(array));
        System.out.println(list.toString());

        // 方法二：java8及以上版本
        List<Integer> list1 = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println(list1.toString());

        // 方法三：for循环
        List<Integer> list3 = new ArrayList<>();
        for (int i : array) {
            list3.add(i);
        }
    }

    /**
     * int[] 转 Integer[]
     * @date 2023/8/31 17:15
     * @param
     * @return void
     */
    @Test
    public void intArray2IntegertArrayTest(){
        int[] intArray = {1, 2, 5, 5, 5, 5, 6, 6, 7, 2, 9, 2};

        // int[] 转 Integer[]
        Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(integerArray));
    }

    @Test
    public void list2IntArrayTest(){
        List<Integer> list = List.of(1, 2, 3);

        // 方法二：java8及以上版本(需要转为int[]的请用这种)
        int[] intArray =  list.stream().mapToInt(Integer::valueOf).toArray();
        System.out.println(Arrays.toString(intArray));
    }

    @Test
    public void list2IntegerArrayTest(){
        List<Integer> list = List.of(1, 2, 3);

        Integer[] integerArr = list.toArray(new Integer[0]);
        // Integer[] integerArr =  list.toArray(new Integer[list.size()]);
    }

}
