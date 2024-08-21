/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.core;

import com.mumu.design.baloot.common.BalootGameListener;
import com.mumu.design.baloot.common.event.BalootEvent;
import com.mumu.design.fsm.FSM;
import com.mumu.design.fsm.inf.FSMState;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * AbstractTable
 * 抽象桌子类
 * @author liuzhen
 * @version 1.0.0 2024/8/18 15:08
 */
public abstract class AbstractTable {
    /** 房间id */
    protected int roomId;
    /** 桌子id */
    protected int tableId;
    /** 桌子名称（默认: "table" + tableId） */
    protected String tableName;
    /** 桌子密码（默认0） */
    protected int tablePassword;

    /** 状态机 */
    protected FSM fsm;
    /** 监听器 */
    protected BalootGameListener balootGameListener;

    /** Baloot事件 */
    protected BlockingQueue<BalootEvent> events;


    public AbstractTable(int roomId, int tableId, String tableName, int tablePassword) {
        this.roomId = roomId;
        this.tableId = tableId;
        this.tableName = tableName;
        this.tablePassword = tablePassword;
        this.events = new LinkedBlockingQueue<>();
    }

    /**
     * 初始化
     * @param balootGameListener balootGameListener
     * @return void
     * @date 2024/8/18 15:34
     */
    public abstract void init(BalootGameListener balootGameListener);
    
    /**
     * start
     * @return void
     * @date 2024/8/18 15:41
     */
    public abstract void start();

    /**
     * ticket
     * @param dt dt
     * @return void
     * @date 2024/8/18 16:01
     */
    public abstract void ticket(int dt);

    /**
     * 修改状态机状态
     * @param state state
     * @return void
     * @date 2024/8/18 16:08
     */
    public void changeFsmState(FSMState state) {
        if (null != fsm) {
            fsm.changeState(state);
        }
    }
}
