/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.bean;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/**
 * CommonGoodsBean
 * 商品基本信息
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class CommonGoodsBean {
  /** 商品名称 */
  private String name;
  /** 商品价格 */
  private ItemBean price;
  /** 商品原价 */
  private ItemBean originPrice;
  /** 商品获得 */
  private java.util.List<ItemBean> rewards = new java.util.ArrayList<>();
  /** 商品原获得 */
  private java.util.List<ItemBean> originRewards = new java.util.ArrayList<>();
  /** 限购类型: 0:无限/赛季限购; 1: 每日限购; 2: 每周限购; 3: 每月限购; 4: 永久限购 */
  private Integer limitType;
  /** 限购数量: 限购次数, -1表示无限购。 */
  private Integer limitCount;
  /** 本次限购的购买次数 */
  private Integer alreadyBuyTimes;
  /** 商品排序 */
  private Integer sort;
  /** 商品图标 */
  private String icon;
  /** param */
  private String param;
  /** 开始购买时间 */
  private Long startBuyTime;
  /** 截止购买时间 */
  private Long endBuyTime;
}