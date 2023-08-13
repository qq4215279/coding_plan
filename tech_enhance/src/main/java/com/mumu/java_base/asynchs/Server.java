/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.asynchs;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.complete("hello world");
        }).start();

        // javac -d out -encoding UTF-8  com\mumu\java_base\asynchs\Server.java
        // java -cp out com.mumu.java_base.asynchs.Server
        System.out.println("res ");
        String result = future.get();
        System.out.println("rew22: " + result);
    }

}
