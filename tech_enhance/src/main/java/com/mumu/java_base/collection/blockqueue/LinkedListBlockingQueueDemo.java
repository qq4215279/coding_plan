package com.mumu.java_base.collection.blockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.junit.Test;

/**
 * LinkedListBlockingQueueDemo
 * @author liuzhen
 * @version 1.0.0 2025/3/10 18:15
 */
public class LinkedListBlockingQueueDemo {

  @Test
  public void test() {
    BlockingQueue<Integer> events = new LinkedBlockingQueue<>();
    events.offer(1);
    events.offer(2);
    events.offer(3);
    events.offer(4);

    for (int i = 0; i < events.size(); i++) {
      try {
        Integer peek = events.peek();
        System.out.println(peek);


        // events.poll();
      } catch (Exception e) {
      }
    }

    System.out.println("------------------------------>");


    // 遍历元素
    for (Integer next : events) {
      if (next == 1) {
        events.remove(1);
      }
    }

    System.out.println("===========>");
    for (Integer next : events) {
      System.out.println(next);
    }

  }


}
