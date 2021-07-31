/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileInputOutputStream
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:36
 */
public class a2_1FileInputOutputStream {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testFileInputOutputStream() throws IOException {

        FileInputStream fis = new FileInputStream(certificateNum);
        FileOutputStream fos = new FileOutputStream(certificateNum2, true);

        int len = 0;
        byte[] bytes = new byte[1024];

        try {
            while ((len = fis.read(bytes)) != -1) {

                System.out.println("bytes: " + bytes);
                System.out.println("bytesToString: " + bytes.toString());

                fos.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
    }


}
