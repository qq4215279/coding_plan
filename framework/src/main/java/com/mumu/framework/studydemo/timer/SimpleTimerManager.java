/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.timer;

import com.mumu.common.redis.RedisConfig;
import com.mumu.common.redis.RedisTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SimpleTimerManager
 * 简单时间定时器任务管理器
 * @author liuzhen
 * @version 1.0.0 2024/5/25 16:14
 */
@Slf4j
public class SimpleTimerManager {
    /** 默认核心线程数 */
    public static final int DEFAULT_LOOP_THREAD_NUM = 1;
    /** 时间任务model key */
    static final String KEY_SIMPLE_TIMER = "Simple_Timer_{0}";
    /** 简单时间任务map */
    static final String DATA_KEY_SIMPLE_TIMER_TASK_MAP = "Data_Simple_Timer_Task_Map";

    /** 任务状态 - 未执行 */
    static final int UN_FINISH_STATE = 0;
    /** 任务状态 - 已取消 */
    static final int CANCEL_STATE = 1;
    /** 任务状态 - 已执行 */
    static final int FINISH_STATE = 2;

    /** id生成器 */
    static final AtomicInteger idGenerator = new AtomicInteger(0);

    /** 帧频率 250ms */
    static final int INTERVAL = 250;
    /** 初始化 */
    private boolean init = false;
    /** 核心线程数 */
    private int loopThreadNum;
    /** MainLoop */
    private SimpleTimer[] children;
    /** 正在运行的任务数量  */
    // private AtomicInteger runningTaskNum = new AtomicInteger();

    /** 分布式锁redis操作模版 */
    private RedisTemplate lockTemplate = new RedisTemplate(new RedisConfig());
    /** 数据redis操作模版 */
    private RedisTemplate redisTemplate = new RedisTemplate(new RedisConfig());

    /**
     * 构造函数
     */
    private SimpleTimerManager() {
        loopThreadNum = Math.max(1, Runtime.getRuntime().availableProcessors());
        loopThreadNum = Math.min(loopThreadNum, 5);
    }

    /**
     * 获取单例
     * @return SimpleTimerManager
     * @date 2024/5/27 13:53
     */
    public static SimpleTimerManager getInstance() {
        return SingletonEnum.INSTANCE.getSimpleTimerManager();
    }

    /**
     * 单例对象枚举
     * @date 2024/5/27 13:53
     */
    private enum SingletonEnum {
        /** 单例 */
        INSTANCE;

        /** 时间任务管理器 */
        private final SimpleTimerManager simpleTimerManager;

        /**
         * 构造函数
         */
        SingletonEnum() {
            simpleTimerManager = new SimpleTimerManager();
        }

        /**
         * 获取对象
         * @return SimpleTimerManager
         * @date 2024/5/27 13:53
         */
        public SimpleTimerManager getSimpleTimerManager() {
            return simpleTimerManager;
        }
    }

    /**
     * 初始化
     * @param lockTemplate lockTemplate
     * @param redisTemplate redisTemplate
     * @return void
     * @date 2024/5/27 13:53
     */
    public void init(RedisTemplate lockTemplate, RedisTemplate redisTemplate) {
        init(loopThreadNum, lockTemplate, redisTemplate);
    }

    /**
     * 初始化
     * @param loopThreadNum 线程数量
     * @param lockTemplate lockTemplate
     * @param redisTemplate redisTemplate
     * @return void
     * @date 2024/5/27 13:54
     */
    public void init(int loopThreadNum, RedisTemplate lockTemplate, RedisTemplate redisTemplate) {
        if (init) {
            return;
        }

        this.init = true;
        this.lockTemplate = lockTemplate;
        this.redisTemplate = redisTemplate;

        // 初始化帧线程池
        this.children = new SimpleTimer[loopThreadNum];
        for (int i = 0; i < loopThreadNum; i++) {
            boolean success = false;
            try {
                this.children[i] = new SimpleTimer(8, lockTemplate, redisTemplate);
                success = true;
            } catch (Exception e) {
                throw new IllegalStateException("failed to create thread group", e);
            } finally {
                if (!success) {
                    log.error("ScheduleThread create error");
                }
            }
        }

        for (int i = 0; i < loopThreadNum; i++) {
            this.children[i].start();
        }

        // 初始化db task
        try {
            initDbTask();
        } catch (Exception e) {
            log.error("SimpleTimerManager#initDbTask error");
            // log.error(Utility.getTraceString(e));
        }
    }

    /**
     * 初始化保存在db的task
     * @return void
     * @date 2024/5/27 15:59
     */
    private void initDbTask() {
        // ServerType serverType = InnerServer.getServerConfig().getServerType();

        Map<Object, Object> idTaskMap = redisTemplate.hashGetAll(DATA_KEY_SIMPLE_TIMER_TASK_MAP);
        for (Map.Entry<Object, Object> entry : idTaskMap.entrySet()) {
            Object o = entry.getValue();
            SimpleTimerTask task = (SimpleTimerTask) o;
            // TODO 不是当前服任务
            // if (!task.getServerType().equals(serverType)) {
            //     continue;
            // }

            addTask(task);
        }

    }

    /**
     * 添加一个任务
     * @param task 时间任务
     * @return void
     * @date 2024/5/27 13:59
     */
    public void addTask(SimpleTimerTask task) {
        if (task.getId() <= 0) {
            throw new RuntimeException("error task, except a none zero taskId");
        }

        // save2db
        checkAndSave2Db(task);

        int index = Math.abs(task.getId() % children.length);
        children[index].addTask(task);

        log.info("SimpleTimerManager#addTask#taskId#{}#taskName#{}#executeTime#index#{}", task.getId(), task.getName(), task.getExecuteTime(), index);
    }

    /**
     * 校验并保存到db
     * @param task task
     * @return void
     * @date 2024/5/27 15:13
     */
    private void checkAndSave2Db(SimpleTimerTask task) {
        if (!task.isSave2Db()) {
            return;
        }

        // save2db
        redisTemplate.hashSet(DATA_KEY_SIMPLE_TIMER_TASK_MAP, task.getId(), task);
    }


    /**
     * 取消任务，会直接从任务队列中移除
     * @param taskId 要移除的任务Id
     * @return boolean 如果任务存在并且还未被执行返回true， 否则返回false
     * @date 2024/5/27 14:00
     */
    @SuppressWarnings("unused")
    public boolean cancelTask(int taskId) {
        if (taskId <= 0) {
            return false;
        }

        int index = Math.abs(taskId % children.length);
        boolean flag = children[index].removeTask(taskId);

        log.info("SimpleTimerManager#cancelTask#taskId#{}#index#{}#isSuccess", taskId, index, flag);
        return flag;
    }

}
