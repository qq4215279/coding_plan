/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.service;

/**
 * ChatService
 * 聊天业务层
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:17
 */
public interface ChatService {

    /**
     * 发送聊天消息
     * @param playerId playerId
     * @param chatType 聊天类型
     * @param msgType 消息类型
     * @param msg 内容
     * @param target 目标
     * @param param param
     * @date 2024/11/3 17:22
     */
    void sendChat(long playerId, int chatType, int msgType, String msg, long target, String param);

    /**
     * 获取聊天信息
     * playerId playerId
     * chatType 聊天类型
     * @param target 目标
     * @date 2024/11/3 17:22
     */
    void getChatInfo(long playerId, int chatType, long target);

    /**
     * 发送系统消息给个人
     * @param msgType 消息类型
     * @param msg 内容
     * @param target 目标
     * @param param param
     * @date 2024/11/3 19:55
     */
    void sendSystemMsg(int msgType, String msg, long target, String param);

    /**
     * 发送系统消息给全服
     * @param msgType 消息类型
     * @param msg 内容
     * @param target 目标
     * @param param param
     * @date 2024/11/3 19:56
     */
    void sendSystemMsg2Globle(int msgType, String msg, long target, String param);

    /**
     * 获取系统消息
     * @param playerId playerId
     * @date 2024/11/3 19:27
     */
    void getSystemMsg(long playerId);
}
