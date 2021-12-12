/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_10_templatemethod.test.account;

// 活期账户类：具体子类
public class CurrentAccount extends Account {
    // 覆盖父类的抽象基本方法
    public void calculateInterest() {
        System.out.println("按活期利率计算利息！");
    }
}
