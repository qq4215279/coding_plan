package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.mumu.game.proto.baloot.BalootTableBean;
import com.mumu.game.proto.baloot.CmdBean;
import lombok.Data;

/**
 * OnGCPushGameMessage 推送玩家游戏中变动消息
 * @author liuzhen
 * @version 1.0.0 2024/8/26 17:45
 */
@ProtobufClass
@Data
public class OnGCPushGameMessage {

  /** 上一个执行操作玩家id */
  private Long lastOpPlayerId;
  /** 上一个执行操作命令 */
  private CmdBean cmdBean;

  /** baloot桌子消息 */
  private BalootTableBean balootInfo;
}
