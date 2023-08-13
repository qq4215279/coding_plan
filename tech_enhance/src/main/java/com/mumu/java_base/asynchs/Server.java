/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.asynchs;

/**
 * Server
 *
 * @author liuzhen
 * @version 1.0.0 2023/8/13 17:35
 */
public class Server {

    /**
     * 处理客户端请求
     * 
     * @date 2023/8/13 17:36
     * @param callBack
     * @param msg
     * @return String
     */
    public String serverTest(ICallBack callBack, String msg) {
        System.out.println("服务端：服务端接收到客户端发送的消息为:" + msg);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        msg = "zzz";
        System.out.println("服务端:数据处理成功，新的消息数据为：" + msg);

        return callBack.callBack(msg);
    }

}
