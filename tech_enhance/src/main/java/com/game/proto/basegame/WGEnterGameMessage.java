package com.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.game.proto.model.PackEntitiesBean;
import lombok.Data;

/** 进入游戏消息 @Date: 2024/8/19 上午11:57 @Author: xu.hai */
@Data
@ProtobufClass
public class WGEnterGameMessage {
  /** 游戏ID */
  private int gameId;

  /** 场次ID */
  private int roomId;

  /** 带入到游戏的缓存模型数据 */
  private PackEntitiesBean packEntities;
}
