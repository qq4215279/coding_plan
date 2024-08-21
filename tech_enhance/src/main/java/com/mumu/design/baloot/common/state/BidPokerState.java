/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.state;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.baloot.common.enums.PokerStateEnum;
import com.mumu.design.fsm.inf.FSMEvent;

/**
 * BidPokerState
 * 叫牌状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:28
 */
public class BidPokerState extends AbstractBalootState {

    /**
     * 构造函数
     * @param state state
     * @param name name
     * @param balootTable table
     */
    public BidPokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);
    }

    @Override
    public void update(int dt) {
        // TODO 叫牌业务


        // TODO 3. 接着进入加倍状态
        table.changeFsmState(PokerStateEnum.MULTI_POKER.createFSMState(table));
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
