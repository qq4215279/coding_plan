/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import com.mumu.design.baloot.common.enums.PokerLevelEnum;
import com.mumu.design.baloot.common.enums.PokerTypeEnum;
import lombok.Data;

/**
 * Poker
 * 扑克牌
 * @author liuzhen
 * @version 1.0.0 2024/8/18 14:41
 */
@Data
public class Poker {
    /** 扑克牌大小 */
    private PokerLevelEnum level;
    /** 扑克牌类型 */
    private PokerTypeEnum type;

    public Poker() {
    }

    public Poker(PokerLevelEnum level, PokerTypeEnum type) {
        this.level = level;
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Poker other = (Poker) obj;
        if (level != other.level) {
            return false;
        }

        return type == other.type;
    }

    @Override
    public String toString() {
        return level.getLevel() + " ";
    }
}
