package com.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 玩家信息 @Date: 2024/7/31 上午11:06 @Author: xu.hai */
@Data
@ProtobufClass
public class PlayerBaseInfoBean {
  /** 玩家唯一ID */
  private long id;

  /** 用户昵称 */
  private String nick;

  /** 头像 */
  private String head;

  /** 头像框 */
  private int headFrame;
}
