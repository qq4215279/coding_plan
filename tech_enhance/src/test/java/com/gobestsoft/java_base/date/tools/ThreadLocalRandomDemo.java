package com.gobestsoft.java_base.date.tools;

import org.junit.Test;

/**
 * ThreadLocalRandomDemo
 * ThreadLocalRandom 使用
 * @author liuzhen
 * @version 1.0.0 2022/6/22 17:48
 */
public class ThreadLocalRandomDemo {

    /**
     * 避免 Random 实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一 seed导致的性能下降。
     * 说明：Random 实例包括 java.util.Random 的实例或者 Math.random()的方式。
     * 正例：在 JDK7 之后，可以直接使用 API ThreadLocalRandom，而在 JDK7 之前，需要编码保证每个线程持有一个单独的 Random 实例。
     */
    @Test
    public void test01() {

    }

}
