/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game;

/**
 * Updatable
 * 可以被每帧调度的协议
 * @author liuzhen
 * @version 1.0.0 2024/8/18 13:16
 */
public interface Updatable {

    /**
     * 帧更新
     * @param dt dt
     * @return void
     * @date 2024/8/18 13:16
     */
    void update(int dt);

}
