/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp3_10_templatemethod.test;

import com.mumu.dp3_10_templatemethod.test.account.Account;

public class Client {
    public static void main(String args[]) {
        Account account;
        account = (Account)XMLUtil.getBean();
        account.handle("张无忌", "123456");
    }
}
