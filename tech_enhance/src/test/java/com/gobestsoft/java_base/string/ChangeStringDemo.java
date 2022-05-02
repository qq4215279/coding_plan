/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.string;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * ChangeStringDemo
 * String字符串真的不可变？
 * @author liuzhen
 * @version 1.0.0 2022/4/24 16:47
 */
public class ChangeStringDemo {

    /**
     * String 类是⽤ final 关键字修饰的，所以我们认为其是不可变对象。但是真的不可变吗？
     * 每个字符串都是由许多单个字符组成的，我们知道其源码是由 char[] value 字符数组构成。
     *
     * value 被 final 修饰，只能保证引⽤不被改变，但是 value 所指向的堆中的数组，才是真实的数据，只要能够操作堆中的数组，依旧能改变数据。
     * ⽽且 value 是基本类型构成，那么⼀定是可变的，即使被声明为 private，我们也可以通过反射来改变。
     */
    
    /** 
     * 通过反射改变字符串
     * @author liuzhen
     * @date 2022/4/24 16:49 
     * @param  
     * @return void
     */
    @Test
    public void changeStringTest() throws NoSuchFieldException, IllegalAccessException {
        String str = "vae";
        // 打印原字符串
        System.out.println(str); // vae

        // 获取String类中的value字段
        Field fieldStr = String.class.getDeclaredField("value");
        // 因为value是private声明的，这⾥修改其访问权限
        fieldStr.setAccessible(true);
        // 获取str对象上的value属性的值
        byte[] value = (byte[])fieldStr.get(str);
        // 将第⼀个字符修改为 V(⼩写改⼤写)
        value[0] = 'V';
        // 打印修改之后的字符串
        System.out.println(str); // Vae
    }
    
}
