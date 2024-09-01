package com.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 玩家退出返回消息 @Date: 2024/8/15 下午6:33 @Author: xu.hai */
@Data
@ProtobufClass
public class ResLogoutMessage {
  /** 测试字段 */
  private String msg;
}
