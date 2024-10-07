/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.core;

import java.util.Map;
import java.util.function.Consumer;

/**
 * FunctionForwardService
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:13
 */
public interface FunctionForwardService {
  /**
   * 玩家登录
   * @param playerId 玩家id
   * @date 2024/10/7 19:15
   */
  public void doPlayerLogin(long playerId);

  /**
   * 玩家登出
   * @param playerId 玩家id
   * @date 2024/10/7 19:15
   */
  public void doPlayerLogout(long playerId);

  /**
   * 道具变化(获得道具)业务处理转发
   * @param playerId playerId
   * @param itemId 道具id
   * @param changeCount 变化的数量
   * @param nowCount 变化后的当前数量
   * @date 2024/10/7 19:14
   */
  public void itemChangeService(long playerId, int itemId, long changeCount, long nowCount);

  /**
   * 获取功能红点状态
   * @param playerId 玩家id
   * @param redPointStatusMap 【红点状态Map<FunctionId, Status>】
   * @date 2024/10/7 19:15
   */
  public void getRedPointStatus(long playerId, Map<Integer, Boolean> redPointStatusMap);

  /**
   * 玩家充值成功回调事件业务处理转发
   * 用于记录玩家购买的商品，充值金额 等
   * @param shopName		-商店名称
   * @param goodsId		-购买的商品
   * @param orderId		-订单Id
   * @param chargeValue	-充值金额
   * @param lastChargeTime-上次充值时间
   */
  public void chargeValue(long playerId, String shopName, int goodsId, String orderId, int chargeValue, long lastChargeTime);

  /***
   * 玩家购买点券处理
   * @param playerId 玩家id
   * @param goodsId 商品id
   * @param orderId 订单id
   * @param chargeValue 充值金额
   */
  public void chargeStamps(long playerId, int goodsId, String orderId, int chargeValue);

  public void activityEndService(long playerId, int functionId, int subType);

  /***
   * 调用内部getService方法
   * @param clazz clazz
   * @return  <T> 必需是ServiceImpl类
   */
  <T> T getConventService(Class<T> clazz);

  /***
   * 运行内部方法
   * @param clazz clazz
   * @param consumer consumer
   */
  <T> void runInnerFunction(Class<T> clazz, Consumer<T> consumer);
}
