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
 * AbstractSystemChat
 * 抽象系统消息
 * @author liuzhen
 * @version 1.0.0 2024/11/3 19:33
 */
public abstract class AbstractSystemChat implements Chat {
    /** 聊天类型 */
    protected int chatType;
    /** 获得系统向个人发送聊天信息缓存 */
    protected final ChatCache<Long, ChatInfo> systemChatCache = new ChatCache<>(AbstractChat.MAX_QUEUE_SIZE, AbstractChat.MAX_QUEUE_SIZE);

    public AbstractSystemChat(int chatType) {
        this.chatType = chatType;
    }

    @Override
    public Tuple3<ErrorCode, String, Boolean> checkChat(int msgType, String msg, long target) {
        // TODO
        return null;
    }

    @Override
    public boolean sentChat(long fromPlayerId, int msgType, String msg, long target, String param, boolean isOneWay) {
        sendSystemChat(msgType, msg, target, param);
        return true;
    }

    @Override
    public void getChatInfo(long playerId, int chatType, long target) {
        getSystemChat(playerId);
    }

    /**
     * 发送系统消息
     * @param msgType 消息类型
     * @param msg 消息内容
     * @param target 接收目标
     * @param param 参数
     * @date 2024/11/3 19:19
     */
    public abstract void sendSystemChat(int msgType, String msg, long target, String param);

    /**
     * 获取指定玩家系统消息
     * @param playerId 指定玩家系统消息
     * @date 2024/11/3 19:44
     */
    public abstract void getSystemChat(long playerId);
}
