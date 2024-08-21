package com.mumu.design.baloot.common.core;

import com.mumu.design.baloot.common.enums.PokerLevelEnum;
import com.mumu.design.baloot.common.enums.PokerTypeEnum;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;

/**
 * Poker
 * 扑克牌
 * @author liuzhen
 * @version 1.0.0 2024/8/18 14:41
 */
@Data
public class Poker {
    /** id */
    private int id;
    /** 扑克牌大小 */
    private PokerLevelEnum level;
    /** 扑克牌类型 */
    private PokerTypeEnum type;

    public Poker() {
    }

    public Poker(PokerLevelEnum level, PokerTypeEnum type) {
        this.id = caclId(type, level);
        this.level = level;
        this.type = type;
    }

    /**
     *
     * @param type 扑克牌类型
     * @param level 扑克牌大小
     * @return int
     * @date 2024/8/19 15:13
     */
    private int caclId(PokerTypeEnum type, PokerLevelEnum level) {
        // 小王
        if (level == PokerLevelEnum.LEVEL_S_X) {
            return 52;

            // 大王
        } else if (level == PokerLevelEnum.LEVEL_L_X) {
            return 53;

            // 数字牌
        } else {
            return type.ordinal() * 13 + level.ordinal();
        }

    }

    @Override
    public int hashCode() {
        return this.id;
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
        return type.getName() + " " + level.getName();
    }

    public PokerLevelEnum getLevel() {
        return level;
    }

    public void setLevel(PokerLevelEnum level) {
        this.level = level;
    }

    public PokerTypeEnum getType() {
        return type;
    }

    public void setType(PokerTypeEnum type) {
        this.type = type;
    }

    public static void main(String[] args) {
        List<Poker> pokers = new LinkedList<>();

        for (PokerTypeEnum typeEnum : PokerTypeEnum.values()) {
            for (PokerLevelEnum levelEnum : PokerLevelEnum.values()) {
                pokers.add(new Poker(typeEnum, levelEnum));
            }
        }

        System.out.println("♠");
        System.out.println("中文");
        System.out.println(pokers.toString());
    }
}
