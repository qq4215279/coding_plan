/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * TeamBean
 * 队伍信息
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class TeamBean {
  // 队伍id
  private Integer teamId;
  // 队伍名称
  private String name;
  // 当前局队伍总分
  private Integer totalScore;
  // 当前盘队伍总分
  private Integer score;
  // 玩家列表
  private java.util.List<Long> playerIds = new java.util.ArrayList<>();
  /** 面板信息 */
  private PanelBean panelBean;
}