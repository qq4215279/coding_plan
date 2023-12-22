/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation;

import com.mumu.java_base.annotation.anno.MyAnno2;
import com.mumu.java_base.annotation.anno.MyAnno;
import com.mumu.java_base.annotation.anno.Pro;
import com.mumu.java_base.enums.WeekEnum;

/**
 * Worker
 * 注解使用
 * @author liuzhen
 * @version 1.0.0 2023/7/13 14:55
 */
@MyAnno(value = 12, weekEnum = WeekEnum.FRIDAY, pro = @Pro(className = "User", methodName = "getAge"), strs = {"bbb", "ccc"})
@MyAnno2
public class AnnotationTest {
    @MyAnno2(value = "")
    public String name = "aaa";

    @MyAnno2("")
    public void show() {

    }

    public static void main(String[] args) {
        Class<AnnotationTest> annotationTestClass = AnnotationTest.class;

        if (!annotationTestClass.isAnnotationPresent(Pro.class)) {
            System.out.println("不存在定义的注解：Pro");
        }

        if (annotationTestClass.isAnnotationPresent(MyAnno.class)) {
            MyAnno annotation = annotationTestClass.getAnnotation(MyAnno.class);

            System.out.println("value: " + annotation.value());
            System.out.println("weekEnum: " + annotation.weekEnum().toString());
            System.out.println("strs: " + annotation.strs()[0] + " " + annotation.strs()[1]);
        }
    }
}
