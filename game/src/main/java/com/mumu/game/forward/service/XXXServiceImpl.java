/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.service;

import com.mumu.common.config.ConfigItemInfo;
import com.mumu.game.forward.anno.FunctionType;
import com.mumu.game.forward.core.FunctionChangeService;

/**
 * XXXServiceImpl
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:56
 */
public class XXXServiceImpl implements FunctionChangeService {

  public void doPlayerLogin(long playerId) {
    // 业务中处理玩家登录
  }

  @Override
  public void doPlayerLogout(long playerId) {
    // 业务中处理玩家登出
  }

  @Override
  @FunctionType(1)
  public void doActivityEndService(long playerId, int functionId, int subType) {
   // TODO 处理活动结束
  }

  @Override
  public void doItemChangeService(long playerId, ConfigItemInfo configItemInfo, int itemId,
                                  long changeCount, long nowCount) {
    // 业务中处理道具变化...
    // 红点检查
    itemChangeCheckRedPoint(playerId, itemId, changeCount);
  }

  private void itemChangeCheckRedPoint(long playerId, int itemId, long changeCount) {
    //消耗Item时红点检查已在相关业务中添加，此处避免重复检查
    if (changeCount < 0) {
      return;
    }

    // 红点校验，并推送红点变化
    /*for (Integer festivalType : checkFestivalTypes) {
      checkRedPoint(playerId, festivalType);
    }*/
  }
}
