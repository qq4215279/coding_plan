/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.service;

import com.mumu.game.chat.common.core.Chat;
import com.mumu.game.chat.common.core.ChatManager;
import com.mumu.game.common.ErrorCode;
import reactor.util.function.Tuple3;

/**
 * ChatServiceImpl
 * 聊天业务实现类
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:17
 */
public class ChatServiceImpl implements ChatService {


    @Override
    public void sendChat(long playerId, int chatType, int msgType, String msg, long target, String param) {
        Chat chat = ChatManager.getInstance().getChat(chatType);
        if (chat == null) {
            return;
        }

        Tuple3<ErrorCode, String, Boolean> tuple3 = chat.checkChat(msgType, msg, target);
        if (tuple3.getT1() != ErrorCode.SUCCESS) {
            return;
        }

        chat.sentChat(playerId, msgType, msg, target, param, tuple3.getT3());

    }

    @Override
    public void getChatInfo(long playerId, int chatType, long target) {
        Chat chat = ChatManager.getInstance().getChat(chatType);
        chat.getChatInfo(playerId, chatType, target);
    }

}
