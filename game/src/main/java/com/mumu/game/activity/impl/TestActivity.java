/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.impl;

import com.mumu.game.activity.anno.ActivityFunction;
import com.mumu.game.activity.constants.FunctionIdConstants;
import com.mumu.game.activity.core.AbstractActivity;
import lombok.extern.slf4j.Slf4j;

/**
 * TestActivity
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:03
 */
@ActivityFunction(id = FunctionIdConstants.FUNCTION_1)
@Slf4j
public class TestActivity extends AbstractActivity {

  @Override
  public void initData() {

  }

  @Override
  public boolean checkRedPoint() {
    return false;
  }
}
