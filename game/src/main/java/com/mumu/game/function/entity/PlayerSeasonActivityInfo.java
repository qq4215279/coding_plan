/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PlayerSeasonActivityInfo
 * @author liuzhen
 * @version 1.0.0 2024/10/7 18:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSeasonActivityInfo {
  /** 赛季信息id */
  private int seasonInfoId;
  /** 赛季开启时间 */
  private long startTime;
  /** 赛季结束时间 */
  private long endTime;
  /** 上次每日重置时间 */
  private long lastResetTime;
  /** 上次每周重置时间 */
  private long lastWeekResetTime;
  /** 上次每月重置时间 */
  private long lastMonthResetTime;
  /** 是否赛季结算过 0：否；1已结算 */
  private long seasonCaclState;

  /**
   * 每日重置
   * @date 2023/6/20 15:14
   * @param resetTime 重置时间
   * @return void
   */
  public void dailyReset(long resetTime) {
    this.lastResetTime = resetTime;
  }

  /**
   * 每周重置
   * @date 2023/7/4 11:54
   * @param resetTime 重置时间
   * @return void
   */
  public void weekReset(long resetTime) {
    this.lastWeekResetTime = resetTime;
  }

  /**
   * 每月重置
   * @date 2023/7/24 14:54
   * @param resetTime 重置时间
   * @return void
   */
  public void monthReset(long resetTime) {
    this.lastMonthResetTime = resetTime;
  }

  /**
   * 重置赛季数据
   * @date 2023/6/16 18:20
   * @return void
   */
  public void seasonReset() {
    this.seasonInfoId = 0;
    this.startTime = 0L;
    this.endTime = 0L;
    this.lastResetTime = 0;
    this.lastWeekResetTime = 0;
    this.lastMonthResetTime = 0;
    this.seasonCaclState = 0;
  }
}
