/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.conts;

/**
 * ChatType
 * 聊天类型常量
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:41
 */
public interface ChatType {
    /** 私聊 */
    int ONE_2_ONE = 1;
    /** 全服聊天 */
    int ONE_2_GLOBAL = 2;
    /** 公会聊天 */
    int ONE_2_CLUB = 3;
    /** 系统向个人发送消息 */
    int SYS_2_ONE = 4;
    /** 系统向全服发送消息 */
    int SYS_2_GLOBAL = 5;

}
