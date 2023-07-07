/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.readwritefile.old;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ObjectInputOutputStream
 *
 * @author liuzhen
 * @version 1.0.0 2021/1/15 10:38
 */
public class a5ObjectInputOutputStream {

    public static final String certificateNum = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum.txt";
    public static final String certificateNum2 = "src\\test\\java\\com\\gobestsoft\\readwritefile\\certificateNum2.txt";

    @Test
    public void testObjectInputOutputStream() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(certificateNum));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(certificateNum2));

        try {
            oos.writeObject(new User("lihua", "123456"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ois.close();
            oos.close();
        }

    }


}
