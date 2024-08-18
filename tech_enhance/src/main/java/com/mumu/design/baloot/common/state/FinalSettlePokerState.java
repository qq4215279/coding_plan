/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.state;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.fsm.inf.FSMEvent;

/**
 * FinalSettlePokerState
 * 最终结算状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:32
 */
public class FinalSettlePokerState extends AbstractBalootState {

    /**
     * 构造函数
     * @param balootTable
     */
    public FinalSettlePokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);
    }

    @Override
    public void update(int dt) {
        // TODO 结算


        // TODO 游戏结束逻辑

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