package com.mumu.game.shop.sdata;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ShopConfigCache 商品静态缓存
 *
 * @author liuzhen
 * @version 1.0.0 2024/9/25 13:41
 */
@Slf4j
@Component
public class ShopConfigCache {

  /** 功能id 与 商品类型列表 映射 */
  private Map<Integer, List<Integer>> funcIdGoodsTypesMap;

  /** 商品类型 与 功能id 映射 */
  private Map<Integer, Integer> goodsTypeFuncIdMap;

  /** goodsId 与 商品 映射 */
  private Map<Integer, ConfigShop> goodsIdConfigShopMap;

  /** 商品类型 与 商品列表 映射 */
  private Map<Integer, List<ConfigShop>> goodsTypeConfigShopsMap;

  public void autoLoad() {
    log.info("加载商品表配置...");
  }


  /**
   * 获取商品类型列表
   *
   * @param functionId 功能id
   * @return java.util.List<java.lang.Integer>
   * @date 2024/9/25 15:06
   */
  public List<Integer> getGoodsTypes(int functionId) {
    return funcIdGoodsTypesMap.getOrDefault(functionId, Collections.emptyList());
  }

  /**
   * @param goodsType 商品类型
   * @return int
   * @date 2024/9/25 15:07
   */
  public int getFunctionId(int goodsType) {
    return goodsTypeFuncIdMap.getOrDefault(goodsType, -1);
  }

  /**
   * 获取商品信息
   *
   * @param goodsId 商品id
   * @return com.game.luban.hall.shop.ConfigShop
   * @date 2024/9/25 15:08
   */
  public ConfigShop getConfigShop(int goodsId) {
    return goodsIdConfigShopMap.get(goodsId);
  }

  /**
   * 获取商品信息列表
   *
   * @param goodsType 商品类型
   * @return java.util.List<com.game.luban.hall.shop.ConfigShop>
   * @date 2024/9/25 15:08
   */
  public List<ConfigShop> getConfigShopList(int goodsType) {
    return goodsTypeConfigShopsMap.getOrDefault(goodsType, Collections.emptyList());
  }
}
