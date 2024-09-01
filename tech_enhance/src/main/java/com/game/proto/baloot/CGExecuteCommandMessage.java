package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.List;
import lombok.Data;

/**
 * CGExecuteCommandMessage 请求发送游戏命令
 * @author liuzhen
 * @version 1.0.0 2024/8/26 14:28
 */
@ProtobufClass
@Data
public class CGExecuteCommandMessage {

  /** 命令 */
  private CommandEnum command;
  /** 命令参数 */
  private List<String> args;
}
