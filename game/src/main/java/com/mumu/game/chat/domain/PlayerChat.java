/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.domain;

import java.util.Date;

import javax.persistence.Id;

/**
 * PlayerChat
 * 玩家聊天内容表
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:49
 */
public class PlayerChat {
    /**  */
    @Id
    private long id;
    /** 发送方 */
    private long playerId;
    /** 类型 */
    private int chatType;
    /** 聊天内容类型 */
    private int msgType;
    /** 消息 */
    private String msg;
    /** 接收方 */
    private long target;
    /** 发送方头像 */
    private String head;
    /** 发送方名称 */
    private String name;
    /** 当前时间 */
    private Date createTime;
    /** 额外参数 */
    private String param;
}
