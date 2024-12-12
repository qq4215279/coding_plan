/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.studydemo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WorldTaskExecutor
 * 任务管理器
 * @author liuzhen
 * @version 1.0.0 2023/10/16 14:10
 */
@Slf4j
public class WorldTaskExecutor {
	/** 计算帧频率  */
	private static final int interval = 1000 / 20;
	/** 初始化 */
	private boolean init = false;
	/** 核心线程数  */
	private int loopThreadNum;
	/** MainLoop */
	private WorldTaskWorker[] children;

	/** 线程绑定索引  */
	private final AtomicInteger childIndex = new AtomicInteger();

	/** 实例  */
	private static final WorldTaskExecutor INSTANCE = new WorldTaskExecutor();

	/**
	 * 获取实例
	 */
	public static WorldTaskExecutor getInstance() {
		return INSTANCE;
	}

	/**
	 * 构造函数
	 */
	private WorldTaskExecutor() {
		loopThreadNum = Math.max(1, Runtime.getRuntime().availableProcessors());
		loopThreadNum = Math.min(loopThreadNum, 4);
		// 暂时单线程
		loopThreadNum = 1;
	}

	/**
	 * 初始化
	 */
	public void init() {
		init(loopThreadNum);
	}

	/**
	 * 初始化
	 */
	public void init(int loopThreadNum) {
		if (init) {
			return;
		}

		init = true;

		// 初始化帧线程池
		children = new WorldTaskWorker[loopThreadNum];
		for (int i = 0; i < loopThreadNum; i ++) {
			boolean success = false;
			try {
				children[i] = new WorldTaskWorker(i);
				success = true;
			} catch (Exception e) {
				throw new IllegalStateException("failed to create thread group", e);
			} finally {
				if (!success) {
					log.error("ScheduleThread create error");
				}
			}
		}

		for (int i = 0; i < loopThreadNum; i ++) {
			children[i].start();
		}
	}

	/**
	 * 添加任务
     */
    public void addTask(WorldTask task) {
		// int index = Math.abs(childIndex.getAndIncrement() % children.length);
		int index = Math.abs(task.getId() % children.length);
		children[index].addTask(task);
    }

	/**
	 * 获取任务
	 * @param taskId
	 */
	public WorldTask getTask(int taskId) {
		int index = Math.abs(taskId % children.length);
		return children[index].getTask(taskId);
    }


    /** 世界任务执行线程 */
	private class WorldTaskWorker extends Thread {
		/**
		 * 有序任务列表
		 */
		protected Map<Integer, WorldTask> taskMap = new ConcurrentSkipListMap<>();

		/**
		 * 构造函数
		 */
		public WorldTaskWorker(int threadNo) {
			super("WorldTaskWorker-" + threadNo);
		}

		/**
		 * 添加任务
		 * @param task
		 */
		public void addTask(WorldTask task) {
			taskMap.put(task.getId(), task);
		}

		/**
		 * 获取任务
		 * @param taskId
		 */
		public WorldTask getTask(int taskId) {
			return taskMap.get(taskId);
		}

		/**
		 * @see Thread#run()
		 */
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

				// 执行世界任务
				runWorldTask(time);
				
				// 计算休眠时间
				long execTime = (System.currentTimeMillis() - start);
				if (execTime < interval) {
					try {
						sleep(interval - execTime);
					} catch (InterruptedException e) {
						// Ignore
					}
				}
				Thread.yield();
			}
		}
		
		/**
		 * 执行世界任务
		 */
		private void runWorldTask(Time time) {
			Iterator<Map.Entry<Integer, WorldTask>> iterator = taskMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, WorldTask> next = iterator.next();
				WorldTask value = next.getValue();

				try {
					if (value.getState() == WorldActionTaskState.PAUSE) {
						continue;
					}
					if (value.execute(time)) {
						/*if (value instanceof RepairTask) {
							log.info("repair task remove {}# {}", value.getId(), time.now);
						}*/

						iterator.remove();
					}
				} catch (Throwable e) {
					log.error("exec task error, taskId:{}", e, value.getId());
				}
			}
		}

	}

}