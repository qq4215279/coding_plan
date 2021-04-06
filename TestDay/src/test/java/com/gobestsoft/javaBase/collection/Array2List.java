/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.collection;

import java.util.*;

/**
 * Array2List
 * 数组转List
 * @author liuzhen
 * @version 1.0.0 2021/3/21 15:28
 */
public class Array2List {

    /**
     * 方式1：int[] arr = new int[3];
     * 方式2：int[] arr = new int[3]{3, 5, 7};
     * 方式2：int[] arr = {3, 5, 7};
     */
    private int[] arr = new int[]{3, 5, 7};

    private String[] arr2 = {"a", "b", "c"};

    private List<Integer> list = Arrays.asList(1, 2, 3);

    /**
     * 原生：for循环
     * @return
     */
    public List<Integer> array2List01() {
        List<Integer> list = new ArrayList<>();

        for (int i : arr) {
            list.add(i);
        }

        return list;
    }

    /**
     * 使用Arrays.asList()
     * 注意：调用Arrays.asList()时，其返回值类型是ArrayList，但此ArrayList是Array的内部类，调用add()时，
     * 会报错：java.lang.UnsupportedOperationException，并且结果会因为array的某个值的改变而改变，故需要再次构造一个新的ArrayList。
     * @return
     */
    public List<String> array2List02() {
        List<String> list = new ArrayList<>(Arrays.asList(arr2));

        // TODO 调用add() 方法没有报错？？？？
        list.add("不报错？？？不报错？？？不报错？？？");
//        try {
//            list.add("d");
//        } catch (UnsupportedOperationException e) {
//            e.printStackTrace();
//        }

        List<String> noErrorList = new ArrayList<>(list);
        try {
            noErrorList.add("eee");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 使用Collections.addAll()
     * @return
     */
    public List<String> array2List03() {
        List<String> list = new ArrayList<>(arr2.length);

        Collections.addAll(list, arr2);

        return list;
    }

    /**
     * 使用List.of()
     * 此方法为 Java9新增方法，定义在List接口内，并且为静态方法，故可以由类名直接调用。
     * @return
     */
    public List<String> array2List04() {
        List<String> list = new ArrayList<>(arr2.length);

        // 仅仅适用于JDK9及以上
//        List<String> resultList = List.of(array);

        return list;
    }

    // -------------------------------------->

    /**
     * List转数组1
     * 使用：toArray()
     * @return
     */
    public int[] list2Array01() {
        Integer[] integers = list.toArray(new Integer[list.size()]);
        int[] arr = new int[integers.length];

        for (int i = 0; i < integers.length; i++) {
            arr[i] = integers[i].intValue();
        }

        return arr;
    }

    /**
     * List转数组2
     * 使用stream()
     * @return
     */
    public int[] list2Array02() {
        int[] arr = list.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }

    public static void main(String[] args) {
        Array2List obj = new Array2List();

        List<String> list = obj.array2List02();
        for (String s : list) {
            System.out.println("---->" + s);
        }
    }


}
