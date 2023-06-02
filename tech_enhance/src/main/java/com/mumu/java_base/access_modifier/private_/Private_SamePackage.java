package com.mumu.java_base.access_modifier.private_;

/**
 * Default_SamePackage
 * private：不能访问，使用了private修饰符
 * @author liuzhen
 * @version 1.0.0 2021/10/8 9:00
 */
public class Private_SamePackage {

    private void getName() {
        PrivateDemo demo = new PrivateDemo("不能访问，使用了private修饰符");
        //        demo.name;
        //        demo.getName(); // 不能访问！！！
    }

}
