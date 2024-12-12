/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game.common.core;

import java.util.Map;

/**
 * Room
 * 房间类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:02
 */
public class Room extends AbstractRoom {

    public Room(int gameId, int gameType, int roomId, int roomName) {
        super(gameId, gameType, roomId, roomName);

    }

    @Override
    public void init() {
        // TODO
    }

    /**
     * ticket
     * @return void
     * @date 2024/8/18 15:55
     */
    @Override
    public void ticket(int dt) {
        try {
            doTicketTable(dt);
        } catch (Exception e) {

        }
    }

    /**
     * doTicketTable
     * @param dt dt
     * @return void
     * @date 2024/8/18 21:16
     */
    private void doTicketTable(int dt) {
        // TODO
        for (Map.Entry<Integer, AbstractTable> entry : tableMap.entrySet()) {
            AbstractTable abstractTable = entry.getValue();
            try {
                abstractTable.ticket(dt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
