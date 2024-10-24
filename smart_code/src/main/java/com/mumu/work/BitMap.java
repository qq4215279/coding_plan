/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work;

/**
 * BitMap
 * TODO 位图法实现
 * @author liuzhen
 * @version 1.0.0 2022/4/5 21:28
 */
public class BitMap {
    /** 数量 */
    public int N;
    /** 数组长度 */
    public int lenght;
    /** 数组0 */
    public int[] tmp0;
    /** 数组1 */
    public int[] tmp1;

    public BitMap(int N) {
        this.N = N;
        this.lenght = N / 32 + 1;
        tmp0 = new int[this.lenght];
        tmp1 = new int[this.lenght];
    }

    public void setBit(int num) {

    }
}
