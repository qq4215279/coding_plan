/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile.old;

import org.junit.Test;

import java.io.*;

/**
 * BufferedInputOutputStream
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:37
 */
public class a3_1BufferedInputOutputStream {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testBufferedInputOutputStream() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(certificateNum));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(certificateNum2));

        int len = 0;
        byte[] bytes = new byte[1024];

        try {
            while ((len = bis.read(bytes)) != -1) {
                System.out.println("bytes: " + bytes);
                System.out.println("bytes.toString(): " + bytes.toString()); // 是个地址?
                bos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bis.close();
            bos.close();
        }
    }

}
