/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * _2_FR_FW
 * 字符流：FileReader类 & FileWriter类
 * @author liuzhen
 * @version 1.0.0 2021/12/26 17:39
 */
public class _2_FR_FW {

    /**
     * 当使用字节流读取文本文件时，可能会有一个小问题。就是遇到中文字符时，可能不会显示完整的字符，那是因为 一个中文字符可能占用多个字节存储。
     * 所以Java提供一些字符流类，以字符为单位读写数据，专门用于处理文本文件。
     *
     * 字符输入流【Reader】
     * java.io.Reader 抽象类是表示用于读取字符流的所有类的超类，可以读取字符信息到内存中。它定义了字符输入 流的基本共性功能方法。
     * public void close() ：关闭此流并释放与此流相关联的任何系统资源。
     * public int read() ： 从输入流读取一个字符。
     * public int read(char[] cbuf) ： 从输入流中读取一些字符，并将它们存储到字符数组 cbuf中 。
     *
     * 字符输出流【Writer】
     * java.io.Writer 抽象类是表示用于写出字符流的所有类的超类，将指定的字符信息写出到目的地。它定义了字节 输出流的基本共性功能方法。
     * void write(int c) 写入单个字符。
     * void write(char[] cbuf) 写入字符数组。
     * abstract void write(char[] cbuf, int off, int len) 写入字符数组的某一部分,off数组的开始索引,len 写的字符个数。
     * void write(String str) 写入字符串。1
     * void write(String str, int off, int len) 写入字符串的某一部分,off字符串的开始索引,len写的字符个数。
     * void flush() 刷新该流的缓冲。
     * void close() 关闭此流，但要先刷新它。
     */

    /**
     * FileReader类
     * java.io.FileReader 类是读取字符文件的便利类。构造时使用系统默认的字符编码和默认字节缓冲区。
     * 小贴士：1. 字符编码：字节与字符的对应规则。Windows系统的中文编码默认是GBK编码表。 idea中UTF-8 2. 字节缓冲区：一个字节数组，用来临时存储字节数据。
     *
     * 构造方法
     * FileReader(File file)：创建一个新的 FileReader ，给定要读取的File对象。
     * FileReader(String fileName)：创建一个新的 FileReader ，给定要读取的文件的名称。 当你创建一个流对象时，必须传入一个文件路径。类似于FileInputStream 。
     *
     * 读取字符数据:
     * 1. 读取字符： read() 方法，每次可以读取一个字符的数据，提升为int类型，读取到文件末尾，返回 -1 ，循环读取
     * 小贴士：虽然读取了一个字符，但是会自动提升为int类型。
     * 2. 使用字符数组读取： read(char[] cbuf) ，每次读取b的长度个字符到数组中，返回读取到的有效字符个数， 读取到末尾时，返回 -1
     */
    @Test
    public void readTest() throws IOException {
        // 使用文件名称创建流对象
        FileReader fr = new FileReader("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\read2.txt");

        // 1. read()
        // 定义变量，保存数据
        /*int b;
        // 循环读取
        while ((b = fr.read()) != -1) {
            System.out.println((char)b);
        }

        System.out.println("---------->");*/

        // 2.  read(char[] cbuf)
        // 定义变量，保存有效字符个数
        int len;
        // 定义字符数组，作为装字符数据的容器
        char[] cbuf = new char[2];
        // 循环读取
        while ((len = fr.read(cbuf)) != -1) {
            // System.out.println(new String(cbuf)); // 你好 啊， 中文 字符 啊符

            System.out.println(new String(cbuf, 0, len)); // 你好 啊， 中文 字符 啊
        }


        // 关闭资源
        fr.close();
    }

    /**
     * FileWriter类
     * java.io.FileWriter 类是写出字符到文件的便利类。构造时使用系统默认的字符编码和默认字节缓冲区。
     * 构造方法：
     * FileWriter(File file)：创建一个新的 FileWriter，给定要读取的File对象。
     * FileWriter(String fileName)：创建一个新的 FileWriter，给定要读取的文件的名称。 当你创建一个流对象时，必须传入一个文件路径，类似于FileOutputStream。
     *
     * 基本写出数据写出字符：
     * write(int b) 方法，每次可以写出一个字符数据
     * 【注意】关闭资源时,与FileOutputStream不同。 如果不关闭,数据只是保存到缓冲区，并未保存到文件。
     * 小贴士： 1. 虽然参数为int类型四个字节，但是只会保留一个字符的信息写出。 2. 未调用close方法，数据只是保存到了缓冲区，并未写出到文件中。
     *
     * 关闭和刷新
     * 因为内置缓冲区的原因，如果不关闭输出流，无法写出字符到文件中。但是关闭的流对象，是无法继续写出数据的。如果我们既想写出数据，又想继续使用流，就需要 flush 方法了。
     *  flush ：刷新缓冲区，流对象可以继续使用。小贴士：即便是flush方法写出了数据，操作的最后还是要调用close方法，释放系统资源。
     *  close :先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。
     *
     * 写出其他数据
     * 1. 写出字符数组：write(char[] cbuf) 和 write(char[] cbuf, int off, int len) ，每次可以写出字符数 组中的数据，用法类似FileOutputStream
     * 2. 写出字符串：write(String str) 和 write(String str, int off, int len) ，每次可以写出字符串中的数据
     *
     * 续写和换行：操作类似于FileOutputStream
     * 小贴士：字符流，只能操作文本文件，不能操作图片，视频等非文本文件。 当我们单纯读或者写文本文件时 使用字符流 其他情况使用字节流
     */
    @Test
    public void writeTest() throws IOException {
        // 使用文件名称创建流对象
        FileWriter fw = new FileWriter("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\fw.txt");

        // 1.1 write(int b) 写出数据
        /*fw.write(97); // 写出第1个字符
        fw.write('b'); // 写出第2个字符
        fw.write('C'); // 写出第3个字符
        fw.write(30000); // 写出第4个字符，中文编码表中30000对应一个汉字。
        // 【注意】关闭资源时,与FileOutputStream不同。 如果不关闭,数据只是保存到缓冲区，并未保存到文件。
        fw.close();
        System.out.println("------------>");*/

        // 1.2 write(int b) 写出数据，通过flush
       /* fw.write('刷'); // 写出第1个字符
        fw.flush();
        fw.write('新'); // 继续写出第2个字符，写出成功
        fw.flush(); // 写出数据，通过close

        fw.write('关'); // 写出第1个字符
        fw.close();
        fw.write('闭'); // 继续写出第2个字符,【报错】java.io.IOException: Stream closed
        fw.close();
        System.out.println("------------------------------>");*/

        // 2.1 write(String str)
        // 字符串转换为字节数组
        char[] chars = "我是程序员".toCharArray();
        // 写出字符数组
        fw.write(chars); // 我是程序员
        // 写出从索引2开始，2个字节。索引2是'程'，两个字节，也就是'程序'。
        fw.write(chars, 2, 2); // 程序

        // 2.2 write(String str, int off, int len)
        String msg = "我爱写代码";
        fw.write(msg);
        fw.write(msg, 0, 2);

        // 写出换行
        fw.write("\r\n");
        // 写出字符串
        fw.write("程序员");
    }
}
