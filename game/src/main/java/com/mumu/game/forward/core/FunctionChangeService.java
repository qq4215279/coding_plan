/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.core;

import com.mumu.game.common.Player;
import com.mumu.game.shop.sdata.ConfigShop;
import com.mumu.java_tools.dom4j.config.ConfigItemInfo;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * FunctionChangeService
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:17
 */
public interface FunctionChangeService {

  /**
   * FunctionMethod方法枚举定义
   */
  enum MethodName {
    /** 处理玩家登录 */
    doPlayerLogin,
    /** 处理玩家登出 */
    doPlayerLogout,
    /**  */
    doGetClosedFunction,
    /**  */
    doGetRedPointStatus,
    /**  */
    doRefreshRedPoint,
    /**  */
    doItemChangeService,
    /**  */
    doItemOverdueService,
    /**  */
    doGiftOrderCheck,
    /**  */
    doBuyGoods,
    /**  */
    doChargeValue,
    /**  */
    doChargeStamps,
    /**  */
    doTaskChange,
    /**  */
    doActivityEndService,
    ;
  }


  /**
   * 玩家登录
   * @param playerId playerId
   */
  default void doPlayerLogin(long playerId) {}

  /**
   * 玩家登出
   * @param playerId playerId
   */
  default void doPlayerLogout(long playerId) {}

  /**
   * 业务实现类对应的 关闭功能入口Id列表
   * @param playerId
   */
  default List<Integer> doGetClosedFunction(long playerId, final Player player) {
    return Collections.emptyList();
  }

  /**
   * 获取功能红点状态【解耦RedPointModel，只需根据自有业务返回红点状态】
   * 备注：【无页签红点记录数据保存时，优先使用】
   * @param playerId
   * @param redPointStatusMap 【红点状态Map<FunctionId, Status>】
   */
  default void doGetRedPointStatus(long playerId, Map<Integer, Boolean> redPointStatusMap) {}

  /**
   * 刷新红点
   * @param playerId
   */
  default void doRefreshRedPoint(long playerId) {}

  /**
   * 道具变更触发的业务逻辑
   * remark: 适用于道具变更的 1-业务逻辑处理、2-任务进度更新、3-排行榜更新
   * @param playerId playerId
   * @param configItemInfo configItemInfo
   * @param itemId itemId
   * @param changeCount changeCount
   * @param nowCount nowCount
   * @date 2024/10/7 19:24
   */
  default void doItemChangeService(long playerId, ConfigItemInfo configItemInfo, int itemId, long changeCount, long nowCount) {}

  /***
   * 道具过期处理
   * @param playerId
   * @param overdueList
   */
  default void doItemOverdueService(long playerId, List<Integer> overdueList) {}

  /***
   * 礼包购买(下单)-前置检查
   * @param playerId playerId
   * @param giftId giftId
   * @return
   */
  default int doGiftOrderCheck(long playerId, int giftId) {
    return 1;
  }

  /**
   * 商品购买-付费成功回调
   * remark: 用于购买商品后，进行额外逻辑处理：可以是加成、额外奖励等
   * describe：配合<ShopName>注解，指定【商城名称】使用
   * @param configShop configShop
   */
  default void doBuyGoods(ConfigShop configShop) {}


  /**
   * 玩家充值成功回调事件
   * remark: 用于记录玩家购买的商品，充值金额 等
   * @param playerId
   * @param shopName		-商店名称
   * @param goodsId		-购买的商品
   * @param orderId		-订单Id
   * @param chargeValue	-充值金额
   * @param lastChargeTime-上次充值时间
   */
  default void doChargeValue(long playerId, String shopName, int goodsId, String orderId, int chargeValue, long lastChargeTime) {}

  /***
   * 玩家充值点券回调事件
   * @param playerId
   * @param goodsId
   * @param orderId
   * @param chargeValue
   * @param lastChargeTime-上次充值时间
   */
  default void doChargeStamps(long playerId, int goodsId, String orderId, int chargeValue, long lastChargeTime) {}

  /**
   * 非捕鱼类任务
   * (包括 道具消耗类任务、道具获得类任务[8.1.1版本开始支持]、其它进度类任务 等)
   * @param playerId
   * @param type	-任务类型
   * @param t		-子类型
   * @param p		-进度
   */
  default void doTaskChange(long playerId, int type, int t, long p){}

  /**
   * 注解类型的活动结束业务处理（道具清理、礼包记录删除）
   * remark：
   * 	1、在该Override方法注解<FunctionType>，指定功能类型
   * 	2、在对应Model中的活动结束位置，调用父类AbstractModel的endCheckHandler方法触发活动结束事件
   * 	3、重要：只能清理个人相关的，不能清理活动全局相关的
   * @param playerId
   */
  default void doActivityEndService(long playerId, int functionId,int subType) {}

}
