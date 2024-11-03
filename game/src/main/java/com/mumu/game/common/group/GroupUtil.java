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
    /** 获取世界的分组名 */
    public static String getWorldGroupName() {
        return "ONLINE";
    }


    /**
     * 获取公会的分组名
     * @param clubId 公会id
     */
    public static String getClubGroupName(int clubId) {
        return "CLUB-" + clubId;
    }

    /**
     * 获取群组聊天的分组名
     * @param groupId 聊天群组id
     */
    public static String getFriendGroupGroupName(int groupId) {
        return "GROUP-" + groupId;
    }

}
