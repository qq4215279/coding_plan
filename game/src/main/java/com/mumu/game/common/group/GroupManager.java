/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common.group;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * GroupManager
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/14 22:54
 */
public class GroupManager {
    /** 组管理者实例 */
    private static final GroupManager instance = new GroupManager();

    /** 组Map */
    private ConcurrentMap<String, Group> groupMap = new ConcurrentHashMap<String, Group>();

    /**
     * 构造函数
     */
    private GroupManager() {
    }

    /**
     * 获取实例
     * @return
     * $Date: 2011-12-9 下午03:26:15
     */
    public static GroupManager getInstance() {
        return instance;
    }

    /**
     * 创建一个组
     * @param groupId 组别Id，要求唯一
     * @return
     * $Date: 2011-12-9 下午03:26:50
     */
    public Group createGroup(String groupId) {
        if (groupMap.containsKey(groupId)) {
            return groupMap.get(groupId);
        }

        Group group = new GroupImpl(groupId, true);
        Group temp = groupMap.putIfAbsent(groupId, group);
        return (null != temp) ? temp : group;
    }

    /**
     * 创建一个组
     * @param groupId 组别Id，要求唯一
     * @param canAutoLeave 是否可以自动退出，指的是玩家掉线时系统自动帮你退组
     * @return
     * $Date: 2011-12-9 下午03:26:50
     */
    public Group createGroup(String groupId, boolean canAutoLeave) {
        if (groupMap.containsKey(groupId)) {
            return groupMap.get(groupId);
        }

        Group group = new GroupImpl(groupId, canAutoLeave);
        Group temp = groupMap.putIfAbsent(groupId, group);
        return (null != temp) ? temp : group;
    }

    /**
     * 销毁一个组
     * @param groupId 组别Id
     * @return
     * $Date: 2011-12-9 下午03:26:50
     */
    public void deleteGroup(String groupId) {
        if (groupMap.containsKey(groupId)) {
            groupMap.remove(groupId);
        }
    }

    /**
     * 查询组
     * @param groupId 组Id
     * @return
     * $Date: 2011-12-9 下午03:29:14
     */
    public Group getGroup(String groupId) {
        return groupMap.get(groupId);
    }

    /**
     * 发送消息
     * @param groupId
     * @param command
     * @param body
     * $Date: 2011-12-9 下午04:09:24
     */
    public void notifyAll(String groupId, String command, byte[] body) {
        Group group = groupMap.get(groupId);
        if (null != group) {
            group.notify(command, body);
        }
    }

    /**
     * 发送消息
     * @param groupId
     * @param sessionId
     * @param command
     * @param body
     * $Date: 2011-12-9 下午04:09:24
     */
    public void notifyAll(String groupId, String sessionId, String command, byte[] body) {
        Group group = groupMap.get(groupId);
        if (null != group) {
            group.notify(sessionId, command, body);
        }
    }

    /**
     * 发送消息
     * @param groupId
     * @param command
     * @param body
     * @param sessionIds
     * $Date: 2011-12-9 下午04:09:24
     */
    public void notify(String groupId, String command, byte[] body, String... sessionIds) {
        Group group = groupMap.get(groupId);
        if (null != group) {
            group.notify(command, body, sessionIds);
        }
    }

    /**
     * 离开所有组
     * @param sessionId 队伍Id
     * $Date: 2011-12-9 下午04:46:58
     */
    public void leave(String sessionId) {
        for (Group group : groupMap.values()) {
            group.autoLeave(sessionId);
        }
    }

    /**
     * 强制离开所有组
     * @param sessionId 队伍Id
     * $Date: 2011-12-9 下午04:46:58
     */
    public void forceLeave(String sessionId) {
        for (Group group : groupMap.values()) {
            group.leave(sessionId);
        }
    }
}
