package com.mumu.dp2_7_proxy.cjlibDynamic;

import net.sf.cglib.proxy.Enhancer;

/**
 * CGLibProxyDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/11 17:15
 */
public class CGLibProxyDemo {

    public static void main(String[] args) {
        UserOb userOb = new UserOb(18);

        userOb = getEnhancerConfigInfo(userOb);
        System.out.println(userOb.getAge());
    }

    public static <ConfigInfo> ConfigInfo getEnhancerConfigInfo(ConfigInfo configInfo) {
        if (configInfo == null) {
            return configInfo;
        }

        // 已经被代理过
        if (Enhancer.isEnhanced(configInfo.getClass())) {
            return configInfo;
        }

        Class<ConfigInfo> aClass = (Class<ConfigInfo>) configInfo.getClass();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(configInfo.getClass());
        enhancer.setCallback(new UserInterceptor(configInfo));

        Object obj = enhancer.create();
        return aClass.cast(obj);
    }

}
