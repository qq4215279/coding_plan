/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.java_base.timer_task;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Demo01
 *
 * @author liuzhen
 * @version 1.0.0 2021/3/19 19:48
 */
public class Demo01 {


    private static class TestTask extends TimerTask{

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("倒计时：" + i);
            }
        }
    }

    public static void main(String[] args) {

        Timer timer = new Timer();

        TestTask task = new TestTask();

        timer.schedule(task, new Date(System.currentTimeMillis() + 5000));

    }


}
