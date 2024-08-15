/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.guava.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * EventAsyncListener
 * 异步事件监听器
 * @author liuzhen
 * @version 1.0.0 2024/6/24 16:39
 */
public class EventAsyncListener {

    @Subscribe
    @AllowConcurrentEvents
    public void handlePlayerItemChangeEvent(PlayerItemChangeEvent event) {
        System.out.println("处理玩家道具变更事件; " + event.toString() + " on thread " + Thread.currentThread().getName());
    }
}


class PlayerItemChangeEvent {
    long playerId;
    int itemId;
    int num;

    public PlayerItemChangeEvent(long playerId, int itemId, int num) {
        this.playerId = playerId;
        this.itemId = itemId;
        this.num = num;
    }

    @Override
    public String toString() {
        return "PlayerItemChangeEvent{" +
                "playerId=" + playerId +
                ", itemId=" + itemId +
                ", num=" + num +
                '}';
    }
}