/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.reflect;

import com.mumu.common.pojo.Person;

import java.lang.reflect.Method;

public class ReflectMethodDemo {

    /**
     * Class对象功能： 获取功能：
     * 获取功能：
     * 2. 获取成员方法们：
     *  Method getMethod(String name, 类<?>... parameterTypes)
     *  Method[] getDeclaredMethods()
     *  Method[] getMethods()
     *  Method getDeclaredMethod(String name, 类<?>... parameterTypes)
     *
     * Method：方法对象
     * 执行方法：Object invoke(Object obj, Object... args)
     * 获取方法名称：String getName:获取方法名
     */

    public static void main(String[] args) throws Exception {
        // 0.获取Person的Class对象
        Class personClass = Person.class;

        /*
          3. 获取成员方法们：
             * Method[] getMethods()
             * Method getMethod(String name, 类<?>... parameterTypes)

             * Method[] getDeclaredMethods()
             * Method getDeclaredMethod(String name, 类<?>... parameterTypes)
         */

        // 获取指定名称的方法
        Method eat_method = personClass.getMethod("eat");
        Person p = new Person();
        // 执行方法
        eat_method.invoke(p);


        Method eat_method2 = personClass.getMethod("eat", String.class);
        // 执行方法
        eat_method2.invoke(p,"饭");

        System.out.println("-----------------");

        // 获取所有public修饰的方法
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
            String name = method.getName();
            System.out.println(name);
            // method.setAccessible(true);
        }

        // 获取类名
        String className = personClass.getName();
        System.out.println(className); // cn.itcast.domain.Person

    }


}
