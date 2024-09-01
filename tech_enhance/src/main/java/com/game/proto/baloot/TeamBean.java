package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

@ProtobufClass
@Data
public class TeamBean {

  /**
   * 队伍id optional int32 teamId=1
   */
  @Protobuf(fieldType = FieldType.INT32, order = 1, required = false)
  public Integer teamId;

  /**
   * 队伍名称 optional int32 name=2
   */
  @Protobuf(fieldType = FieldType.STRING, order = 2, required = false)
  public String name;

  /**
   * 当前局队伍总分 optional int32 totalsScore=3
   */
  @Protobuf(fieldType = FieldType.INT32, order = 3, required = false)
  public Integer totalsScore;

  /**
   * 当前盘队伍总分 optional int32 score=4
   */
  @Protobuf(fieldType = FieldType.INT32, order = 4, required = false)
  public Integer score;

  /**
   * 玩家列表 repeated int64 playerIds=5
   */
  @Protobuf(fieldType = FieldType.INT64, order = 5)
  public java.util.List<Long> playerIds;

}
