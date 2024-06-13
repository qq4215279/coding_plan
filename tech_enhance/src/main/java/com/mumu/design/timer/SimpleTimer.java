/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import com.mumu.design.thread.Time;
import com.mumu.design.timer.redis.RedisTemplate;
import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Objects;
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
    private final PriorityBlockingQueue<SimpleTimerTask> taskList = new PriorityBlockingQueue<>();
    /** executor */
    private final ExecutorService executor;

    /** 分布式锁redis操作模版 */
    private RedisTemplate lockTemplate;
    /** 数据redis操作模版 */
    private RedisTemplate redisTemplate;

    /**
     * 构造函数
     */
    public SimpleTimer(RedisTemplate lockTemplate, RedisTemplate redisTemplate) {
        this(8, lockTemplate, redisTemplate);
    }

    /**
     * 构造函数
     * @param num 线程池数量
     */
    public SimpleTimer(int num, RedisTemplate lockTemplate, RedisTemplate redisTemplate) {
        this.threadNum = num;
        this.executor = new ThreadPoolExecutor(threadNum, threadNum, 60 * 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new StandardThreadFactory("SimpleTimerPool"));

        this.lockTemplate = lockTemplate;
        this.redisTemplate = redisTemplate;
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
     * @param time 时间
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
                    // 服务器重启时，保证分布式环境任务只执行一次
                    if (task.isSave2Db()) {
                        int taskId = task.getId();
                        String key = MessageFormat.format(SimpleTimerManager.KEY_SIMPLE_TIMER, String.valueOf(taskId));
                        try {
                            boolean lock = lockTemplate.tryLock(key, 100, 180 * 1000, TimeUnit.MILLISECONDS);
                            // 没拿到锁
                            if (!lock) {
                                continue;
                            }

                            Object o = redisTemplate.hashGet(SimpleTimerManager.DATA_KEY_SIMPLE_TIMER_TASK_MAP, taskId);
                            // 已执行过
                            if (Objects.isNull(o)) {
                                task.setFinishState();
                            } else {
                                doRunTask();

                                redisTemplate.hashDelete(SimpleTimerManager.DATA_KEY_SIMPLE_TIMER_TASK_MAP, taskId);
                            }
                        } catch (Exception e) {
                            // log.error(Utility.getTraceString(e));
                        } finally {
                            lockTemplate.releaseLock(key);
                        }
                    } else {
                        doRunTask();
                    }

                } else {
                    break;
                }
            }
        }
    }

    /**
     * do执行任务
     * @return void
     * @date 2024/5/27 19:42
     */
    private void doRunTask() {
        SimpleTimerTask task;// 执行任务
        task = taskList.poll();
        if (task.canExecute()) {
            task.setFinishState();
            executor.execute(task);
            log.info("start execute timer task #name#{}taskId{}executeTime#{}#state#{}", task.getName(), task.getId(), task.getExecuteTime(), task.getState());
        } else {
            log.info("cancel execute timer task #name#{}taskId{}executeTime#{}#state#{}", task.getName(), task.getId(), task.getExecuteTime(), task.getState());
        }
    }
}
