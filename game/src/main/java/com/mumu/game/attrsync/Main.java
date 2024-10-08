/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync;

import com.mumu.design.baloot.common.core.BalootTable;
import com.mumu.game.attrsync.event.ModuleEvent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Main
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 15:35
 */
public class Main {

    private static final Set<Class<?>> WRAPPER_TYPES;

    static {
        Set<Class<?>> tempSet = new HashSet<>();
        tempSet.add(Boolean.class);
        tempSet.add(Character.class);
        tempSet.add(Byte.class);
        tempSet.add(Short.class);
        tempSet.add(Integer.class);
        tempSet.add(Long.class);
        tempSet.add(Float.class);
        tempSet.add(Double.class);
        tempSet.add(Void.class);

        WRAPPER_TYPES = Collections.unmodifiableSet(tempSet);
    }

    public static boolean isWrapperType(Object obj) {
        if (obj == null) {
            return false;
        }
        return WRAPPER_TYPES.contains(obj.getClass());
    }

    public static void main(String[] args) {
        System.out.println(isWrapperType(123)); // true
        System.out.println(isWrapperType(123.45)); // true
        System.out.println(isWrapperType("Hello")); // false
        System.out.println(isWrapperType(true)); // true


        // 发送指令推送事件
        ModuleSyncManager.getInstance()
            .onEvent(ModuleEvent.sendCommand(1000, new BalootTable(1, 1, "sss", 0)));

        // 道具变化推送事件
        ModuleSyncManager.getInstance().onEvent(ModuleEvent.changeItem(1000, 1001, 100, 999999));
    }

}
