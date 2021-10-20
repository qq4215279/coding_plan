package com.mumu.dp1_5_singleton.iodh;

/**
 * IoDHSingleton
 * Initialization on Demand Holder(IoDH) 实现单例
 * 优点：既能实现延迟加载，又可以保证线程安全，不影响系统性能，不失为一种最好的Java语言单例模式的实现方式。
 * 缺点：与编程语言的特性相关，很多面向对象语言并不支持IoDH。
 * @author liuzhen
 * @version 1.0.0 2021/10/19 22:24
 */
public class IoDHSingleton {
    private IoDHSingleton() {
    }

    /**
     * 任何初始化失败都会导致单例类不可用。也就是说，IoDH这种实现方式只能用于能保证初始化不会失败的情况。
     * 第一次调用getInstance()时，由于出现了异常导致SingleTon对象没有生成，进而导致该Holder类没有成功加载。
     * 第二次调用时，则会出现NoClassDefFoundError。
     */
    /*private IoDHSingleton() {
        int i = 1 / 0;
    }*/

    /**
     * 静态内部类
     */
    private static class HolderClass {
        private static final IoDHSingleton instance = new IoDHSingleton();
    }

    public static IoDHSingleton getInstance() {
        return HolderClass.instance;
    }

}
