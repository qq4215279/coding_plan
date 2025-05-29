package com.mumu.framework.studydemo.lock;

/**
 * LockConstants
 * @author liuzhen
 * @version 1.0.0 2025/5/28 17:13
 */
public interface LockConstants {
  /** 获取锁的超时时间，单位：s */
  int LOCK_TIME_OUT = 5;

   /** 转盘抽奖 锁对象 */
   Object TURNTABLE_DRAW_LOCK = new Object();

}
