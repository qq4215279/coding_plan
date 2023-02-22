/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.readwritefile;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * ReadTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/2/8 20:08
 */
public class ReadTest {

    @Test
    public void test() throws IOException {
        // 创建流对象

        BufferedReader br = new BufferedReader(new FileReader("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\xiaozhao.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\gongsi.txt"));

        BufferedWriter bw1 = new BufferedWriter(new FileWriter("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\out1.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\out2.txt"));
        BufferedWriter bw3 = new BufferedWriter(new FileWriter("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\out3.txt"));

        List<String> list1 = new ArrayList<>();
        // 读取数据

        Set<String> filterSet = getFilterSet();

        String line = null;
        while ((line = br.readLine()) != null) {
            list1.add(line);
        }

        List<String> list2 = new ArrayList<>();
        while ((line = br2.readLine()) != null) {
            list2.add(line);
        }

        Map<String, String> map = new HashMap<>();
        for (String str : list1) {
            if (str.length() < 2) {
                if (filterSet.contains(str)) {
                    continue;
                }
                map.put(str, str);
            } else {
                for (int i = 0; i < str.length() - 2; i++) {
                    String s = str.substring(i, i + 2);
                    if (filterSet.contains(s)) {
                        continue;
                    }
                    map.put(s, str);
                }
            }
        }

        String sql = "INSERT INTO test VALUES(NULL, %s, %s);";

        // String s  = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");

        List<UserEntity> res = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String s = entry.getKey();
            String str = entry.getValue();

            for (String target : list2) {
                if (target.contains(s)) {
                    // 写出拼接文本
                    bw1.write(str);
                    // 写出换行
                    bw1.newLine();

                    bw2.write(target);
                    bw2.newLine();


                    String s3  = String.format("INSERT INTO test VALUES(NULL, '%s', '%s');", str, target);
                    bw3.write(s3);
                    bw3.newLine();

                    UserEntity userEntity = new UserEntity();
                    userEntity.setKey(str);
                    userEntity.setValue(target);
                    res.add(userEntity);
                }
            }
        }

        EasyExcel.write("src\\test\\java\\com\\gobestsoft\\java_base\\readwritefile\\text\\easyexcel-user1.xls", UserEntity.class).sheet("信息").doWrite(res);

        // 释放资源
        br.close();
        bw1.close();
        bw2.close();
        bw3.close();
    }

    public Set<String> getFilterSet() {
        String str = "3,4,20,5,8,公司,集团,健康,药业,制造,管理,咨询,有限,责任,上海,北京,山东,杭州,电子,科技,国际,美国,资源,能源,电气,科技,咨询,中国,生命,基金,投资,企业,商务,直播,信息,技术,研究,汽车,银行,建筑,校园,招聘,半导体,出版社,建设,农业,发展,传媒,技术";
        String[] split = str.split(",");

        Set<String> set = new HashSet<>();
        for (String s : split) {
            set.add(s);
        }

        return set;
    }

}
