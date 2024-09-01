package com.game.proto.server;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/** 服务器信息列表消息 @Date: 2024/7/24 上午10:01 @Author: xu.hai */
@Data
@ProtobufClass
public class OnServerInfosMessage {

  /** 服务器列表 */
  @Protobuf(fieldType = FieldType.OBJECT, order = 1, required = true)
  private List<ClientServerInfo> servers = new ArrayList<>();
}
