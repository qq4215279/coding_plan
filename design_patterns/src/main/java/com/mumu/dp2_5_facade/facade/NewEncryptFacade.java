/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.dp2_5_facade.facade;

import com.mumu.dp2_5_facade.sub_system.FileReader;
import com.mumu.dp2_5_facade.sub_system.FileWriter;
import com.mumu.dp2_5_facade.sub_system.NewCipherMachine;

public class NewEncryptFacade extends AbstractEncryptFacade {
    private FileReader reader;
    private NewCipherMachine cipher;
    private FileWriter writer;

    public NewEncryptFacade() {
        reader = new FileReader();
        cipher = new NewCipherMachine();
        writer = new FileWriter();
    }

    public void fileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = reader.read(fileNameSrc);
        String encryptStr = cipher.encrypt(plainStr);
        writer.write(encryptStr, fileNameDes);
    }
}
