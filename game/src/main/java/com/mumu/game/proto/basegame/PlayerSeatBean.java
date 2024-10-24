package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 玩家位置信息 @Date: 2024/8/21 下午12:10 @Author: xu.hai */
@Data
@ProtobufClass
public class PlayerSeatBean {

  /** 玩家ID */
  private long playerId;

  /** 游戏ID */
  private int gameId;

  /** 场次ID */
  private int roomId;

  /** 桌子ID */
  private int tableId;

  /** 座位ID */
  private int seatId;
}
