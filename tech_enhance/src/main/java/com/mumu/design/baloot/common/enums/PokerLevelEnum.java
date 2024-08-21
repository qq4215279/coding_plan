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
    /** 2 */
    LEVEL_2(2, "2"),
    /** 3 */
    LEVEL_3(3, "3"),
    /** 4 */
    LEVEL_4(4, "4"),
    /** 5 */
    LEVEL_5(5, "5"),
    /** 6 */
    LEVEL_6(6, "6"),
    /** 7 */
    LEVEL_7(7, "7"),
    /** 8 */
    LEVEL_8(8, "8"),
    /** 9 */
    LEVEL_9(9, "9"),
    /** 10 */
    LEVEL_10(10, "10"),
    /** J */
    LEVEL_J(11, "J"),
    /** Q */
    LEVEL_Q(12, "Q"),
    /** K */
    LEVEL_K(13, "K"),
    /** A */
    LEVEL_A(14, "A"),
    /** 小王 */
    LEVEL_SMALL_KING(15, "S"),
    /** 大王 */
    LEVEL_BIG_KING(16, "X"),
    ;

    /** 大小 */
    private int level;
    /** name */
    private String name;

    PokerLevelEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * @return int
     * @date 2024/8/18 14:54
     */
    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
