/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * ClientTCP
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/27 21:54
 */
public class ClientTCP {

    /**
     * TCP通信程序:
     * 概述：TCP通信能实现两台计算机之间的数据交互，通信的两端，要严格区分为客户端（Client）与服务端（Server）。
     * 两端通信时步骤： 1. 服务端程序，需要事先启动，等待客户端的连接。 2. 客户端主动连接服务器端，连接成功才能通信。服务端不可以主动连接客户端。
     * 在Java中，提供了两个类用于实现TCP通信程序：
     *  1. 客户端： java.net.Socket 类表示。创建 Socket 对象，向服务端发出连接请求，服务端响应请求，两者建 立连接开始通信。
     *  2. 服务端： java.net.ServerSocket 类表示。创建 ServerSocket 对象，相当于开启一个服务，并等待客户端 的连接。
     */

    /**
     * Socket类：该类实现客户端套接字，套接字指的是两台设备之间通讯的端点。
     * 构造方法：
     * public Socket(String host, int port): 创建套接字对象并将其连接到指定主机上的指定端口号。如果指 定的host是null ，则相当于指定地址为回送地址。
     * 小贴士：回送地址(127.x.x.x) 是本机回送地址（Loopback Address），主要用于网络软件测试以及本 地机进程间通信，无论什么程序，一旦使用回送地址发送数据，立即返回，不进行任何网络传输。
     *
     * 成员方法:
     * public InputStream getInputStream()：返回此套接字的输入流。
     *      如果此Scoket具有相关联的通道，则生成的InputStream 的所有操作也关联该通道。
     *      关闭生成的InputStream也将关闭相关的Socket。
     * public OutputStream getOutputStream()：返回此套接字的输出流。
     *      如果此Scoket具有相关联的通道，则生成的OutputStream 的所有操作也关联该通道。
     *      关闭生成的OutputStream也将关闭相关的Socket。
     * public void close()：关闭此套接字。
     *      一旦一个socket被关闭，它不可再使用。
     *      关闭此socket也将关闭相关的InputStream和OutputStream 。
     * public void shutdownOutput() ： 禁用此套接字的输出流。
     *      任何先前写出的数据将被发送，随后终止输出流。
     */
    public static void main(String[] args) throws IOException {
        System.out.println("客户端 发送数据");
        // 1.创建 Socket ( ip , port ) , 确定连接到哪里.
        Socket client = new Socket("localhost", 6666);
        // 2.获取流对象 . 输出流
        OutputStream os = client.getOutputStream();
        // 3.写出数据.
        os.write("你好么? tcp ,我来了".getBytes());
        // 4. 关闭资源 .
        os.close();
        client.close();
    }

}
