/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * PropertiesTest
 * 属性集 Properties类
 * @author liuzhen
 * @version 1.0.0 2021/12/26 18:13
 */
public class PropertiesTest {

    /**
     * 概述：java.util.Properties 继承于 Hashtable ，来表示一个持久的属性集。它使用键值结构存储数据，每个键及其 对应值都是一个字符串。该类也被许多Java类使用，
     * 比如获取系统属性时， System.getProperties 方法就是返回 一个 Properties 对象。
     *
     * Properties类
     * 构造方法：public Properties() :创建一个空的属性列表
     * 基本的存储方：
     * public Object setProperty(String key, String value)： 保存一对属性。
     * public String getProperty(String key)：使用此属性列表中指定的键搜索属性值。
     * public Set<String> stringPropertyNames()：所有键的名称的集合。
     */
    @Test
    public void test01() {
        // 0. 创建属性集对象
        Properties properties = new Properties();
        // 1. 添加键值对元素
        properties.setProperty("filename", "a.txt");
        properties.setProperty("length", "209385038");
        properties.setProperty("location", "D:\\a.txt");

        // 2. 打印属性集对象
        System.out.println(properties); // 通过键,获取属性值
        System.out.println(properties.getProperty("filename"));
        System.out.println(properties.getProperty("length"));
        System.out.println(properties.getProperty("location"));

        // 3. 遍历属性集,获取所有键的集合
        Set<String> strings = properties.stringPropertyNames();
        // 打印键值对
        for (String key : strings) {
            System.out.println(key + " ‐‐ " + properties.getProperty(key));
        }
    }

    /**
     * 与流相关的方法:
     * public void load(InputStream inStream)：从字节输入流中读取键值对。
     * 小贴士：文本中的数据，必须是键值对形式，可以使用空格、等号、冒号等符号分隔。
     *
     * 注意：
     * 使用JDK自带Properties 工具类加载配置文件时，如果value值为中文，得将中文转成ASCII编码，不然读取出的value会乱码！！！
     * 转码地址: https://www.ip138.com/ascii/
     */
    @Test
    public void test02() throws IOException {
        // 创建属性集对象
        Properties pro = new Properties();
        // 加载文本中信息到属性集
        pro.load(new FileInputStream("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\sql.properties"));

        // 遍历集合并打印
        Set<String> strings = pro.stringPropertyNames();
        for (String key : strings) {
            System.out.println(key + " ‐‐ " + pro.getProperty(key));
        }
    }

}
