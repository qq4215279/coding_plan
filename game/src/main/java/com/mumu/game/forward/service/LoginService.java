/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.service;

/**
 * LoginService
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:52
 */
public interface LoginService {
  /**
   * 玩家登录
   * @param playerId playerId
   * @return void
   * @date 2024/10/7 19:54
   */
  void handleLogin(long playerId);

  /**
   * 玩家登出
   * @param playerId playerId
   * @return void
   * @date 2024/10/7 19:54
   */
  void handleLogout(long playerId);

  void activityEnd(long playerId, int functionId, int subType);

  void itemChange(long playerId, int itemId, long changeCount, long nowCount);
}
