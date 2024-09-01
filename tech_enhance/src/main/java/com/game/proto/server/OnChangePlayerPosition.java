package com.game.proto.server;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.game.proto.basegame.PlayerSeatBean;
import lombok.Data;

/** 玩家位置信息变动操作 @Date: 2024/8/14 上午11:09 @Author: xu.hai */
@Data
@ProtobufClass
public class OnChangePlayerPosition {
  /** 玩家id */
  private long playerId;

  /** 服务类型 */
  private String serverGroup;

  /** 服务id */
  private int serverId;

  /** 玩家游戏位置信息 */
  private PlayerSeatBean gameSeat;

  /** 位置变动操作 */
  private OptType optType;

  /** 操作（加入或移除） */
  public enum OptType {
    /** 加入 */
    ADD,
    /** 移除 */
    DEL
  }
}
