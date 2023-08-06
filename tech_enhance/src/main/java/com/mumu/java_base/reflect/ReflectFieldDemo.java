/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.reflect;

import com.mumu.common.pojo.Person;

import java.lang.reflect.Field;

public class ReflectFieldDemo {

    /**
     * Class对象功能： 获取功能：
     * 获取功能：
     * 3. 获取成员变量们
     *  Field getField(String name)
     *  Field getDeclaredField(String name)
     *  Field[] getFields()
     *  Field[] getDeclaredFields()
     *
     * Field：成员变量
     * 操作：
     * 1. 设置值：void set(Object obj, Object value)
     * 2. 获取值：get(Object obj)
     * 3. 忽略访问权限修饰符的安全检查：setAccessible(true): 暴力反射
     *
     */

    public static void main(String[] args) throws Exception {
        // 0. 获取Person的Class对象
        Class personClass = Person.class;
        /*
             1. 获取成员变量们
                 * Field[] getFields()
                 * Field getField(String name)
        
                 * Field[] getDeclaredFields()
                 * Field getDeclaredField(String name)
        
         */

        // 1. Field[] getFields()获取所有public修饰的成员变量
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("------------");

        // 2.Field getField(String name)
        Field a = personClass.getField("a");
        // 获取成员变量a 的值
        Person p = new Person();
        Object value = a.get(p);
        System.out.println(value);
        // 设置a的值
        a.set(p, "张三");
        System.out.println(p);

        System.out.println("===================");

        // Field[] getDeclaredFields()：获取所有的成员变量，不考虑修饰符
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        // Field getDeclaredField(String name)
        Field d = personClass.getDeclaredField("d");
        // 忽略访问权限修饰符的安全检查
        d.setAccessible(true);// 暴力反射
        Object value2 = d.get(p);
        System.out.println(value2);

    }

}
