/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * CDUtil
 * 用于CD的计算
 * 提供一些计算CD的实用方法
 * @author liuzhen
 * @version 1.0.0 2023/7/7 11:23
 */
public class CDUtil {
    /**
     * 获得剩余的毫秒数
     * @param totalCDTime CD毫秒数
     * @param lastCDTime CD起始时刻
     * @param nowDate 当前时刻
     * @return 剩余CD时间
     */
    public static long getCD(long totalCDTime, Date lastCDTime, Date nowDate) {
        long cd = totalCDTime - (lastCDTime == null ? totalCDTime : (nowDate.getTime() - lastCDTime.getTime()));
        if (cd < 0) {
            cd = 0l;
        }
        return cd;
    }

    /**
     * 获得剩余的毫秒数
     * @param totalCDTime CD毫秒数
     * @param lastCDTime 上次CD时间戳
     * @param nowDate 当前时间
     * @return 剩余CD时间
     */
    public static long getCD(long totalCDTime, Long lastCDTime, Date nowDate) {
        // 征战CD
        long cd = totalCDTime - (lastCDTime == null ? totalCDTime : (nowDate.getTime() - lastCDTime));
        if (cd < 0) {
            cd = 0L;
        }

        return cd;
    }

    /**
     * 是否在CD中
     * @param totalCDTime CD毫秒数
     * @param lastCDTime 上次CD时间
     * @param nowDate 当前时间
     * @return CD中 true 不再CD中 false
     */
    public static boolean isInCD(long totalCDTime, Date lastCDTime, Date nowDate) {
        long cd = getCD(totalCDTime, lastCDTime, nowDate);

        if (cd > 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否在CD中
     * @param totalCDTime CD毫秒数
     * @param lastCDTime 上次CD时间
     * @param nowDate 当前时间
     * @return CD中 true 不再CD中 false
     */
    public static boolean isInCD(long totalCDTime, Long lastCDTime, Date nowDate) {
        long cd = getCD(totalCDTime, lastCDTime, nowDate);
        if (cd > 0) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否在CD中
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @return
     */
    public static boolean isInCD(Date endTime, Date nowDate) {
        boolean result = true;
        if (endTime == null) {
            result = false;
        } else {
            result = nowDate.before(endTime);
        }
        return result;
    }

    /**
     * 判断是否在CD中
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @param lastTime 持续事件
     * @param cdFlag 当前CDFlag
     * @param eventFlag 当前CD事件Flag
     *1 @return
     */
    public static boolean isInCD(Date endTime, Date nowDate, long lastTime, int cdFlag, int eventFlag) {
        boolean result = true;
        if (endTime == null) {
            // 没有CD
            result = false;
        } else {
            if (!nowDate.before(endTime)) {
                // CD已消除
                result = false;
            } else {
                if ((cdFlag & eventFlag) == eventFlag) {
                    // 已经累计过了持续时间
                    result = true;
                } else {
                    result = (endTime.getTime() - nowDate.getTime() - lastTime) > 0;
                }
            }
        }
        return result;
    }

    /**
     * 判断是否在CD中
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @param lastTime 持续事件
     * @param cdFlag 当前CDFlag
     * @param eventFlag 当前CD事件Flag
     * @return
     */
    public static boolean isInCD(Long endTime, Date nowDate, long lastTime, int cdFlag, int eventFlag) {
        boolean result = true;
        if (endTime == null) {
            // 没有CD
            result = false;
        } else {
            if (nowDate.getTime() > endTime) {
                // CD已消除
                result = false;
            } else {
                if ((cdFlag & eventFlag) == eventFlag) {
                    // 已经累计过了持续时间
                    result = true;
                } else {
                    result = (endTime - nowDate.getTime() - lastTime) > 0;
                }
            }
        }
        return result;
    }

    /**
     * 判断是否在CD中
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @return
     */
    public static boolean isInCD(Long endTime, Date nowDate) {
        boolean result = true;
        if (endTime == null) {
            result = false;
        } else {
            result = nowDate.getTime() < endTime;
        }
        return result;
    }

    /**
     * 计算CD剩余时间
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @return
     */
    public static long getCD(Date endTime, Date nowDate) {
        long cd = 0;
        if (endTime == null) {
            cd = 0;
        } else {
            cd = endTime.getTime() - nowDate.getTime();
            if (cd < 0) {
                cd = 0;
            }
        }

        return cd;
    }

    /**
     * 计算CD剩余时间
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @return
     */
    public static long getCD(Long endTime, Date nowDate) {
        long cd = 0;
        if (endTime == null) {
            cd = 0;
        } else {
            cd = endTime - nowDate.getTime();
            if (cd < 0) {
                cd = 0;
            }
        }

        return cd;
    }

    /**
     * 计算CD剩余时间
     * @param endTime 结束时间
     * @param nowDate 当前时间
     * @return
     */
    public static long getCD(Long endTime, long currentTime) {
        long cd = 0;
        if (endTime == null) {
            cd = 0;
        } else {
            cd = endTime - currentTime;
            if (cd < 0) {
                cd = 0;
            }
        }

        return cd;
    }

    /**
     * 获得cd - for 名将跨服国战
     * @param oldCD
     * @param secend
     * @return
     * $Date: 2012-7-24 下午02:43:44
     */
    public static long getCD(long oldCD , int secend){
        long now  = System.currentTimeMillis();
        if(oldCD == 0 || oldCD - now <= 0){
            oldCD = now;
        }
        return oldCD + secend * 1000;
    }

    /**
     * 获取CD字符串
     * @param cd
     * @return
     */
    public static String getCDStr(final long cd) {
        StringBuilder builder = new StringBuilder(16);

        long DAY_SEC = TimeUnit.DAYS.toMillis(1);
        long HOUR_SEC_MS = TimeUnit.HOURS.toMillis(1);
        long MINUTE_SEC = TimeUnit.MINUTES.toMillis(1);

        long temp = cd;
        int day = (int) (temp / DAY_SEC);

        temp = temp % DAY_SEC;
        int hour = (int)(temp / HOUR_SEC_MS);

        temp = temp % HOUR_SEC_MS;
        int minute = (int)(temp / MINUTE_SEC);

        temp = temp % MINUTE_SEC;
        int sec = (int) (temp / 1000);

        // 显示天
        if (day != 0) {
            builder.append(day).append("\\u5929").append("\\");
        }

        // 显示小时
        if (hour != 0) {
            builder.append(StringUtils.leftPad(String.valueOf(hour), 2, '0')).append("\\uff1a");
        }

        // 显示分钟
        builder.append(StringUtils.leftPad(String.valueOf(minute), 2, '0')).append("\\uff1a");

        // 显示秒
        builder.append(StringUtils.leftPad(String.valueOf(sec), 2, '0'));

        return builder.toString();
    }
}
