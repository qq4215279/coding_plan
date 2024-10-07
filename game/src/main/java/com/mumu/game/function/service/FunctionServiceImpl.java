/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.service;

import com.mumu.game.activity.core.Activity;
import com.mumu.game.activity.core.ActivityManager;
import com.mumu.game.activity.core.ActivityState;
import com.mumu.game.function.bean.SingleFunctionInfoBean;
import java.util.Map;

/**
 * FunctionServiceImpl
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:42
 */
public class FunctionServiceImpl implements FunctionService {

  @Override
  public void getFunctionState(long playerId, int functionId) {
    Activity activity = ActivityManager.loadActivity(playerId, functionId);
    if (activity != null) {
      Map<Integer, ActivityState> stateMap = activity.getStateMap();
      stateMap.forEach((id, status) -> {
        Activity subActivity = activity.getSubActivity(id);
        SingleFunctionInfoBean singleFunctionInfo = new SingleFunctionInfoBean();
        singleFunctionInfo.setMainFunction(status.getId());
        singleFunctionInfo.setRedPoint((byte) (subActivity.hasRedPoint() ? 1 : 0));
        singleFunctionInfo.setOpen((byte) (status.isOpen() ? 1 : 0));
        singleFunctionInfo.setTwinkle((byte) 0);
        singleFunctionInfo.setTwinkleEndTime("");
        singleFunctionInfo.setOpenShop(status.isOpenShop() ? 1 : 0);
        singleFunctionInfo.setInActivity(status.isInActivity() ? 1 : 0);
        singleFunctionInfo.setShowType((byte) 0);
      });
    }
  }
}
