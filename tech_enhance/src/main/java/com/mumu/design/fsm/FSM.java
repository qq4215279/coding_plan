/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.fsm;

import com.mumu.design.fsm.inf.FSMEvent;
import com.mumu.design.fsm.inf.FSMState;

/**
 * FSM
 * 状态机
 * @author liuzhen
 * @version 1.0.0 2024/8/18 12:01
 */
public class FSM {
    /** 当前状态 */
    public FSMState curr;
    /** 前一个状态 */
    public FSMState prev;
    /** 是否被暂停 */
    private boolean pause;

    /**
     * 改变状态
     * @param state state
     * @return void
     * @date 2024/8/18 13:03
     */
    public void changeState(FSMState state) {
        if (null != curr) {
            curr.onExit();
            prev = curr;
        }
        curr = state;
        curr.onEnter();
    }

    /**
     * 状态机更新
     * @param round round
     * @return void
     * @date 2024/8/18 13:03
     */
    public void update(int round) {
        if (null == curr || pause) {
            return;
        }

        curr.update(round);
    }

    /**
     * 暂停状态机
     * @return void
     * @date 2024/8/18 13:03
     */
    public void pause() {
        if (!pause) {
            pause = true;
        }
    }

    /**
     * 恢复状态机
     * @return void
     * @date 2024/8/18 13:03
     */
    public void recover() {
        if (pause) {
            pause = false;
        }
    }

    /**
     * 处理事件
     * @param event event
     * @return void
     * @date 2024/8/18 13:04
     */
    public void onEvent(FSMEvent event) {
        if (null != curr) {
            curr.onEvent(event);
        }
    }
}
