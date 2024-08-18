/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.enums;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.baloot.common.state.*;
import com.mumu.design.fsm.inf.FSMState;

/**
 * PokerStateEnum
 * 状态机枚举
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:02
 */
public enum PokerStateEnum {
    /** 发牌状态 */
    SEND_POKER(1, "send_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new SendPokerState(state, name, table);
        }
    },
    /** 叫牌状态 */
    BID_POKER(2, "bid_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new BidPokerState(state, name, table);
        }
    },
    /** 加倍状态 */
    MULTI_POKER(3, "multi_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new MultiPokerState(state, name, table);
        }
    },
    /** 行牌状态 */
    PLAY_POKER(4, "play_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new PlayPokerState(state, name, table);
        }
    },
    /** 行牌结算状态 */
    PLAY_SETTLE_POKER(5, "play_settle_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new PlaySettlePokerState(state, name, table);
        }
    },
    /** 最终结算状态 */
    FINAL_SETTLE_POKER(6, "final_settle_poker") {
        @Override
        public FSMState createFSMState(BalootTable table) {
            return new FinalSettlePokerState(state, name, table);
        }
    },
    ;


    /** 状态 */
    protected int state;
    /** 状态名称 */
    protected String name;

    PokerStateEnum(int state, String name) {
        this.state = state;
        this.name = name;
    }

    /**
     * 创建状态
     * @param table table
     * @return com.mumu.design.fsm.inf.FSMState
     * @date 2024/8/18 21:21
     */
    public abstract FSMState createFSMState(BalootTable table);

    /**
     * 获取状态
     * @return int
     * @date 2024/8/18 17:06
     */
    public int getState() {
        return state;
    }

    /**
     * 获取状态名称
     * @return java.lang.String
     * @date 2024/8/18 17:06
     */
    public String getName() {
        return name;
    }
}
