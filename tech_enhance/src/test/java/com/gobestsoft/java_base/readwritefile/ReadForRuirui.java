/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import org.junit.Test;

import java.io.*;

/**
 * ReadForRuirui
 *
 * @author liuzhen
 * @version 1.0.0 2020/12/3 18:38
 */
public class ReadForRuirui {

    @Test
    public void test07() throws IOException {//结论：bw可读中文，但是bw不能写中文（为空）
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\liuzhen\\Desktop\\新建文件夹 (2)\\R3.txt"));
//        BufferedReader br = new BufferedReader(new FileReader("H:\\MajorData\\IDEAWorkspace\\tools\\WorkGoing\\readfile\\simpleName.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\liuzhen\\Desktop\\新建文件夹 (2)\\Res.txt"));
        String line;
        int count = 0;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("<!-- ")) {
                    bw.write(line);
                    bw.newLine();
                    count++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            bw.close();
        }
        //        System.out.println(sb);
        System.out.println("----->" + count);
    }

}
