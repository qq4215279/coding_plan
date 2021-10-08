package com.gobestsoft.java_base.access.protected_;

/**
 * Protected_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Protected_SamePackage {

    public Protected_SamePackage(String name) {
    }

    protected void getNameProtected() {
        // protected同包下可以访问
        ProtectedDemo demo = new ProtectedDemo("haha");
        String name = demo.name;
    }


}
