/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core;

import com.mumu.game.common.ErrorCode;
import reactor.util.function.Tuple3;

/**
 * Chat
 * 聊天模版接口定义
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:23
 */
public interface Chat {

    /**
     * 校验能否发送聊天
     * @param msgType 消息类型
     * @param msg 消息内容
     * @param target 接收目标
     * @return Tuple3<ErrorCode,String,Boolean> <错误码, 真正发送消息内容, 是否禁言>
     * @date 2024/11/3 18:12
     */
    Tuple3<ErrorCode, String, Boolean> checkChat(int msgType, String msg, long target);

    /**
     * 发送聊天
     * @param fromPlayerId 发送人
     * @param msgType 消息类型
     * @param msg 消息内容
     * @param target 接收目标
     * @param param 参数
     * @param isOneWay 是否禁言
     * @return boolean
     * @date 2024/11/3 17:33
     */
    boolean sentChat(long fromPlayerId, int msgType, String msg, long target, String param, boolean isOneWay);

    /**
     * 获取聊天消息内容
     * @param playerId playerId
     * @param chatType 消息类型
     * @param target 接收目标
     * @date 2024/11/3 17:35
     */
    void getChatInfo(long playerId, int chatType, long target);

}
