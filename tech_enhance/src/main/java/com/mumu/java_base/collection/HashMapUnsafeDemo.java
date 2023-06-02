/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * HashMapUnsafeDemo HashMap不安全Demo
 * 
 * @author liuzhen
 * @version 1.0.0 2022/7/23 21:10
 */
public class HashMapUnsafeDemo {

    /**
     * 为什么HashMap 是非线程安全的？
     * 1. 扩容期间取出的值不准确。代码 dream_plan下的 `HashMapUnsafeDemo`
     *    HashMap 本身默认的容量不是很大，如果不停地往 map 中添加新的数据，它便会在合适的时机进行扩容。而在扩容期间，它会新建一个新的空数组，
     *    并且用旧的项填充到这个新的数组中去。那么，在这个填充的过程中，如果有线程获取值，很可能会取到 null 值，而不是我们所希望的、原来添加的值。
     * 2. 同时put数据碰撞导致数据丢失。
     *    比如，有多个线程同时使用 put 来添加元素，而且恰好两个 put 的 key 是一样的，它们发生了碰撞，也就是根据 hash 值计算出来的 bucket 位置一样，
     *    并且两个线程又同时判断该位置是空的，可以写入，所以这两个线程的两个不同的 value 便会添加到数组的同一个位置，这样最终就只会保留一个数据，丢失一个数据。
     * 3. 可见性问题无法保证。
     *    可见性也是线程安全的一部分，如果某一个数据结构声称自己是线程安全的，那么它同样需要保证可见性，也就是说，当一个线程操作这个容器的时候，
     *    该操作需要对另外的线程都可见，也就是其他线程都能感知到本次操作。可是 HashMap 对此是做不到的，<u>如果线程1 给某个 key 放入了一个新值，
     *    那么线程2 在获取对应的 key 的值的时候，它的可见性是无法保证的</u>，也就是说线程 2 可能可以看到这一次的更改，但也有可能看不到。
     *    所以从可见性的角度出发，HashMap 同样是线程非安全的。
     * 4. 死循环造成CPU100%。
     *    下面我们再举一个死循环造成 CPU 100% 的例子。HashMap 有可能会发生死循环并且造成 CPU 100% ，这种情况发生最主要的原因就是在扩容的时候，
     *    也就是内部新建新的 HashMap 的时候，扩容的逻辑会反转散列桶中的节点顺序，当有多个线程同时进行扩容的时候，由于 HashMap 并非线程安全的，
     *    所以如果两个线程同时反转的话，便可能形成一个循环，并且这种循环是链表的循环，相当于 A 节点指向 B 节点，B 节点又指回到 A 节点，
     *    这样一来，在下一次想要获取该 key 所对应的 value 的时候，便会在遍历链表的时候发生永远无法遍历结束的情况，也就发生 CPU 100% 的情况。
     * 总结
     * 综上所述，HashMap 是线程不安全的，在多线程使用场景中如果需要使用 Map，应该尽量避免使用线程不安全的 HashMap。
     * 同时，虽然 `Collections.synchronizedMap(new HashMap())` 是线程安全的，但是效率低下，因为内部用了很多的 synchronized，多个线程不能同时操作。
     * 推荐使用线程安全同时性能比较好的 `ConcurrentHashMap`。
     */

    /**
     * 证明 HashMap 是非线程安全的。
     * HashMap 本身默认的容量不是很大，如果不停地往 map 中添加新的数据，它便会在合适的时机进行扩容。
     * 而在扩容期间，它会新建一个新的空数组，并且用旧的项填充到这个新的数组中去。
     * 那么，在这个填充的过程中，如果有线程获取值，很可能会取到 null 值，而不是我们所希望的、原来添加的值。如下演示：
     * @date 2022/7/23 21:14
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        final Map<Integer, String> map = new HashMap<>();
        final Integer targetKey = 0b1111_1111_1111_1111; // 65535
        final String targetValue = "v";
        map.put(targetKey, targetValue);
        new Thread(() -> {
            IntStream.range(0, targetKey).forEach(key -> {
                map.put(key, "someValue");
            });
        }).start();

        while (true) {
            if (null == map.get(targetKey)) {
                throw new RuntimeException("HashMap is not thread safe.");
            }
        }
    }

}
