package com.mumu.java_tools.hutool_all;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;

/**
 * HutoolAllTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/2/24 15:40
 */
public class HutoolAllTest {

    /**
     * 文档地址: https://plus.hutool.cn/docs/#/
     * Hutool是一个小而全的Java工具类库，通过静态方法封装，降低相关API的学习成本，提高工作效率，使Java拥有函数式语言般的优雅，让Java语言也可以“甜甜的”。
     *
     */

    /**
     * 读取json文件
     */
    @Test
    public void jsonToComplexObj() {
        // 读取类路径下的caipu.json文件,这里使用了第三方工具hutool进行读取json文件
        // 工具类参见：https://hutool.cn/docs/#/
        String path = "src\\test\\java\\com\\gobestsoft\\java_tools\\fastjson\\caipu.json";
        String jsonStr = FileUtil.readUtf8String(new File(path));
        System.out.println(jsonStr);

    }

}
