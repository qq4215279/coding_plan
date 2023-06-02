/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile.old;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * FileReaderWriter
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:37
 */
public class a2_2FileReaderWriter {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testFileReaderWriter() throws IOException {
        FileReader fr = new FileReader(certificateNum);
        FileWriter fw = new FileWriter(certificateNum2);

        int len = 0;
        char[] chars = new char[1024];

        try {
            while ((len = fr.read(chars)) != -1) {
                System.out.println("chars: " + chars);
                System.out.println("chars.toString(): " + chars.toString());

                fw.write(chars);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fr.close();
            fw.close();
        }
    }

}
