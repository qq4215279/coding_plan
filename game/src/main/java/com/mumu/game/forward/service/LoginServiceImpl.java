/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.service;

import com.mumu.game.forward.core.FunctionForwardService;
import javax.annotation.Resource;

/**
 * LoginServiceImpl
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:52
 */
public class LoginServiceImpl implements LoginService {

  @Resource
  private FunctionForwardService functionForwardService;

  @Override
  public void handleLogin(long playerId) {
    // 登录代码中插入下面这行代码
    functionForwardService.doPlayerLogin(playerId);
  }

  @Override
  public void handleLogout(long playerId) {
    // 登出代码中插入下面这行代码
    functionForwardService.doPlayerLogout(playerId);
  }

  @Override
  public void activityEnd(long playerId, int functionId, int subType) {
    // 活动结束的业务中插入下面代码
    functionForwardService.activityEndService(playerId, functionId, subType);
  }

  @Override
  public void itemChange(long playerId, int itemId, long changeCount, long nowCount) {
    // 道具变化的业务中插入下面这行代码
    functionForwardService.itemChangeService(playerId, itemId, changeCount, nowCount);
  }
}
