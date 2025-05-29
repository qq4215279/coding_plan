package com.mumu.framework.studydemo.lock;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockUtil
 * jvm锁工具类
 * 作用：
 *  1. 利用try-with-resource机制来unlock，替换使用synchronized进行加锁，避免synchronized嵌套使用导致游戏死锁，无法释放锁。
 *  2. 支持打印死锁堆栈日志
 * 使用：
 *      try (InternalLock ignored = LockUtil.getLock(LockConstants.TURNTABLE_DRAW_LOCK // 传入指定锁对象)) {
 *          // 加锁代码块
 *       }
 *
 * @author liuzhen
 * @version 1.0.0 2025/5/28 15:48
 */
public class LockUtil {
  /** 默认锁 */
  private static final InternalLock DEFAULT_LOCK = new InternalLock();

  /** 锁对象缓存，仅1min */
  private static final LoadingCache<Object, InternalLock> LOCK_CACHE = CacheBuilder.newBuilder()
      .expireAfterAccess(1, TimeUnit.MINUTES)
      .build(new CacheLoader<>() {
        @Override
        public InternalLock load(Object key) {
          return new InternalLock();
        }
      });

  /**
   * 根据对象获取锁
   * 对象相同即是同一把锁
   * @param lockTarget lockTarget
   * @return com.game.framework.core.lock.InternalLock
   * @date 2025/5/28 16:50
   */
  public static InternalLock getLock(Object lockTarget) {
    Preconditions.checkNotNull(lockTarget, "获取锁不允许null");
    Preconditions.checkArgument(!lockTarget.getClass().isPrimitive(),"对象不允许是primitive");

    try {
      InternalLock lock = LOCK_CACHE.get(lockTarget);
      lock.tryLock();
      return lock;
    } catch (Exception e) {
      Throwables.throwIfUnchecked(e);
    }

    return DEFAULT_LOCK;
  }

  public static void main(String[] args) {
    // demo
    useDemo();

    // 2. 超时未获取锁测试
    // overTimeDeadLockTest();

    // 3. 死锁测试
    // deadLockTest();
  }

  /** 使用实例 */
  private static void useDemo() {
    try (InternalLock ignored = LockUtil.getLock(LockConstants.TURNTABLE_DRAW_LOCK)) {
      System.out.println(Thread.currentThread() + " 1111");
    }

    System.out.println(Thread.currentThread() + " 2222");
  }

  /**
   * 超时死锁测试
   * @return void
   * @date 2025/5/28 17:45
   */
  private static void overTimeDeadLockTest() {
    // 死锁测试，超过5秒发送死锁
    Object obj = new Object();
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

  private static void deadLockTest() {
    Object o1 = new Object();
    Object o2 = new Object();

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
}
