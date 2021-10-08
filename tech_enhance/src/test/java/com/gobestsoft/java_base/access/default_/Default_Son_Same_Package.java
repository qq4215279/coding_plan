package com.gobestsoft.java_base.access.default_;

/**
 * Default_Son
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:06
 */
public class Default_Son_Same_Package extends DefaultDemo {

    public Default_Son_Same_Package(String name) {
        super(name);
    }

    public void getNameSon() {
        // 相同包下，可以访问父类default修饰符定义的变量
        String name = super.name;
    }

    public void getNameSon2() {
        DefaultDemo demo = new DefaultDemo("default_son 访问");
        // 相同包下，可以访问父类default修饰符定义的变量
        String name = demo.name;
        System.out.println(name);
    }

}

