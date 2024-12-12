/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.fsm.inf;

/**
 * FSMEvent
 * 状态机事件
 * @author liuzhen
 * @version 1.0.0 2024/8/18 12:04
 */
public interface FSMEvent {

    /**
     * 处理状态机事件
     * @param state state
     * @return void
     * @date 2024/8/18 12:05
     */
    void onEvent(FSMState state);

}
