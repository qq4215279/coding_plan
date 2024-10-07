/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync.handler;

import com.mumu.game.attrsync.enums.ModuleEnum;
import com.mumu.game.attrsync.event.ModuleEvent;

/**
 * AbstractModuleHandler
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:17
 */
public abstract class AbstractModuleHandler<bean> implements ModuleHandler {
    /** 所属模块 */
    protected ModuleEnum module;

    /**
     * 构造函数
     * @param module
     */
    public AbstractModuleHandler(ModuleEnum module) {
        this.module = module;
    }

    @Override
    public ModuleEnum getModule() {
        return module;
    }

    /**
     * 获取Id
     * @return
     */
    private long getId(ModuleEvent event) {
        if (event.args.length > 0) {
            return (long)event.args[0];
        }
        return event.playerId;
    }
}
