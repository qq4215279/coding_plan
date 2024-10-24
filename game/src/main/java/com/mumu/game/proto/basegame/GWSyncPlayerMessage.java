package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.mumu.game.proto.model.PackEntitiesBean;
import lombok.Data;

/** 游戏服同步玩家数据给大厅 @Date: 2024/8/19 上午11:57 @Author: xu.hai */
@Data
@ProtobufClass
public class GWSyncPlayerMessage {
  /** 是否退出游戏时触发的同步 */
  private boolean exit;

  /** 玩家位置信息 */
  private PlayerSeatBean playerSeat;

  /** 同步的模型数据 */
  private PackEntitiesBean packEntities;
}
