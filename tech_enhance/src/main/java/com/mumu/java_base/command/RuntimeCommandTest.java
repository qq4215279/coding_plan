/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * RuntimeCommandTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/12/22 21:22
 */
public class RuntimeCommandTest {
    public static void main(String[] args) {
        try {
            String command = "python D:\\Code\\PythonWorkSpace\\study_python\\py_study_test\\daily\\command.py";  // 你要执行的 Python 脚本
            String command2 = "python -c from D:\\Code\\PythonWorkSpace\\study_python\\py_study_test\\daily\\command import my_function; my_function()";
            Process process = Runtime.getRuntime().exec(command2);

            // 获取命令执行的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令执行完成
            int exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
