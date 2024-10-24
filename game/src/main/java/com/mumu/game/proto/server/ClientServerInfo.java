package com.mumu.game.proto.server;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 服务器信息 - 用于数据传递 @Date: 2024/7/23 下午6:40 @Author: xu.hai */
@Data
@ProtobufClass
public class ClientServerInfo {

  /** 服务器名称 */
  @Protobuf(fieldType = FieldType.STRING, order = 1, required = true)
  private String serverName;

  /** 服务器编号 */
  @Protobuf(fieldType = FieldType.FIXED32, order = 2, required = true)
  private int serverId;

  /** 服务器组类型 */
  @Protobuf(fieldType = FieldType.STRING, order = 3, required = true)
  private String group;

  /** 服务端是否开启 */
  @Protobuf(fieldType = FieldType.BOOL, order = 4, required = true)
  private boolean serverEnable;

  /** 服务端协议 */
  @Protobuf(fieldType = FieldType.STRING, order = 5)
  private String protocol;

  /** 服务器ip */
  @Protobuf(fieldType = FieldType.STRING, order = 6)
  private String ip;

  /** 服务器端口 */
  @Protobuf(fieldType = FieldType.FIXED32, order = 7)
  private Integer port;

  /** 是否是主服务 */
  @Protobuf(fieldType = FieldType.BOOL, order = 8)
  private boolean master;
}
