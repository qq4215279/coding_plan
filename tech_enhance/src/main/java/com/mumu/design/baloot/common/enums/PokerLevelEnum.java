/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.enums;

/**
 * PokerLevelEnum
 * 扑克牌大小
 * @author liuzhen
 * @version 1.0.0 2024/8/18 14:33
 */
public enum PokerLevelEnum {
    /** 7 */
    LEVEL_7(7, "7", 7),
    /** 8 */
    LEVEL_8(8, "8", 8),
    /** 9 */
    LEVEL_9(9, "9", 9),
    /** 10 */
    LEVEL_10(10, "10", 10),
    /** J */
    LEVEL_J(11, "J", 11),
    /** Q */
    LEVEL_Q(12, "Q", 12),
    /** K */
    LEVEL_K(13, "K", 13),
    /** A */
    LEVEL_A(14, "A", 14),
    ;

    /** 大小 */
    private int level;
    /** name */
    private String name;
    /** 大小 */
    private int priority;

    PokerLevelEnum(int level, String name, int priority) {
        this.level = level;
        this.name = name;
        this.priority = priority;
    }

    /**
     * 
     * @return int
     * @date 2024/8/18 14:54
     */
    public int getLevel() {
        return level;
    }
}
