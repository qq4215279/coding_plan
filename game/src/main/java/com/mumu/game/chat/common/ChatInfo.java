/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common;

/**
 * ChatInfo
 * 聊天信息
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:59
 */
public class ChatInfo {
    public long id;
    /** 发送方 */
    public long from;
    /** 聊天类型 */
    public int chatType;
    /** 消息内容类型 */
    public int msgType;
    /** 消息 */
    public String msg;
    /** 接收方 */
    public long to;
    /** 当前时间 */
    public long currTime;
    /** 发送方头像 */
    private String head;
    /** 发送方名称 */
    private String name;


    /**
     *
     * @return com.mumu.game.chat.common.ChatInfo
     * @date 2024/11/3 18:10
     */
    public static ChatInfo createChatInfo() {
        // TODO
        return new ChatInfo();
    }

}
