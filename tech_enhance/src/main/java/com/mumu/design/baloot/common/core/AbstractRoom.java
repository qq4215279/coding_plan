/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * AbstractRoom
 * 抽象房间类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 21:13
 */
public abstract class AbstractRoom {
    /** 游戏id */
    protected int gameId;
    /** 游戏类型 */
    protected int gameType;

    /** 房间id */
    protected int roomId;
    /** 房间名称 */
    protected int roomName;
    /** 房间内在线人数 */
    protected long onlime;

    /**  */
    protected Map<Integer, AbstractTable> tableMap;

    public AbstractRoom(int gameId, int gameType, int roomId, int roomName) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.roomId = roomId;
        this.roomName = roomName;
        this.onlime = 0;
        this.tableMap = new HashMap<>(10);
    }

    /**
     * 初始化
     * @return void
     * @date 2024/8/18 15:36
     */
    public abstract void init();

    /**
     * ticket
     * @return void
     * @date 2024/8/18 15:55
     */
    public abstract void doTicket(int dt);
}
