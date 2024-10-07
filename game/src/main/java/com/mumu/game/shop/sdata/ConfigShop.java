/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.shop.sdata;

import lombok.Data;

/**
 * ConfigShop
 * @author liuzhen
 * @version 1.0.0 2024/10/7 17:07
 */
@Data
public class ConfigShop {
  /** 商品id */
  private int shopId;

  /** 商品类型 */
  private int type;

  /** 商品名称 */
  private String name;

  /** 价格 */
  private String price;

  /** 原价 */
  private String originPrice;

  /** 奖励 */
  private String reward;

  /** 原奖励 */
  private String originReward;

  /** 限购类型 */
  private String limitType;

  /** 限购数量 */
  private int limitCount;

  /** 商品排序 */
  private int sort;

  /** 商品图标 */
  private String icon;

  /** 额外参数 */
  private String param;

  /** 开始购买时间 */
  private long startBuyTime;

  /** 截止购买时间 */
  private long endBuyTime;
}
