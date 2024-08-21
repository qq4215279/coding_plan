/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.state;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.baloot.common.enums.PokerStateEnum;
import com.mumu.design.fsm.inf.FSMEvent;

/**
 * PlayPokerState
 * 行牌状态
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:38
 */
public class PlayPokerState extends AbstractBalootState {
    /** 第几墩游戏 */
    private int sequence;


    /**
     * 构造函数
     * @param state state
     * @param name name
     * @param balootTable table
     */
    public PlayPokerState(int state, String name, BalootTable balootTable) {
        super(state, name, balootTable);

        this.sequence = 1;
    }

    @Override
    public void update(int dt) {
        // TODO 行牌业务


        // TODO 4. 接着进入行牌结算状态
        table.changeFsmState(PokerStateEnum.PLAY_SETTLE_POKER.createFSMState(table));
    }

    /**
     * 增加游戏墩
     * @return void
     * @date 2024/8/18 17:53
     */
    public void addSequence() {
        this.sequence++;
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