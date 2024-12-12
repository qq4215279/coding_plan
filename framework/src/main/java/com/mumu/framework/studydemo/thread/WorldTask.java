/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.thread;

/**
 * WorldTask
 * 任务接口
 * @author liuzhen
 * @version 1.0.0 2023/10/16 14:10
 */
public interface WorldTask {

    /**
     * 获取任务Id
     */
    int getId();

    /**
     * 获取任务类型
     * @return
     */
    int getType();

    /**
     * 获取任务CD
     */
    long getCD();

    /**
     * 立即停止任务
     */
    void stopNow();

    /**
     * 获取任务状态 0 未执行 1 执行结束 2 已取消
     * @return
     */
    int getState();

    /**
     * 执行任务
     * @param time
     */
    boolean execute(Time time);

    /**
     * 暂停任务
     */
    void pause();

    /**
     * 恢复任务
     */
    void recover();
}