package com.game.proto.model;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 实体缓存的数据包，对应 IDataEntity 的数据 @Date: 2024/8/8 下午4:09 @Author: xu.hai */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ProtobufClass
public class PackEntityBean {
  /** 操作类型 */
  private String modifyType;

  /** 实体数据类型 */
  private String entityType;

  /** 实体数据 */
  private byte[] data;
}
