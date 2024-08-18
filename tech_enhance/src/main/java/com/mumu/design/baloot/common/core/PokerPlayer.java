/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import java.util.List;

/**
 * PokerPlayer
 * 扑克玩家
 * @author liuzhen
 * @version 1.0.0 2024/8/18 16:11
 */
public class PokerPlayer {
    /** 玩家id */
    private long playerId;
    /** 座位号(1 2 3 4) */
    private int tag;

    /** 是否为发牌员 */
    private boolean isDealer;
    /** 是否为主宰者 */
    private boolean isMaster;

    /** 剩余手牌 */
    private List<Poker> restPokers;
    /** 获得的分数手牌 */
    private List<Poker> scorePokers;
    /** 原始手牌 */
    private List<Poker> allPokers;

    /** 所在桌子 */
    private BalootTable table;

    public PokerPlayer(long playerId, int tag, BalootTable table) {
        this.playerId = playerId;
        this.tag = tag;
        this.table = table;
    }
}
