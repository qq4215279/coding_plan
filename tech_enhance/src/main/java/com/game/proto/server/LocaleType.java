package com.game.proto.server;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

/** 国际化语言类型 @Date: 2024/8/30 下午5:02 @Author: xu.hai */
@ProtobufClass
public enum LocaleType {
  /** 中文 */
  zh,
  /** 英文 */
  en,
  /** 阿拉伯文 */
  ar;
}
