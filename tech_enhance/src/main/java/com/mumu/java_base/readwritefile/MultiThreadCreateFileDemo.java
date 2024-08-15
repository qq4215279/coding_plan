/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Set;

/**
 * MultiThreadCreateFileDemo
 * FileChannel link: https://blog.csdn.net/lixinkuan328/article/details/114156567
 * @author liuzhen
 * @version 1.0.0 2024/6/7 17:41
 */
public class MultiThreadCreateFileDemo {


    /**
     * FileChannel 是Java NIO对应于磁盘等存储设备文件操作的通道。
     *
     * 一、获取FileChannel的API
     * FileChannel open(Path path,  Set<? extends OpenOption> options, FileAttribute<?>... attrs);
     *      打开一个与文件的连接通道，用于进行文件操作。path：path，文件的路径对象，可用Path.get("文件路径")，获取。options：通道的操作参数。通常使用实现类枚举StandardOpenOption指定。attrs：创建文件时自动设置的文件属性的可选列表，比如读写执行。
     * FileChannel open(Path path, OpenOption... options)  打开一个与文件的连接通道，用于进行文件操作。并可以设置通道的操作参数options
     * 
     * 
     * 二、读写API
     * //
     * int read(ByteBuffer dst) throws IOException;  把文件数据通过通道读取到缓冲区dst中。返回读取到数据长度。返回-1表示到达文件尾。
     * long read(ByteBuffer[] dsts) throws IOException;  把文件数据通过通道读取到缓冲区数组dst中。返回读取到数据长度。返回-1表示到达文件尾。会依次读取到缓冲区数组中的缓冲区，知道把数据读完或者缓冲区不够大。
     * long read(ByteBuffer[] dsts, int offset, int length);  把文件数据通过通道读取到缓冲区数组dst中。返回读取到数据长度。返回-1表示到达文件尾。可以设置读取到缓冲区数组中的哪些数组，假如dsts数组有5个缓冲区，offset=1 length=3，则只会把数据读取到缓冲区数组的下标 1,2,3三个缓冲区上，offset代表下标，length代表长度会依次读取到缓冲区数组中的缓冲区，知道把数据读完或者缓冲区不够大。
     * int read(ByteBuffer dst, long position) throws IOException; 把文件数据通过通道读取到缓冲区数组dst中。以上的读取数组方法读取一个字节数据FileChannel中的游标会右移一位，但是这个方法不会移动游标。也就是重复读取还是读取那些数据。
     * int write(ByteBuffer src) throws IOException;  把缓冲区中的position到limit-1的数据通过通道写到文件中去。返回写的数据长度。
     * long write(ByteBuffer[] srcs, int offset, int length)  把缓冲区数组中的多个缓冲区依次通过管道写到文件中去，offset与length与read对应方法大致。
     * long write(ByteBuffer[] srcs) throws IOException  把缓冲区数组中的多个缓冲区依次通过管道写到文件中去。
     * int write(ByteBuffer src, long position) throws IOException;  读取缓冲区的数组通过通道写到文件中去，可指定读取缓冲区的position偏移量。假如缓冲区当前position为0，position参数为1，那么最终缓冲区数据读取0+1 到limit-1 的数据。
     *
     * FileLock lock = channel.tryLock()  尝试获取锁
     * FileLock lock = channel.lock()
     *  lock.release();
     *
     * channel.close();
     *
     * ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
     *
     */

    /*
     * StandardOpenOption枚举类。代表着文件连接时的标准选项，通常可以多个选项一起使用。
     *  READ 以读的方式连接文件。
     *  WRITE以写的方式连接文件。
     *  APPEND 以追加的方式连接文件，不会覆盖文件原本内容，在后面追加。
     *  TRUNCATE_EXISTING如果文件存在并且以WRITE的方式连接时就会把文件内容清空，文件设置为0字节大小。 如果文件只以READ连接 时，该选项会被忽略。
     *  CREATE  创建一个文件，如果文件已存在，就打开文件连接。与CREATE_NEW同时存在时该选项会被忽略。
     *  CREATE_NEW 创建一个文件，如果文件已存在，如果已经存在会抛异常。
     *  DELETE_ON_CLOSE 通道关闭时删除文件
     *  SPARSE 创建稀疏文件，与CREATE_NEW选项配合使用。
     *  SYNC 要求每次写入要把内容和元数据刷到存储设备上。
     *  DSYNC 要求每次写入那内容刷到存储设备上
     *  StandardOpenOption 枚举类 代表着文件连接时的标准选项，通常可以多个选项一起使用。
     */

    private static final String configFilePath = "F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_base\\readwritefile" +
            "\\text\\config_cannon.xml";


    public static void main(String[] args) throws Exception {
        byte[] byteArray = null;
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        FileChannel channel = null;
        FileLock lock = null;
        try {

            channel = FileChannel.open(Paths.get(configFilePath), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            lock = channel.tryLock();

            // 拿到锁，保存配置表
            if (lock != null) {
                System.out.println("开始下载 = " + configFilePath);
                // 下载到本地
                downloadConfig2Local(inputStream, channel);
                System.out.println("下载完成 = " + configFilePath);
            }

            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
            // 释放文件锁
            if (lock != null) {
                lock.release();
            }

            if (channel != null) {
                channel.close();
            }
        }


        // 无锁读取本地配置表
        System.out.println("本地存在配置 直接读取... AbsolutePath: " + configFilePath);
       /* try {

            // loadContext2(new FileInputStream(configFilePath), configFilePath, indexInstance);
        } catch (UnmarshalException | IOException e) {
            // 文件被锁定，远程加载
            try (InputStream inputStream = getRemoteXmlInputStream(configFilePath)) {
                return loadContext2(inputStream, configFilePath, indexInstance);
            }
        }*/
    }

    /**
     * 下载配置表到本地
     * 操作channel流，下载文件
     * @param inputStream
     * @param channel 写入流
     * @return void
     * @date 2024/7/12 17:36
     */
    private static void downloadConfig2Local(InputStream inputStream, FileChannel channel) throws IOException {
        int bytesRead;
        // 下载到本地
        ByteBuffer byteBuffer = ByteBuffer.allocate(8192);
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
            byteBuffer.put(buffer, 0 , bytesRead);
            byteBuffer.flip();

            // 将缓冲区的数据写入 FileChannel
            while (byteBuffer.hasRemaining()) {
                channel.write(byteBuffer);
            }

            byteBuffer.clear();
        }
    }

    /**
     * InputStream流 转 byte[]
     * @param srcInputStream srcInputStream
     * @return byte[]
     * @date 2024/4/15 14:06
     */
    private static byte[] inputStream2ByteArray(InputStream srcInputStream) throws IOException {
        // 将InputStream的内容读入ByteArrayOutputStream
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int length;
        while ((length = srcInputStream.read(buffer)) != -1) {
            stream.write(buffer, 0, length);
        }
        stream.flush();
        // 转换为byte数组
        return stream.toByteArray();
    }
}
