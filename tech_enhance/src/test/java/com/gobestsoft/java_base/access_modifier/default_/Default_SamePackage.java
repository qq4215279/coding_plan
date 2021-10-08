package com.gobestsoft.java_base.access_modifier.default_;

/**
 * Default_SamePackage
 * default同包下可以访问
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Default_SamePackage {

    void getNameDefault() {
        DefaultDemo demo = new DefaultDemo("default同包下可以访问");
        // default同包下可以访问
        String name = demo.name;
    }

}
