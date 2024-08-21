/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.enums;

/**
 * PokerTypeEnum
 * 扑克牌类型
 * @author liuzhen
 * @version 1.0.0 2024/8/18 14:37
 */
public enum PokerTypeEnum {
    /** 方块 */
    DIAMOND("♦"),
    /** 梅花 */
    CLUB("♣"),
    /** 红桃 */
    HEART("♥"),
    /** 黑桃 */
    SPADE("♠"),
    BLANK(" "),
    ;

    /** name */
    private final String name;

    PokerTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
