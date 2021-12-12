/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_8_state.switchstate;

public class Switch {
    // 定义三个静态的状态对象
    private static SwitchState currentState, onState, offState;
    private String name;

    public Switch(String name) {
        this.name = name;
        onState = new OnState();
        offState = new OffState();
        currentState = onState;
    }

    public void setState(SwitchState state) {
        currentState = state;
    }

    public static SwitchState getState(String type) {
        if (type.equalsIgnoreCase("on")) {
            return onState;
        } else {
            return offState;
        }
    }

    // 打开开关
    public void on() {
        System.out.print(name);
        currentState.on(this);
    }

    // 关闭开关
    public void off() {
        System.out.print(name);
        currentState.off(this);
    }
}
