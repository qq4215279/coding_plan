/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.core;

import cn.hutool.core.date.DateUtil;
import com.mumu.design.timer.redis.Utility;
import com.mumu.game.function.entity.PlayerSeasonActivityInfo;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * AbstractSeasonActivity
 * 赛季活动抽象类
 * @author liuzhen
 * @version 1.0.0 2024/10/7 17:05
 */
@Slf4j
public abstract class AbstractSeasonActivity extends AbstractActivity {
  /** 结算状态 */
  private static final short CACL_STATE = 1;

  /** 玩家赛季活动信息 */
  protected PlayerSeasonActivityInfo playerSeasonActivityInfo;

  @Override
  public void initData() {
    // PlayerSeasonActivityModel playerSeasonActivityModel = getWriteModel(playerId, PlayerSeasonActivityModel.class);
    int moduleParentFuncId = getModuleParentFuncId();
    // TODO 
    this.playerSeasonActivityInfo = new PlayerSeasonActivityInfo();

    // 活动开启时，赛季类活动校验
    initSeasonActivityData();
  }

  /**
   * 活动开启时，赛季类活动校验
   * @date 2024/10/7 19:01
   */
  private void initSeasonActivityData() {
    boolean open = isOpen();
    int moduleParentFuncId = getModuleParentFuncId();

    Date date = new Date();
    long now = System.currentTimeMillis();

    // 赛季进行中
    int seasonInfoId = getSeasonInfoId();
    if (seasonInfoId != 0) {
      long saveStartTime = playerSeasonActivityInfo.getStartTime();
      long startTime = getStartTime();
      // 新赛季开启时
      if (saveStartTime != startTime) {
        try {
          // 校验上赛季结算
          if (checkSeasonCacl()) {
            // handleSeasonCacl();
            // this.playerSeasonActivityInfo.setSeasonCaclState(CACL_STATE);
            doSeasonCacl(now);
          }
        } catch (Exception e) {
          e.printStackTrace();
          log.error("moduleParentFunId#{}#playerId#{}#LastSeasonCaclError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
        }


        try {
          // 清除上赛季数据（下赛季开始触发）
          handleSeasonReset();
          playerSeasonActivityInfo.seasonReset();
        } catch (Exception e) {
          e.printStackTrace();
          log.error("moduleParentFunId#{}#playerId#{}#SeasonResetError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
        }

        // 功能开放校验
        try {
          // 初始化新赛季数据
          if (open) {
            playerSeasonActivityInfo.setSeasonInfoId(seasonInfoId);
            playerSeasonActivityInfo.setStartTime(startTime);
            playerSeasonActivityInfo.setEndTime(getEndTime());

            initNewSeasonData();
          }
        } catch (Exception e) {
          e.printStackTrace();
          log.error("moduleParentFunId#{}#playerId#{}#InitNewSeasonDataError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
        }
      }

      try {
        // 每日重置
        if (open && playerSeasonActivityInfo.getLastResetTime() < DateUtil.beginOfDay(date).getTime()) {
          handleDailyReset();
          playerSeasonActivityInfo.dailyReset(now);
        }
      } catch (Exception e) {
        e.printStackTrace();
        log.error("moduleParentFunId#{}#playerId#{}#DailyResetError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
      }

      try {
        // 每周重置（每周一）
        Date weekBegin = DateUtil.beginOfWeek(date);
        if (open && playerSeasonActivityInfo.getLastWeekResetTime() < weekBegin.getTime()) {
          handleWeeklyReset();
          playerSeasonActivityInfo.weekReset(now);
        }
      } catch (Exception e) {
        e.printStackTrace();
        log.error("moduleParentFunId#{}#playerId#{}#WeeklyResetError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
      }

      try {
        // 每月重置（每月1号）
        long monthBegin = cn.hutool.core.date.DateUtil.beginOfMonth(new Date()).getTime();
        if (open && playerSeasonActivityInfo.getLastMonthResetTime() < monthBegin) {
          handleMonthReset();
          playerSeasonActivityInfo.monthReset(now);
        }
      } catch (Exception e) {
        e.printStackTrace();
        log.error("moduleParentFunId#{}#playerId#{}#MonthResetError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
      }

      // 上赛季结束期间 且 未结算过
    } else {
      try {
        if (checkSeasonCacl()) {
          // handleSeasonCacl();
          // this.playerSeasonActivityInfo.setSeasonCaclState(CACL_STATE);

          doSeasonCacl(now);
        }
      } catch (Exception e) {
        e.printStackTrace();
        log.error("moduleParentFunId#{}#playerId#{}#SeasonCaclError#errMsg#{}", moduleParentFuncId, playerId, Utility.getTraceString(e));
      }
    }
  }

  /**
   * do 赛季结算
   * @param now 当前时间
   * @date 2024/10/7 19:01
   */
  private void doSeasonCacl(long now) {
    // 每日结算
    handleDailyReset();
    playerSeasonActivityInfo.dailyReset(now);

    // 每周结算
    handleWeeklyReset();
    playerSeasonActivityInfo.weekReset(now);

    // 每月结算
    handleMonthReset();
    playerSeasonActivityInfo.monthReset(now);

    // 赛季结算
    handleSeasonCacl();
    this.playerSeasonActivityInfo.setSeasonCaclState(CACL_STATE);
  }


  /**
   * 是否进行过赛季结算
   * @date 2024/10/7 19:01
   * @return boolean
   */
  private boolean checkSeasonCacl() {
    return this.playerSeasonActivityInfo.getSeasonInfoId() > 0 && this.playerSeasonActivityInfo.getSeasonCaclState() != CACL_STATE;
  }

  /**
   * 获取模块父功能id
   * @date 2024/10/7 19:01
   * @return int
   */
  protected int getModuleParentFuncId() {
    return ActivityManager.getModuleActivityId(getActivityId());
  }

  /**
   * 获取最新赛季info.id
   * @date 2024/10/7 19:01
   * @return int
   */
  protected abstract int getSeasonInfoId();

  /**
   * 获取最新赛季开始时间
   * @return long
   * @date 2024/10/7 19:01
   */
  protected abstract long getStartTime();

  /**
   * 获取最新赛季结束时间
   * @date 2024/10/7 19:01
   * @return long
   */
  protected abstract long getEndTime();

  /**
   * 初始化新赛季数据
   * @date 2024/10/7 19:01
   */
  protected abstract void initNewSeasonData();

  /**
   * 处理每日重置
   * @date 2024/10/7 19:01
   */
  protected abstract void handleDailyReset();

  /**
   * 处理每周重置
   * @date 2024/10/7 19:01
   */
  protected abstract void handleWeeklyReset();

  /**
   * 处理每月重置
   * @date 2024/10/7 19:01
   */
  protected abstract void handleMonthReset();

  /**
   * 赛季结算（赛季结束触发）
   * @date 2024/10/7 19:01
   */
  protected abstract void handleSeasonCacl();

  /**
   * 赛季重置（下赛季开始触发）
   * @date 2024/10/7 19:01
   */
  protected abstract void handleSeasonReset();


}
