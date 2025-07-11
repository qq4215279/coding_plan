/*
 * Copyright 2020-2025, mumu without 996.
 * All Right Reserved.
 */

package com.mumu.framework.future;

import com.mumu.common.thread.StandardThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GamePromiseExample {
    public static void main(String[] args) {
        // 1. 初始化线程池数组
        ThreadPoolExecutor[] executors = new ThreadPoolExecutor[4];
        for (int i = 0; i < executors.length; i++) {
            executors[i] = new ThreadPoolExecutor(
                    1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    new StandardThreadFactory("GameWorker-" + i));
        }

        // 2. 创建Promise
        String playerId = "player_12345";
        DefaultGameChannelPromise<String> promise = new DefaultGameChannelPromise<>(executors, playerId);

        // 3. 添加监听器
        promise.addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("操作成功，结果: " + future.getNow());
            } else {
                System.out.println("操作失败: " + future.cause().getMessage());
            }
        });

        // 4. 异步执行任务
        executors[Math.abs(playerId.hashCode()) % executors.length].execute(() -> {
            try {
                // 模拟业务处理
                Thread.sleep(1000);
                // 设置成功结果
                promise.setSuccess("完成任务");
            } catch (Exception e) {
                promise.setFailure(e);
            }
        });

        // 5. 主线程等待结果
        try {
            String result = promise.get(2, TimeUnit.SECONDS);
            System.out.println("最终结果: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 6. 关闭线程池
        for (ThreadPoolExecutor executor : executors) {
            executor.shutdown();
        }
    }
}