package com.gobestsoft.java_base.access.other_package;

import com.gobestsoft.java_base.access.protected_.ProtectedDemo;

/**
 * Protected_Son_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Protected_OtherPackage {

    public Protected_OtherPackage() {
    }

    protected void getNameProtected() {
        ProtectedDemo demo = new ProtectedDemo("protected 不同包不是父子类，不能访问");
        // protected 不同包不是父子类，不能访问
//        String name = demo.name;
    }

}
