package com.gobestsoft.java_base.access.other_package;

import com.gobestsoft.java_base.access.default_.DefaultDemo;

/**
 * Default_Son_OtherPackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:06
 */
public class Default_Son_OtherPackage extends DefaultDemo {

    public Default_Son_OtherPackage(String name) {
        super(name);
    }

    public void getNameSon() {
        // 在其他包下，也不能访问父类default修饰符定义的变量
//        String name1 = name;
//        String name2 = super.name;
    }

    public void getNameSon2() {
        DefaultDemo demo = new DefaultDemo("default_son 访问");
        // 在其他包下，也不能访问父类default修饰符定义的变量
//        String name = demo.name;
//        System.out.println(name);
    }

}

