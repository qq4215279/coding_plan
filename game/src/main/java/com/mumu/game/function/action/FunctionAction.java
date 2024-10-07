/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.action;

import com.mumu.game.function.service.FunctionService;
import javax.annotation.Resource;
import org.apache.commons.lang.math.Fraction;

/**
 * FunctionAction
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:41
 */
public class FunctionAction {

  @Resource
  private FunctionService functionService;

  /**
   * 获取功能状态
   * @param playerId 玩家id
   * @param functionId 功能id(0为全部功能)
   * @date 2024/10/7 16:44
   */
  public void getFunctionState(long playerId, int functionId) {
    functionService.getFunctionState(playerId, functionId);
  }

}
