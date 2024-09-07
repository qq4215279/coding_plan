/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.game.proto;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/** 消息代理 - 所有的消息都会通过这个类进行传输 @Date: 2024/7/22 下午5:22 @Author: xu.hai */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ProtobufClass
public class MessageProxy {

  /** 请求协议 cmd */
  @Protobuf(fieldType = FieldType.FIXED32, order = 1, required = true)
  private int cmd;

  @Protobuf(fieldType = FieldType.FIXED32, order = 2, required = true)
  private int seq;

  /** 错误 code */
  @Protobuf(fieldType = FieldType.FIXED32, order = 3, required = true)
  private int errorCode;

  /** 具体的业务消息通过protoBuf序列化后的二进制数据 */
  @Protobuf(fieldType = FieldType.BYTES, order = 4)
  private byte[] data;

  /** 玩家id（内部使用） */
  @Protobuf(fieldType = FieldType.FIXED64, order = 5)
  private Long playerId;

  /** 消息关联 session（内部使用） */
  // private transient IoSession session;

  /** 任务回调（内部使用） */
  private transient Runnable callBack;

  /** 获取消息 */
  // public <T> T getMsg(Class<T> clazz) {
  //   return JProtoBufUtil.decode(data, clazz);
  // }

  /** 设置消息 */
  // public MessageProxy setMsg(Object data) {
  //   this.data = JProtoBufUtil.encode(data);
  //   return this;
  // }

  /** 获取一个新的 proxy */
  public MessageProxy copy() {
    return MessageProxy.builder()
        .cmd(cmd)
        .seq(seq)
        .errorCode(errorCode)
        .data(data)
        .playerId(playerId)
        .build();
  }
}
