package com.mumu.game.shop.domain;

import lombok.Data;

/**
 * PlayerBuyGoodsDO
 * 玩家购买商品信息表
 * @author liuzhen
 * @version 1.0.0 2024/9/26 13:44
 */
@Data
public class PlayerBuyGoodsDO {

  /** 玩家id */
  private long playerId;
  /** 商品id */
  private int goodsId;
  /** 购买次数 */
  private int count;
  /** 上一次重置时间 */
  private long lastResetTime;

  /**
   * 增加购买数量
   * @param add add
   * @date 2024/9/26 13:52
   */
  public void addCount(long add) {
    this.count += add;
  }

  /**
   * 重置购买次数
   * @date 2024/9/26 13:53
   */
  public void reset() {
    this.count = 0;
    this.lastResetTime = System.currentTimeMillis();
  }
}
