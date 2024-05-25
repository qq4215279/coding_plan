/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * SimpleTimerTask
 * 简单时间任务
 * @author liuzhen
 * @version 1.0.0 2024/5/25 16:29
 */
public abstract class SimpleTimerTask implements Runnable {

    /** id生成器 */
    protected static final AtomicInteger idGenerator = new AtomicInteger(0);

    /** 任务id */
    private int id;
    /** 任务名称 */
    private String name;
    /** 执行时间 */
    private long executeTime;

    /** 任务取消标记 */
    private volatile boolean cancel;
    /** 任务已运行标记 */
    private volatile boolean executed;

    /**
     * 构造函数
     * @param name 任务名称
     * @param executeTime 执行时间
     * @return
     * @date 2024/5/25 16:42
     */
    public SimpleTimerTask(String name, long executeTime) {
        this.name = name;
        this.id = idGenerator.incrementAndGet();
        this.executeTime = executeTime;
        this.cancel = false;
        this.executed = false;
    }



    @Override
    public void run() {
        execute();
    }


    /**
     * 执行任务
     * @return void
     * @date 2024/5/25 16:39
     */
    public abstract void execute();

    
    
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
     * 取消任务
     * @return true 取消成功， false 取消失败，任务已执行
     * $Date: 2013-6-8 下午01:41:45
     */
    public boolean cancel() {
        if (executed) {
            return false;
        }
        cancel = true;
        return true;
    }

    /**
     *
     * @return boolean
     * @date 2024/5/25 16:42
     */
    public boolean isCancel() {
        return cancel;
    }

    /**
     * 判断任务是否可以执行, 如果可以执行，标记任务已执行
     * @return true 可以执行 false 不可以执行，任务已取消
     * $Date: 2013-6-8 下午01:44:32
     */
    public boolean canExecute() {
        if (cancel) {
            return false;
        }
        executed = true;
        return true;
    }

    /**
     *  
     * @return boolean
     * @date 2024/5/25 16:45
     */
    public boolean isExecuted() {
        return executed;
    }
    

}
