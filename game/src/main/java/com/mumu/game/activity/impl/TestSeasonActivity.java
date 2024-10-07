/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.impl;

import com.mumu.game.activity.anno.ActivityFunction;
import com.mumu.game.activity.constants.FunctionIdConstants;
import com.mumu.game.activity.core.AbstractSeasonActivity;
import lombok.extern.slf4j.Slf4j;

/**
 * TestSeasonActivity
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:04
 */
@ActivityFunction(id = FunctionIdConstants.FUNCTION_2)
@Slf4j
public class TestSeasonActivity extends AbstractSeasonActivity {

  @Override
  public boolean checkRedPoint() {
    return false;
  }


  @Override
  protected int getSeasonInfoId() {
    return 0;
  }

  @Override
  protected long getStartTime() {
    return 0;
  }

  @Override
  protected long getEndTime() {
    return 0;
  }

  @Override
  protected void initNewSeasonData() {

  }

  @Override
  protected void handleDailyReset() {

  }

  @Override
  protected void handleWeeklyReset() {

  }

  @Override
  protected void handleMonthReset() {

  }

  @Override
  protected void handleSeasonCacl() {

  }

  @Override
  protected void handleSeasonReset() {

  }
}
