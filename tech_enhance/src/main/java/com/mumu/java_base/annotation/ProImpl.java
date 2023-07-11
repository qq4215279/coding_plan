/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation;

import com.mumu.java_base.annotation.anno.Pro;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;

/**
 * ProImpl
 * 实现一个注解类
 * // TODO 如何是使用？？？
 * @author liuzhen
 * @version 1.0.0 2023/7/8 15:42
 */
public class ProImpl implements Pro {

    private String className;
    private String methodName;

    public ProImpl() {
    }

    public ProImpl(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public String className() {
        if (!StringUtils.isEmpty(className)) {
            return className;
        }

        return "cn.itcast.annotation.Demo1";
    }

    @Override
    public String methodName() {
        if (!StringUtils.isEmpty(methodName)) {
            return methodName;
        }

        return "show";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return ProImpl.class;
    }
}
