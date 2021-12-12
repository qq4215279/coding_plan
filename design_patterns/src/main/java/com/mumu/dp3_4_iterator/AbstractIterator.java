/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_4_iterator;

// 抽象迭代器
public interface AbstractIterator {
    // 移至下一个元素
    void next();

    // 判断是否为最后一个元素
    boolean isLast();

    // 移至上一个元素
    void previous();

    // 判断是否为第一个元素
    boolean isFirst();

    // 获取下一个元素
    Object getNextItem();

    // 获取上一个元素
    Object getPreviousItem();
}
