package com.mumu.java_tools.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * ImmutableMapDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/21 11:26
 */
public class ImmutableMapDemo {

    

    /**
     * 创建 ImmutableMap 对象后，不可在修改值test
     * @return void
     * @date 2024/5/21 11:40
     */
    @Test
    public void unChangeTest() {
        ImmutableMap<Integer, String> map = ImmutableMap.of();
        map.put(1, "1");
        
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * ImmutableMap 是 Google 的 Guava 库提供的一个不可变的 Map 实现。
     * 好处：
     *  1. 线程安全: 由于不可变对象在创建之后不会改变，因此它们是天然线程安全的，可以在多个线程之间安全共享，而不需要同步。
     *  2. 性能优化: 不可变对象可以进行许多优化，例如共享内部数据结构，减少内存占用。
     *  3. 简化代码: 不可变对象简化了代码，因为你不需要担心对象被意外修改。
     * 特性:
     *  不可变性: ImmutableMap 一旦创建就不能再修改，任何修改操作（如 put, remove）都会抛出 UnsupportedOperationException。
     *  高效: ImmutableMap 在内部进行了许多优化，可以高效地存储和访问数据。
     *  安全: 因为不可变，所以可以安全地在多个线程中共享同一个实例。
     * 使用场景:
     *  常量数据: 当你有一组常量数据时，使用 ImmutableMap 可以确保这些数据不会被修改。
     *  缓存: 在缓存中使用 ImmutableMap 可以避免缓存数据被意外修改。
     *  多线程环境: 在多线程环境中使用 ImmutableMap 可以避免同步问题。
     *
     * 创建方式:
     *  1. 使用 Builder: ImmutableMap.Builder<K, V> builder = ImmutableMap.<K, V>builder();
     *      builder.put();
     *      builder.putAll();
     *      ImmutableMap<K, V> build()
     *
     *  2. 使用 of() : ImmutableMap<String, Integer> map = ImmutableMap.of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5);
     *
     *  3. 使用 copyOf(map) : Map<K, V> map = new HashMap<>();  ImmutableMap<String, Integer> immutableMap = ImmutableMap.copyOf(map);
     *
     * @return void
     * @date 2024/5/21 11:40
     */
    @Test
    public void immutableMapTest() {
        // 1. 使用 Builder
        ImmutableMap<String, Integer> immutableMap1 = ImmutableMap.<String, Integer>builder()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3)
                .build();

        // 2. 使用 of 方法
        ImmutableMap<String, Integer> immutableMap2 = ImmutableMap.of(
                "one", 1,
                "two", 2,
                "three", 3
        );

        // 3. 从现有的 Map 创建
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        ImmutableMap<String, Integer> immutableMap3 = ImmutableMap.copyOf(map);

        System.out.println(immutableMap1);
        System.out.println(immutableMap2);
        System.out.println(immutableMap3);
    }

    /**
     * ImmutableMultimap 不可变的 Multimap 实现，允许一个键对应多个值。
     * @return void
     * @date 2024/5/21 14:59
     */
    @Test
    public void immutableMultimapTest() {
        // 1. 使用 Builder
        ImmutableMultimap<String, Integer> multimap1 = ImmutableMultimap.<String, Integer>builder()
                .put("one", 1)
                .put("one", 11)
                .put("two", 2)
                .put("three", 3)
                .build();

        // 2. 从现有的 Multimap 创建
        Multimap<String, Integer> mutableMultimap = ArrayListMultimap.create();
        mutableMultimap.put("one", 1);
        mutableMultimap.put("one", 11);
        mutableMultimap.put("two", 2);
        mutableMultimap.put("three", 3);
        ImmutableMultimap<String, Integer> multimap2 = ImmutableMultimap.copyOf(mutableMultimap);

        System.out.println(multimap1);
        System.out.println(multimap2);
    }

    /**
     * ImmutableSortedMap 不可变的排序 Map 实现。
     * @return void
     * @date 2024/5/21 14:59
     */
    @Test
    public void immutableSortedMapTest() {
        // 1. 使用 Builder
        ImmutableSortedMap<String, Integer> sortedMap1 = ImmutableSortedMap.<String, Integer>naturalOrder()
                .put("one", 1)
                .put("two", 2)
                .put("three", 3)
                .build();

        // 2. 使用 of 方法
        ImmutableSortedMap<String, Integer> sortedMap2 = ImmutableSortedMap.of(
                "one", 1,
                "two", 2,
                "three", 3
        );

        // 3. 从现有的 SortedMap 创建
        SortedMap<String, Integer> mutableSortedMap = new TreeMap<>();
        mutableSortedMap.put("one", 1);
        mutableSortedMap.put("two", 2);
        mutableSortedMap.put("three", 3);
        ImmutableSortedMap<String, Integer> sortedMap3 = ImmutableSortedMap.copyOf(mutableSortedMap);

        System.out.println(sortedMap1);
        System.out.println(sortedMap2);
        System.out.println(sortedMap3);
    }
}
