/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * ManyThreadCreateFileDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/6/7 17:41
 */
public class ManyThreadCreateFileDemo {

    public static final String path = "F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_base\\readwritefile\\text" +
            "\\config_cannon.xml";

    public static boolean first = true;

    public static void main(String[] args) throws InterruptedException {
        randomAccessFileDemo();

        // fileChannelDemo();
        // Thread.sleep(15000L);

        // fileLockDemo();

    }

    public static void fileLockDemo() {
        FileLock lock = null;
        try {
            FileOutputStream fos = new FileOutputStream(path);
            FileChannel fileChannel = fos.getChannel();
            // 获取文件锁
            lock = fileChannel.tryLock();
            if (lock != null) {
                // 文件被锁定，进行安全的文件操作
                System.out.println("File is locked by current process.");

                // 写入数据
                String content = "Hello, FileChannel with Lock!";
                fos.write(content.getBytes());
                fos.flush();

                Thread.sleep(15000L);

            } else {
                System.out.println("没有拿到锁！");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放文件锁
            try {
                lock.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("File lock released.");
        }
    }

    @Test
    public void test1() {
        Path path = Paths.get("example.txt");

        // 锁定文件并写入数据
        try (FileChannel fileChannel = FileChannel.open(path, java.nio.file.StandardOpenOption.WRITE, java.nio.file.StandardOpenOption.CREATE)) {
            // 获取文件锁
            FileLock lock = fileChannel.lock();
            if (lock != null) {

                try (FileOutputStream fos = new FileOutputStream(path.toFile())) {
                    // 文件被锁定，进行安全的文件操作
                    System.out.println("File is locked by current process.");

                    // 写入数据
                    String content = "Hello, FileChannel with Lock!";
                    fos.write(content.getBytes());
                    fos.flush();
                } finally {
                    // 释放文件锁
                    lock.release();
                    System.out.println("File lock released.");
                }
            } else {
                System.out.println("Failed to acquire lock.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取被锁定的文件内容
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                System.out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void fileChannelDemo() {
        Path p = Paths.get(path);

        try (FileChannel fileChannel = FileChannel.open(p, StandardOpenOption.WRITE)) {
            // 尝试获取文件锁
            FileLock lock = fileChannel.tryLock();
            if (lock != null) {
                try {
                    // 文件未被其他进程锁定
                    System.out.println("File is not locked by other processes.");
                    // 执行文件操作


                    if (first) {
                        first = false;
                        Thread.sleep(10000L);

                    }
                } finally {
                    lock.release();
                }
            } else {
                // 文件已被其他进程锁定
                System.out.println("File is locked by another process.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkFileWorking(String filePath) {
        boolean isWorking = true;

        Path p = Paths.get(filePath);
        try (FileChannel fileChannel = FileChannel.open(p, StandardOpenOption.WRITE)) {
            // 尝试获取文件锁
            FileLock lock = fileChannel.tryLock();
            if (lock != null) {
                try {
                    // 文件未被其他进程锁定
                    System.out.println("File is not locked by other processes.");
                    // 执行文件操作


                    if (first) {
                        first = false;
                        Thread.sleep(10000L);

                    }
                } finally {
                    lock.release();
                }
            } else {
                // 文件已被其他进程锁定
                System.out.println("File is locked by another process.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return isWorking;
    }

    public static void randomAccessFileDemo() {
        for (int i = 0; i < 1; i++) {
                new Thread(() -> {
                    try (RandomAccessFile file = new RandomAccessFile(path, "rw")) {
                        byte[] b = new byte[256];
                        while (file.read(b) != -1) {
                            System.out.println(b.toString());
                            Thread.sleep(10000L);
                        }



                        // 文件未被其他进程锁定
                        System.out.println("File is not locked by other processes.");
                        Thread.sleep(10000L);
                    } catch (IOException | InterruptedException e) {
                        // 文件已被其他进程锁定
                        System.out.println("锁住 File is locked by another process.");
                    }
                }).start();
        }
    }

}
