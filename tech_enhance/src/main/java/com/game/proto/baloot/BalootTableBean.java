/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.game.proto.baloot;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;


/**
 * BalootTableBean
 * 响应游戏桌子信息
 * @author Auto-generated
 * @version 1.0.0 2024/9/9 10:00
 */
@ProtobufClass
@Data
public class BalootTableBean {
  // 状态: 1: 等待; 2: 发牌; 3: 叫牌; 4: 行牌; 5: 行牌结算; 6: 最终结算;
  private Integer state;
  // 游戏指令: 0: 初始化; 1: 玩家准备; 2: 开始游戏，首次发牌; 3: 2次发牌; 4: 玩家叫牌; 5: 发底牌; 6: 发展示牌给指定玩家; 7: 游戏流局; 8: 玩家行牌; 9: 行牌结算; 10: 最终结算;
  private Integer action;
  // 游戏模式: 0: 未确定; 1: hokum; 2: 2rd hokum; 3: sun; 4: ashkal
  private Integer mode;
  // 游戏倍数: 1: 默认1倍; 2: 2倍; 3: 3倍; 4: 4倍; 5: 5倍(gahwa);
  private Integer multi;
  // 盘数
  private Integer circle;
  // 墩数
  private Integer round;
  // 发牌员
  private Integer dealerSeatId;
  // 主宰者
  private Integer masterSeatId;
  // 加倍者
  private Integer multiSeatId;
  // 展示牌
  private Integer displayPokerId;
  // 展示牌拥有玩家
  private Integer displaySeatId;
  // 当前叫牌玩家
  private Integer bidSeatId;
  // 玩家命令集
  private java.util.List<CmdBean> cmdBeans = new java.util.ArrayList<>();
  // 操作倒计时
  private Long countdown;
  // 队伍信息列表
  private java.util.List<TeamBean> teanbeans = new java.util.ArrayList<>();
  // 游戏主花色
  private Integer mainColorType;
}