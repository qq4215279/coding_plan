/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.java_base.collection;

import java.util.Collections;
import java.util.List;

/**
 * EmptyDemo
 *
 * @author liuzhen
 * @version 1.0.0 2021/3/21 15:11
 */
public class EmptyDemo {

    public static void test() {
        List emptyList = Collections.EMPTY_LIST;

        List<Integer> integerList1 = emptyList;

        List<Integer> integerList2 = Collections.emptyList();

    }


    public static void main(String[] args) {

    }

}
