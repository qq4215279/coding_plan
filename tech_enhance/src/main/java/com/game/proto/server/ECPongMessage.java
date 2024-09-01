package com.game.proto.server;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 响应消息 @Date: 2024/8/15 下午3:51 @Author: xu.hai */
@Data
@ProtobufClass
public class ECPongMessage {

  /** 系统时间 */
  private long systemTime;
}
