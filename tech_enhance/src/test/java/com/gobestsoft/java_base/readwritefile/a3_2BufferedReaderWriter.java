/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import org.junit.Test;

import java.io.*;

/**
 * BufferedReaderWriter
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:37
 */
public class a3_2BufferedReaderWriter {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testBufferedReaderWriter() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(certificateNum));
        BufferedWriter bw = new BufferedWriter(new FileWriter(certificateNum2));

        String line;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        try {
            while ((line = br.readLine()) != null) {
                count++;
                sb.append("'" + line + "',");
                System.out.println(line);

                // 换行方式1：
                // bw.write("'" + line + "',\r\n");
                // 换行方式2：
                bw.write("'" + line + "',");
                bw.newLine();

                bw.flush();
            }
            bw.flush();
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            bw.close();
        }

        System.out.println(sb);
        System.out.println("-----> " + count);
    }


}
