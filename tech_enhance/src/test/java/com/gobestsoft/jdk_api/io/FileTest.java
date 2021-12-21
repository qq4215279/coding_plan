/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.jdk_api.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * FileTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/21 23:03
 */
public class FileTest {

    /**
     * 构造方法：
     * public File(String pathname) ：通过将给定的路径名字符串转换为抽象路径名来创建新的 File实例。
     * public File(String parent, String child) ：从父路径名字符串和子路径名字符串创建新的 File实例。
     * public File(File parent, String child) ：从父抽象路径名和子路径名字符串创建新的 File实例。
     * <p>
     * 小贴士： 1. 一个File对象代表硬盘中实际存在的一个文件或者目录。 2. 无论该路径下是否存在文件或者目录，都不影响File对象的创建。
     */
    @Test
    public void test01() {
        // 文件路径名
        String pathname = "D:\\Code\\IdeaWorkSpace\\dream_plan\\tech_enhance\\src\\test\\java\\com\\gobestsoft\\jdk_api\\io\\text\\a.txt";
        File file1 = new File(pathname);

        // 文件路径名
        String pathname2 = "D:/Code/IdeaWorkSpace/dream_plan/tech_enhance/src/test/java/com/gobestsoft/jdk_api/io/text/b.txt";
        File file2 = new File(pathname2);

        // 通过父路径和子路径字符串
        String parent = "./text";
        String child1 = "c.txt";
        File file3 = new File(parent, child1);

        // 通过父级File对象和子路径字符串
        File parentDir = new File("./text/haha");
        String child2 = "d.txt";
        File file4 = new File(parentDir, child2);
    }

    /**
     * 获取功能的方法
     * public String getAbsolutePath() ：返回此File的绝对路径名字符串。
     * public String getPath() ：将此File转换为路径名字符串。
     * public String getName() ：返回由此File表示的文件或目录的名称。
     * public long length() ：返回由此File表示的文件的长
     * <p>
     * API中说明：length()，表示文件的长度。但是File对象表示目录，则返回值未指定。
     */
    @Test
    public void getMethodTest() {
        File f = new File("D:\\Code\\IdeaWorkSpace\\dream_plan\\tech_enhance\\src\\test\\java\\com\\gobestsoft\\jdk_api\\io\\text\\f.java");
        System.out.println("文件绝对路径:" + f.getAbsolutePath());
        System.out.println("文件构造路径:" + f.getPath());
        System.out.println("文件名称:" + f.getName());
        System.out.println("文件长度:" + f.length() + "字节");

        File f2 = new File("D:/Code/IdeaWorkSpace/dream_plan/tech_enhance/src/test/java/com/gobestsoft/jdk_api/io/text/e.txt");
        System.out.println("目录绝对路径:" + f2.getAbsolutePath());
        System.out.println("目录构造路径:" + f2.getPath());
        System.out.println("目录名称:" + f2.getName());
        System.out.println("目录长度:" + f2.length());
    }

    /**
     * 判断功能的方法
     * public boolean exists() ：此File表示的文件或目录是否实际存在。
     * public boolean isDirectory() ：此File表示的是否为目录。
     * public boolean isFile() ：此File表示的是否为文件。
     */
    @Test
    public void judgeMethodTest() {
        File f = new File("D:\\Code\\IdeaWorkSpace\\dream_plan\\tech_enhance\\src\\test\\java\\com\\gobestsoft\\jdk_api\\io\\text\\g.java");
        File f2 = new File("D:/Code/IdeaWorkSpace/dream_plan/tech_enhance/src/test/java/com/gobestsoft/jdk_api/io/text/bb");

        // 判断是否存在
        System.out.println("g.java 是否存在:" + f.exists());
        System.out.println("text/bb 是否存在:" + f2.exists());

        // 判断是文件还是目录
        System.out.println("text/bb 文件?:" + f2.isFile());
        System.out.println("text/bb 目录?:" + f2.isDirectory());
    }

    /**
     * 创建删除功能的方法
     * public boolean createNewFile() ：当且仅当具有该名称的文件尚不存在时，创建一个新的空文件。
     * public boolean delete() ：删除由此File表示的文件或目录。
     * public boolean mkdir() ：创建由此File表示的目录。
     * public boolean mkdirs() ：创建由此File表示的目录，包括任何必需但不存在的父目录。
     * <p>
     * API中说明：delete方法，如果此File表示目录，则目录必须为空才能删除。
     */
    @Test
    public void crudMethod() throws IOException {
        // 文件的创建
        File f = new File("D:/Code/IdeaWorkSpace/dream_plan/tech_enhance/src/test/java/com/gobestsoft/jdk_api/io/text/ff.txt");
        System.out.println("是否存在:" + f.exists()); // false
        System.out.println("是否创建:" + f.createNewFile()); // true
        System.out.println("是否存在:" + f.exists()); // true

        // 目录的创建
        File f2 = new File("D:/Code/IdeaWorkSpace/dream_plan/tech_enhance/src/test/java/com/gobestsoft/jdk_api/io/text/newDir");
        System.out.println("是否存在:" + f2.exists()); // false
        System.out.println("是否创建:" + f2.mkdir()); // true
        System.out.println("是否存在:" + f2.exists()); // true

        // 创建多级目录
        File f3 = new File(
            "D:\\Code\\IdeaWorkSpace\\dream_plan\\tech_enhance\\src\\test\\java\\com\\gobestsoft\\jdk_api\\io\\text\\newDir2\\newDir22");
        System.out.println(f3.mkdir()); // false
        File f4 = new File(
            "D:\\Code\\IdeaWorkSpace\\dream_plan\\tech_enhance\\src\\test\\java\\com\\gobestsoft\\jdk_api\\io\\text\\newDir2\\newDir22");
        System.out.println(f4.mkdirs()); // true

        // 文件的删除
        System.out.println(f.delete()); // true

        // 目录的删除
        System.out.println(f2.delete());// true
        System.out.println(f4.delete());// false
    }

    /**
     * 目录的遍历
     * public String[] list() ：返回一个String数组，表示该File目录中的所有子文件或目录。
     * public File[] listFiles() ：返回一个File数组，表示该File目录中的所有的子文件或目录。
     * <p>
     * 小贴士： 调用listFiles方法的File对象，表示的必须是实际存在的目录，否则返回null，无法进行遍历。
     */
    @Test
    public void getMethod() {
        File dir = new File("d:\\java_code");
        // 获取当前目录下的文件以及文件夹的名称。
        String[] names = dir.list();
        for (String name : names) {
            System.out.println(name);

        }

        // 获取当前目录下的文件以及文件夹对象，只要拿到了文件对象，那么就可以获取更多信息
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file);
        }
    }

}
