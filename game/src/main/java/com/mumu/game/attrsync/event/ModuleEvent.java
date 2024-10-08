/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.event;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.game.attrsync.constants.EventConstants;
import com.mumu.game.attrsync.enums.ModuleEnum;

/**
 * ModuleEvent
 * 推送模块变化事件
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:16
 */
public class ModuleEvent {
    /** 玩家ID */
    public long playerId;
    /** 事件ID */
    public int eventId;
    /** 模块 */
    public ModuleEnum module;
    /** 模块列表 */
    public ModuleEnum[] modules;
    /** 备用参数 */
    public Object[] args;


    /**
     * 发送指令
     * @param playerId playerId
     * @return com.mumu.game.attrsync.event.ModuleEvent
     * @date 2024/10/6 20:28
     */
    public static ModuleEvent sendCommand(int playerId, BalootTable table) {
        ModuleEvent event = new ModuleEvent();
        event.playerId = playerId;
        event.eventId = EventConstants.BALOOT_SEND_COMMAND;
        event.module = ModuleEnum.BALOOT;
        event.args = new Object[] {table};

        return event;
    }

    /**
     * 道具变化
     * @param playerId playerId
     * @param itemId itemId
     * @param changeCount changeCount
     * @param nowCount nowCount
     * @return com.mumu.game.attrsync.event.ModuleEvent
     * @date 2024/10/8 11:43
     */
    public static ModuleEvent changeItem(int playerId, int itemId, int changeCount, int nowCount) {
        ModuleEvent event = new ModuleEvent();
        event.playerId = playerId;
        event.eventId = EventConstants.CHANGE_ITEM;
        event.module = ModuleEnum.ITEM;
        event.args = new Object[] {itemId, changeCount, nowCount};

        return event;
    }
}
