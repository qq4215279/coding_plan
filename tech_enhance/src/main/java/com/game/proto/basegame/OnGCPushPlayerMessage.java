package com.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 推送玩家消息 @Date: 2024/8/21 上午9:54 @Author: xu.hai */
@Data
@ProtobufClass
public class OnGCPushPlayerMessage {

  /** 玩家位置信息(推送者的位置) */
  private int seatId;

  /** 玩家信息- 别人的数据 */
  private GamePlayerBean player;

  /** 玩家桌子事件 */
  private PlayerTableAction action;

  /** 玩家桌子事件 */
  public enum PlayerTableAction {
    /** 进入游戏 */
    ENTER,
    /** 退出游戏 */
    EXIT;
  }
}
