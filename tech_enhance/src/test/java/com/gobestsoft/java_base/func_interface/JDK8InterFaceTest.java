/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.func_interface;

/**
 * JDK8InterFaceTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/20 23:00
 */
public interface JDK8InterFaceTest {

    /** 接口中定义的变量都是常量 */
    int CONSTANTS_X = 100;

    /**
     * 接口中定义的方法不能使用 private protected 这两个修饰符修饰，默认使用public访问修饰符
     */
    //    private void cc();
    //    protected void dd();

    /**
     * 1.8以上，接口中可定义静态方法（public类型的）
     * @return
     */
    static int staticMethodA() {
        return 1;
    }

    /**
     * JDK1.8新增关键字：default，就是在接口中可以增加默认实现。default关键字仅仅使用于接口中!
     * 因为改动接口所有的实现类都要改动,所以增加了default关键字后不需要修改其他类,默认给所有实现类增加了方法.
     * 接口中可定义方法（public类型的）
     * @return
     */
    default int defaultMethodB() {
        return 2;
    }

}
