/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.socket.file_upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * TCPServer
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/27 22:06
 */
public class TCPServer2 {

    /**
     * 文件上传案例服务器端:读取客户端上传的文件,保存到服务器的硬盘,给客户端回写"上传成功"
     * <p>
     * 明确:
     * 数据源:客户端上传的文件
     * 目的地:服务器的硬盘 d:\\upload\\1.jpg
     * <p>
     * 实现步骤:
     * 1.创建一个服务器ServerSocket对象,和系统要指定的端口号
     * 2.使用ServerSocket对象中的方法accept,获取到请求的客户端Socket对象
     * 3.使用Socket对象中的方法getInputStream,获取到网络字节输入流InputStream对象
     * 4.判断d:\\upload文件夹是否存在,不存在则创建
     * 5.创建一个本地字节输出流FileOutputStream对象,构造方法中绑定要输出的目的地
     * 6.使用网络字节输入流InputStream对象中的方法read,读取客户端上传的文件
     * 7.使用本地字节输出流FileOutputStream对象中的方法write,把读取到的文件保存到服务器的硬盘上
     * 8.使用Socket对象中的方法getOutputStream,获取到网络字节输出流OutputStream对象
     * 9.使用网络字节输出流OutputStream对象中的方法write,给客户端回写"上传成功"
     * 10.释放资源(FileOutputStream,Socket,ServerSocket)
     *
     * 文件上传优化分析：
     * 1.文件名称写死的问题 服务端，保存文件的名称如果写死，那么最终导致服务器硬盘，只会保留一个文件，建议使用系统时间优 化，保证文件名称唯一
     *      FileOutputStream fis = new FileOutputStream(System.currentTimeMillis()+".jpg") // 文件名称 BufferedOutputStream bos = new BufferedOutputStream(fis);
     * 2. 循环接收的问题 服务端，指保存一个文件就关闭了，之后的用户无法再上传，这是不符合实际的，使用循环改进，可以不断 的接收不同用户的文件
     *    每次接收新的连接,创建一个Socket while（true）{ Socket accept = serverSocket.accept(); ...... }
     * 3. 效率问题 服务端，在接收大文件时，可能耗费几秒钟的时间，此时不能接收其他用户上传，所以，使用多线程技术优化
     *    while（true）{ Socket accept = serverSocket.accept(); // accept 交给子线程处理. new Thread(() ‐> { ...... InputStream bis = accept.getInputStream(); ...... }).start(); }
     */
    public static void main(String[] args) throws IOException {
        // 1.创建一个服务器ServerSocket对象,和系统要指定的端口号
        ServerSocket server = new ServerSocket(8888);
        // 2.使用ServerSocket对象中的方法accept,获取到请求的客户端Socket对象

        /*
            让服务器一直处于监听状态(死循环accept方法) // 2. 循环接收,建立连接
            有一个客户端上传文件,就保存一个文件
         */
        while (true) {
            Socket accept = server.accept();

            /*
                使用多线程技术,提高程序的效率
                有一个客户端上传文件,就开启一个线程,完成文件的上传
             */
            // 完成文件的上传
            new Thread(() -> {
                try {
                    // 3.使用Socket对象中的方法getInputStream,获取到网络字节输入流InputStream对象
                    InputStream is2 = accept.getInputStream();
                    // 3.1 获取输入流对象
                    BufferedInputStream bis = new BufferedInputStream(is2);

                    // 3.2 创建输出流对象, 保存到本地 .
                    // 自定义一个文件的命名规则:防止同名的文件被覆盖   规则:域名+毫秒值+随机数
                    String fileName = "itcast" + System.currentTimeMillis() + new Random().nextInt(999999) + ".jpg";

                    FileOutputStream fos2 = new FileOutputStream("d:\\upload" + fileName);
                    BufferedOutputStream bos = new BufferedOutputStream(fos2);
                    // 4 读写数据
                    byte[] bytes1 = new byte[1024 * 8];
                    int len2;
                    while ((len2 = bis.read(bytes1)) != -1) {
                        bos.write(bytes1, 0, len2);
                    }

                    // 5.1 使用Socket对象中的方法getOutputStream,获取到网络字节输出流OutputStream对象
                    // 5.2 使用网络字节输出流OutputStream对象中的方法write,给客户端回写"上传成功"
                    accept.getOutputStream().write("上传成功".getBytes());

                    // 6. 关闭资源
                    bos.close();
                    bis.close();
                    accept.close();
                    System.out.println("文件上传已保存");
                } catch (IOException e) {
                    System.out.println(e);
                }
            }).start();
        }

        // 服务器就不用关闭
        // server.close();
    }
}
