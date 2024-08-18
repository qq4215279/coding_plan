/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot;

import com.mumu.design.thread.Time;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GameSchedule
 * 游戏调度器
 * @author liuzhen
 * @version 1.0.0 2024/8/18 13:05
 */
@Slf4j
public class GameSchedule {
    /** 默认核心线程数 */
    public static final int DEFAULT_LOOP_THREAD_NUM = 1;

    /** 帧频率 1000ms */
    static final long INTERVAL = 1000L;
    /** 初始化 */
    private boolean init = false;
    /** 核心线程数 */
    private int loopThreadNum;
    /** MainLoop */
    private ScheduleThread[] children;

    /** 线程绑定索引  */
    private final AtomicInteger childIndex = new AtomicInteger();

    /**
     * 构造函数
     */
    private GameSchedule() {
        loopThreadNum = Math.max(1, Runtime.getRuntime().availableProcessors());
        loopThreadNum = Math.min(loopThreadNum, 4);
    }

    /**
     * 获取单例
     * @return com.mumu.design.baloot.GameSchedule
     * @date 2024/8/18 13:08
     */
    public static GameSchedule getInstance() {
        return GameSchedule.SingletonEnum.INSTANCE.getGameSchedule();
    }

    /**
     * 单例对象枚举
     * @date 2024/8/18 13:09
     */
    private enum SingletonEnum {
        /** 单例 */
        INSTANCE;

        /** 游戏调度器 */
        private final GameSchedule gameSchedule;

        /**
         * 构造函数
         */
        SingletonEnum() {
            gameSchedule = new GameSchedule();
        }

        /**
         * 获取对象
         * @return com.mumu.design.baloot.GameSchedule
         * @date 2024/8/18 13:08
         */
        public GameSchedule getGameSchedule() {
            return gameSchedule;
        }
    }

    /**
     * 初始化
     * @return void
     * @date 2024/8/18 13:09
     */
    public void init() {
        init(loopThreadNum);
    }

    /**
     * 初始化
     * @param loopThreadNum 线程数量
     * @return void
     * @date 2024/8/18 13:09
     */
    public void init(int loopThreadNum) {
        if (init) {
            return;
        }

        this.init = true;

        // 初始化帧线程池
        children = new ScheduleThread[loopThreadNum];
        for (int i = 0; i < loopThreadNum; i ++) {
            boolean success = false;
            try {
                children[i] = new ScheduleThread(i);
                success = true;
            } catch (Exception e) {
                throw new IllegalStateException("failed to create thread group", e);
            } finally {
                if (!success) {
                    // log.error("ScheduleThread create error");
                }
            }
        }

        for (int i = 0; i < loopThreadNum; i ++) {
            children[i].start();
        }
    }

    /**
     * 添加战场
     * @param battle battle
     * @return void
     * @date 2024/8/18 13:16
     */
    public void schedule(Updatable battle) {
        int index = Math.abs(childIndex.getAndIncrement() % children.length);
        children[index].schedule(battle);
    }

    /**
     * 移除战场
     * @param battle battle
     * @return void
     * @date 2024/8/18 13:16
     */
    public void unschedule(Updatable battle) {
        for (ScheduleThread child : children) {
            child.unschedule(battle);
        }
    }


    /**
     * ScheduleThread 执行线程
     * @date 2024/8/18 13:18
     */
    private class ScheduleThread extends Thread {
        /** 战场列表 */
        private CopyOnWriteArrayList<Updatable> battleFieldList;

        /**
         * 构造函数
         */
        public ScheduleThread(int threadNo) {
            super("ScheduleThread-" + threadNo);
            battleFieldList = new CopyOnWriteArrayList<>();
        }

        /**
         * 添加战场
         * @param battle battle
         * @return void
         * @date 2024/8/18 13:18
         */
        public void schedule(Updatable battle) {
            battleFieldList.add(battle);
        }

        /**
         * 移除战场
         * @param battle battle
         * @return void
         * @date 2024/8/18 13:19
         */
        public void unschedule(Updatable battle) {
            battleFieldList.remove(battle);
        }

        @Override
        public void run() {
            // 帧间隔
            long frameDt = 0;
            // 当前时间
            long curr = 0;
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

                // 执行帧运算(dt = 两帧之间的间隔时间)
                runFrame(time);

                // 计算休眠时间
                long execTime = (System.currentTimeMillis() - start);
                if (execTime < INTERVAL) {
                    try {
                        sleep(INTERVAL - execTime);
                    } catch (InterruptedException e) {
                        // Ignore
                    }
                }
                Thread.yield();
            }
        }

        /**
         * 执行帧运算
         * @param time time
         * @return void
         * @date 2024/8/18 13:20
         */
        private void runFrame(Time time) {
            for (Updatable battle : battleFieldList) {
                try {
                    battle.update(time.dt);
                } catch (Exception e) {
                    e.printStackTrace();
                    // log.error("runFrame error, roomId:{}", e, battle);
                }
            }
        }

    }
}
