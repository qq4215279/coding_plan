package com.mumu.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 玩家账号信息 @Date: 2024/7/31 上午11:06 @Author: xu.hai */
@Data
@ProtobufClass
public class AccountBean {
  /** 玩家唯一ID */
  private long id;

  /** 用户账号名 */
  private String name;

  /** 注册的渠道号 */
  private String channel;

  /** 注册的设备号 */
  private String deviceId;
}
