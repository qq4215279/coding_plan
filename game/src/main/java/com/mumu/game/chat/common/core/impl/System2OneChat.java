/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core.impl;

import com.mumu.game.chat.common.ChatInfo;
import com.mumu.game.chat.common.core.AbstractSystemChat;

import java.util.Queue;

/**
 * System2OneChat
 * 发送系统消息给个人模板
 * @author liuzhen
 * @version 1.0.0 2024/11/3 19:32
 */
public class System2OneChat extends AbstractSystemChat {

    // TODO
    private PrivateChat privateChat;

    public System2OneChat(int chatType) {
        super(chatType);
    }

    @Override
    public void sendSystemChat(int msgType, String msg, long target, String param) {
        // TODO
        // 构建消息
        ChatInfo info = ChatInfo.createChatInfo();

        // 加入到聊天缓存中
        systemChatCache.add(target, info);

        // TODO 推送给target

        // TODO save db
    }

    @Override
    public void getSystemChat(long playerId) {
        Queue<ChatInfo> queue = systemChatCache.getQueue(playerId);
        // TODO
    }

}
