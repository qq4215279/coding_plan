/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.mvc.servlet;

import java.lang.reflect.Method;

import com.mumu.framework.mvc.servlet.parse.ParamParse;

/**
 * ActionInvocation
 *
 * @author liuzhen
 * @version 1.0.0 2024/12/5 22:27
 */
public class ActionInvocation {
    /** 目标Action */
    protected Object obj;
    /** 目标方法  */
    protected Method method;

    /** action名称  */
    protected String actionName;
    /** method名称  */
    protected String methodName;

    /** 参数解析器 */
    protected ParamParse paramParse;
}
