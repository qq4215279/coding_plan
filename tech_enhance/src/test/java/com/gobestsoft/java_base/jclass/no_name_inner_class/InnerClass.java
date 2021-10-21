package com.gobestsoft.java_base.jclass.no_name_inner_class;

/**
 * InnerClass
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/21 17:58
 */
public class InnerClass {
    public static void main(String[] args) {
        /**
         * 1.等号右边:是匿名内部类，定义并创建该接口的子类对象
         * 2.等号左边:是多态赋值,接口类型引用指向子类对象
         */
        FlyAble f = new FlyAble() {
            public void fly() {
                System.out.println("我飞了~~~");
            }
        }; // 调用 fly方法,执行重写后的方法 f.fly(); }

    }
}
