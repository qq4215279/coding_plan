/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ServerTCP
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/27 21:48
 */
public class ServerTCP {

    /**
     * ServerSocket类：这个类实现了服务器套接字，该对象等待通过网络的请求。
     * 构造方法：
     * public ServerSocket(int port)：使用该构造方法在创建ServerSocket对象时，就可以将其绑定到一个指 定的端口号上，参数port就是端口号。
     * 成员方法：
     * public Socket accept() ：侦听并接受连接，返回一个新的Socket对象，用于和客户端实现通信。该方法 会一直阻塞直到建立连接。
     */
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动 , 等待连接 .... ");
        // 1.创建 ServerSocket对象，绑定端口，开始等待连接
        ServerSocket ss = new ServerSocket(6666);
        // 2.接收连接 accept 方法, 返回 socket 对象.
        Socket server = ss.accept();
        // 3.通过socket 获取输入流
        InputStream is = server.getInputStream();
        // 4.一次性读取数据
        // 4.1 创建字节数组
        byte[] b = new byte[1024];
        // 4.2 据读取到字节数组中.
        int len = is.read(b);
        // 4.3 解析数组,打印字符串信息
        String msg = new String(b, 0, len);
        System.out.println(msg);
        // 5.关闭资源.
        is.close();
        server.close();
    }

}
