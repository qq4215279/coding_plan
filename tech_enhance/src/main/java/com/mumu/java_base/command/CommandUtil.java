/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * CommandUtil
 * 命令Util
 * @author liuzhen
 * @version 1.0.0 2023/12/23 16:42
 */
public class CommandUtil {

    /**
     * 异步执行命令
     * @param command 命令
     * @return int 执行返回状态码
     * @date 2023/12/23 16:46
     */
    public static void asynExecCommand(String command) {
        new Thread(() -> {
            try {
                // 执行命令
                Process process = Runtime.getRuntime().exec(command);
                // 获取命令执行的输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }

    /**
     * 执行命令
     * @param command 命令
     * @param outInfoList 返回信息列表
     * @return int 执行返回状态码
     * @date 2023/12/23 16:46
     */
    public static int execCommand(String command, List<String> outInfoList) {
        int exitCode = 1;
        try {
            // 执行命令
            Process process = Runtime.getRuntime().exec(command);

            // 获取命令执行的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                outInfoList.add(line);
                System.out.println(line);
            }

            // 等待命令执行完成
            exitCode = process.waitFor();
            System.out.println("Exit Code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            return exitCode;
        }
    }

}
