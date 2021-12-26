/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import org.junit.Test;

import java.io.*;

/**
 * _4_ISR_OSW
 *  转换流：InputStreamReader & OutputStreamWriter
 * @author liuzhen
 * @version 1.0.0 2021/12/26 18:55
 */
public class _4_ISR_OSW {

    /**
     * 字符编码: 计算机中储存的信息都是用二进制数表示的，而我们在屏幕上看到的数字、英文、标点符号、汉字等字符是二进制 数转换之后的结果。按照某种规则，将字符存储到计算机中，称为编码 。
     * 反之，将存储在计算机中的二进制数按照 某种规则解析显示出来，称为解码 。
     * 比如说，按照A规则存储，同样按照A规则解析，那么就能显示正确的文本f符号。反之，按照A规则存储，再按照B规则解析，就会导致乱码现象。
     * 字符编码 Character Encoding : 就是一套自然语言的字符与二进制数之间的对应规则。
     *
     * 字符集字符集 Charset ：也叫编码表。是一个系统支持的所有字符的集合，包括各国家文字、标点符号、图形符号、数字等。
     * 计算机要准确的存储和识别各种字符集符号，需要进行字符编码，一套字符集必然至少有一套字符编码。常见字符 集有ASCII字符集、GBK字符集、Unicode字符集等。
     * 可见，当指定了编码，它所对应的字符集自然就指定了，所以编码才是我们最终要关心的。
     * ASCII字符集：
     *   ASCII（American Standard Code for Information Interchange，美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统，用于显示现代英语，
     *      主要包括控制字符（回车键、退格、换行键等）和可显 示字符（英文大小写字符、阿拉伯数字和西文符号）。
     *   基本的ASCII字符集，使用7位（bits）表示一个字符，共128字符。ASCII的扩展字符集使用8位（bits） 表示一个字符，共256字符，方便支持欧洲常用字符。
     *
     * ISO-8859-1字符集：
     *   拉丁码表，别名Latin-1，用于显示欧洲使用的语言，包括荷兰、丹麦、德语、意大利语、西班牙语等。
     *   ISO-5559-1使用单字节编码，兼容ASCII编码。
     * GBxxx字符集：
     *   GB就是国标的意思，是为了显示中文而设计的一套字符集。
     *   GB2312：简体中文码表。一个小于127的字符的意义与原来相同。但两个大于127的字符连在一起时， 就表示一个汉字，这样大约可以组合了包含7000多个简体汉字，此外数学符号、罗马希腊的字母、日文
     *      的假名们都编进去了，连在ASCII里本来就有的数字、标点、字母都统统重新编了两个字节长的编码，这就是常说的"全角"字符，而原来在127号以下的那些就叫"半角"字符了。
     *   GBK：最常用的中文码表。是在GB2312标准基础上的扩展规范，使用了双字节编码方案，共收录了 21003个汉字，完全兼容GB2312标准，同时支持繁体汉字以及日韩汉字等。
     *   GB18030：最新的中文码表。收录汉字70244个，采用多字节编码，每个字可以由1个、2个或4个字节 组成。支持中国国内少数民族的文字，同时支持繁体汉字以及日韩汉字等。
     * Unicode字符集 ：
     *   Unicode编码系统为表达任意语言的任意字符而设计，是业界的一种标准，也称为统一码、标准万国码。
     *   它最多使用4个字节的数字来表达每个字母、符号，或者文字。有三种编码方案，UTF-8、UTF-16和UTF- 32。最为常用的UTF-8编码。
     *   UTF-8编码，可以用来表示Unicode标准中任何字符，它是电子邮件、网页及其他存储或传送文字的应用 中，优先采用的编码。互联网工程工作小组（IETF）要求所有互联网协议都必须支持UTF-8编码。
     *      所以， 我们开发Web应用，也要使用UTF-8编码。它使用一至四个字节为每个字符编码，
     *      编码规则：
     *        1. 128个US-ASCII字符，只需一个字节编码。
     *        2. 拉丁文等字符，需要二个字节编码。
     *        3. 大部分常用字（含中文），使用三个字节编码。
     *        4. 其他极少使用的Unicode辅助字符，使用四字节编码。
     */

    /**
     * 编码引出的问题:
     * 在IDEA中，使用 FileReader 读取项目中的文本文件。由于IDEA的设置，都是默认的 UTF-8 编码，所以没有任何 问题。但是，当读取Windows系统中创建的文本文件时，
     * 由于Windows系统的默认是GBK编码，就会出现乱码。
     */
    @Test
    public void question() throws IOException {
        FileReader fileReader = new FileReader("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\File_GBK.txt");
        int read;
        while ((read = fileReader.read()) != -1){
            System.out.print((char)read); // ����GBK ������
        }
        fileReader.close();
    }

    /**
     *  InputStreamReader类
     *  转换流 java.io.InputStreamReader ，是Reader的子类，是从字节流到字符流的桥梁。它读取字节，并使用指定 的字符集将其解码为字符。它的字符集可以由名称指定，也可以接受平台的默认字符集。
     *  构造方法：
     *  InputStreamReader(InputStream in): 创建一个使用默认字符集的字符流。
     *  InputStreamReader(InputStream in, String charsetName): 创建一个指定字符集的字符流。
     *
     *  OutputStreamWriter类
     *  转换流 java.io.OutputStreamWriter ，是Writer的子类，是从字符流到字节流的桥梁。使用指定的字符集将字符 编码为字节。它的字符集可以由名称指定，也可以接受平台的默认字符集。
     *  构造方法：
     *  OutputStreamWriter(OutputStream in): 创建一个使用默认字符集的字符流。
     *  OutputStreamWriter(OutputStream in, String charsetName): 创建一个指定字符集的字符流。
     */
    @Test
    public void inputStreamReader() throws IOException {
        // 1.定义文件路径
        String srcFile = "src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\File_GBK.txt";
        String destFile = "src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\File_utf8.txt";

        // 2.创建流对象
        // 2.1 转换输入流,指定GBK编码
        InputStreamReader isr = new InputStreamReader(new FileInputStream(srcFile), "GBK");
        // 2.2 转换输出流,默认utf8编码
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile));

        // 3.读写数据
        // 3.1 定义数组
        char[] cbuf = new char[1024];
        // 3.2 定义长度
        int len;
        // 3.3 循环读取
        while ((len = isr.read(cbuf)) != -1) {
            // 循环写出
            osw.write(cbuf, 0, len);
        }

        // 4.释放资源
        osw.close();
        isr.close();
    }

}
