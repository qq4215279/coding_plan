/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.timer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author liuzhen
 * @version 1.0.0 2024/5/27 14:50
 */
public class Main {

    public static void main(String[] args) {
        // 初始化
        SimpleTimerManager.getInstance().init(1, null, null);

        // long executeTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(5);
        // SimpleTimerManager.getInstance().addTask(new RefreshConfigTask(999, "RefreshConfigTask", executeTime));
        SimpleTimerManager.getInstance().addTask(new RefreshConfigTask(999, System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2)));

    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class RefreshConfigTask extends SimpleTimerTask {
        long uid = 0;

        /**
         * 构造函数
         * @param executeTime 执行时间
         * @return
         * @date 2024/5/25 16:42
         */
        public RefreshConfigTask(long uid, long executeTime) {
            super("RefreshConfigTask", executeTime, true);
            this.uid = uid;
        }

        @Override
        public void execute() {
            System.out.println("刷新配置表... 执行时间: " + new Date());

        }
    }

}
