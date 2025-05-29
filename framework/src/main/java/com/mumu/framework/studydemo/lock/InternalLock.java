package com.mumu.framework.studydemo.lock;

import com.google.common.base.Joiner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * InternalLock
 * 锁
 * @author liuzhen
 * @version 1.0.0 2025/5/28 15:48
 */
public class InternalLock implements AutoCloseable {
  /** 锁 */
  private final Lock lock = new ReentrantLock();
  /** 当前持有锁的线程 */
  private Thread holdThread;

  /**
   * 获取锁
   * @date 2025/5/28 17:00
   */
  public void tryLock() {
    boolean success = false;
    try {
      success = lock.tryLock(LockConstants.LOCK_TIME_OUT, TimeUnit.SECONDS);
    } catch (InterruptedException ignored) {
    }

    if (success) {
      holdThread = Thread.currentThread();
    } else {
      // 堆栈信息
      String currentStack = getThreadStackTrace(Thread.currentThread());
      String holdStack = getThreadStackTrace(holdThread);

      // LogTopic.ACTION.error(LogAction.INTERNAL_LOCK, " =========== 获取InternalLock超时，可能发生死锁=========== ",
      //     "【当前线程】堆栈：", Thread.currentThread(), " ", currentStack, " 【持有锁线程】堆栈：", holdStack, " ", holdStack);
      throw new RuntimeException("获取InternalLock超时，可能发生死锁");
    }
  }

  /** 获取堆栈信息 */
  private static String getThreadStackTrace(Thread thread) {
    return Joiner.on("\n").join(thread.getStackTrace());
  }

  @Override
  public void close() {
    holdThread = null;
    lock.unlock();
  }
}
