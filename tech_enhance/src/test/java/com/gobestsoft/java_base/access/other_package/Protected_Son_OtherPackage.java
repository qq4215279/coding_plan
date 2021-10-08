package com.gobestsoft.java_base.access.other_package;

import com.gobestsoft.java_base.access.protected_.ProtectedDemo;

/**
 * Protected_Son_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Protected_Son_OtherPackage extends ProtectedDemo {

    public Protected_Son_OtherPackage(String name) {
        super(name);
    }

    protected void getNameProtected() {
        // 不同包下父子类，也可以访问
        String name = super.name;
    }

}
