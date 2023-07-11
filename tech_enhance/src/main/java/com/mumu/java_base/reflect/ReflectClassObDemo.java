/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.reflect;

import com.mumu.common.pojo.Person;
import com.mumu.common.pojo.Student;

public class ReflectClassObDemo {

    /**
     * 获取Class对象的方式：
     *  1. Class.forName("全类名")：将字节码文件加载进内存，返回Class对象
     *  2. 类名.class：通过类名的属性class获取
     *  3. 对象.getClass()：getClass()方法在Object类中定义着。
     *
     *
     * Class对象功能： 获取功能：
     * 获取功能：
     * 1. 获取构造方法们
     *  Constructor<T> getConstructor(类<?>... parameterTypes)
     *  Constructor<T> getDeclaredConstructor(类<?>... parameterTypes)
     *  Constructor<?>[] getConstructors()
     *  Constructor<?>[] getDeclaredConstructors()
     *
     * 2. 获取成员方法们：
     *  Method getMethod(String name, 类<?>... parameterTypes)
     *  Method[] getDeclaredMethods()
     *  Method[] getMethods()
     *  Method getDeclaredMethod(String name, 类<?>... parameterTypes)
     *
     * 3. 获取成员变量们
     *  Field getField(String name)
     *  Field getDeclaredField(String name)
     *  Field[] getFields()
     *  Field[] getDeclaredFields()
     *
     * Constructor:构造方法
     * 创建对象：T newInstance(Object... initargs)
     * 如果使用空参数构造方法创建对象，操作可以简化：Class对象的newInstance方法
     *
     * Method：方法对象
     * 执行方法：Object invoke(Object obj, Object... args)
     * 获取方法名称：String getName:获取方法名
     *
     * Field：成员变量
     * 操作：
     * 1. 设置值：void set(Object obj, Object value)
     * 2. 获取值：get(Object obj)
     * 3. 忽略访问权限修饰符的安全检查：setAccessible(true): 暴力反射
     *
     */


    /**
     *
     * @date 2022/7/17 17:08
     * @param args
     * @return void
     */
    public static void main(String[] args) throws Exception {
        // 1.Class.forName("全类名")
        Class cls1 = Class.forName("com.mumu.common.pojo.Person");
        System.out.println(cls1);

        // 2.类名.class
        Class cls2 = Person.class;
        System.out.println(cls2);

        // 3. 对象.getClass()
        Person p = new Person();
        Class cls3 = p.getClass();
        System.out.println(cls3);

        // == 比较三个对象
        // true
        System.out.println(cls1 == cls2);
        // true
        System.out.println(cls1 == cls3);

        Class c = Student.class;
        // false
        System.out.println(c == cls1);
    }
}
