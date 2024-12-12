/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game.common.state;

import com.baloot.game.common.core.BalootTable;
import com.baloot.game.common.enums.PokerStateEnum;
import com.mumu.framework.studydemo.fsm.inf.FSMEvent;

/**
 * SendPokerState
 * 发牌状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:23
 */
public class SendPokerState extends AbstractBalootState {

    /**
     * 构造函数
     * @param state state
     * @param name name
     * @param balootTable table
     */
    public SendPokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);
    }

    @Override
    public void update(int dt) {
        // TODO 发牌业务


        // TODO 2. 接着进入叫牌状态
        table.changeFsmState(PokerStateEnum.BID_POKER.createFSMState(table));
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
