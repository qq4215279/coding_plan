/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.guava.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * EventSyncListener
 * 同步事件监听器
 * @author liuzhen
 * @version 1.0.0 2024/6/24 16:27
 */
public class EventSyncListener {

    @Subscribe
    @AllowConcurrentEvents
    public void handleEvent(String event) {
        System.out.println("Received event: " + event + " in thread " + Thread.currentThread().getName());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handlePlayerUplevelEvent(PlayerUplevelEvent event) {
        System.out.println("处理玩家升级事件; " + event.toString() + " on thread " + Thread.currentThread().getName());
    }

}

class PlayerUplevelEvent {
    long playerId;
    int level;

    public PlayerUplevelEvent(long playerId, int level) {
        this.playerId = playerId;
        this.level = level;
    }

    @Override
    public String toString() {
        return "PlayerUplevelEvent{" +
                "playerId=" + playerId +
                ", level=" + level +
                '}';
    }
}
