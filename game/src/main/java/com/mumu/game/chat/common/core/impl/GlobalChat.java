/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core.impl;

import java.util.Queue;

import com.mumu.game.chat.common.ChatInfo;
import com.mumu.game.chat.common.conts.ChatType;
import com.mumu.game.chat.common.core.AbstractChat;
import com.mumu.game.common.ErrorCode;
import com.mumu.game.common.group.Group;
import com.mumu.game.common.group.GroupManager;
import com.mumu.game.common.group.GroupUtil;

import reactor.util.function.Tuple3;

/**
 * GlobalChat
 * 全局聊天模板
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:40
 */
public class GlobalChat extends AbstractChat {
    /** 世界组ID */
    private static final Long ONLINE_GROUP_ID = -1L;


    public GlobalChat() {
        super(ChatType.ONE_2_GLOBAL);
    }


    @Override
    public Tuple3<ErrorCode, String, Boolean> checkChat(int msgType, String msg, long target) {
        return null;
    }

    @Override
    public boolean sentChat(long fromPlayerId, int msgType, String msg, long target, String param, boolean isOneWay) {
        ChatInfo info = ChatInfo.createChatInfo();

        if (!isOneWay) {
            chatCache.add(ONLINE_GROUP_ID, info);

            // TODO 玩家登录的时候，加入组；登出，session失效，退出组
            Group group = GroupManager.getInstance().getGroup(GroupUtil.getWorldGroupName());
            // TODO 全服推送
            // group.notify();

            // TODO save to db

        } else {
            // TODO 禁言只推送给自己
        }

        // TODO 记录玩家全服聊天次数


        return false;
    }

    @Override
    public void getChatInfo(long playerId, int chatType, long target) {
        Queue<ChatInfo> playerChat = chatCache.getQueue(ONLINE_GROUP_ID);

    }
}
