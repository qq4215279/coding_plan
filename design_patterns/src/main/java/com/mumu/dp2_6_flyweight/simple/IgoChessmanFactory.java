/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_6_flyweight.simple;

import com.mumu.dp2_6_flyweight.simple.chessman.BlackIgoChessman;
import com.mumu.dp2_6_flyweight.simple.chessman.IgoChessman;
import com.mumu.dp2_6_flyweight.simple.chessman.WhiteIgoChessman;

import java.util.*;

/**
 * @author D0381
 * 围棋棋子工厂类：享元工厂类，使用单例模式进行设计
 */
public class IgoChessmanFactory {

    /** 使用Hashtable来存储享元对象，充当享元池 */
    private Map<String, IgoChessman> map;

    private static IgoChessmanFactory instance = new IgoChessmanFactory();

    private IgoChessmanFactory() {
        map = new HashMap();
        IgoChessman black, white;
        black = new BlackIgoChessman();
        map.put("b", black);
        white = new WhiteIgoChessman();
        map.put("w", white);
    }

    /**
     * 返回享元工厂类的唯一实例
     * @return com.mumu.dp2_6_flyweight.simple.IgoChessmanFactory
     * @date 2023/11/13 15:34
     */
    public static IgoChessmanFactory getInstance() {
        return instance;
    }

    /**
     * 通过key来获取存储在Hashtable中的享元对象
     * @param color color
     * @return com.mumu.dp2_6_flyweight.simple.chessman.IgoChessman
     * @date 2023/11/13 15:34
     */
    public IgoChessman getIgoChessman(String color) {
        return map.get(color);
    }
}
