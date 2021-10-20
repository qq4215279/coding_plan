package com.mumu.dp2_2_bridge.image;

import com.mumu.dp2_2_bridge.core.Image;
import com.mumu.dp2_2_bridge.Matrix;

// PNG格式图像类，充当扩充抽象类
public class PNGImage extends Image {
    public void parseFile(String fileName) {
        // 模拟解析PNG文件并获得一个像素矩阵对象m;
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName + "，格式为PNG。");
    }
}
