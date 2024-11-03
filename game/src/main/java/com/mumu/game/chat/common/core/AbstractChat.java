/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core;

import com.mumu.game.chat.common.ChatCache;
import com.mumu.game.chat.common.ChatInfo;
import com.mumu.game.common.ErrorCode;

import reactor.util.function.Tuple3;

/**
 * AbstractChat
 * 抽象聊天模版
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:36
 */
public abstract class AbstractChat implements Chat {
    /** 最大队列长度 */
    public static final int MAX_QUEUE_SIZE = 300;
    /** 最大私聊聊天记录 */
    public static final int MAX_PERSON_CHAT_SIZE = 30;

    /** 聊天类型 */
    protected int chatType;
    /** 玩家聊天记录缓存 */
    protected final ChatCache<Long, ChatInfo> chatCache = new ChatCache<>(MAX_QUEUE_SIZE, MAX_QUEUE_SIZE);

    public AbstractChat(int chatType) {
        this.chatType = chatType;
    }

    protected Tuple3<ErrorCode, String, Boolean> defalutCheckChat(int chatType, long target, String sourceMsg) {
        // TODO
        return null;
    }
}
