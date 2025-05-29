package com.mumu.framework.studydemo.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import org.junit.Test;

/**
 * LockTest
 * @author liuzhen
 * @version 1.0.0 2025/5/28 15:47
 */
public class LockTest {
  /**
   * 基于对象使用锁
   */
  @Test
  public void test2() {
    TTT obj = new TTT();

    // 死锁测试，超过5秒发送死锁

    new Thread(() -> {
      try (InternalLock ignored = LockUtil.getLock(obj)) {
        System.out.println(Thread.currentThread() + ">>> olai olai ooo...");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(6));
      }
    }).start();

    new Thread(() -> {
      try (InternalLock ignored = LockUtil.getLock(obj)) {
        System.out.println(Thread.currentThread() + ">>> olai olai ooo...");
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(6));
      }
    }).start();

    LockSupport.park();
  }

  @Test
  public void test3() {
    System.out.println(System.identityHashCode(new TTT()));
    System.out.println(System.identityHashCode(new TTT()));
  }

  /**
   * 嵌套锁测试
   */
  @Test
  public void deadLockTest() {
    TTT o1 = new TTT();
    TTT o2 = new TTT();

    // 下面代码运行时会出现嵌套锁
    Thread t1 = new Thread(() -> {
      try (InternalLock ignored = LockUtil.getLock(o1)) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        try (InternalLock ignored2 = LockUtil.getLock(o2)) {
          System.out.println(Thread.currentThread() + "111111>>> olai olai ooo...");
        }
      }
    });

    Thread t2 = new Thread(() -> {
      try (InternalLock ignored = LockUtil.getLock(o2)) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
        try (InternalLock ignored2 = LockUtil.getLock(o1)) {
          System.out.println(Thread.currentThread() + "222222>>> olai olai ooo...");
        }
      }
    });
    t1.start();
    t2.start();

    LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
    System.out.println("t1状态：" + t1.getState());
    System.out.println("t2状态：" + t2.getState());
  }

  public static void main(String[] args) {
    // try (InternalLock ignored = LockUtil.getLock(new Object())) {
    //   System.out.println("1111");
    // }

    InternalLock ignored = LockUtil.getLock(new Object());
    System.out.println("1111");

    System.out.println("2222");
  }

  private static class TTT {

    @Override
    public int hashCode() {
      return 123456;
    }
  }
}
