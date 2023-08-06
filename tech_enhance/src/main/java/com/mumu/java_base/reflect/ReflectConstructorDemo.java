/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.reflect;

import com.mumu.common.pojo.Person;

import java.lang.reflect.Constructor;

public class ReflectConstructorDemo {

    /**
     * Class对象功能： 获取功能：
     * 获取功能：
     * 1. 获取构造方法们
     *  Constructor<T> getConstructor(类<?>... parameterTypes)
     *  Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
     *  Constructor<?>[] getConstructors()
     *  Constructor<?>[] getDeclaredConstructors()
     *
     * Constructor:构造方法
     * 创建对象：T newInstance(Object... initargs)
     * 如果使用空参数构造方法创建对象，操作可以简化：Class对象的newInstance方法
     *
     */

    public static void main(String[] args) throws Exception {
        // 0.获取Person的Class对象
        Class personClass = Person.class;

        /*
            2. 获取构造方法们
                 * Constructor<?>[] getConstructors()
                 * Constructor<T> getConstructor(类<?>... parameterTypes)

                 * Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
                 * Constructor<?>[] getDeclaredConstructors()
         */


        // Constructor<T> getConstructor(类<?>... parameterTypes)
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        //创建对象
        Object person = constructor.newInstance("张三", 23);
        System.out.println(person);

        System.out.println("----------");


        Constructor constructor1 = personClass.getConstructor();
        System.out.println(constructor1);
        // 创建对象
        Object person1 = constructor1.newInstance();
        System.out.println(person1);

        Object o = personClass.newInstance();
        System.out.println(o);


        //constructor1.setAccessible(true);
    }


}
