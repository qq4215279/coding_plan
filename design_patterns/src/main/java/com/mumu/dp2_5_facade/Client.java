/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_5_facade;

import com.mumu.dp2_5_facade.facade.AbstractEncryptFacade;

public class Client {
    public static void main(String args[]) {
		/*
		EncryptFacade ef = new EncryptFacade();
		ef.fileEncrypt("src//designpatterns//facade//src.txt","src//designpatterns//facade//des.txt");
		*/
        AbstractEncryptFacade ef;
        ef = (AbstractEncryptFacade)XMLUtil.getBean();
        ef.fileEncrypt("src//designpatterns//facade//src.txt", "src//designpatterns//facade//des.txt");

    }
}
