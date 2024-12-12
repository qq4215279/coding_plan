/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.fsm.inf;

/**
 * FSMState
 * 状态机状态接口
 * @author liuzhen
 * @version 1.0.0 2024/8/18 12:02
 */
public interface FSMState {

    /**
     * 获取状态id
     * @return int
     * @date 2024/8/18 17:21
     */
    int getState();

    /**
     * 获取名称
     * @return java.lang.String
     * @date 2024/8/18 12:07
     */
    String getName();

    /**
     * 进入状态
     * @return void
     * @date 2024/8/18 12:08
     */
    void onEnter();

    /**
     * 退出状态
     * @return void
     * @date 2024/8/18 12:08
     */
    void onExit();

    /**
     * 状态机更新
     * @param dt dt
     * @return void
     * @date 2024/8/18 12:10
     */
    void update(int dt);

    /**
     * 处理事件
     * @param event event
     * @return void
     * @date 2024/8/18 12:12
     */
    void onEvent(FSMEvent event);
}
