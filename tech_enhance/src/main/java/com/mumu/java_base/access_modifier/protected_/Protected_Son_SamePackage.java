package com.mumu.java_base.access_modifier.protected_;

/**
 * Protected_Son_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Protected_Son_SamePackage extends ProtectedDemo {

    public Protected_Son_SamePackage(String name) {
        super(name);
    }

    protected void getNameProtected() {
        // 同包下父子类，可以访问
        String name = super.name;
    }

}
