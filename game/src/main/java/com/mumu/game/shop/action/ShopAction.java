package com.mumu.game.shop.action;

import com.mumu.game.shop.service.ShopService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * ShopAction
 * 商城action
 * @author liuzhen
 * @version 1.0.0 2024/9/25 15:18
 */
@Slf4j
public class ShopAction {

  @Resource
  private ShopService shopService;

  /**
   * 请求商品信息列表
   * @param playerId playerId
   * @param functionId functionId
   * @date 2024/9/25 15:25
   */
  public void getShopGoodsInfoList(long playerId, int functionId) {
    shopService.getShopGoodsInfoList(playerId, functionId);
  }

  /**
   * 请求购买商品
   * @param playerId playerId
   * @param goodsId goodsId
   * @date 2024/9/25 15:25
   */
  public void busShopGoods(long playerId, int goodsId) {
    shopService.busShopGoods(playerId, goodsId);
  }

}
