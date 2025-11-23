/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_base.future;

import cn.hutool.core.thread.ThreadUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * TaskChain
 * 任务链
 * @author liuzhen
 * @version 1.0.0 2025/6/17 21:31
 */
public class TaskChain {

    /** 链子任务列表 */
    private List<CompletableFuture<?>> chainList = new ArrayList<>();

    private TaskChain() {
    }

    /**
     *
     * @return com.game.framework.core.rpc.core.TaskChain
     * @date 2025/6/18 09:41
     */
    public static TaskChain build() {
        return new TaskChain();
    }

    /**
     *
     * @param task task
     * @param callback callback
     * @return com.game.framework.core.rpc.core.TaskChain
     * @date 2025/6/18 09:42
     */
    public <T> TaskChain addTask(Supplier<T> task, Consumer<T> callback) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(task)
            .thenAccept(callback);

        chainList.add(future);
        return this;
    }

    /**
     *
     * @param taskFuture taskFuture
     * @param callback callback
     * @return com.game.framework.core.rpc.core.TaskChain
     * @date 2025/6/18 09:42
     */
    public <T> TaskChain addTask(CompletableFuture<T> taskFuture, Consumer<T> callback) {
        CompletableFuture<Void> future = taskFuture.thenAccept(callback);

        chainList.add(future);
        return this;
    }

    public void asyncThenRun(Runnable run) {
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(chainList.toArray(new CompletableFuture[0]));
        completableFuture.thenRun(run);
    }

    public void syncThenRun(Runnable run) {
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(chainList.toArray(new CompletableFuture[0]));
        completableFuture.thenRun(run).join();
    }

    public void sync() {
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(chainList.toArray(new CompletableFuture[0]));
        completableFuture.join();
    }

    public static void main(String[] args) {
        int i = 100;

        System.out.println("start: " + new Date());

        TaskChain taskChain = TaskChain.build();

        taskChain.addTask(() -> {
                // System.out.println("task1 start");
                ThreadUtil.sleep(2000);
                // System.out.println("task1 end");
                return i;
            }, x -> {
                System.out.println("任务1-消费-res: " + (x - 10));
            })

            .addTask(() -> {
                // System.out.println("task2");
                ThreadUtil.sleep(3000);
                return i;
            }, x -> {
                System.out.println("任务2-消费");
            })

            .asyncThenRun(() -> {
                System.out.println("finalThenRun: " + new Date());
            });

        System.out.println("end: " + new Date());
        // taskChain.syncThenRun(() -> System.out.println("finalGet: " + new Date()));
        taskChain.sync();

        System.out.println("finalEnd: " + new Date());

    }
}
