/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection;

import org.junit.Test;

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
	private int[] intArr = new int[] { 3, 5, 7 };

	private String[] strArr = { "a", "b", "c" };

    private List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3));

    /**
     * 原生：for循环
     * @return
     */
    public List<Integer> array2List01() {
        List<Integer> list = new ArrayList<>();

        for (int i : intArr) {
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
    @Test
    public List<String> array2List02() {
		// arr数组是int类型，不是Integer，数组的类型不能是基本数据类型，所以不能用这种方法转List
		// List<Integer> intList = new ArrayList<>(Arrays.asList(intArr));
		List<String> list = new ArrayList<>(Arrays.asList(strArr));

		/*
		 * 调用add() 方法没有报错？？？？ 解释：因为Arrays.asList(arr2)返回的Arrays内部类ArrayList集合，已经被new
		 * ArrayList重新生成一个java.util包下的ArrayList集合了
		 */
        list.add("不报错？？？不报错？？？不报错？？？");

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
        // 同上：arr数组是int类型，不是Integer，数组的类型不能是基本数据类型，所以不能用这种方法转List
//        List<Integer> intList = new ArrayList<>(arr.length);
//        Collections.addAll(intList, arr);

        List<String> list = new ArrayList<>(strArr.length);
        Collections.addAll(list, strArr);

        return list;
    }

    /**
     * 使用List.of()
     * 此方法为 Java9新增方法，定义在List接口内，并且为静态方法，故可以由类名直接调用。
     * @return
     */
    public List<String> array2List04() {
//        List<int[]> intList = List.of(this.arr);

        // 仅仅适用于JDK9及以上
        List<String> resultList = List.of(strArr);

        return resultList;
    }

    // --------------------------------------------------------------------------------------------------------------->

    /**
     * List转数组1
     * 使用：toArray()
     * @return
     */
    public int[] list2Array01() {
        Integer[] integerArr = integerList.toArray(new Integer[integerList.size()]);
        int[] arr = new int[integerArr.length];

        for (int i = 0; i < integerArr.length; i++) {
            arr[i] = integerArr[i].intValue();
        }

        return arr;
    }

    /**
     * List转数组2
     * 使用stream()
     * @return
     */
    public int[] list2Array02() {
        int[] arr = integerList.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }

    public static void main(String[] args) {
        Array2List obj = new Array2List();

        List<String> list = obj.array2List02();
        for (String s : list) {
            System.out.println("---->" + s);
        }
    }

    @Test
    public void test() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.trimToSize();

        List<Integer> list = new ArrayList<>();
//        list.trimToSize(); // 没有该方法
    }


}
