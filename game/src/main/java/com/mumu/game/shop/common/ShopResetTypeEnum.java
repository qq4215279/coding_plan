package com.mumu.game.shop.common;

import cn.hutool.core.date.DateUtil;
import com.mumu.game.shop.sdata.ConfigShop;
import com.mumu.game.shop.domain.PlayerBuyGoodsDO;
import com.mumu.game.shop.sdata.ShopConfigCache;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * ShopResetTypeEnum
 * 商品重置枚举
 * @author liuzhen
 * @version 1.0.0 2024/9/26 14:04
 */
public enum ShopResetTypeEnum {
  /** 无限 */
  INFINITE(0),
  /** 每天 */
  DAILY(1) {
    @Override
    public boolean checkReset(PlayerBuyGoodsDO playerBuyGoodsDO, ShopConfigCache shopConfigCache) {
      return DateUtil.isSameDay(new Date(), new Date(playerBuyGoodsDO.getLastResetTime()));
    }
  },
  /** 每周 */
  WEEKLY(2) {
    @Override
    public boolean checkReset(PlayerBuyGoodsDO playerBuyGoodsDO, ShopConfigCache shopConfigCache) {
      return DateUtil.isSameWeek(new Date(), new Date(playerBuyGoodsDO.getLastResetTime()), true);
    }
  },
  /** 每月 */
  MONTHLY(3) {
    @Override
    public boolean checkReset(PlayerBuyGoodsDO playerBuyGoodsDO, ShopConfigCache shopConfigCache) {
      return DateUtil.isSameMonth(new Date(), new Date(playerBuyGoodsDO.getLastResetTime()));
    }
  },
  /** 终身 */
  LIFETIME(4),
  /** 赛季限购 */
  SEASON(5) {
    @Override
    public boolean checkBuy(PlayerBuyGoodsDO playerBuyGoodsDO, ConfigShop configShop) {
      if (!super.checkBuy(playerBuyGoodsDO, configShop)) {
        return false;
      }

      // TODO 是否在赛季时间范围内
      int goodsId = playerBuyGoodsDO.getGoodsId();
      // int functionId = shopConfigCache.getFunctionId(configShop.getType());

      return false;
    }
  },

  ;

  /** 重置类型 */
  private final int reseType;

  ShopResetTypeEnum(int reseType) {
    this.reseType = reseType;
  }

  private static final Map<Integer, ShopResetTypeEnum> TYPE_ENUM_MAP = new HashMap<>(4);
  static {
    for (ShopResetTypeEnum shopResetTypeEnum : values()) {
      TYPE_ENUM_MAP.put(shopResetTypeEnum.reseType, shopResetTypeEnum);
    }
  }

  /**
   * getShopResetTypeEnum
   * @param resetType 商品重置类型
   * @return com.game.shop.common.ShopResetTypeEnum
   * @date 2024/9/26 15:01
   */
  public static ShopResetTypeEnum getShopResetTypeEnum(int resetType) {
    return TYPE_ENUM_MAP.get(resetType);
  }

  /**
   * 是否需要重置购买次数
   * @param playerBuyGoodsDO playerBuyGoodsDO
   * @return boolean
   * @date 2024/9/26 14:58
   */
  public boolean checkReset(PlayerBuyGoodsDO playerBuyGoodsDO, ShopConfigCache shopConfigCache) {
    return false;
  }

  /**
   * 校验能否购买
   * @param playerBuyGoodsDO playerBuyGoodsDO
   * @param configShop configShop
   * @return boolean
   * @date 2024/9/26 15:39
   */
  public boolean checkBuy(PlayerBuyGoodsDO playerBuyGoodsDO, ConfigShop configShop) {
    return checkBuyLimit(playerBuyGoodsDO, configShop);
  }

  /**
   * 校验购买上限
   * @param playerBuyGoodsDO playerBuyGoodsDO
   * @param configShop configShop
   * @return boolean
   * @date 2024/9/26 15:39
   */
  private boolean checkBuyLimit(PlayerBuyGoodsDO playerBuyGoodsDO, ConfigShop configShop) {
    return configShop.getLimitCount() != -1
        && playerBuyGoodsDO.getCount() >= configShop.getLimitCount();
  }

}
