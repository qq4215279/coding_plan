/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile.old;

import org.junit.Test;

import java.io.*;

public class a0ReadDemo01 {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    /* BufferedWriter BufferedReader */
    @Test
    public void testBufferedReaderBufferedWriter() throws IOException { // 字符操作不能写？ 待跟进...    ->解决：写操作没有释放流动作，会写不到文件中！
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
                bw.write("'" + line + "',\r\n");
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

    @Test
    public void testBufferedReader() throws IOException {
        //        BufferedReader br = new BufferedReader(new FileReader("H:\\MajorData\\IDEAWorkspace\\tools\\WorkGoing\\readfile\\personID.txt"));
        BufferedReader br = new BufferedReader(new FileReader(certificateNum));
        String line;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line + ",");
                count++;
            }
        } catch (IOException e) {
            br.close();
        } finally {
        }
        System.out.println(sb);
        System.out.println("----->" + count);
    }

    @Test
    public void testFileInputStreamFileOutputStream1() throws IOException {  // fis可读数字,fos可写数字
        FileInputStream fis = new FileInputStream(certificateNum);
        FileOutputStream fos = new FileOutputStream(certificateNum2);
        int len = 0;
        byte[] bytes = new byte[1024];
        int count = 0;
        try {
            while ((len = fis.read(bytes)) != -1) {
                System.out.println("len: " + len);
                System.out.println(bytes.toString());
                fos.write(bytes);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
        System.out.println("finalLen: " + len); // -1
        System.out.println("----->" + count);
        //        System.out.println(sb);
    }

    @Test
    public void testFileInputStreamFileOutputStream() throws IOException { //结论：fis可以读中文，但是fos写中文会出现乱码。
        FileInputStream fis = new FileInputStream(certificateNum);
        FileOutputStream fos = new FileOutputStream(certificateNum2);
        int len = 0;
        byte[] bytes = new byte[1024];
        int count = 0;
        try {
            while ((len = fis.read(bytes)) != -1) {
                //            System.out.println(new String(bytes));
                System.out.println("--->" + bytes);
                fos.write(bytes);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
        //        System.out.println(sb);
        System.out.println("----->" + count);
    }

    @Test
    public void testFileReaderWriter1() throws IOException { //结论：FileReader可以读数字，但是不能用FileWriter写数字（为空）
        FileReader fr = new FileReader(certificateNum);
        FileWriter fw = new FileWriter(certificateNum2);
        int len = 0;
        char[] chars = new char[1024];
        int count = 0;
        try {
            while ((len = fr.read(chars)) != -1) {
                System.out.println(new String(chars));
                fw.write(new String(chars));
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fr.close();
            fw.close();
        }
        //        System.out.println(sb);
        System.out.println("----->" + count);
    }

    @Test
    public void testFileReaderWriter() throws IOException { //结论：fr可读中文，fw不能写中文（为空）
        FileReader fr = new FileReader(certificateNum);
        FileWriter fw = new FileWriter(certificateNum2);
        int len = 0;
        char[] chars = new char[1024];
        int count = 0;
        try {
            while ((len = fr.read(chars)) != -1) {
                System.out.println(new String(chars));
                fw.write("abc");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fr.close();
            fw.close();
        }
        //        System.out.println(sb);
        System.out.println("----->" + count);
    }

    @Test
    public void testBufferedReaderWriter() throws IOException { //结论：bw可读中文，但是bw不能写中文（为空）
        BufferedReader br = new BufferedReader(new FileReader(certificateNum));
        BufferedWriter bw = new BufferedWriter(new FileWriter(certificateNum2));
        String line;
        int count = 0;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                bw.write(line);
                bw.newLine();
                count++;
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
