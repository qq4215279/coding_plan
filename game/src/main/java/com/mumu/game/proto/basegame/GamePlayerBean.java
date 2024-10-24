package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.mumu.game.proto.baloot.PokerPlayerBean;
import com.mumu.game.proto.login.PlayerBaseInfoBean;
import lombok.Data;

/** 客户端游戏内玩家信息对象 @Date: 2024/8/21 下午12:10 @Author: xu.hai */
@Data
@ProtobufClass
public class GamePlayerBean {

  /** 玩家基础信息 */
  private PlayerBaseInfoBean baseInfo;

  /** 玩家位置信息 */
  private PlayerSeatBean seatInfo;

  /** 桌子玩家信息 */
  private PokerPlayerBean pokerPlayerBean;
}
