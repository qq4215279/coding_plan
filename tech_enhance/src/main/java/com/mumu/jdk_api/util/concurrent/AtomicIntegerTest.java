package com.mumu.jdk_api.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicIntegerTest
 * @author liuzhen
 * @version 1.0.0 2024/12/24 15:14
 */
public class AtomicIntegerTest {
  /**
   * AtomicInteger 是 Java 中的一种原子性整数类，位于 java.util.concurrent.atomic 包中。它提供了一种线程安全的方式来操作整数值，而不需要使用传统的同步机制（如 synchronized 关键字或显式锁），从而提高了并发性能。
   *
   * 主要特点
   * 原子性：AtomicInteger 的操作是原子的，意味着在多线程环境中，对其值的读写操作不会被其他线程干扰。例如，使用 incrementAndGet() 方法可以安全地对其值进行递增。
   * 无锁机制：与使用 synchronized 关键字的传统方式相比，AtomicInteger 使用乐观锁（CAS，Compare-And-Swap）算法来实现线程安全。这种方法可以减少上下文切换和锁竞争，从而提高性能。
   * 提供多种操作：AtomicInteger 提供了一系列方法，包括但不限于：
   *  get()：获取当前值。
   *  set(int newValue)：设置新值。
   *  incrementAndGet()：将当前值加一并返回新的值。
   *  decrementAndGet()：将当前值减一并返回新的值。
   *  addAndGet(int delta)：将当前值加上指定的增量并返回新的值。
   *  compareAndSet(int expect, int update)：如果当前值等于预期值，则将其设置为新的值，并返回 true；否则返回 false。
   * @date 2024/12/24 15:15
   */

  public static void main(String[] args) {
    // 创建 AtomicInteger 实例
    AtomicInteger atomicInt = new AtomicInteger(0);

    // 获取当前值
    System.out.println("Initial value: " + atomicInt.get());

    // 增加值
    int incrementedValue = atomicInt.incrementAndGet();
    System.out.println("After increment: " + incrementedValue);

    // 设置新值
    atomicInt.set(10);
    System.out.println("After set to 10: " + atomicInt.get());

    // 使用 compareAndSet 方法
    boolean wasUpdated = atomicInt.compareAndSet(10, 20);
    System.out.println("Was the value updated? " + wasUpdated);
    System.out.println("Current value: " + atomicInt.get());

    // 添加增量
    int newValue = atomicInt.addAndGet(5);
    System.out.println("After adding 5: " + newValue);
  }
}
