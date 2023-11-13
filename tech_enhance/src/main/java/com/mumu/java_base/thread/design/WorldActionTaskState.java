/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.thread.design;

/**
 * WorldActionTaskState
 * 世界任务状态
 * @author liuzhen
 * @version 1.0.0 2023/10/16 14:10
 */
public class WorldActionTaskState {
	/** 初始化 */
	public static final int INIT = 1;
	/** 进行中 */
	public static final int RUNING = 2;
	/** 结束 */
	public static final int DONE = 3;
	/** 暂停 */
	public static final int STOP = 4;
	/** 失败 */
	public static final int FAIL = 5;
	/** 暂停 */
	public static final int PAUSE = 6;
}