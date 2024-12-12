/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game.common.core;

import java.util.HashMap;
import java.util.Map;

/**
 * GameManager
 * 游戏管理器
 * @author liuzhen
 * @version 1.0.0 2024/8/18 14:51
 */
public class GameManager {
    /** 游戏map */
    private Map<Integer, Game> gameMap = new HashMap<>();


    /**
     * 构造函数
     */
    private GameManager() {
    }

    /**
     * 获取单例
     * @return com.baloot.game.common.core.GameManager
     * @date 2024/8/18 15:18
     */
    public static GameManager getInstance() {
        return GameManager.SingletonEnum.INSTANCE.getGameManager();
    }

    /**
     * 单例对象枚举
     * @date 2024/8/18 13:09
     */
    private enum SingletonEnum {
        /** 单例 */
        INSTANCE;

        /** 游戏管理器 */
        private final GameManager gameSchedule;

        /**
         * 构造函数
         */
        SingletonEnum() {
            gameSchedule = new GameManager();
        }

        /**
         * 获取对象
         * @return com.baloot.game.common.core.GameManager
         * @date 2024/8/18 15:18
         */
        public GameManager getGameManager() {
            return gameSchedule;
        }
    }
}
