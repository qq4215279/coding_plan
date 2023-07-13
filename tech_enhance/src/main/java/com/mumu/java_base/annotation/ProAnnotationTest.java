/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation;

import com.mumu.java_base.annotation.anno.Pro;

import java.lang.reflect.Method;

/**
 * ProAnnotationTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/13 15:25
 */
@Pro(className = "com.mumu.java_base.annotation.Demo1", methodName = "show")
// @ProImpl(className = "com.mumu.java_base.annotation.Demo1", methodName = "show")
public class ProAnnotationTest {
    public static void main(String[] args) throws Exception {

        /*
            前提：不能改变该类的任何代码。可以创建任意类的对象，可以执行任意方法
         */

        // 1. 解析注解
        // 1.1 获取该类的字节码文件对象
        Class<ProAnnotationTest> proAnnoTestClass = ProAnnotationTest.class;
        // 2. 获取上边的注解对象
        // 其实就是在内存中生成了一个该注解接口的子类实现对象
        /*

            public class ProImpl implements Pro {
                public String className(){
                    return "cn.itcast.annotation.Demo1";
                }
                public String methodName(){
                    return "show";
                }

            }
        */



        Pro an = proAnnoTestClass.getAnnotation(Pro.class);
        // 3. 调用注解对象中定义的抽象方法，获取返回值
        String className = an.className();
        String methodName = an.methodName();
        System.out.println(className);
        System.out.println(methodName);

        // 3. 加载该类进内存
        Class cls = Class.forName(className);
        // 4. 创建对象
        Object obj = cls.newInstance();
        // 5. 获取方法对象
        Method method = cls.getMethod(methodName);
        // 6. 执行方法
        method.invoke(obj);
    }
}
