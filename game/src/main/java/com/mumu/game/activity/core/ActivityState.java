/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ActivityState
 * 活动状态
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:24
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityState {
  /** 活动id */
  private int id;
  /** 是否开启 */
  private boolean isOpen;
  /** 是否有小红点 */
  private boolean hasRedPoint;
  /** 是否拥有商城 */
  private boolean isOpenShop;
  /** 是否在活动期间内 */
  private boolean isInActivity;
  /** TODO 是否为赛季类活动 */
  private boolean isSeaSonActivity;
  /** TODO 赛季开始时间，无默认-1 */
  private long startTime;
  /** TODO 赛季结束时间，无默认-1 */
  private long endTime;

}
