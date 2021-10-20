package com.mumu.dp2_2_bridge.core;

import com.mumu.dp2_2_bridge.Matrix;

// 抽象操作系统实现类，充当实现类接口
public interface ImageImp {
    // 显示像素矩阵m
    void doPaint(Matrix m);
}
