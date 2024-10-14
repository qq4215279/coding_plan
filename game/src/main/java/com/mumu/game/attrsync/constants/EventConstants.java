/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.constants;

/**
 * EventConstants
 * 推送事件常量
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:20
 */
public final class EventConstants {
    private EventConstants() {
    }

    /** 新增 */
    public static final int CREATE = 1;
    /** 删除 */
    public static final int DELETE = 2;
    /** 修改 */
    public static final int UPDATE = 3;
    /** 批量修改 */
    public static final int BATCH_UPDATE = 4;


    /** baloot - 发送指令 */
    public static final int BALOOT_SEND_COMMAND = 1001;
    /** 道具变化 */
    public static final int CHANGE_ITEM = 1002;
}
