package com.mumu.dp2_2_bridge;

import com.mumu.dp2_2_bridge.core.Image;
import com.mumu.dp2_2_bridge.core.ImageImp;

// 客户端测试类
public class Client {
    public static void main(String args[]) {
        Image image;
        ImageImp imp;
        image = (Image)XMLUtil.getBean("image");
        imp = (ImageImp)XMLUtil.getBean("os");
        image.setImageImp(imp);
        image.parseFile("小龙女");
    }
}
