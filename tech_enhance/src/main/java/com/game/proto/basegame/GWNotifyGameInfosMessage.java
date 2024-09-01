package com.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

/** 推送游戏信息 @Date: 2024/8/26 上午10:58 @Author: xu.hai */
@Data
@ProtobufClass
public class GWNotifyGameInfosMessage {
  /** 游戏服ID */
  private int serverId;

  /** 游戏信息 */
  private List<GameInfoBean> games = Lists.newArrayList();
}
