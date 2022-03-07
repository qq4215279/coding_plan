/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.jdk_api.lang;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * SystemTest
 * System静态类常用api
 * @author liuzhen
 * @version 1.0.0 2021/6/30 7:43
 */
public class SystemTest {

    /**
     * base api
     */
    @Test
    public void apiTest() {
        // 1. currentTimeMillis()：获取当前系统时间与1970年01月01日00:00点之间的毫秒差值
        long curTime = System.currentTimeMillis();

        // 2. nanoTime(): 返回正在运行的Java虚拟机的高分辨率时间源的当前值，以纳秒为单位。
        long nanoTime = System.nanoTime();

        // 3. arraycopy(): 用来实现将源数组部分元素复制到目标数组的指定位置
        int[] src = new int[]{1,2,3,4,5};
        int[] dest = new int[]{6,7,8,9,10};
        System.arraycopy( src, 0, dest, 0, 3); // dest复制后：[1,2,3,9,10]

        // 4. gc(): Java的GC是由JVM自行调动的，在需要的时候才执行，上面的指令只是告诉JVM尽快GC一次，但不会立即执行GC。
//        System.gc();

        System.out.println("----------------------->");

        // 5.1 getenv(): 返回当前系统环境的不可修改的字符串映射视图。
        // 5.2 getenv(String key): 获取指定环境变量的值。
        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);

        System.out.println("ProgramData: " + System.getenv("ProgramData"));
        System.out.println("GOPATH: " + System.getenv("GOPATH"));

    }

    /**
     * exit(int status) 用来结束正在运行的Java程序。参数传入一个数字即可。通常传入0记为正常状态，其他为异常状态
     */
    @Test
    public void exitTest() {
        Random random = new Random();
        while(true){
            int number = random.nextInt(900)+100; // 0-899 + 100
            System.out.println("num: " + number);
            if (number % 10 == 0) {
                System.out.println("我要退出了！！！");
                System.exit(0);
            }

        }
    }

    /**
     * setProperty(String key, String value): 设置指定键指示的系统属性。
     * getProperty(String key): 用来获取指定键(字符串名称)中所记录的系统属性信息
     * clearProperty​(String key): 删除指定键指示的系统属性。
     *
     * 系统属性：
     * java.version                         Java运行时环境版本
     * java.vendor                          Java运行时环境供应商
     * java.vendor.url                      Java供应商的 URL
     * java.home                            Java安装目录
     * java.vm.specification.version        Java虚拟机规范版本
     * java.vm.specification.vendor         Java虚拟机规范供应商
     * java.vm.specification.name           Java虚拟机规范名称
     * java.vm.version                      Java虚拟机实现版本
     * java.vm.vendor                       Java虚拟机实现供应商
     * java.vm.name                         Java虚拟机实现名称
     * java.specification.version           Java运行时环境规范版本
     * java.specification.vendor            Java运行时环境规范供应商
     * java.specification.name              Java运行时环境规范名称
     * java.class.version                   Java类格式版本号
     * java.class.path                      Java类路径
     * java.library.path                    加载库时搜索的路径列表
     * java.io.tmpdir                       默认的临时文件路径
     * java.compiler                        要使用的 JIT 编译器的名称
     * java.ext.dirs                        一个或多个扩展目录的路径
     * os.name                              操作系统的名称
     * os.arch                              操作系统的架构
     * os.version                           操作系统的版本
     * file.separator                       文件分隔符（在 UNIX 系统中是“/”）
     * path.separator                       路径分隔符（在 UNIX 系统中是“:”）
     * line.separator                       行分隔符（在 UNIX 系统中是“/n”）
     * user.name                            用户的账户名称
     * user.home                            用户的主目录
     * user.dir                             用户的当前工作目录
     */
    @Test
    public void propertyTest() {
        String javaVersion = System.getProperty("java.version");
        System.out.println(javaVersion);

        String property = System.getProperty("user.home");
        System.out.println(property);

        // 2. setProperties()的使用:
        Properties pro = new Properties();
        pro.setProperty("helloProperty", "Hello Properties");
        System.setProperties(pro);
        System.out.println(System.getProperty("helloProperty")); // Hello Properties

        // 3. clearProperty():
        System.clearProperty("helloProperty");
        System.out.println(System.getProperty("helloProperty")); // null
    }

}
