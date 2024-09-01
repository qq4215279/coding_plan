package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * CmdBean 命令
 * @author liuzhen
 * @version 1.0.0 2024/8/27 17:38
 */
@ProtobufClass
@Data
public class CmdBean {
  /** 命令 */
  @Protobuf(fieldType = FieldType.ENUM, order = 1, required = true)
  private CommandEnum command;
  /** 命令参数列表 */
  @Protobuf(fieldType = FieldType.STRING, order = 2)
  private List<String> args = new ArrayList<>();
}
