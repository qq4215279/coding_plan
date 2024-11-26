/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.baloot.game.regin;

/**
 * ServerGroup
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 17:54
 */
public enum ServerGroup {
    ALL(1),
    GATE(2),
    WORLD(2),

    ;

    final int groupId;

    ServerGroup(int groupId) {
        this.groupId = groupId;
    }
}
