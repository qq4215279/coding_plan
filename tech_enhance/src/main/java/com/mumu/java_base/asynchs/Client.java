/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.asynchs;

/**
 * Client 客户端
 * 
 * @author liuzhen
 * @version 1.0.0 2023/8/13 17:36
 */
public class Client {
    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    private String s = "ccc";

    /**
     * 客户端请求
     * 
     * @date 2023/8/13 17:39
     * @param callBack
     * @param msg
     * @return java.lang.String
     */
    public String clientTest(ICallBack callBack, String msg) {
        System.out.println("client 开始");
        System.out.println("客户端：发送的消息为：" + msg);

        // 1. 异步方式处理回调
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            s = server.serverTest(callBack, msg);
        }).start();
        System.out.println("客户端：异步发送成功");

        System.out.println("client 结束");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2. 同步方式处理回调
        // s = server.serverTest(callBack, msg);

        return s;
    }

    public static void main(String[] args) {
        Server server = new Server();
        Client client = new Client(server);

        String res = client.clientTest(Client::getiCallBack, "sss");
        
        System.out.println("最终结果为：" + res);
    }

    private static String getiCallBack(String str) {
        try {
            System.out.println("开始处理回调 ------------->");
            return "" + str.substring(0, 2);
        } catch (Exception e) {
            return "出现错误";
        }
    }

}
