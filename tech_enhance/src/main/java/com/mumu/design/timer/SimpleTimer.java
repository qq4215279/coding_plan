/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import com.mumu.design.thread.Time;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * SimpleTimer
 * 简单时间定时器
 * @author liuzhen
 * @version 1.0.0 2024/5/25 16:32
 */
@Slf4j
public class SimpleTimer extends Thread {
    /** 线程数量 */
    private int threadNum;
    /** 任务列表 */
    private PriorityBlockingQueue<SimpleTimerTask> taskList = new PriorityBlockingQueue<>();
    /** executor */
    private ExecutorService executor;

    /** 开始标识符 */
    private boolean start;
    /** 停止标识符 */
    private boolean stop;




    /**
     * 构造函数
     */
    public SimpleTimer() {
        this(3);
    }

    /**
     * 构造函数
     * @param num 线程池数量
     */
    public SimpleTimer(int num) {
        this.threadNum = num;
        this.executor = new ThreadPoolExecutor(threadNum, threadNum, 60 * 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new StandardThreadFactory("SimpleTimerPool"));
    }

    /**
     * 添加任务
     * @date 2023/10/24 11:04
     * @param task 机器人任务
     * @return void
     */
    public void addTask(SimpleTimerTask task) {
        taskList.offer(task);
    }

    /**
     * 移除任务
     * @date 2023/10/24 11:27
     * @param taskId 任务id
     * @return void
     */
    public boolean removeTask(int taskId) {
        SimpleTimerTask findTask = null;
        for (SimpleTimerTask temp : taskList) {
            if (temp.getId() == taskId) {
                findTask = temp;
                break;
            }
        }

        if (null != findTask) {
            return findTask.cancel();
        }

        return false;
    }


    public synchronized void startExecutor() {
        if (start) {
            return;
        }

        start = true;
    }

    public synchronized void stopExecutor() {
        if (stop || !start) {
            return;
        }

        // TODO 清空队列
        this.taskList.clear();

        // 设置标志位为停止
        stop = true;
    }


    @Override
    public void run() {
        // 帧间隔
        long frameDt;
        // 当前时间
        long curr;
        // 开始时间
        long start = System.currentTimeMillis();
        Time time = new Time();
        while (!this.isInterrupted()) {
            // 当前时间
            curr = System.currentTimeMillis();
            // 一帧真正消耗的时间
            frameDt = curr - start;
            // 帧循环开始时间
            start = curr;

            // 执行帧运算(dt = 两帧之间的间隔时间)
            time.dt = (int)frameDt;
            time.now = curr;

            // 执行任务
            runTask(time);

            // 计算休眠时间
            long execTime = (System.currentTimeMillis() - start);
            if (execTime < SimpleTimerManager.INTERVAL) {
                try {
                    sleep(SimpleTimerManager.INTERVAL - execTime);
                } catch (InterruptedException e) {
                    // Ignore
                }
            }
            Thread.yield();
        }
    }

    /**
     * 执行任务
     * @param time time
     * @return void
     * @date 2024/5/27 11:52
     */
    private void runTask(Time time) {
        long currentTime; // 当前时间
        if (taskList.size() > 0) {
            currentTime = time.now;
            SimpleTimerTask task = null;
            while ((task = taskList.peek()) != null) {
                if (task.getExecuteTime() <= currentTime) {
                    // 执行任务
                    task = taskList.poll();
                    if (task.canExecute()) {
                        executor.execute(task);
                        log.info("start execute timer task #name#{}taskId{}executeTime#{}#state#{}", task.getName(), task.getId(), task.getExecuteTime(), task.getState());
                    } else {
                        log.info("cancel execute timer task #name#{}taskId{}executeTime#{}#state#{}", task.getName(), task.getId(), task.getExecuteTime(), task.getState());
                    }
                } else {
                    break;
                }
            }
        }
    }
}
