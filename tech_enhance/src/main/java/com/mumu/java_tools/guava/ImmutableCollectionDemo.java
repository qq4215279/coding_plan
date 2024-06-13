package com.mumu.java_tools.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * ImmutableCollectionDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/21 11:26
 */
public class ImmutableCollectionDemo {

    /**
     * ImmutableList 不可变的 List 实现。
     * @return void
     * @date 2024/5/21 14:57
     */
    @Test
    public void immutableListTest() {
        // 1. 使用 Builder
        ImmutableList<String> list1 = ImmutableList.<String>builder()
                .add("one")
                .add("two")
                .add("three")
                .build();

        // 2. 使用 of 方法
        ImmutableList<String> list2 = ImmutableList.of("one", "two", "three");

        // 3. 从现有的 List 创建
        List<String> mutableList = new ArrayList<>();
        mutableList.add("one");
        mutableList.add("two");
        mutableList.add("three");
        ImmutableList<String> list3 = ImmutableList.copyOf(mutableList);

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
    }

    /**
     * ImmutableSet 不可变的 Set 实现。
     * @return void
     * @date 2024/5/21 14:58
     */
    @Test
    public void immutableSetTest() {
        // 1. 使用 Builder
        ImmutableSet<String> set1 = ImmutableSet.<String>builder()
                .add("one")
                .add("two")
                .add("three")
                .build();

        // 2. 使用 of 方法
        ImmutableSet<String> set2 = ImmutableSet.of("one", "two", "three");

        // 3. 从现有的 Set 创建
        Set<String> mutableSet = new HashSet<>();
        mutableSet.add("one");
        mutableSet.add("two");
        mutableSet.add("three");
        ImmutableSet<String> set3 = ImmutableSet.copyOf(mutableSet);

        System.out.println(set1);
        System.out.println(set2);
        System.out.println(set3);
    }

    /**
     * ImmutableSortedSet 不可变的排序 Set 实现。
     * @return void
     * @date 2024/5/21 14:59
     */
    @Test
    public void immutableSortedSetTest() {
        // 1. 使用 Builder
        ImmutableSortedSet<String> sortedSet1 = ImmutableSortedSet.<String>naturalOrder()
                .add("one")
                .add("two")
                .add("three")
                .build();

        // 2. 使用 of 方法
        ImmutableSortedSet<String> sortedSet2 = ImmutableSortedSet.of("one", "two", "three");

        // 3. 从现有的 SortedSet 创建
        SortedSet<String> mutableSortedSet = new TreeSet<>();
        mutableSortedSet.add("one");
        mutableSortedSet.add("two");
        mutableSortedSet.add("three");
        ImmutableSortedSet<String> sortedSet3 = ImmutableSortedSet.copyOf(mutableSortedSet);

        System.out.println(sortedSet1);
        System.out.println(sortedSet2);
        System.out.println(sortedSet3);
    }



}
