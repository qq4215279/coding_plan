/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core.impl;

import java.util.LinkedList;
import java.util.Queue;

import com.mumu.game.chat.common.ChatInfo;
import com.mumu.game.chat.common.conts.ChatType;
import com.mumu.game.chat.common.core.AbstractChat;
import com.mumu.game.common.ErrorCode;

import reactor.util.function.Tuple3;

/**
 * PrivateChat
 * 私聊模板
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:31
 */
public class PrivateChat extends AbstractChat {

    public PrivateChat() {
        super(ChatType.ONE_2_ONE);
    }

    @Override
    public Tuple3<ErrorCode, String, Boolean> checkChat(int msgType, String msg, long target) {
        Tuple3<ErrorCode, String, Boolean> tuple3 = defalutCheckChat(chatType, target, msg);

        return null;
    }

    @Override
    public boolean sentChat(long fromPlayerId, int msgType, String msg, long toPlayerId, String param, boolean isOneWay) {
        // TODO
        ChatInfo info = ChatInfo.createChatInfo();
        // 加到他人的聊天缓存中
        chatCache.add(fromPlayerId, info);

        // TODO 推送给自己

        // 没有禁言，推送给私聊玩家
        if (!isOneWay) {
            // 加入到target目标缓存
            chatCache.add(toPlayerId, info);

            // TODO 在线盘点 推送给target

            // TODO save保存私聊 PlayerChat

            // TODO 记录最近聊天列表信息
        }

        return true;
    }

    @Override
    public void getChatInfo(long playerId, int chatType, long toPlayerId) {
        Queue<ChatInfo> queue = chatCache.getQueue(playerId);
        Queue<ChatInfo> playerChat = filterOtherPlayerChatCacheInfo(playerId, toPlayerId, queue);

        // TODO
    }

    private Queue<ChatInfo> filterOtherPlayerChatCacheInfo(long playerId, long target, Queue<ChatInfo> queue) {
        Queue<ChatInfo> playerChat = new LinkedList<>();
        for (ChatInfo chatInfo : queue) {
            long id = chatInfo.from;
            long toPlayerId = chatInfo.to;
            if ((playerId == id && target == toPlayerId) || (playerId == toPlayerId && target == id)) {
                playerChat.add(chatInfo);
            }
        }
        return playerChat;
    }
}
