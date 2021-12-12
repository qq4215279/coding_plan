/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_8_state.test;

public class Client {
    public static void main(String args[]) {
        AccountContext acc = new AccountContext("段誉", 0.0);
        acc.deposit(1000);
        acc.withdraw(2000);
        acc.deposit(3000);
        acc.withdraw(4000);
        acc.withdraw(1000);
        acc.computeInterest();
    }
}
