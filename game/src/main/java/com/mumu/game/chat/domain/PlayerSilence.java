/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.domain;

import java.util.Date;

/**
 * PlayerSilence
 * 禁言
 * @author liuzhen
 * @version 1.0.0 2024/11/3 17:51
 */
public class PlayerSilence {
    /** 主键 */
    private int id;
    /** 被禁言者角色编号 */
    private int playerId;
    /** 解放禁言的时间 */
    private Date nextSayTime;
    /** 解放单向禁言时间 */
    private Date nextOnewayTime;
    /** 开始禁言时间 */
    private Date silenceTime;
    /** 禁言原因 */
    private String reason;

}
