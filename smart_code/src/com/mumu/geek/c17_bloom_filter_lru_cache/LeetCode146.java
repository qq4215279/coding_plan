/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c17_bloom_filter_lru_cache;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LeetCode146 LRU 缓存机制
 * @author liuzhen
 * @version 1.0.0 2021/8/27 21:41
 */
public class LeetCode146 {

    /**
     * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
     * 实现 LRUCache 类：
     * 1. LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
     * 2.int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * 3. void put(int key, intvalue) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
     * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
     * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。  
     *
     * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？
     *
     * 示例：
     * 输入
     * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
     * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
     * 输出
     * [null, null, null, 1, null, -1, null, -1, 3, 4]
     *
     * 解释
     * LRUCache lRUCache = new LRUCache(2);
     * lRUCache.put(1, 1); // 缓存是 {1=1}
     * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
     * lRUCache.get(1);    // 返回 1
     * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
     * lRUCache.get(2);    // 返回 -1 (未找到)
     * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
     * lRUCache.get(1);    // 返回 -1 (未找到)
     * lRUCache.get(3);    // 返回 3
     * lRUCache.get(4);    // 返回 4
     *
     * 提示：
     * 1 <= capacity <= 3000
     * 0 <= key <= 10000
     * 0 <= value <= 105
     * 最多调用 2 * 105 次 get 和 put
     */

    /**
     * 方法1：哈希表 + 双向链表 算法： LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
     * 1.双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
     * 2. 哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
     *
     * 这样以来，我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表的头部，即可在 O(1) 的时间内完成 get 或者 put 操作。
     * 具体的方法如下：
     * 对于 get() 操作，首先判断 key是否存在：
     * 1. 如果 key 不存在，则返回 -1；
     * 2. 如果 key 存在，则 key 对应的节点是最近被使用的节点。通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
     *
     * 对于 put() 操作，首先判断 key 是否存在：
     * 1. 如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，并将 key 和该节点添加进哈希表中。
     * 然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
     * 2. 如果 key 存在，则与 get 操作类似，先通过哈希表定位，再将对应的节点的值更新为value，并将该节点移到双向链表的头部。
     *
     * 上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为 O(1)。
     * 而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
     *
     * 小贴士 在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻的节点是否存在。
     */
    public class LRUCache {
        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {}

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private DLinkedNode head;
        private DLinkedNode tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        /**
         * 和兴方法 -- put
         *
         * @author liuzhen
         * @date 2022/4/22 22:53
         * @param key
         * @param value
         * @return void
         */
        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;

                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        /**
         * 核心方法 -- get
         *
         * @author liuzhen
         * @date 2022/4/22 22:53
         * @param key
         * @return int
         */
        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位，再移到头部
            moveToHead(node);
            return node.value;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }

    /**
     * 方法2：使用语言自带的数据结构
     */
    public static class LRUCache2 {
        private Map<Integer, Integer> map;

        public LRUCache2(int capacity) {
            map = new LinkedCappedHashMap<>(capacity);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            return map.get(key);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }

        private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
            int maximumCapacity;

            LinkedCappedHashMap(int maximumCapacity) {
                super(16, 0.75f, true);
                this.maximumCapacity = maximumCapacity;
            }

            /**
             * 如果Map的尺寸大于设定的最大长度，返回true，再新加入对象时删除最老的对象
             * @author liuzhen
             * @date 2022/4/22 23:01
             * @param eldest 表示要从此LinkedHashMap中删除的最旧的条目或最近的条目。
             * @return boolean 当应从Map中删除最旧的条目时返回true，否则返回false。
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maximumCapacity;
            }
        }
    }

}
