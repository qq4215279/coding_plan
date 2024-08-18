/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common;

import com.mumu.design.baloot.common.core.BalootTable;

/**
 * WorldBalootTable
 * Baloot桌子外围
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:29
 */
public class WorldBalootTable {
    /** 战场 */
    public BalootTable battleField;
    /** 监听回调 */
    private BalootGameListener balootGameListener;
    /** 开启标识 */
    private volatile boolean startFlag;
    /** 开始时间 */
    public long startTime;


    public WorldBalootTable(BalootTable battleField, BalootGameListener balootGameListener) {
        this.battleField = battleField;
        this.balootGameListener = balootGameListener;

    }

    /**
     * 初始化
     */
    public void init(BalootGameListener balootGameListener) {
        this.balootGameListener = balootGameListener;

        battleField.init(balootGameListener);
    }

    /**
     * 战斗开始
     */
    public void start() {
        if (startFlag) {
            return;
        }

        startFlag = true;
        startTime = System.currentTimeMillis();

        battleField.start();
    }

}
