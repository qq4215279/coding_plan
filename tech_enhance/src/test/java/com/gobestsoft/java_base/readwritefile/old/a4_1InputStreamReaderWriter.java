/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile.old;

import org.junit.Test;

import java.io.*;

/**
 * InputStreamReaderWriter
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:38
 */
public class a4_1InputStreamReaderWriter {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testInputStreamReaderWriter() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream(certificateNum));
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(certificateNum2));

        int len = 0;
        char[] chars = new char[1024];

        try {
            while ((len = isr.read(chars)) != -1) {
                System.out.println("chars: " + chars);
                System.out.println("chars.toString(): " + chars.toString());

                osw.write(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isr.close();
            osw.close();
        }
    }



}

