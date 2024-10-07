package com.mumu.game.shop.service;

import com.mumu.game.activity.core.Activity;
import com.mumu.game.activity.core.ActivityManager;
import com.mumu.game.function.bean.CommonGoodsBean;
import com.mumu.game.function.bean.GoodsBean;
import com.mumu.game.shop.sdata.ConfigShop;
import com.mumu.game.shop.common.ShopResetTypeEnum;
import com.mumu.game.shop.domain.PlayerBuyGoodsDO;
import com.mumu.game.shop.manager.PlayerBuyGoodsDOManager;
import com.mumu.game.shop.sdata.ShopConfigCache;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.weekend.Fn;

/**
 * ShopServiceImpl
 * 商城service
 * @author liuzhen
 * @version 1.0.0 2024/9/25 15:18
 */
@Service("shopServiceImpl")
public class ShopServiceImpl implements ShopService {

  @Resource
  ShopConfigCache shopConfigCache;

  @Resource
  PlayerBuyGoodsDOManager playerBuyGoodsDOManager;

  @Override
  public void getShopGoodsInfoList(long playerId, int functionId) {
    // TODO
    Activity activity = ActivityManager.loadActivity(playerId, functionId);
    if (!activity.isOpen()) {
      return;
    }

    // activity.

    // List<Integer> goodsTypes = shopConfigCache.getGoodsTypes(functionId);
    //
    // WCGetShopGoodsMessage resMsg = new WCGetShopGoodsMessage();
    // resMsg.setFunctionId(functionId);
    //
    // for (int goodsType : goodsTypes) {
    //   resMsg.getGoodsList().addAll(buildGoodsBeanList(playerId, goodsType));
    // }
    //
    // // TODO 返回
    // MessageSender.sendToPlayer(playerId, Cmd.WCGetShopGoods, resMsg);
  }

  /**
   * 构建商品bean列表
   * @param playerId 玩家id
   * @param goodsType 商品类型
   * @return java.util.List<com.game.proto.shop.GoodsBean>
   * @date 2024/9/25 15:53
   */
  private List<GoodsBean> buildGoodsBeanList(long playerId, int goodsType) {
    List<GoodsBean> goodsList = new ArrayList<>();
    List<ConfigShop> configShopList = shopConfigCache.getConfigShopList(goodsType);
    for (ConfigShop configShop : configShopList) {
      GoodsBean bean = buildGoodsBean(playerId, configShop);

      goodsList.add(bean);
    }

    return goodsList;
  }

  /**
   * 构建商品信息bean
   * @param playerId 玩家id
   * @param configShop 商品配置表
   * @return com.game.proto.shop.GoodsBean
   * @date 2024/9/25 15:53
   */
  private GoodsBean buildGoodsBean(long playerId, ConfigShop configShop) {
    GoodsBean bean = new GoodsBean();
    int goodsId = configShop.getShopId();
    bean.setGoodsId(goodsId);
    bean.setCommonGoods(buildCommonGoods(playerId, goodsId, configShop));

    return bean;
  }

  /**
   * 构建通用商品信息
   * @param playerId 玩家id
   * @param goodsId 商品id
   * @param configShop 商品配置表
   * @return com.game.proto.shop.CommonGoodsBean
   * @date 2024/9/25 15:54
   */
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

  @Override
  public void busShopGoods(long playerId, int goodsId) {
    // TODO
    ConfigShop configShop = shopConfigCache.getConfigShop(goodsId);
    // TODO
    if (configShop == null) {
      return;
    }

    // TODO 购买上限
    ShopResetTypeEnum shopResetTypeEnum = ShopResetTypeEnum.getShopResetTypeEnum(
        configShop.getType());
    PlayerBuyGoodsDO playerBuyGoodsDO = getAndResetPlayerBuyGoodsDO(playerId, goodsId);
    if (shopResetTypeEnum.checkBuy(playerBuyGoodsDO, configShop)) {

    }

    // TODO 消耗校验
    // TODO 购买条件Price: 1. 免费  2. 金币  3. 钻石  4. 人民币  5. 广告
    String price = configShop.getPrice();

    // TODO


    // TODO 消耗

    //
    playerBuyGoodsDO.addCount(1);
    // playerBuyGoodsDOManager.update(playerId, playerBuyGoodsDO);

    // TODO 发货


    // TODO 构建奖励返回
  }

  /**
   * 购买上限
   * @param playerBuyGoodsDO playerBuyGoodsDO
   * @param configShop configShop
   * @return boolean
   * @date 2024/9/26 15:05
   */
  private boolean checkBuyLimit(PlayerBuyGoodsDO playerBuyGoodsDO, ConfigShop configShop) {
    return configShop.getLimitCount() != -1
        && playerBuyGoodsDO.getCount() >= configShop.getLimitCount();
  }

  @Override
  public PlayerBuyGoodsDO getAndResetPlayerBuyGoodsDO(long playerId, int goodsId) {
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

}
