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
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserOb.class);
        enhancer.setCallback(new UserInterceptor());

        UserOb proxy = (UserOb) enhancer.create();
        proxy.getAge(); // 输出 Before method call, Hello CGLIB!, After method call
    }
}
