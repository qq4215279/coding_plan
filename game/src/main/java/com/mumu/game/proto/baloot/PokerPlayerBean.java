/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * PokerPlayerBean
 * 桌子玩家信息
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class PokerPlayerBean {
  // 是否准备开始游戏
  private Boolean isReady;
  // 游戏玩家信息
  private PokerBean pokerBean;
}