package com.mumu.game.shop.service;

import com.mumu.game.shop.domain.PlayerBuyGoodsDO;

/**
 * ShopService
 * 商城service
 * @author liuzhen
 * @version 1.0.0 2024/9/25 15:18
 */
public interface ShopService {

  /**
   * 请求商品信息列表
   * @param playerId 玩家id
   * @param functionId 功能id
   * @date 2024/9/25 15:28
   */
  void getShopGoodsInfoList(long playerId, int functionId);

  /**
   * 请求购买商品
   * @param playerId 玩家id
   * @param goodsId 商品id
   * @date 2024/9/25 15:28
   */
  void busShopGoods(long playerId, int goodsId);

  /**
   * 获取并重置玩家商品购买信息
   * @param playerId 玩家id 
   * @param goodsId 商品id
   * @return com.game.shop.domain.PlayerBuyGoodsDO
   * @date 2024/9/26 14:59
   */
  PlayerBuyGoodsDO getAndResetPlayerBuyGoodsDO(long playerId, int goodsId);
}
