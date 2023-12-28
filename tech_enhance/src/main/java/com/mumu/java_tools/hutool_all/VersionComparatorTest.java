/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.hutool_all;

import cn.hutool.core.comparator.VersionComparator;
import org.junit.Test;

/**
 * VersionComparatorTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/12/26 14:10
 */
public class VersionComparatorTest {


    @Test
    public void test() {
        // 检测版本号 如果版本号过低则更新版本
        String dataVersion = "v2.0.0";
        String serverVersion = "v2.1.0";
        int compare = VersionComparator.INSTANCE.compare(dataVersion, serverVersion);
        System.out.println(compare);
    }

}
