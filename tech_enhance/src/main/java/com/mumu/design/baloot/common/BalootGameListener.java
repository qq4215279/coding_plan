/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common;

/**
 * BalootGameListener
 * Baloot监听器
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:28
 */
public class BalootGameListener implements BalootListener {

    /**
     * 设置世界桌子
     * @param table table
     * @return void
     * @date 2024/8/18 15:31
     */
    public void setWorldBattleField(WorldBalootTable table) {
        this.table = table;
    }

    /** 世界桌子 */
    private WorldBalootTable table;



}
