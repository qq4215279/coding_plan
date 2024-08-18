/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import com.mumu.design.baloot.common.BalootGameListener;
import com.mumu.design.baloot.common.event.BalootEvent;
import com.mumu.design.baloot.common.state.SendPokerState;
import com.mumu.design.fsm.FSM;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * BalootTable
 * baloot桌子类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:20
 */
public class BalootTable extends AbstractTable {
    /** 游戏模式 */
    protected int mode;
    /** 第几局 */
    protected int round;
    /** 发牌员tag */
    protected int dealerTag;
    /** 主宰者tag */
    protected int masterTag;

    /** 当前叫话玩家 */
    protected PokerPlayer bider;
    /** 当前局发牌员 */
    protected PokerPlayer dealer;
    /** 当前局主宰者 */
    protected PokerPlayer master;

    /** 玩家tag 与 玩家信息 */
    protected Map<Integer, PokerPlayer> tagPlayerMap = new HashMap<>(4);

    public BalootTable(int roomId, int tableId, String tableName, int tablePassword) {
        super(roomId, tableId, tableName, tablePassword);
    }


    @Override
    public void init(BalootGameListener balootGameListener) {
        // TODO 初始化
        super.fsm = new FSM();
        super.balootGameListener = balootGameListener;

        this.round = 1;
        // TODO tagPlayerMap
    }

    @Override
    public void start() {

        // 1. 进入发牌状态
        changeFsmState(new SendPokerState(this));
    }

    @Override
    public void doTicket(int dt) {
        // TODO

        // 处理事件
        handleEvent();

        if (null != fsm) {
            fsm.update(dt);
        }
    }

    /**
     * 处理Baloot事件
     * @return void
     * @date 2024/8/18 20:54
     */
    private void handleEvent() {
        int times = 0;
        long curr = System.currentTimeMillis();
        while (!events.isEmpty()) {
            // TODO 最多处理100ms
            if (System.currentTimeMillis() - curr > 100L) {
                return;
            }

            // 最多处理100个事件
            if (times > 100) {
                return;
            }

            events.poll().onEvent();

            times++;
        }
    }

    /**
     * add事件
     * @param event event
     * @return void
     * @date 2024/8/18 20:58
     */
    public void offerEvent(BalootEvent event) {
        try {
            // TODO
            if (!events.offer(event, 1L, TimeUnit.MILLISECONDS)) {
                // log.error("offerEvent fail");
            }
        } catch (InterruptedException e) {
            // log.error("offerEvent error", e);
        }
    }


    // ========================================================================>

    /**
     * 设置游戏模式
     * @param mode mode
     * @return void
     * @date 2024/8/18 17:50
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 增加局数
     * @return void
     * @date 2024/8/18 17:50
     */
    public void addRound() {
        this.round++;
    }

    /**
     * 设置当前发牌员
     * @param dealerTag dealerTag
     * @return void
     * @date 2024/8/18 17:50
     */
    public void setDealerTag(int dealerTag) {
        this.dealerTag = dealerTag;
    }

    public void setMasterTag(int masterTag) {
        this.masterTag = masterTag;
    }
}
