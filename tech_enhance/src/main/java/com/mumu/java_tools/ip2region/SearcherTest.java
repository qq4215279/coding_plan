/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.ip2region;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * SearcherTest
 * ip2region ip解析工具
 *
 * @author liuzhen
 * @version 1.0.0 2024/3/5 15:30
 */
public class SearcherTest {

    /**
     * 介绍：https://blog.csdn.net/weixin_45081575/article/details/135118302
     * 源码地址: https://github.com/lionsoul2014/ip2region
     *          https://github.com/lionsoul2014/ip2region/tree/master
     * @param args args
     * @return void
     * @date 2024/3/5 15:32
     */
    public static void main(String[] args) throws IOException {
        // 1、创建 searcher 对象
        String dbPath = "F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_tools\\ip2region\\data\\ip2region.xdb";
        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、查询
        // String ip = "1.2.3.4";
        // String ip = "2408:840d:1320:3b8d:6cc5:8ff:fe8b:ff6";
        String ip = "37.61.54.158";
        // String ip = "116.232.78.242";
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        searcher.close();

        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
    }
}
