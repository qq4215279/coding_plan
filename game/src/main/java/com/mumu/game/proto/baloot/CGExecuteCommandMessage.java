/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * CGExecuteCommandMessage
 * 请求执行游戏命令
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class CGExecuteCommandMessage {
  // 游戏命令
  private CommandEnum command;
  // 游戏命令参数
  private java.util.List<String> args = new java.util.ArrayList<>();
}