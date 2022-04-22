/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c17_bloom_filter_lru_cache;

import java.util.BitSet;
import java.util.Random;
import java.util.Iterator;

/**
 * BloomFilter
 * 布隆过滤器 Java实现
 * @author liuzhen
 * @version 1.0.0 2022/04/22 21:41
 */
public class BloomFilter implements Cloneable {

    /**
     * 布隆过滤器学习连接：
     * 1. 布隆过滤器的原理及实现: https://www.cnblogs.com/cpselvis/p/6265825.html
     * 2. 使用BloomFilter布隆过滤器解决缓存击穿、垃圾邮件识别、集合判重: https://blog.csdn.net/tianyaleixiaowu/article/details/74721877
     * 3. Orestes-Bloomfilter: https://github.com/Baqend/Orestes-Bloomfilter
     */

    private BitSet hashes;
    private RandomInRange prng;
    /** Number of hash functions */
    private int k;
    /** ln(2) */
    private static final double LN2 = 0.6931471805599453;

    /**
     * Create a bloom filter of 1Mib.
     *
     * @param n Expected number of elements
     **/
    public BloomFilter(int n) {
        this(n, 1024 * 1024 * 8);
    }

    /**
     * Create a new bloom filter.
     *
     * @param n Expected number of elements
     * @param m Desired size of the container in bits
     **/
    public BloomFilter(int n, int m) {
        k = (int)Math.round(LN2 * m / n);
        if (k <= 0) {
            k = 1;
        }
        this.hashes = new BitSet(m);
        this.prng = new RandomInRange(m, k);
    }

    /**
     * 向容器中添加元素
     * @author liuzhen
     * @date 2022/4/22 22:18
     * @param o
     * @return void
     */
    public void add(Object o) {
        prng.init(o);
        for (RandomInRange r : prng) {
            hashes.set(r.value);
        }
    }

    /**
     * 判断元素是否包含在容器中：
     * 1. 如果在容器中，返回true，那么可能存在改元素，probability ≈ e^(-ln(2)² * m/n)
     * 2. 如果不在容器红，返回falses，那么容器中一定不存在改元素。
     * @author liuzhen
     * @date 2022/4/22 22:18
     * @param o
     * @return boolean
     */
    public boolean contains(Object o) {
        prng.init(o);
        for (RandomInRange r : prng) {
            if (!hashes.get(r.value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除容器中的所有元素
     * @author liuzhen
     * @date 2022/4/22 22:21
     * @param
     * @return void
     */
    public void clear() {
        hashes.clear();
    }

    /**
     * Create a copy of the current filter
     **/
    @Override
    public BloomFilter clone() throws CloneNotSupportedException {
        return (BloomFilter)super.clone();
    }

    /**
     * Generate a unique hash representing the filter
     **/
    @Override
    public int hashCode() {
        return hashes.hashCode() ^ k;
    }

    /**
     * Test if the filters have equal bitsets. WARNING: two filters may contain the same elements, but not be equal (if
     * the filters have different size for example).
     */
    public boolean equals(BloomFilter other) {
        return this.hashes.equals(other.hashes) && this.k == other.k;
    }

    /**
     * Merge another bloom filter into the current one. After this operation, the current bloom filter contains all
     * elements in other.
     **/
    public void merge(BloomFilter other) {
        if (other.k != this.k || other.hashes.size() != this.hashes.size()) {
            throw new IllegalArgumentException("Incompatible bloom filters");
        }
        this.hashes.or(other.hashes);
    }

    /**
     *
     */
    private class RandomInRange implements Iterable<RandomInRange>, Iterator<RandomInRange> {
        private Random prng;
        /** Maximum value returned + 1 */
        private int max;
        /** Number of random elements to generate */
        private int count;
        /** Number of elements generated */
        private int i = 0;
        /** The current value */
        public int value;

        RandomInRange(int maximum, int k) {
            max = maximum;
            count = k;
            prng = new Random();
        }

        public void init(Object o) {
            prng.setSeed(o.hashCode());
        }

        @Override
        public Iterator<RandomInRange> iterator() {
            i = 0;
            return this;
        }

        @Override
        public RandomInRange next() {
            i++;
            value = prng.nextInt() % max;
            if (value < 0) {
                value = -value;
            }
            return this;
        }

        @Override
        public boolean hasNext() {
            return i < count;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}