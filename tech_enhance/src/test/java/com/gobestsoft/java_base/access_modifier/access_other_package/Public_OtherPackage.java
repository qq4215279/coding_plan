package com.gobestsoft.java_base.access_modifier.access_other_package;

import com.gobestsoft.java_base.access_modifier.public_.PublicDemo;

/**
 * Public_OtherPackage
 * public 不同包不是父子类或不是父子类，也都可以访问
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Public_OtherPackage extends PublicDemo {

    public Public_OtherPackage(String name) {
        super(name);
    }

    public void getNamePublic() {
        // public 不同包的父子类，也可以访问
        String name = super.name;
    }

    public void getNamePublic2() {
        PublicDemo demo = new PublicDemo("public 不同包不是父子类，也可以访问");
        // public 不同包不是父子类，也可以访问
        String name = demo.name;
    }

}
