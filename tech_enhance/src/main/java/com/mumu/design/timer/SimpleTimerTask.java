/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * SimpleTimerTask
 * 简单时间任务
 * @author liuzhen
 * @version 1.0.0 2024/5/25 16:29
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class SimpleTimerTask implements Runnable, Comparable<SimpleTimerTask>, Serializable {
    /** 任务id */
    private int id;
    /** 任务名称 */
    private String name;
    /** 任务执行时间 */
    private long executeTime;
    /** 保存到db，保证服务器重启时，任务可继续执行 */
    private boolean save2Db;
    /** 任务状态：0: 未执行；1: 已取消；2: 已执行 */
    private volatile int state;
    /** 服务器类型 */
    // private ServerType serverType;

    /**
     * 构造函数
     * @param name 任务名称
     * @param executeTime 执行时间
     * @return
     * @date 2024/5/25 16:42
     */
    public SimpleTimerTask(String name, long executeTime) {
        this(name, executeTime, false);
    }

    /**
     * 构造函数
     * @param name 任务名称
     * @param executeTime 执行时间
     * @param save2Db 保存到db，保证服务器重启时，任务可继续执行
     * @return
     * @date 2024/5/25 16:42
     */
    public SimpleTimerTask(String name, long executeTime, boolean save2Db) {
        this.id = SimpleTimerManager.idGenerator.incrementAndGet();
        this.name = name;
        this.executeTime = executeTime;
        this.save2Db = save2Db;
        this.state = SimpleTimerManager.UN_FINISH_STATE;
        // this.serverType = InnerServer.getServerConfig().getServerType();
    }


    /**
     * 执行任务
     * @return void
     * @date 2024/5/25 16:39
     */
    public abstract void execute();


    @Override
    public void run() {
        try {
            execute();
        } catch (RuntimeException e) {
            log.error("SimpleTimerTask#execute error");
            // log.error(Utility.getTraceString(e));
        }
    }


    /**
     * 获取任务Id
     * @return int
     * @date 2024/5/25 16:42
     */
    public int getId() {
        return id;
    }

    /**
     * 获取任务名称
     * @return java.lang.String
     * @date 2024/5/25 16:42
     */
    public String getName() {
        return name;
    }

    /**
     * 获取任务执行时间
     * @return long
     * @date 2024/5/27 11:52
     */
    public long getExecuteTime() {
        return executeTime;
    }

    /**
     * 是否保存到db
     * @return boolean
     * @date 2024/5/27 15:09
     */
    public boolean isSave2Db() {
        return save2Db;
    }

    /**
     * 任务是否已经取消
     * @return boolean
     * @date 2024/5/25 16:42
     */
    public boolean isCancel() {
        return state == SimpleTimerManager.CANCEL_STATE;
    }

    /**
     * 取消任务
     * @return boolean true 取消成功， false 取消失败，任务已执行
     * @date 2024/5/27 11:35
     */
    public boolean cancel() {
        if (isCancel() || isFinish()) {
            return false;
        }

        state = SimpleTimerManager.CANCEL_STATE;
        return true;
    }

    /**
     * 任务是否已经执行完成
     * @return boolean
     * @date 2024/5/25 16:45
     */
    public boolean isFinish() {
        return state == SimpleTimerManager.FINISH_STATE;
    }

    /**
     * 判断任务是否可以执行, 如果可以执行，标记任务已执行
     * @return boolean true 可以执行 false 不可以执行，任务已取消
     * @date 2024/5/27 11:35
     */
    public boolean canExecute() {
        if (isCancel() || isFinish()) {
            return false;
        }

        return true;
    }

    /**
     * 标记完成
     * @return void
     * @date 2024/5/27 19:42
     */
    public void setFinishState() {
        this.state = SimpleTimerManager.FINISH_STATE;
    }

    /**
     * 任务状态
     * @return int
     * @date 2024/5/27 19:56
     */
    public int getState() {
        return state;
    }

    /**
     * 服务器类型
     * @return com.cxx.hf.servercore.mina.ServerType
     * @date 2024/5/27 19:56
     */
    // public ServerType getServerType() {
    //     return serverType;
    // }

    @Override
    public int compareTo(SimpleTimerTask o) {
        if (this.executeTime == o.executeTime) {
            return 0;
        } else if (this.executeTime > o.executeTime) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof SimpleTimerTask)) {
            return false;
        }

        SimpleTimerTask o = (SimpleTimerTask) obj;
        return this.getId() == o.getId();
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

}
