/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.action;

import com.mumu.game.chat.service.ChatService;

/**
 * ChatAction
 * 聊天action
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:16
 */
public class ChatAction {

    private ChatService chatService;

    /**
     * 发送聊天消息
     * @param chatType 聊天类型
     * @param target 目标
     * @param msgType 消息类型
     * @param msg 内容
     * @param param param
     * @date 2024/11/3 17:19
     */
    public void sendChat(int chatType, int msgType, String msg, long target, String param) {
        long playerId = 10001;
        chatService.sendChat(playerId, chatType, msgType, msg, target, param);
    }

    /**
     * 获取聊天信息
     * @param chatType 聊天类型
     * @param target 目标
     * @date 2024/11/3 17:20
     */
    public void getChatInfo(int chatType, long target) {
        long playerId = 10001;
        chatService.getChatInfo(playerId, chatType, target);
    }

    /**
     * 获取系统发送给自己的消息
     * @date 2024/11/3 19:51
     */
    public void getSystemMsg() {
        long playerId = 10001;
        chatService.getSystemMsg(playerId);
    }

}
