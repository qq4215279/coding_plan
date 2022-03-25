/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work;

/**
 * TestAlibabaCodeFormat 阿里巴巴格式化工具阿里巴巴格式化工具
 * 
 * @author liuzhen
 * @version 1.0.0 2022/3/25 17:20
 */
public class AlibabaCodeFormatTest {

    /**
     * tewst 阿里巴巴格式化工具test
     *
     * @param
     * @return void
     * @author liuzhen
     * @date 2022/3/25 17:24
     */
    public void test() {
        String str1 = "sssssssssssssssssssssssssssssssssssbbbbbbbbbbbbbbbbbbbbbbbbb啊啊啊啊啊啊啊啊啊";
        StringBuilder sb = new StringBuilder();
        int a = 1 + 1;
        sb.append(str1).append(
            "sssssssssssssssssssssssssssssssssssbbbbbbbbbbbbbbbbbbbbbbbbb啊啊啊啊啊啊啊sbbbbbbbbbbbbbbbbbbbbbbbbb啊啊啊啊啊啊啊sbbbbbbbbbbbbbbbbbbbbbbbbb啊啊啊啊啊啊啊啊啊")
            .append("sssssssssssssssssssssssssssssssssssbbbbbbbbbbbbbbbbbbbbbbbbb啊啊啊啊啊啊啊啊啊");

        System.out.println(sb.toString());
    }

}
