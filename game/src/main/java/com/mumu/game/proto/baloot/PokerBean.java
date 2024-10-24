/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * PokerBean
 * 玩家游戏牌
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class PokerBean {
  // 剩余手牌
  private java.util.List<Integer> restPokers = new java.util.ArrayList<>();
  // 剩余手牌数量
  private Integer restCount;
  // 获得的分数手牌
  private java.util.List<Integer> scorePokers = new java.util.ArrayList<>();
  // 原始手牌
  private java.util.List<Integer> allPokers = new java.util.ArrayList<>();
}