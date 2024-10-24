package com.mumu.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 客户端请求进入游戏 @Date: 2024/8/19 上午11:57 @Author: xu.hai */
@Data
@ProtobufClass
public class CWEnterGameMessage {
  private int gameId;
  private int roomId;
}
