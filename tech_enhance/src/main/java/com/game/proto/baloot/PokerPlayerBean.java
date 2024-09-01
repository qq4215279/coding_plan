package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/**
 * PokerPlayerBean 桌子玩家基本信息
 * @author liuzhen
 * @version 1.0.0 2024/8/26 13:52
 */
@Data
@ProtobufClass
public class PokerPlayerBean {

  /** 是否准备开始游戏 */
  private Boolean isReady;
  /** 游戏玩家信息 */
  private PokerBean pokerBean;
}
