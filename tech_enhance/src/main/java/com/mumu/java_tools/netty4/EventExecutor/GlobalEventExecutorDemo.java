/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_tools.netty4.EventExecutor;

/**
 * GlobalEventExecutorDemo
 *
 * @author liuzhen
 * @version 1.0.0 2025/6/10 22:38
 */
public class GlobalEventExecutorDemo {

    /**
     * GlobalEventExecutor 是 Netty 提供的一个特殊事件执行器实现，它是一个全局共享的单线程事件执行器。
     * 核心特点:
     *   全局单例：整个JVM共享同一个实例
     *   懒启动：首次使用时才启动线程
     *   后台线程：使用守护线程(Daemon Thread)
     *   自动终止：当没有任务时线程会自动终止
     *
     * 实现原理: 单例模式实现
     *
     * 核心特性
     * 1. 任务执行行为
     *    异步执行：提交的任务会在后台线程执行
     *    顺序执行：任务按提交顺序依次执行
     *    无界队列：使用LinkedBlockingQueue存储待执行任务
     * 2. 生命周期管理
     *    自动启动：首次提交任务时启动线程
     *    自动终止：空闲60秒后自动终止线程
     *    手动终止：可调用shutdownGracefully()主动关闭
     *
     * 使用场景
     *    轻量级后台任务：如日志记录、指标统计等
     *    全局事件广播：需要跨Channel共享的事件处理
     *    资源清理：JVM关闭时的统一资源释放
     *    测试场景：简单的异步测试需求
     *
     * 与其它执行器的对比
     * 特性	            GlobalEventExecutor	                EventLoopGroup	             ThreadPoolExecutor
     * 线程数量	            1	                        可配置(通常2*cpu核数)	            可配置
     * 线程生命周期	    按需创建/自动终止	                    固定	                    可配置
     * 任务队列	            无界	                            通常无界	                可配置
     * 适用场景	        轻量级全局任务	                        I/O事件处理	                通用业务逻辑
     *
     * 注意事项
     *    不适用于I/O操作：它不是I/O事件循环，不能处理Channel事件
     *    任务堆积风险：由于使用无界队列，可能引起OOM
     *    守护线程限制：JVM退出时可能中断正在执行的任务
     *    性能限制：单线程处理能力有限，不适合高负载场景
     *
     *
     */

}
