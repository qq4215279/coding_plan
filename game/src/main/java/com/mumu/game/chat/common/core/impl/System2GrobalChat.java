/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common.core.impl;

import com.mumu.game.chat.common.conts.ChatType;
import com.mumu.game.chat.common.core.AbstractSystemChat;

/**
 * System2GrobalChat
 * 系统消息给全局模板
 * @author liuzhen
 * @version 1.0.0 2024/11/3 19:33
 */
public class System2GrobalChat extends AbstractSystemChat {

    private GlobalChat globalChat;

    public System2GrobalChat() {
        super(ChatType.SYS_2_ONE);
    }

    @Override
    public void sendSystemChat(int msgType, String msg, long target, String param) {
        globalChat.sentChat(-1, msgType, msg, target, param, false);
    }

    @Override
    public void getSystemChat(long playerId) {
        // TODO
    }
}
