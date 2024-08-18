/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.design.baloot.common.enums;

import com.mumu.design.baloot.common.constant.BalootConstans;

import java.util.Arrays;
import java.util.List;

/**
 * PlayerCommandEnum
 * 玩家叫牌命令
 * @author liuzhen
 * @version 1.0.0 2024/8/18 16:48
 */
public enum PlayerCommandEnum {
    // TODO 处理组合
    PLAYER_1(Arrays.asList(BalootConstans.SUN_BID_TYPE, BalootConstans.HOKUM_BID_TYPE, BalootConstans.ASK_BID_TYPE, BalootConstans.PASS_BID_TYPE)),
    PLAYER_2(Arrays.asList(BalootConstans.SUN_BID_TYPE, BalootConstans.HOKUM_BID_TYPE, BalootConstans.PASS_BID_TYPE)),
    PLAYER_3(Arrays.asList(BalootConstans.SUN_BID_TYPE, BalootConstans.HOKUM_BID_TYPE, BalootConstans.PASS_BID_TYPE)),
    PLAYER_4(Arrays.asList(BalootConstans.SUN_BID_TYPE, BalootConstans.HOKUM_BID_TYPE, BalootConstans.ASK_BID_TYPE, BalootConstans.PASS_BID_TYPE)),
    ;

    private List<String> commandList;

    PlayerCommandEnum(List<String> commandList) {
        this.commandList = commandList;
    }
}
