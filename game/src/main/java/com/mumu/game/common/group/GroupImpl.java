/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common.group;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * GroupImpl
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/14 22:54
 */
public class GroupImpl implements Group {
    /** 组中成员Map */
    private ConcurrentMap<String, Session> userMap = new ConcurrentHashMap<String, Session>();

    /** 组Id */
    private String groupId;

    /** 是否可以自动退出  */
    private boolean canAutoLeave;

    @Override public ConcurrentMap<String, Session> getUserMap() {
        return userMap;
    }

    /**
     * 构造函数
     * @param groupId
     * @param canAutoLeave
     */
    public GroupImpl(String groupId, boolean canAutoLeave) {
        this.groupId = groupId;
        this.canAutoLeave = canAutoLeave;
    }

    @Override
    public Group createGroup(String groupId) {
        Group group = new GroupImpl(groupId, true);

        return group;
    }

    @Override
    public Group createGroup(String groupId, boolean canAutoLeave) {
        Group group = new GroupImpl(groupId, canAutoLeave);

        return group;
    }

    @Override
    public boolean join(Session session) {
        userMap.put(session.getId(), session);
        return true;
    }

    @Override
    public boolean leave(String sessionId) {
        return userMap.remove(sessionId) != null;
    }

    @Override
    public boolean autoLeave(String sessionId) {
        if (canAutoLeave) {
            return leave(sessionId);
        }
        return false;
    }

    /**
     * 清除
     */
    @Override public void clear() {
        userMap.clear();
    }

    @Override
    public void notify(String sessionId, String command, byte[] body) {
        Set<Map.Entry<String, Session>> entrySet = userMap.entrySet();
        /*Object buffer = WrapperUtil.wrapper(command, 0, body);
        try {
            if (null == sessionId) {
                // 推送给所有人
                for (Map.Entry<String, Session> entry : entrySet) {
                    WrapperUtil.retain(buffer);
                    entry.getValue().push(buffer);
                }
            } else {
                // 推送给排除指定sessionId的人
                for (Map.Entry<String, Session> entry : entrySet) {
                    if (!entry.getKey().equals(sessionId)) {
                        WrapperUtil.retain(buffer);
                        entry.getValue().push(buffer);
                    }
                }
            }
        } finally {
            WrapperUtil.release(buffer);
        }*/
    }

    @Override
    public void notifyMix(Group group, String command, byte[] body) {
        Set<Map.Entry<String, Session>> entrySet = userMap.entrySet();
        /*Object buffer = WrapperUtil.wrapper(command, 0, body);
        try {
            if (null == group) {
                // 推送给所有人
                for (Map.Entry<String, Session> entry : entrySet) {
                    WrapperUtil.retain(buffer);
                    entry.getValue().push(buffer);
                }
            } else {
                // 推送给2个集合中都存在的人
                for (Map.Entry<String, Session> entry : entrySet) {
                    if (group.getUserMap().containsKey(entry.getKey())) {
                        WrapperUtil.retain(buffer);
                        entry.getValue().push(buffer);
                    }
                }
            }
        } finally {
            WrapperUtil.release(buffer);
        }*/
    }

    @Override
    public void notify(String command, byte[] body) {
        Set<Map.Entry<String, Session>> entrySet = userMap.entrySet();
        /*Object buffer = WrapperUtil.wrapper(command, 0, body);
        try {
            // 推送给所有人
            for (Map.Entry<String, Session> entry : entrySet) {
                WrapperUtil.retain(buffer);
                entry.getValue().push(buffer);
            }
        } finally {
            WrapperUtil.release(buffer);
        }*/
    }

    @Override
    public int[] notify(String command, byte[] body, String... sessionIds) {
        int[] result = new int[sessionIds.length];
        /*int index = 0;
        Object buffer = WrapperUtil.wrapper(command, 0, body);
        try {
            for (String id : sessionIds) {
                if (userMap.containsKey(id)) {
                    Session session = userMap.get(id);
                    WrapperUtil.retain(buffer);
                    session.push(buffer);
                    result[index++] = 1;
                }
            }
        } finally {
            WrapperUtil.release(buffer);
        }*/

        return result;
    }

    /**
     * 获得组编号
     * @return
     */
    @Override public String getGroupId() {
        return groupId;
    }
}
