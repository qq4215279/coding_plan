/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.mumu.game.attrsync.enums.ModuleEnum;
import com.mumu.game.attrsync.event.ModuleEvent;
import com.mumu.game.attrsync.handler.ModuleHandler;

/**
 * ModuleSyncManager
 * 同步管理器
 * @author liuzhen
 * @version 1.0.0 2024/10/6 17:18
 */
public class ModuleSyncManager {
    /** instance */
    private static final ModuleSyncManager instance = new ModuleSyncManager();
    private ModuleSyncManager() {
    }

    public static ModuleSyncManager getInstance() {
        return instance;
    }

    private Map<ModuleEnum, ModuleHandler> moduleHandlerMap = new HashMap<>();

    /**
     * 初始化
     */
    public void init() {
        // Set<Class<?>> classSet = Scans.getClasses("com.reign.sbtj.attrsync.data.handler");
        /*for (Class<?> clazz : classSet) {
            if (clazz == ModuleHandler.class || clazz == BaseModuleHandler.class) {
                continue;
            }

            if (BaseModuleHandler.class.isAssignableFrom(clazz)) {
                try {
                    ModuleHandler handler = (ModuleHandler)clazz.getDeclaredConstructor().newInstance();
                    moduleHandlerMap.put(handler.getModule(), handler);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        classSet = Scans.getClasses("com.reign.sbtj.attrsync.data");
        for (Class<?> clazz : classSet) {
            if (BaseAttr.class.isAssignableFrom(clazz)) {
                BaseAttr.init(clazz);
            }
        }*/
    }

    /**
     * 处理事件
     * @param event
     */
    public void onEvent(ModuleEvent event) {
        // 单模块
        if (ArrayUtils.isEmpty(event.modules)) {
            ModuleHandler handler = moduleHandlerMap.get(event.module);
            if (null == handler) {
                return;
            }

            handler.onEvent(event);
            return;
        }

        // 多模块
        for (ModuleEnum module : event.modules) {
            ModuleHandler handler = moduleHandlerMap.get(module);
            if (null == handler) {
                continue;
            }

            handler.onEvent(event);
        }
    }

}
