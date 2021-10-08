package com.gobestsoft.java_base.access.private_;

/**
 * Default_SamePackage
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Private_SamePackage {

    private String getName() {
        PrivateDemo demo = new PrivateDemo("testPrivate");
        // demoe.name 不能访问，使用了private修饰符
//        demo.name;
//        demo.getName(); // 不能访问！！！
        return "";
    }

}
