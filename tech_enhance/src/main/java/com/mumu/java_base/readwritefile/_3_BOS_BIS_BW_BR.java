/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * _3_BOS_BIS_BW_BR
 * 缓冲流：字节缓冲流(BufferedInputStream BufferedOutputStream) & 字符缓冲流(BufferedReader BufferedWriter)
 * @author liuzhen
 * @version 1.0.0 2021/12/26 18:23
 */
public class _3_BOS_BIS_BW_BR {

    /**
     * 缓冲流：
     * 缓冲流,也叫高效流，是对4个基本的 FileXxx 流的增强，所以也是4个流，
     * 按照数据类型分类：
     *      字节缓冲流： BufferedInputStream ， BufferedOutputStream
     *      字符缓冲流： BufferedReader ， BufferedWriter
     * 缓冲流的基本原理，是在创建流对象时，会创建一个内置的默认大小的缓冲区数组，通过缓冲区读写，减少系统IO 次数，从而提高读写的效率。
     */

    /**
     * 字节缓冲流
     * 构造方法:
     * public BufferedInputStream(InputStream in)：创建一个 新的缓冲输入流。
     * public BufferedOutputStream(OutputStream out): 创建一个新的缓冲输出流。
     */
    @Test
    public void BufferedDemo0() {
        // 记录开始时间
        long start = System.currentTimeMillis();
        // 创建流对象
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("jdk9.exe"));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("copy.exe"))) {
            // 1. 读写数据
            /*int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }*/

            // 2. 使用数组的方式 读写数据
            int len;
            byte[] bytes = new byte[8 * 1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 记录结束时间
        long end = System.currentTimeMillis();
        System.out.println("缓冲流使用数组复制时间:" + (end - start) + " 毫秒");
    }

    /**
     * 字符缓冲流
     * 构造方法：
     * public BufferedReader(Reader in) ：创建一个 新的缓冲输入流。
     * public BufferedWriter(Writer out) ： 创建一个新的缓冲输出流。
     *
     * 特有方法
     * 字符缓冲流的基本方法与普通字符流调用方式一致，不再阐述，我们来看它们具备的特有方法。
     * BufferedReader：public String readLine(): 读一行文字。
     * BufferedWriter：public void newLine(): 写一行行分隔符,由系统属性定义符号。
     */
    @Test
    public void BufferedDemo() throws IOException {
        /*
        * 问题：
        * 1. 逐行读取文本信息。
        * 2. 解析文本信息到集合中。
        * 3. 遍历集合，按顺序，写出文本信息。
        */

        // 创建map集合,保存文本数据,键为序号,值为文字
        Map<String, String> lineMap = new HashMap<>();
        // 创建流对象
        BufferedReader br = new BufferedReader(new FileReader("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\in.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\out.txt"));

        // 读取数据
        String line = null;
        while ((line = br.readLine()) != null) {
            // 解析文本
            String[] split = line.split("\\.");
            // 保存到集合
            lineMap.put(split[0], split[1]);
        }

        // 遍历map集合
        for (int i = 1; i <= lineMap.size(); i++) {
            String key = String.valueOf(i);
            // 获取map中文本
            String value = lineMap.get(key);
            // 写出拼接文本
            bw.write(key + "." + value);
            // 写出换行
            bw.newLine();
        }

        // 释放资源
        br.close();
        bw.close();
    }

}
