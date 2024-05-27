/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SimpleTimerTaskInfo
 * 简单时间任务信息
 * @author liuzhen
 * @version 1.0.0 2024/5/27 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTimerTaskInfo {
    /** 全限定类名 */
    private String className;
    /** 任务id */
    private int id;
    /** 任务名称 */
    private String name;
    /** 任务执行时间 */
    private long executeTime;
    /** 任务状态：0: 未执行；1: 已取消；2: 已执行 */
    private volatile int state;

    @Override
    public String toString() {
        return "SimpleTimerTaskInfo{" +
                "className='" + className + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", executeTime=" + executeTime +
                ", state=" + state +
                '}';
    }
}
