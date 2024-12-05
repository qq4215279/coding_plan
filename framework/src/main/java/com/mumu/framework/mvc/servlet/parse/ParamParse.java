/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.mvc.servlet.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.mumu.framework.mvc.MessageContext;
import com.mumu.framework.mvc.servlet.param.ParamInjector;

/**
 * ParamParse
 * 参数解析器
 * @author liuzhen
 * @version 1.0.0 2024/12/5 22:34
 */
public class ParamParse {
    /** 参数解释器  */
    protected ParamInjector[] injectors;

    /**
     * 初始化
     * @param context context
     * @param method method
     * @date 2024/12/5 22:52
     */
    public void init(MessageContext context, Method method) {
        Class<?>[] argTypes = method.getParameterTypes();
        injectors = new ParamInjector[argTypes.length];
        Annotation[][] annss = method.getParameterAnnotations();
        // 第i个参数
        for (int i = 0; i < annss.length; i++) {
            Annotation[] anns = annss[i];
            /*RequestParam requestParam = null;
            SessionParam sessionParam = null;
            GlobalCacheParam globalCacheParam = null;
            ProtobufParam protobufParam = null;
            // 第x个注解
            for (int x = 0; x < anns.length; x++) {
                if (anns[x] instanceof RequestParam) {
                    requestParam = (RequestParam) anns[x];
                    break;
                } else if (anns[x] instanceof SessionParam) {
                    sessionParam = (SessionParam) anns[x];
                    break;
                } else if (anns[x] instanceof GlobalCacheParam) {
                    globalCacheParam = (GlobalCacheParam) anns[x];
                    break;
                }  else if (anns[x] instanceof ProtobufParam) {
                    protobufParam = (ProtobufParam)anns[x];
                    break;
                }
            }

            if (null != sessionParam) {
                injectors[i] = new SessionInjector(sessionParam.value());
                continue;
            } else if (null != globalCacheParam) {
                injectors[i] = new GlobalCacheInjector();
                continue;
            } else if (null != protobufParam) {
                injectors[i] = new ProtobufInjector(argTypes[i], protobufParam.value(), protobufParam.compress());
                continue;
            }*/

            // request / response
            // injectors[i] = createCommonParamInjector(argTypes[i]);
            if (null != injectors[i]) {
                continue;
            }

            // 针对 RequestParam 注解参数
            // injectors[i] = evalInjector(argTypes[i], requestParam);
        }
    }

    public Object[] adapt(MessageContext context) {
        Object[] args = new Object[injectors.length];
        for (int i = 0; i < injectors.length; i++) {
            args[i] = injectors[i].getValue(context);
        }

        return args;
    }

    private ParamInjector createCommonParamInjector(Class<?> clazz) {
        /*if (null == requestParam) {
            return new NullInjector(clazz);
        }

        if (clazz.isArray()) {
            return new ArrayInjector(requestParam.value(), clazz);
        }

        return new NameInjector(requestParam.value(), clazz);*/

        return null;
    }
}
