package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;

/**
 * CommandEnum 游戏操作命令集
 * @author liuzhen
 * @version 1.0.0 2024/8/18 16:48
 */
@ProtobufClass
public enum CommandEnum {
  /** 准备开始游戏 */
  READY,
  /** pass */
  PASS,
  /** wala */
  WALA,
  /** sun */
  SUN,
  /** hokum */
  HOKUM,
  /** 2nd hokum */
  SECEND_HOKUM,
  /** ashkal */
  ASHKAL,
  /** 加倍 - double */
  DOUBLE,
  /** 加倍 - three */
  THREE,
  /** 加倍 - four */
  FOUR,
  /** 加倍 - Gahwa */
  GAHWA,
  /** 主花色 */
  MAIN_COLOR,
  /** 行牌 */
  PLAY,
  /** 发牌 **/
  SEND_POKER,
  /** 发展示牌 **/
  SEND_DISPLAY_POKER,
  /** 玩家墩结算 **/
  SETTLE_POKER,
  ;

  /**
   * 构造方法
   */
  CommandEnum() {
  }

}
