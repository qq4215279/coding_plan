package com.mumu.java_base.access_modifier.access_other_package;

import com.mumu.java_base.access_modifier.protected_.ProtectedDemo;

/**
 * Protected_Son_SamePackage
 * protected：在不同包下，可以访问父类定义的Protected变量
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Protected_OtherPackage extends ProtectedDemo {

    public Protected_OtherPackage(String name) {
        super(name);
    }

    protected void getNameProtected() {
        // 不同包下父子类，也可以访问
        String name = super.name;
    }

    protected void getNameProtected2() {
        ProtectedDemo demo = new ProtectedDemo("protected：不同包不是父子类，不能访问！");
        // protected 不同包不是父子类，不能访问
        //        String name = demo.name;
    }
}
