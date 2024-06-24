/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * _1_FOS_FIS
 * 字节流：FileOutputStream类 & FileInputStream类
 * @author liuzhen
 * @version 1.0.0 2021/12/26 16:41
 */
public class _1_FOS_FIS {

    /**
     * 字节输出流【OutputStream】 java.io.OutputStream 抽象类是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。它定义了字节输出流的基本共性功能方法。
     * public void close()：关闭此输出流并释放与此流相关联的任何系统资源。
     * public void flush()：刷新此输出流并强制任何缓冲的输出字节被写出。
     * public void write(byte[] b)：将 b.length字节从指定的字节数组写入此输出流。
     * public void write(byte[] b, int off, int len)：从指定的字节数组写入 len字节，从偏移量 off开始输 出到此输出流。
     * public abstract void write(int b)：将指定的字节输出流。
     * 小贴士： close方法，当完成流的操作时，必须调用此方法，释放系统资源。
     *
     * 字节输入流【InputStream】 java.io.InputStream 抽象类是表示字节输入流的所有类的超类，可以读取字节信息到内存中。它定义了字节输入 流的基本共性功能方法。
     * public void close() ：关闭此输入流并释放与此流相关联的任何系统资源。
     * public abstract int read() ： 从输入流读取数据的下一个字节。
     * public int read(byte[] b) ： 从输入流中读取一些字节数，并将它们存储到字节数组 b中 。
     * 小贴士： close方法，当完成流的操作时，必须调用此方法，释放系统资源。
     */

    /**
     * FileOutputStream类
     * OutputStream 有很多子类，我们从最简单的一个子类开始。 java.io.FileOutputStream 类是文件输出流，用于将数据写出到文件。
     * 构造方法：
     * public FileOutputStream(File file)：创建文件输出流以写入由指定的 File对象表示的文件。
     * public FileOutputStream(String name)：创建文件输出流以指定的名称写入文件。
     * 当你创建一个流对象时，必须传入一个文件路径。该路径下，如果没有这个文件，会创建该文件。如果有这个文件，会清空这个文件的数据。
     * public FileOutputStream(File file, boolean append): 创建文件输出流以写入由指定的 File对象表示的文件。
     * public FileOutputStream(String name, boolean append)：创建文件输出流以指定的名称写入文件
     * 这两个构造方法，参数中都需要传入一个boolean类型的值， true 表示追加数据， false 表示清空原有数据。
     *
     * 写出字节数据：
     * 1. 写出字节： write(int b) 方法，每次可以写出一个字节数据
     * 2. 写出字节数组： write(byte[] b) ，每次可以写出数组中的数据
     * 3. 写出指定长度字节数组： write(byte[] b, int off, int len) ,每次写出从off索引开始，len个字节
     *
     * 小贴士： 1. 虽然参数为int类型四个字节，但是只会保留一个字节的信息写出。 2. 流操作完毕后，必须释放系统资源，调用close方法，千万记得。
     *
     * 写出换行：
     *  回车符 \r 和换行符 \n ：
     *      回车符：回到一行的开头（return）。
     *      换行符：下一行（newline）。
     *  系统中的换行：
     *      Windows系统里，每行结尾是 回车+换行 ，即 \r\n ；
     *      Unix系统里，每行结尾只有 换行 ，即 \n ；
     *      Mac系统里，每行结尾是 回车 ，即 \r 。从 Mac OS X开始与Linux统一。
     */
    @Test
    public void writeTest() throws IOException {
        // 使用文件名称创建流对象
        FileOutputStream fos = new FileOutputStream("fos.txt");

        // 1. write(int b) 写出数据
        fos.write(97); // 写出第1个字节
        fos.write(98); // 写出第2个字节
        fos.write(99); // 写出第3个字节

        // 2. write(byte[] b) 字符串转换为字节数组
        byte[] b = "我是程序员".getBytes();
        // 写出字节数组数据
        fos.write(b);
        // 写出一个换行, 换行符号转成数组写出
        fos.write("\r\n".getBytes());

        // 3. write(byte[] b, int off, int len) 字符串转换为字节数组
        byte[] c = "abcde".getBytes(); // 写出从索引2开始，2个字节。索引2是c，两个字节，也就是cd。
        fos.write(c,2,2);

        // 关闭资源
        fos.close();
    }

    /**
     * FileInputStream类
     * java.io.FileInputStream 类是文件输入流，从文件中读取字节。
     * 构造方法：
     * FileInputStream(File file)：通过打开与实际文件的连接来创建一个 FileInputStream ，该文件由文件系 统中的 File对象 file命名。
     * FileInputStream(String name)：通过打开与实际文件的连接来创建一个 FileInputStream ，该文件由文件 系统中的路径名 name命名。
     * 当你创建一个流对象时，必须传入一个文件路径。该路径下，如果没有该文件,会抛出 FileNotFoundException 。
     *
     * 读取字节数据
     * 1. 读取字节： read()，每次可以读取一个字节的数据，提升为int类型，读取到文件末尾，返回 -1
     * 2. 使用字节数组读取：read(byte[] b) ，每次读取b的长度个字节到数组中，返回读取到的有效字节个数，读 取到末尾时，返回 -1
     *
     * 小贴士： 1. 虽然读取了一个字节，但是会自动提升为int类型。 2. 流操作完毕后，必须释放系统资源，调用close方法，千万记得。
     */
    @Test
    public void readTest() throws IOException {
        // 使用文件名称创建流对象
        FileInputStream fis = new FileInputStream("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\read.txt");

        // 1.1 read() 读取数据，返回一个字节
        /*int read = fis.read();
        System.out.println((char)read);
        read = fis.read();
        System.out.println((char)read);
        read = fis.read();
        System.out.println((char)read);
        read = fis.read();
        System.out.println((char)read);
        read = fis.read();
        System.out.println((char)read);
        // 读取到末尾,返回‐1
        read = fis.read();
        System.out.println(read);

        System.out.println("--------------------->");*/

        // 1.2 read() 定义变量，保存数据
       /* int b;
        // 循环读取
        while ((b = fis.read()) != -1) {
            System.out.println((char)b);
        }

        System.out.println("--------------------->");*/

        // 2. read(byte[] b) 定义变量，作为有效个数
        int len;
        // 定义字节数组，作为装字节数据的容器
        byte[] bytes = new byte[2];
        // 循环读取
        while ((len = fis.read(bytes)) != -1) {
            // 每次读取后,把数组变成字符串打印
            System.out.println(new String(bytes)); // ab cd ed

            // 每次读取后,把数组的有效字节部分，变成字符串打印
            // System.out.println(new String(bytes, 0, len)); // len 每次读取的有效字节个数   ab cd e
        }

        // 关闭资源
        fis.close();
    }

    /**
     * 图片复制
     */
    @Test
    public void test() throws IOException {
        // 1.创建流对象
        // 1.1 指定数据源
        FileInputStream fis = new FileInputStream("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\t4.jpg");
        // 1.2 指定目的地
        FileOutputStream fos = new FileOutputStream("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\test_copy.jpg");

        // 2.读写数据
        // 2.1 定义数组
        byte[] b = new byte[1024];
        // 2.2 定义长度
        int len;
        // 2.3 循环读取
        while ((len = fis.read(b)) != -1) {
            // 2.4 写出数据
            fos.write(b, 0, len);
        }

        // 3.关闭资源
        fos.close();
        fis.close();
    }

}
