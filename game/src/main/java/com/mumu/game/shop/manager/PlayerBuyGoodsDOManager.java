package com.mumu.game.shop.manager;

import com.mumu.game.shop.domain.PlayerBuyGoodsDO;
import org.springframework.stereotype.Component;

/**
 * PlayerBuyGoodsDOManager
 * @author liuzhen
 * @version 1.0.0 2024/9/26 13:53
 */
@Component
public class PlayerBuyGoodsDOManager {

  /**
   * 获取玩家购买信息
   * @param playerId 玩家id
   * @param goodsId 商品id
   * @return com.game.shop.domain.PlayerBuyGoodsDO
   * @date 2024/9/26 14:29
   */
  public PlayerBuyGoodsDO getPlayerBuyGoodsDO(long playerId, int goodsId) {
    PlayerBuyGoodsDO playerBuyGoodsDO = null;
    if (playerBuyGoodsDO == null) {
      playerBuyGoodsDO = createPlayerBuyGoodsDO(playerId, goodsId);
    }

    return playerBuyGoodsDO;
  }

  /**
   * 创建PlayerBuyGoodsDO
   * @param playerId 玩家id
   * @param goodsId 商品id
   * @return com.game.shop.domain.PlayerBuyGoodsDO
   * @date 2024/9/26 14:02
   */
  private PlayerBuyGoodsDO createPlayerBuyGoodsDO(long playerId, int goodsId) {
    PlayerBuyGoodsDO playerBuyGoodsDO = new PlayerBuyGoodsDO();
    playerBuyGoodsDO.setPlayerId(playerId);
    playerBuyGoodsDO.setGoodsId(goodsId);
    playerBuyGoodsDO.setCount(0);
    playerBuyGoodsDO.setLastResetTime(System.currentTimeMillis());

    // insertOrUpdate(playerId, playerBuyGoodsDO);
    return playerBuyGoodsDO;
  }
}
