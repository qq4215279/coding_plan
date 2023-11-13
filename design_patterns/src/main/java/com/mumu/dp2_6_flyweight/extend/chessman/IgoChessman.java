/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_6_flyweight.extend.chessman;

import com.mumu.dp2_6_flyweight.extend.Coordinates;

// 围棋棋子类：抽象享元类
public abstract class IgoChessman {
    public abstract String getColor();

    public void display(Coordinates coord) {
        System.out.println("棋子颜色：" + this.getColor() + "，棋子位置：" + coord.getX() + "，" + coord.getY());
    }
}

