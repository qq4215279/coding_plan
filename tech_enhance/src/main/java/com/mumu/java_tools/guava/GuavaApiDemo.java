package com.mumu.java_tools.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.BaseEncoding;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * CaseFormatDemo
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/21 15:08
 */
public class GuavaApiDemo {

    /**
     *  驼峰下划线互转
     * @param args args 
     * @return void
     * @date 2024/5/21 15:09
     */
    public static void main(String[] args) {
        String column = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testFish");
        System.out.println(column);  // colunm : test_fish

        String field = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "meng_qiu");
        System.out.println(field); // field : mengQiu
    }

    /**
     * guava的base64处理
     * @return void
     * @date 2024/5/21 15:11
     */
    @Test
    public void base64Demo() {
        // 随着jdk版本的升级 变更 ，用jdk自带的base64很可能在升级后就找不到包路径了, 干脆使用guava的工具类 以逸待劳

        String url = "test";
        String encode= BaseEncoding.base64().encode(url.getBytes());
        System.out.println(encode);
        String decode = new String(BaseEncoding.base64().decode(encode));
        System.out.println(decode);
    }


    /**
     * guava的文件读写 代码非常简洁
     * @return void
     * @date 2024/5/21 15:11
     */
    @Test
    public void fileDemo() throws IOException {

        File input = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
        // 会自动关闭流
        List<String> files = Files.readLines(input, Charsets.UTF_8);
        //由于是按行读取，所以这里只有加上"\n"才能保证这个字符串和文本内容完全一致
        String text = Joiner.on("\n").join(files);
        System.out.println(text);

        File output = new File("F:\\Code\\MumuSpace\\coding_plan\\tech_enhance\\src\\main\\java\\com\\mumu\\java_tools\\guava\\b.txt");
        Files.write(text.getBytes(), output);
    }

}
