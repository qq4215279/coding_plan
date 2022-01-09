/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.jdk_api.util;

import org.junit.Test;

import java.util.*;

/**
 * CollectionsTest
 * Collections 静态类常用api
 * @author liuzhen
 * @version 1.0.0 2022/1/9 22:17
 */
public class CollectionsTest {

    /**
     * addAll(Collection<T> c, T... elements):往集合中添加一些元素。
     * binarySearch(List<? extends Comparable<? super T>> list, T key) 使用二进制搜索算法在指定列表中搜索指定对象。 注：前提集合是有序的！
     * copy(List<? super T> dest, List<? extends T> src) 将一个列表中的所有元素复制到另一个列表中。目标列表的大小必须大于或等于源列表的大小。如果它更大，则目标列表中的其余元素不受影响。
     * disjoint​(Collection<?> c1, Collection<?> c2) 如果两个指定的集合没有共同的元素，则返回 true 。
     *
     * emptyEnumeration() 返回没有元素的枚举。
     * emptyIterator() 返回没有元素的迭代器。
     * emptyList() 返回一个空列表（不可变）。
     * emptySet() 返回一个空集（不可变）。
     * emptyMap() 返回一个空映射（不可变）。
     *
     * max(Collection<? extends T> coll) 根据元素的自然顺序返回给定集合的最大元素。
     * max(Collection<? extends T> coll, Comparator<? super T> comp) 根据指定比较器引发的顺序返回给定集合的最大元素。
     * min(Collection<? extends T> coll) 根据元素的自然顺序返回给定集合的最小元素。
     * min(Collection<? extends T> coll, Comparator<? super T> comp) 根据指定比较器引发的顺序返回给定集合的最小元素。
     *
     * fill(List<? super T> list, T obj) 用指定的元素替换指定列表的所有元素。
     * replaceAll(List<T> list, T oldVal, T newVal) 用列表替换列表中所有出现的指定值。
     * reverse(List<?> list) 反转指定列表中元素的顺序。
     * reverseOrder() 返回一个比较器， Comparable实现 Comparable接口的对象集合强制执行自然排序的 Comparable 。
     *
     * shuffle(List<?> list) 打乱顺序，打乱集合顺序。
     * sort(List<T> list) 将集合中元素按照默认规则排序。
     * sort(List<T> list，Comparator<? super T> ) 将集合中元素按照指定规则排序。
     * swap(List<?> list, int i, int j) 交换指定列表中指定位置的元素。
     *
     */
    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>();

        // 1. addAll(Collection<T> c, T... elements)
        Integer[] arr = {1, 2, 4, 88};
        // Collections.addAll(list, 1, 2, 4);
        Collections.addAll(list, arr);
        System.out.println(list.toString());

        // 2. binarySearch(List<? extends Comparable<? super T>> list, T key)   前提有序集合！！
        int index = Collections.binarySearch(list, 4);
        System.out.println("index: " + index); // 2

        /*
        * 错误的方式打印出来的 copyList.size() == 0 ！！！可是我们明明将其设置为了100！Why?
        * 通过仔细观察，发现100并不是指的是newList的size,而是其容量（打个比方，你有一个桶，它的容量很大，但是它是空的！！！）。
        * 修改如下：List<Integer> copyList = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
        */
        // List<Integer> copyList = new ArrayList<>(100);
        List<Integer> copyList = new ArrayList<>(Arrays.asList(new Integer[list.size()]));
        Collections.copy(copyList, list);
        System.out.println(copyList.toString()); // [1, 2, 4, 88]

        // 3. disjoint​(Collection<?> c1, Collection<?> c2)
        List<Integer> list1 = new ArrayList<>();
        Collections.addAll(list1, -1, 999);
        boolean disjoint = Collections.disjoint(list, list1);
        System.out.println("disjoint: " + disjoint);

        // 4. emptyXXX()
        List<Object> emptyList = Collections.emptyList();
        System.out.println("空集合对象："+ emptyList.toString());

        // 5. max(Collection<? extends T> coll) / min(Collection<? extends T> coll, Comparator<? super T> comp)
        list.add(-1);
        Integer max = Collections.max(list);
        System.out.println("max: " + max); // 88
        Integer min = Collections.min(list);
        System.out.println("min: " + min); // -1

        // 6. fill(List<? super T> list, T obj)
        System.out.println("fill 前：" + list1);
        Collections.fill(list1, 10000);
        System.out.println("fill 后：" + list1);

        // 7. replaceAll(List<T> list, T oldVal, T newVal)
        Collections.replaceAll(list, -1, 999);
        System.out.println(list.toString()); // [1, 2, 4, 88, 999]

        // 8. reverse(List<?> list) 反转
        Collections.shuffle(list);
        System.out.println("反转前：" + list.toString()); // [2, 999, 88, 4, 1]
        Collections.reverse(list);
        System.out.println("反转后：" + list.toString()); // [1, 4, 88, 999, 2]

        // 9. reverseOrder()
        Comparator<Integer> comparator = Collections.reverseOrder();

        // 10. shuffle(List<?> list) 打乱顺序
        Collections.shuffle(list);

        // 11. sort(List<T> list)
        System.out.println("排序前：" + list.toString()); // [2, 999, 4, 88, 1]
        Collections.sort(list);
        System.out.println("排序后：" + list.toString()); // [1, 2, 4, 88, 999]

        // 12. swap(List<?> list, int i, int j)
        Collections.swap(list, 0, list.size() - 1);
        System.out.println("swap 交换元素后：" + list); // [999, 2, 4, 88, 1]

    }

}
