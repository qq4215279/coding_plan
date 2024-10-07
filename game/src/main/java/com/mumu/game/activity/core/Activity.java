/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.core;

import cn.hutool.core.lang.Pair;
import com.mumu.game.common.ErrorCode;
import com.mumu.game.function.bean.GoodsBean;
import com.mumu.game.shop.sdata.ConfigShop;
import java.util.List;
import java.util.Map;

/**
 * Activity
 * 功能模块
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:19
 */
public interface Activity {

  /**
   * 初始化数据
   */
  void initData();

  /**
   * 绑定玩家
   * @param playerId 玩家唯一id
   * @param activityId 功能id
   */
  void bindPlayer(long playerId, int activityId);

  /**
   * 检查本功能是否有红点
   * @return bool
   */
  boolean checkRedPoint();

  /**
   * 获取活动id
   * @return int
   * @date 2024/10/7 17:34
   */
  int getActivityId();

  /**
   * 通过id获取子对象
   * @param subId id
   * @return 子对象
   */
  Activity getSubActivity(int subId);

  /**
   * 检查子功能状态
   */
  void checkSubActivity();

  /**
   * 是否开启
   * @return bool
   */
  boolean isOpen();

  /**
   * 获取包括值功能的状态map
   * @return 状态map
   */
  Map<Integer, ActivityState> getStateMap();

  /**
   * 获取当前状态
   * @return 状态类
   */
  ActivityState getStatus();

  /**
   * 检查本功能以及子功能是否有红点
   * @return bool
   */
  boolean hasRedPoint();

  /**
   * 是否为赛季类活动
   */
  boolean isSeasonActivity();

  /**
   * 设置是否开启
   * @param isOpen bool
   */
  void setOpen(boolean isOpen);

  /**
   * 设置是否有红点
   * @param isHas bool
   */
  void setRedPoint(boolean isHas);

  /**
   * 每日第一次打开页面消除红点
   * 删除当前红点
   */
  void quenchRedPoint();

  /**
   * 推送功能红点状态消息
   * @return void
   * @date 2023/12/21 11:43
   */
  void pushFunctionStatusMessage();

  /**
   * 是否开启商城
   * @return bool
   */
  boolean hasShop();

  /**
   * 检查是否可以购买
   * @param goodsId 商品id
   * @return 操作结果
   */
  Pair<Boolean, ErrorCode> checkBuy(int goodsId);

  /**
   * 购买成功后的处理逻辑 发货等
   * @param goodsId 商品id
   * @return 操作结果
   */
  Pair<Boolean, ErrorCode> afterBuy(long playerId, int goodsId, String channel, Map<Integer, Long> getItemsMap, Map<Integer, Long> extraRewardInfos);

  /**
   * 获取商店商品配置列表
   * @param funcId 商店id
   * @return 商品列表
   */
  List<ConfigShop> getShopGoodsList(int funcId);

  /**
   * 获取商店商品
   * @param goodsId 商品id
   * @return 商品列表
   */
  GoodsBean getShopGoodsBean(int goodsId);

}
