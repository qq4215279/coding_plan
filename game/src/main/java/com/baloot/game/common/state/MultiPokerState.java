/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game.common.state;

import com.baloot.game.common.core.BalootTable;
import com.baloot.game.common.enums.PokerStateEnum;
import com.mumu.framework.studydemo.fsm.inf.FSMEvent;

/**
 * MultiPokerState
 * 加倍状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:29
 */
public class MultiPokerState extends AbstractBalootState {

    /**
     * 构造函数
     * @param state state
     * @param name name
     * @param balootTable table
     */
    public MultiPokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);
    }

    @Override
    public void update(int dt) {
        // TODO 加倍业务


        // TODO 3. 接着进入行牌状态
        table.changeFsmState(PokerStateEnum.PLAY_POKER.createFSMState(table));
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