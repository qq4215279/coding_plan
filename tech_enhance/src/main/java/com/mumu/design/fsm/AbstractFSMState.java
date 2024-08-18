/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.fsm;

import com.mumu.design.fsm.inf.FSMState;

/**
 * AbstractFSMState
 * 状态机状态抽象类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 12:05
 */
public abstract class AbstractFSMState implements FSMState {
    /** 状态名称 */
    protected int state;
    /** 状态名称 */
    protected String name;

    /**
     * 构造函数
     * @param state
     * @param name
     */
    public AbstractFSMState(int state, String name) {
        this.state = state;
        this.name = name;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
