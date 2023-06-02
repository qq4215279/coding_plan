/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.ref;

/**
 * RefDemo02
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/7 20:39
 */
public class RefDemo02 {

    // 在调用方法时，传递参数可以传递基本数据类型和引用类型
    public static void main(String[] args) {
        Person person = new Person("张三", 18);
        System.out.println("调用参数之前：" + person);
        call(person);
        System.out.println("调用参数之后：" + person);
    }

    public static void call(Person person) {
        person.setName("李四");
        person.setAge(30);
    }


}


class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
