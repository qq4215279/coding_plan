/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.java_base.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * TaskChain
 *
 * @author liuzhen
 * @version 1.0.0 2025/6/17 21:31
 */
public class TaskChain {
    private List<CompletableFuture<?>> chainList = new ArrayList<>();

    private TaskChain() {
    }

    public static TaskChain build() {
        return new TaskChain();
    }

    public <T> TaskChain addTask(Supplier<T> task, Consumer<T> callback) {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> task.get())
                .thenAccept(callback);

        chainList.add(future);
        return this;
    }

    public <T> TaskChain addTask(CompletableFuture<T> taskFuture, Consumer<T> callback) {
        CompletableFuture<Void> future = taskFuture.thenAccept(callback);

        chainList.add(future);
        return this;
    }

    public void finalThenRun(Runnable run) {
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(chainList.toArray(new CompletableFuture[0]));
        completableFuture.thenRun(run);
    }

    public static void main(String[] args) {
        int i = 100;

        System.out.println("start: " + new Date());

        TaskChain taskChain = TaskChain.build();

        taskChain.addTask(() -> {
            return i;
        }, x -> System.out.println(x - 10))
        .addTask(() -> {
            return i;
        }, x -> {
            System.out.println("任务2");
        }).finalThenRun(() -> {
            System.out.println("end111: " + new Date());
        });

        System.out.println("end: " + new Date());

    }

}
