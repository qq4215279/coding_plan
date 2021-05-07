package com.gobestsoft.javaBase.ref;

/**
 * RefDemo04
 *
 * @author liuzhen
 * @version 1.0.0 2021/5/7 20:47
 */
public class RefDemo04 {

    public static void main(String[] args) {
        RefDemo02.Person person = new RefDemo02.Person("刘亦菲", 20);
        System.out.println("调用参数志强：" + person);
        call(person);
        System.out.println("调用参数之后：" + person);
    }

    public static void call(RefDemo02.Person person) {
        person = new RefDemo02.Person("lihua", 20);
    }

}
