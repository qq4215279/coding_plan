/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation.anno;

import com.mumu.java_base.enums.WeekEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * MyAnno
 * 定义注解
 * 元注解：用于描述注解的注解
 * @Target：描述注解能够作用的位置
 * @Retention：描述注解被保留的阶段
 * @Documented：描述注解是否被抽取到api文档中
 * @Inherited：描述注解是否被子类继承
 * @author liuzhen
 * @version 1.0.0 2023/7/13 14:36
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited // 子类可获得父类定义的注解
public @interface MyAnno {
    /**
     * 1. 若有且仅有一个字段，且字段名叫 value() ，则赋值时，value可以省略，直接定义值即可。
     * 2. 若通过 default 设置默认自豪，则使用注解时，可以不用进行赋值。
     */
    /** 定义int类型 */
    int value() default 0;

    String name() default "张三";

    /**
     * 使用注解给数组赋值时，值使用{}包裹。如果数组中只有一个值，则{}可以省略。
     */
    /** 定义String数组类型  */
    String[] strs();

    /** 定义枚举类型 */
    WeekEnum weekEnum();

    /** 定义别的注解 */
    Pro pro();

}
