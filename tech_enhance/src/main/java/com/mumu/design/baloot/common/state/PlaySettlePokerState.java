/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.state;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.baloot.common.enums.PokerStateEnum;
import com.mumu.design.fsm.inf.FSMEvent;

/**
 * PlaySettlePokerState
 * 行牌结算状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:29
 */
public class PlaySettlePokerState extends AbstractBalootState {

    /**
     * 构造函数
     * @param balootTable
     */
    public PlaySettlePokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);
    }

    @Override
    public void update(int dt) {
        // TODO 行牌结算业务


        // TODO 单次行牌结束，如果整局游戏未结束，继续进入行牌状态
        // TODO 4. 接着进入行牌结算状态
        table.changeFsmState(PokerStateEnum.FINAL_SETTLE_POKER.createFSMState(table));


        // TODO 未满足152分或未all in，重新进入发牌状态
        table.changeFsmState(PokerStateEnum.SEND_POKER.createFSMState(table));


        // TODO 6. 最终进入结算状态
        table.changeFsmState(PokerStateEnum.FINAL_SETTLE_POKER.createFSMState(table));
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onExit() {
    }

    @Override
    public void onEvent(FSMEvent event) {
    }
}
