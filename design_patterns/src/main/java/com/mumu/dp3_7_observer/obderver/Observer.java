/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_7_observer.obderver;

import com.mumu.dp3_7_observer.subject.AllyControlCenter;

// 抽象观察类
public interface Observer {
    String getName();

    void setName(String name);

    // 声明支援盟友方法
    void help();

    // 声明遭受攻击方法
    void beAttacked(AllyControlCenter acc);
}
