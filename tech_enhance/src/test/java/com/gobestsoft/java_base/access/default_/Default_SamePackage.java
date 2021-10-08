package com.gobestsoft.java_base.access.default_;

/**
 * Default_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Default_SamePackage extends DefaultDemo {

    public Default_SamePackage(String name) {
        super(name);
    }

    void getNameDefault() {
        // 同包下可以访问
        String name = super.name;
    }

    void getNameDefault2() {
        DefaultDemo demo = new DefaultDemo("testDefault");
        // 同包下可以访问
        String name = demo.name;
    }

}
