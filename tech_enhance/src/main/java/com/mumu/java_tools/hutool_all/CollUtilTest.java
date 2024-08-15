/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.hutool_all;

import cn.hutool.core.collection.CollUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * CollUtilTest
 *
 * @author liuzhen
 * @version 1.0.0 2024/8/6 20:15
 */
public class CollUtilTest {

    @Test
    public void test01() {
        List<Integer> sourceList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> destList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        Collection<Integer> subtract = CollUtil.subtract(sourceList, destList);
        System.out.println(subtract.toString());


        Collection<Integer> subtract2 = CollUtil.subtract(destList, sourceList);
        System.out.println(subtract2.toString());

    }

}
