/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.enums.inf;

/**
 * AnimalEatEnum
 * 枚举来消除掉 if/else
 * 实现接口，消除 if/else
 * 创建的枚举类默认是被final修饰，并且默认继承了Enum类。因此不能再继承其他的类。但是可以去实现接口。
 * 假如我想扩充新的动物，只需要去枚举类中加代码即可，而不用改任何老代码，符合开闭原则！
 * @author liuzhen
 * @version 1.0.0 2023/7/6 17:00
 */
public enum AnimalEatEnum implements Eat {
    Dog() {
        @Override
        public void eat() {
            System.out.println("狗吃屎");
        }
    },

    Cat() {
        @Override
        public void eat() {
            System.out.println("猫吃鱼");
        }
    },

    ;

}

class Test {
    public static void main(String[] args) {
        AnimalEatEnum.valueOf("Cat").eat();
    }
}