/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_8_state.test.state;

import com.mumu.dp3_8_state.test.AccountContext;

// 抽象状态类
public abstract class AccountState {
    protected AccountContext acc;

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract void computeInterest();

    public abstract void stateCheck();
}
