/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.service;

/**
 * FunctionService
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:42
 */
public interface FunctionService {

  /**
   * 获取功能状态
   * @param playerId 玩家id
   * @param functionId 功能id(0为全部功能)
   * @date 2024/10/7 16:45
   */
  void getFunctionState(long playerId, int functionId);

}
