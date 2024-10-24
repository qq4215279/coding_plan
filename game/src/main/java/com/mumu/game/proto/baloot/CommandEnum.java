/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;


/**
 * CommandEnum
 * 命令枚举
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
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
  /** 主花色 参数为选择的扑克牌类型 */
  MAIN_COLOR,
  /** 行牌 参数为扑克牌Id */
  PLAY,
  /** 发牌 **/
  SEND_POKER,
  /** 发展示牌给玩家 **/
  SEND_DISPLAY_POKER,
  /** 玩家墩结算 **/
  SETTLE_POKER,
}