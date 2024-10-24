package com.mumu.game.proto.login;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/** 登陆请求 @Date: 2024/7/31 上午10:27 @Author: xu.hai */
@Data
@ProtobufClass
public class ReqLoginMessage {
  /** 登陆 token */
  private String token;
}
