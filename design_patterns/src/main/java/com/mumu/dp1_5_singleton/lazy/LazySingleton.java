/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp1_5_singleton.lazy;

/**
 * LazySingleton
 * 懒汉单例实现
 * @author liuzhen
 * @version 1.0.0 2021/10/19 22:04
 */
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    /**
     * 懒加载实现方式1:
     * 优点：解决了线程安全问题
     * 缺点：每次调用getInstance()时都需要进行线程锁定判断，在多线程高并发访问环境中将导致系统性能大大降低。
     * @author liuzhen
     * @date 2021/10/19 22:08
     * @param
     * @return com.mumu.singleton.lazy.LazySingleton
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    /**
     * 如下错误实现单例：此方式实现单例会导致对象不唯一的情况。错误实例！！！！！
     * 原因如下：假如在某一瞬间线程A和线程B都在调用getInstance2()方法，此时instance对象为null值，均能通过“instance == null”的判断，由于实现了synchronized加锁机制，
     * 线程A进入synchronized锁定的代码。但当A执行完毕时线程B并不知道示例已经创建，将继续创建示例，导致产生多个示例对象。
     *
     * @author liuzhen
     * @date 2021/10/19 22:10
     * @param
     * @return com.mumu.singleton.lazy.LazySingleton
     */
    public static LazySingleton getInstance2() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                instance = new LazySingleton();
            }
        }
        return instance;
    }

    /**
     * 注：使用双重检查锁定来实现懒汉式单例时，需要使用valatile关键字！因为被valatile修饰的成员变量可以确保多个线程都能正确处理。
     * 由于volatile关键字会屏蔽Java虚拟机所做的一些代码优化，可能会导致系统的运行效率降低，因此即使使用双重检查锁定来实现单例模式也不是一种完美的实现方式。
     * @author liuzhen
     * @date 2021/10/19 22:22
     * @param null
     * @return
     */
    private static volatile LazySingleton instance3 = null;
    public static LazySingleton getInstance3() {
        // 第一重判断
        if (instance3 == null) {
            // 锁住代码块
            synchronized (LazySingleton.class) {
               // 第二重判断
                if (instance3 == null) {
                    instance3 = new LazySingleton();
                }
            }
        }
        return instance3;
    }

}
