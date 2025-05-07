package com.mumu.java_base.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * FixedSizeFifoList
 * 固定大小并且遵循先进先出（FIFO）容器
 * @author liuzhen
 * @version 1.0.0 2025/3/14 13:54
 */
public class LRUArrayDeque<E> {
  private final int maxSize;
  private final Deque<E> deque;

  public LRUArrayDeque(int maxSize) {
    this.maxSize = maxSize;
    this.deque = new ArrayDeque<>(maxSize);
  }

  public void add(E element) {
    if (deque.size() >= maxSize) {
      deque.poll(); // 移除队列头部的元素，即最老的元素
    }
    deque.offer(element);
  }

  public E get(int index) {
    if (index < 0 || index >= deque.size()) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + deque.size());
    }
    return (E) deque.toArray()[index];
  }

  public int size() {
    return deque.size();
  }

  @Override
  public String toString() {
    return deque.toString();
  }

  public static void main(String[] args) {
    LRUArrayDeque<String> fifoList = new LRUArrayDeque<>(3);

    fifoList.add("One");
    fifoList.add("Two");
    fifoList.add("Three");

    System.out.println(fifoList); // [One, Two, Three]

    fifoList.add("Four");
    System.out.println(fifoList); // [Two, Three, Four]

    fifoList.add("Five");
    System.out.println(fifoList); // [Three, Four, Five]
  }

}
