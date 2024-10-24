/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * CmdBean
 * 命令
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class CmdBean {
  // 命令
  private CommandEnum command;
  // 命令参数列表
  private java.util.List<String> args = new java.util.ArrayList<>();
}