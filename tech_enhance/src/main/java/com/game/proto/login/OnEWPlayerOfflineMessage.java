package com.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 玩家退出通知 @Date: 2024/8/15 下午5:49 @Author: xu.hai */
@Data
@ProtobufClass
public class OnEWPlayerOfflineMessage {

  /** 退出原因 */
  private ExitReason reason;

  /** 退出原因定义 */
  public enum ExitReason {
    /** 正常退出 */
    NORMAL_EXIT,
    /** 连接关闭退出 */
    SESSION_CLOSE_EXIT,
    /** 玩家被强制下线 */
    KICK_EXIT,
    /** 服务器关闭退出 */
    STOP_EXIT;
  }
}
