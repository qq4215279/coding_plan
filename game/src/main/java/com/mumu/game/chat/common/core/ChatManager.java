/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * ChatManager
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:23
 */
public class ChatManager {
    /** 聊天类型 与 聊天模版映射 */
    private final Map<Integer, Chat> typeChatMap = new HashMap<>();


    private ChatManager() {
    }

    private static class Holder {
        private static final ChatManager instance = new ChatManager();
    }

    /**
     *
     * @return com.mumu.game.chat.common.core.ChatManager
     * @date 2024/11/3 17:48
     */
    public static ChatManager getInstance() {
        return Holder.instance;
    }

    /**
     * 初始化
     * @date 2024/11/3 18:09
     */
    public void init() {
        // TODO init typeChatMap

        // TODO 清理db多余的聊天消息

        // TODO 初始化聊天缓存
    }

    /**
     * 获取聊天模版
     * @param chatType 聊天类型
     * @return com.mumu.game.chat.common.core.Chat
     * @date 2024/11/3 18:28
     */
    public Chat getChat(int chatType) {
        return typeChatMap.get(chatType);
    }


}
