package com.mumu.game.proto.model;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/** 实体缓存数据包集合，用于在服务间传递缓存数据 @Date: 2024/8/8 下午3:11 @Author: xu.hai */
@Data
@ProtobufClass
public class PackEntitiesBean {
  /** 实体数据 */
  private List<PackEntityBean> entities = new ArrayList<>();
}
