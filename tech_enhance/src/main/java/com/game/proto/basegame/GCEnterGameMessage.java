package com.game.proto.basegame;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 进入游戏返回消息 @Date: 2024/8/19 上午11:57 @Author: xu.hai */
@Data
@ProtobufClass
public class GCEnterGameMessage {
  private PlayerSeatBean seat;
}
