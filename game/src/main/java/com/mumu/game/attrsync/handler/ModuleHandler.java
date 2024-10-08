/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.handler;

import com.mumu.game.attrsync.enums.ModuleEnum;
import com.mumu.game.attrsync.event.ModuleEvent;

/**
 * ModuleHandler
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:17
 */
public interface ModuleHandler {

    /**
     *
     * @param playerId 玩家id
     * @date 2024/10/8 11:41
     */
    void sync(long playerId);

    /**
     * 处理事件
     * @param event event
     * @return boolean
     * @date 2024/10/6 17:21
     */
    boolean onEvent(ModuleEvent event);

    /**
     * 获取module
     * @return ModuleEnum
     * @date 2024/10/6 17:22
     */
    ModuleEnum getModule();

}
