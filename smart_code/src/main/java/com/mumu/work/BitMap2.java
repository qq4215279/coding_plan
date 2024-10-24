/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work;

/**
 * BitMap2
 * Java实现位图功能
 * @author liuzhen
 * @version 1.0.0 2022/4/5 22:00
 */
public class BitMap2 {
    /**
     * bitmap实际存储处
     */
    private byte[] buf;

    /**
     * 偏移基准位
     */
    private int base;

    /**
     *
     * @param capacity bitmap总长度
     * @param base     偏移基准位. 假设 userId一般是从10_000开始,那么 建立一个bitMap为 2_000的长度时 capacity为2_000,base为就是 10_000,
     */
    public BitMap2(int capacity, int base) {
        int length = capacity / 8 + 1;
        this.buf = new byte[length];
        this.base = base;
    }

    /**
     *
     * @param capacity bitmap总长度
     */
    public BitMap2(int capacity) {
        this(capacity, 0);
    }

    /**
     * 根据偏移位获取状态
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
     * @param offset 偏移位
     * @return 返回值为 1或者0
     */
    public byte getbit(int offset) {
        int actualOffset = getActualOffset(offset);
        int index = getIndex(actualOffset);
        byte b = buf[index];

        return (byte) ((b & (1 << getPosition(actualOffset))) != 0 ? 1 : 0);
    }

    /**
     * 设置偏移位所在状态为1
     * 将1左移position后，那个位置自然就是1，然后和以前的数据做 或| 操作.这样，那个位置就替换成1了
     * @param offset 偏移位
     */
    public void setbit(int offset) {
        int actualNum = getActualOffset(offset);
        int index = getIndex(actualNum);
        byte b = buf[index];
        buf[index] = (byte) (b | (1 << getPosition(actualNum)));
    }

    /**
     * 设置偏移位所在状态为0
     * 对1进行左移，然后取反，最后与buf[index]作 与& 操作。
     * @param offset 偏移位
     */
    public void clear(int offset) {
        int actualNum = getActualOffset(offset);
        int index = getIndex(actualNum);
        byte b = buf[index];
        buf[index] = (byte) (b & ~(1 << getPosition(actualNum)));
    }

    /**
     * 获取当前偏移offset在数组中的索引
     * Offset/8得到byte[]的index
     */
    private int getIndex(int actualOffset) {
        // 相当于 Offset / 8 (因为8 是2^n次方,所以可以这样取整)
        return (actualOffset) >> 3;
    }

    /**
     * 获取当前偏移offset在字节里的位数
     * Offset % 8得到byte 的 pos
     */
    private int getPosition(int actualOffset) {
        // 相当于 Offset % 8 (因为8 是2^n次方,所以可以这样取模)
        return (actualOffset) & 0x07;
    }

    private int getActualOffset(int offset) {
        return offset - base;
    }

    public static void main(String[] args) {
        // 假设user_id的基准是 50W(用户id为 500001,500002...)
        // 当前系统里有1W个用户,则设计bitmap如下:
        int base = 500_000;
        BitMap2 bitmap = new BitMap2(10000, base);
        
        // 对 500009 设置 1
        bitmap.setbit(500009);
        System.out.println(bitmap.getbit(500009));
        System.out.println(bitmap.getbit(500008));
        System.out.println(bitmap.getbit(500010));
        
        // 对 500009 设置 0
        bitmap.clear(500009);
        
        // 对 500010 设置 1
        bitmap.setbit(500010);
        System.out.println(bitmap.getbit(500009));
        System.out.println(bitmap.getbit(500008));
        System.out.println(bitmap.getbit(500010));
    }
}
