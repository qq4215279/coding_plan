package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

@ProtobufClass
@Data
public class PokerBean {

  /**
   * 剩余手牌 repeated int32 restPokers=1
   */
  @Protobuf(fieldType = FieldType.INT32, order = 1)
  public java.util.List<Integer> restPokers;

  /**
   * 剩余手牌数量 int32 restPokers=2
   */
  @Protobuf(fieldType = FieldType.INT32, order = 2)
  public Integer restCount;

  /**
   * 获得的分数手牌 repeated int32 scorePokers=3
   */
  @Protobuf(fieldType = FieldType.INT32, order = 3)
  public java.util.List<Integer> scorePokers;

  /**
   * 原始手牌 repeated int32 allPokers=4
   */
  @Protobuf(fieldType = FieldType.INT32, order = 4)
  public java.util.List<Integer> allPokers;

}
