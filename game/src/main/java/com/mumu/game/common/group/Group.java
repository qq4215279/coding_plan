/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common.group;

import java.util.concurrent.ConcurrentMap;

/**
 * Group
 * 组，适用于需要将特定的一些人圈在一个范围里面
 * 这里面的人一个人产生了一个消息，一般需要组里面的人都知晓
 * @author liuzhen
 * @version 1.0.0 2024/10/14 22:53
 */
public interface Group {
    /**
     * 创建一个组
     * @param groupId 组Id
     * @return
     * $Date: 2011-12-9 下午02:53:01
     */
    Group createGroup(String groupId);

    /**
     * 创建一个组
     * @param groupId 组Id
     * @param canAutoLeave 是否可以自动退出，指的是玩家掉线时系统自动帮你退组
     * @return
     * $Date: 2011-12-9 下午02:53:01
     */
    Group createGroup(String groupId, boolean canAutoLeave);

    /**
     * 加入组
     * @param session
     * $Date: 2011-12-9 下午02:55:12
     */
    boolean join(Session session);

    /**
     * 离开组
     * @param sessionId SessionId
     * $Date: 2011-12-9 下午02:55:41
     */
    boolean leave(String sessionId);

    /**
     * 自动退出
     * @param sessionId
     * @return
     * $Date: 2015年6月24日 下午2:39:59
     */
    boolean autoLeave(String sessionId);

    /**
     * 清除
     */
    void clear();

    /**
     * 产生通知, 通知给组内所有人
     * @param command 推送command
     * @param body 推送内容
     * $Date: 2011-12-9 下午02:56:48
     */
    void notify(String command, byte[] body);

    /**
     * 产生通知,通知给组内除sessionId外的所有人
     * @param sessionId SessionId
     * @param command 推送command
     * @param body 推送内容
     * $Date: 2011-12-9 下午02:56:48
     */
    void notify(String sessionId, String command, byte[] body);

    /**
     * 推送给与给定group有交集的人
     * @param group
     * @param command 推送command
     * @param body
     * $Date: 2014-5-14 下午08:29:40
     */
    void notifyMix(Group group, String command, byte[] body);

    /**
     * 将消息发送给指定的人（必须在组内的成员）
     * @param command 推送command
     * @param body 内容
     * @param sessionIds 发送对象
     * $Date: 2011-12-9 下午04:05:24
     */
    int[] notify(String command, byte[] body, String... sessionIds);

    /**
     * 获得组编号
     * @return
     */
    String getGroupId();

    /**
     * 获得userMap
     * @return
     * @return: ConcurrentMap<String,Session>
     * @author: fanyy
     * @date: 2013-8-21 下午01:42:25
     */
    ConcurrentMap<String, Session> getUserMap();
}
