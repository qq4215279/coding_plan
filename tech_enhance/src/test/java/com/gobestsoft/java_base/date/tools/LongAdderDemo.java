package com.gobestsoft.java_base.date.tools;

import org.junit.Test;

/**
 * LongAdderDemo
 * LongAdder 使用
 * JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观锁的重试次数）。
 * @author liuzhen
 * @version 1.0.0 2022/6/22 17:58
 */
public class LongAdderDemo {

    /**
     * volatile 解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但
     * 是如果多写，同样无法解决线程安全问题。
     * 说明：如果是 count++操作，使用如下类实现：AtomicInteger count = new AtomicInteger();
     * count.addAndGet(1); 如果是 JDK8，推荐使用 LongAdder 对象，比 AtomicLong 性能更好（减少乐观
     * 锁的重试次数）。
     */
    @Test
    public void test01() {

    }

}
