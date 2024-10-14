/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common.group;

/**
 * GroupUtil
 * 推送组工具类
 * @author liuzhen
 * @version 1.0.0 2024/10/14 22:59
 */
public class GroupUtil {
    /**
     * 获取世界的分组名
     * @return
     */
    public static String getWorldGroupName() {
        return "ONLINE";
    }

    /**
     * 获取州的分组名
     * @param region
     * @return
     */
    public static String getRegionGroupName(int region) {
        return "Region-" + region;
    }

    /**
     * 获取公会的分组名
     * @param clubId
     * @return
     */
    public static String getClubGroupName(int clubId) {
        return "club-" + clubId;
    }

    /**
     * 获取群组聊天的分组名
     * @param groupId
     * @return
     */
    public static String getFriendGroupGroupName(int groupId) {
        return "groupId-" + groupId;
    }
}
