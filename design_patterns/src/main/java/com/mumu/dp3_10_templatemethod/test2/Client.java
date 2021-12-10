package com.mumu.dp3_10_templatemethod.test2;

import com.mumu.dp3_10_templatemethod.test2.account.Account;

public class Client {
    public static void main(String args[]) {
        Account account;
        account = (Account)XMLUtil.getBean();
        account.handle("张无忌", "123456");
    }
}
