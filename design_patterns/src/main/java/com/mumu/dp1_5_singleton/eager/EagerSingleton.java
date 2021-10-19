package com.mumu.dp1_5_singleton.eager;

/**
 * EagerSingleton
 * 恶汉单例实现
 * @author liuzhen
 * @version 1.0.0 2021/10/19 22:03
 */
public class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
