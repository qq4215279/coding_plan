package com.mumu.game.activity.core;

import cn.hutool.core.lang.Pair;
import com.mumu.game.common.ErrorCode;
import java.util.Map;

/**
 * VipActivity
 * @author liuzhen
 * @version 1.0.0 2024/11/19 16:45
 */
public class VipActivity extends AbstractActivity {



  @Override
  public void initData() {
    //


  }

  @Override
  public boolean checkRedPoint() {
    //
    return false;
  }

  @Override
  public Pair<Boolean, ErrorCode> checkBuy(int goodsId) {
    Pair<Boolean, ErrorCode> booleanErrorCodePair = super.checkBuy(goodsId);
    if (booleanErrorCodePair.getValue() != ErrorCode.SUCCESS) {
      return booleanErrorCodePair;
    }

    return null;
  }

  @Override
  public Pair<Boolean, ErrorCode> afterBuy(long playerId, int goodsId, String channel,
      Map<Integer, Long> getItemsMap, Map<Integer, Long> extraRewardInfos) {
    return super.afterBuy(playerId, goodsId, channel, getItemsMap, extraRewardInfos);
  }
}
