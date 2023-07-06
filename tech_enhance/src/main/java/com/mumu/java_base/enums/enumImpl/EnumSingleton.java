/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.enums.enumImpl;

/**
 * EnumSingleton
 * 枚举实现单例模式
 * 枚举在单例模式的一种实现方式中也可以用到。
 * @author liuzhen
 * @version 1.0.0 2023/7/6 17:52
 */
public class EnumSingleton {

    private EnumSingleton() {
    }

    public void out() {
        System.out.println("获取单例成功！");
    }

    /**
     * 获取单例
     * @date 2023/7/6 17:55
     * @param
     * @return com.mumu.dp1_5_singleton.enumImpl.EnumSingleton
     */
    public static EnumSingleton getInstance() {
       return Singleton.INSTANCE.getInstance();
       // return Singleton.INSTANCE.instance;
    }

    private enum Singleton {
        INSTANCE;
        private EnumSingleton instance;

        /**
         * JVM 保证这个方法绝对只调用一次
         * @date 2023/7/6 17:05
         */
        Singleton() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }


}

class Test {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.getInstance();
        instance.out();
    }
}
