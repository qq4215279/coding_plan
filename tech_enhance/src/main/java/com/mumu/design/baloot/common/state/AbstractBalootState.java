/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.state;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.design.fsm.AbstractFSMState;
import com.mumu.design.fsm.inf.FSMEvent;

/**
 * AbstractBalootState
 * 抽象Baloot state
 * @author liuzhen
 * @version 1.0.0 2024/8/18 17:25
 */
public class AbstractBalootState extends AbstractFSMState {
    /**  */
    protected BalootTable table;

    /**
     * 构造函数
     * @param state
     * @param name
     */
    public AbstractBalootState(int state, String name, BalootTable table) {
        super(state, name);

        this.table = table;
    }

    @Override
    public void update(int dt) {

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
