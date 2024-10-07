/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.core;

import cn.hutool.core.lang.Pair;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mumu.game.common.ErrorCode;
import com.mumu.game.common.Player;
import com.mumu.game.function.bean.CommonGoodsBean;
import com.mumu.game.function.bean.GoodsBean;
import com.mumu.game.function.bean.PushFunctionStatusMessage;
import com.mumu.game.function.bean.SingleFunctionInfoBean;
import com.mumu.game.sdata.ConfigFunction;
import com.mumu.game.sdata.ConfigFunctionCache;
import com.mumu.game.shop.common.ShopResetTypeEnum;
import com.mumu.game.shop.domain.PlayerBuyGoodsDO;
import com.mumu.game.shop.manager.PlayerBuyGoodsDOManager;
import com.mumu.game.shop.sdata.ConfigShop;
import com.mumu.game.shop.sdata.ShopConfigCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * AbstractActivity
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:21
 */
@Slf4j
public abstract class AbstractActivity implements Activity {
  /** 玩家id */
  protected long playerId;
  /** 玩家渠道 */
  protected String channel;
  /** 活动状态信息 */
  protected ActivityState activityState;
  /** 子活动map */
  protected Map<Integer, Activity> subActivityMap;

  @Override
  public int getActivityId() {
    if (activityState != null) {
      return this.activityState.getId();
    } else {
      throw new RuntimeException("getMyId:未绑定ID调用此方法");
    }
  }

  @Override
  public void bindPlayer(long uid, int activityId) {
    this.playerId = uid;
    // TODO  this.channel = getPlayer(uid).getChannel();
    this.activityState = new ActivityState(activityId, false, false, false, false, false, -1, -1);
    this.subActivityMap = new HashMap<>();
  }

  @Override
  public Activity getSubActivity(int subId) {
    Activity activity = subActivityMap.get(subId);
    if (activity == null) {
      activity = ActivityManager.loadActivity(playerId, subId);
      if (activity == null) {
        log.warn("SubId不存在{}", subId);
        return null;
      }
      subActivityMap.put(subId, activity);
    }
    return activity;
  }

  @Override
  public void checkSubActivity() {
    List<Integer> subActivity = getSubActivityIdList();
    for (int i = 0; i < subActivity.size(); i++) {
      int subId = subActivity.get(i);
      Activity activity = getSubActivity(subId);
      if (activity != null) {
        activity.checkSubActivity();
      }
    }
  }

  /**
   * 获取当前功能下所有子功能id
   * @return java.util.List<java.lang.Integer>
   * @date 2024/10/7 18:45
   */
  private List<Integer> getSubActivityIdList() {
    return ActivityManager.getSubActivityIdList(getActivityId());
  }

  @Override
  public boolean isOpen() {
    // TODO
    ConfigFunctionCache configFunctionCache = new ConfigFunctionCache();
    ImmutableMap<Integer, ConfigFunction> mainMap = configFunctionCache.getMainMap();
    ConfigFunction configFunction = mainMap.get(activityState.getId());
    if (configFunction == null) {
      return false;
    } else {
      if (configFunction.isClose()) {
        return false;
      }

      // TODO
      Player player = new Player(100000);

      // 等级条件
      if (player.getLevel() < configFunction.getLvLimit()) {
        return false;
      }
      // 玩家vip条件
      if (player.getVip() < configFunction.getVipLimit()) {
        return false;
      }

      // 功能增加渠道控制检查
      String[] channels = configFunction.getChannels();
      if (channels == null || channels.length == 0) {
        return true;

      } else {
        return Arrays.binarySearch(channels, channel) != -1;
      }
    }
  }

  @Override
  public Map<Integer, ActivityState> getStateMap() {
    HashMap<Integer, ActivityState> statusMap = new HashMap<>();
    statusMap.put(getActivityId(), getStatus());
    subActivityMap.forEach((id, sub) -> statusMap.putAll(sub.getStateMap()));
    return statusMap;
  }

  @Override
  public ActivityState getStatus() {
    return activityState;
  }

  @Override
  public boolean hasRedPoint() {
    boolean hasRedPoint = false;
    // 当前是activity是否有红点
    if (activityState.isHasRedPoint()) {
      hasRedPoint = true;

      // 子功能是否有红点
    } else {
      Iterator<Activity> iterator = subActivityMap.values().iterator();
      while (iterator.hasNext() && !hasRedPoint) {
        Activity next = iterator.next();
        if (next.hasRedPoint()) {
          hasRedPoint = true;
        }
      }
    }

    return hasRedPoint;
  }

  /**
   * 是否开启活动
   * @return bool
   */
  @Override
  public boolean isSeasonActivity() {
    return activityState.isSeaSonActivity();
  }

  @Override
  public void setOpen(boolean isOpen) {
    activityState.setOpen(isOpen);
  }

  @Override
  public void setRedPoint(boolean isHas) {
    activityState.setHasRedPoint(isHas);
  }

  @Override
  public void quenchRedPoint() {
  }

  public void pushFunctionStatusMessage() {
    // TODO 红点状态推送
    // getSessionManager().sendPlayerMsg(getStatusPushMsg(), playerId);
  }

  /**
   * 构建推送功能状态信息
   * @return com.cxx.hf.protocol.message.player.common.PushFunctionStatusMessage
   * @date 2023/12/21 11:46
   */
  protected PushFunctionStatusMessage getStatusPushMsg() {
    PushFunctionStatusMessage push = new PushFunctionStatusMessage();
    push.setFunctionId(getActivityId());
    Map<Integer, ActivityState> statusMap = getStatusMap();
    statusMap.forEach((id, subStatus) -> {
      Activity subActivity = getSubActivity(id);
      SingleFunctionInfoBean singleFunctionInfo = new SingleFunctionInfoBean();
      singleFunctionInfo.setMainFunction(subStatus.getId());
      singleFunctionInfo.setRedPoint((byte) (subActivity.hasRedPoint() ? 1 : 0));
      singleFunctionInfo.setOpen((byte) (subStatus.isOpen() ? 1 : 0));
      singleFunctionInfo.setTwinkle((byte) 0);
      singleFunctionInfo.setTwinkleEndTime("");
      singleFunctionInfo.setShowType((byte) 0);
      singleFunctionInfo.setOpenShop(subStatus.isOpenShop() ? 1 : 0);
      singleFunctionInfo.setInActivity(subStatus.isInActivity() ? 1 : 0);
      push.getFunctionStatus().add(singleFunctionInfo);
    });
    return push;
  }

  /**
   * 是否开启商城
   * @return bool
   */
  @Override
  public boolean hasShop() {
    ShopConfigCache shopConfigCache = new ShopConfigCache();
    List<Integer> goodsTypes = shopConfigCache.getGoodsTypes(getActivityId());
    return goodsTypes.isEmpty();
  }

  @Override
  public Pair<Boolean, ErrorCode> checkBuy(int goodsId) {
    ShopConfigCache shopConfigCache = new ShopConfigCache();

    ConfigShop configShop = shopConfigCache.getConfigShop(goodsId);
    if (configShop == null) {
      return Pair.of(false, ErrorCode.FAIL);
    }
   /* // 检查限购
    TimeRecordModel timeRecordModel = getWriteModel(playerId, TimeRecordModel.class);
    // 判断购买上限
    String keys = TimeRecordConstant.GOODS_BUY_LIMIT + "_" + goodsId;
    TimeRecordInfo recordOrCreate = timeRecordModel.getRecordOrCreate(keys, TimeRecordTimeType.getByType(configShop.getBuyLimitType().intValue()),
        configShop.getBuyLimitNumber().longValue());
    if (!recordOrCreate.check()) {
      return ItemOpnCode.SHOP_MT_GOODS_BUY_LIMIT;
    }*/
    return Pair.of(true, ErrorCode.SUCCESS);
  }

  @Override
  public Pair<Boolean, ErrorCode> afterBuy(long playerId, int goodsId, String channel, Map<Integer, Long> getItemsMap, Map<Integer, Long> extraRewardInfos) {
    ShopConfigCache shopConfigCache = new ShopConfigCache();
    ConfigShop configShop = shopConfigCache.getConfigShop(goodsId);
    if (configShop == null) {
      return Pair.of(false, ErrorCode.FAIL);
    }

    if (getItemsMap == null) {
      getItemsMap = Maps.newHashMapWithExpectedSize(6);
    }

    Player player = new Player(1000L);
    if (player == null) {
      return Pair.of(false, ErrorCode.FAIL);
    }

    // TODO 检查限购

    // TODO 记录购买次数

    // TODO 购买

    // TODO 发放额外奖励内容
    // grantExtraReward(configShop, player, shopName, goods, getItemsMap, isFirst);

    return Pair.of(true, ErrorCode.SUCCESS);
  }


  protected void grantExtraReward(ConfigShop configShop, Player player, String shopName, int goods, Map<Integer, Long> getItemsMap,
      boolean isFirst) {
  }

  @Override
  public List<ConfigShop> getShopGoodsList(int funcId) {
    ShopConfigCache shopConfigCache = new ShopConfigCache();
    List<Integer> goodsTypes = shopConfigCache.getGoodsTypes(funcId);

    List<ConfigShop> res = new ArrayList<>();
    goodsTypes.forEach(goodsType -> res.addAll(shopConfigCache.getConfigShopList(goodsType)));
    return res;
  }

  @Override
  public GoodsBean getShopGoodsBean(int goodsId) {
    ShopConfigCache shopConfigCache = new ShopConfigCache();
    ConfigShop configShop = shopConfigCache.getConfigShop(goodsId);
    if (configShop == null) {
      return null;
    }

    GoodsBean bean = new GoodsBean();
    bean.setGoodsId(goodsId);
    bean.setCommonGoods(buildCommonGoods(playerId, goodsId, configShop));

    return bean;

  }

  private CommonGoodsBean buildCommonGoods(long playerId, int goodsId, ConfigShop configShop) {
    CommonGoodsBean bean = new CommonGoodsBean();
    bean.setName(configShop.getName());
    // TODO 道具解析
    // bean.setPrice();
    // TODO
    // bean.setOriginPrice();
    // TODO 简历解析
    // bean.setRewards();
    // TODO
    // bean.setOriginRewards();
    // TODO 限购类型
    bean.setLimitType(Integer.parseInt(configShop.getLimitType()));
    bean.setLimitCount(configShop.getLimitCount());
    // 当前玩家购买次数
    PlayerBuyGoodsDO playerBuyGoodsDO = getAndResetPlayerBuyGoodsDO(playerId, goodsId);
    bean.setAlreadyBuyTimes(playerBuyGoodsDO.getCount());
    bean.setIcon(configShop.getIcon());
    bean.setParam(configShop.getParam());
    bean.setStartBuyTime(configShop.getStartBuyTime());
    bean.setEndBuyTime(configShop.getEndBuyTime());
    return bean;
  }

  public PlayerBuyGoodsDO getAndResetPlayerBuyGoodsDO(long playerId, int goodsId) {
    // TODO
    ShopConfigCache shopConfigCache = new ShopConfigCache();
    PlayerBuyGoodsDOManager playerBuyGoodsDOManager = new PlayerBuyGoodsDOManager();

    ConfigShop configShop = shopConfigCache.getConfigShop(goodsId);

    PlayerBuyGoodsDO playerBuyGoodsDO = playerBuyGoodsDOManager.getPlayerBuyGoodsDO(playerId,
        goodsId);

    ShopResetTypeEnum shopResetTypeEnum = ShopResetTypeEnum.getShopResetTypeEnum(
        configShop.getType());

    // 重置
    if (shopResetTypeEnum.checkReset(playerBuyGoodsDO, shopConfigCache)) {
      playerBuyGoodsDO.reset();
      // playerBuyGoodsDOManager.update(playerId, playerBuyGoodsDO);
    }

    return playerBuyGoodsDO;
  }


  /**
   * 处理赛季结束，移除商品购买记录信息
   * @date 2023/7/18 16:18
   * @param funcId 模块中的功能id
   * @return void
   */
 /* public void removeSeasonGoodsRecord(int funcId) {
    Set<Integer> goodsIdSet = new HashSet<>();
    // 当前模块所有功能id
    List<Integer> funcIdList = ActivityManager.getModuleAllActivityId(funcId);
    for (int id : funcIdList) {
      Map<Integer, ConfigGoodsCommonInfo> goodsIdCommonInfoMap = getConfigManager().getConfigGoodsIndex().getGoodsGroupingByFunc().getOrDefault(id,
          Collections.EMPTY_MAP);
      goodsIdSet.addAll(goodsIdCommonInfoMap.keySet());
    }

    TimeRecordModel timeRecordModel = getWriteModel(playerId, TimeRecordModel.class);
    for (int goodsId : goodsIdSet) {
      // 移除购买记录
      String recordKey = TimeRecordConstant.GOODS_BUY_LIMIT + "_" + goodsId;
      timeRecordModel.removeRecord(recordKey);
    }
  }*/

  /**
   * 校验父级节点功能开放
   * @return boolean
   * @date 2023/12/20 18:18
   */
  protected boolean checkParentFunctionOpen() {
    int myId = getActivityId();
    int parentId = ActivityManager.getModuleActivityId(myId);
    if (myId == parentId) {
      return true;
    }

    Activity activity = ActivityManager.loadActivity(playerId, parentId, false);
    if (activity == null) {
      return false;
    }

    return activity.isOpen();
  }

}
