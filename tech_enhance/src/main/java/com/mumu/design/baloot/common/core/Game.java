/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import com.mumu.design.baloot.GameSchedule;
import com.mumu.design.baloot.Updatable;

import java.util.HashMap;
import java.util.Map;

/**
 * Game
 * 游戏类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:00
 */
public class Game implements Updatable {
    /** 游戏id */
    private int gameId;
    /** 游戏类型 */
    private int gameType;
    /** 游戏名称 */
    private String gameName;
    /** 游戏开始时间 */
    private long startTime;
    /** 游戏在线人数 */
    private long onlime;

    /** 房间id 与 房间 映射 */
    private Map<Integer, Room> rooms;

    public Game(int gameType, int gameId, String gameName) {
        this.gameType = gameType;
        this.gameId = gameId;
        this.gameName = gameName;
        this.startTime = System.currentTimeMillis();
        this.onlime = 0;

        this.rooms = new HashMap<>(1);
    }

    /**
     * 初始化
     * @return void
     * @date 2024/8/18 15:36
     */
    public void init() {
        // TODO

        start();
    }

    /**
     * 开始游戏
     * @return void
     * @date 2024/8/18 21:17
     */
    private void start() {
        GameSchedule.getInstance().schedule(this);
    }

    @Override
    public void update(int dt) {
        // TODO 帧同步

        try {
            doTicketRoom(dt);
        } catch (Exception e) {

        }
    }

    /**
     * doTicketTable
     * @param dt dt
     * @return void
     * @date 2024/8/18 15:56
     */
    private void doTicketRoom(int dt) {
        // TODO
        for (Map.Entry<Integer, Room> entry : rooms.entrySet()) {
            Room room = entry.getValue();
            try {
                room.doTicket(dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
