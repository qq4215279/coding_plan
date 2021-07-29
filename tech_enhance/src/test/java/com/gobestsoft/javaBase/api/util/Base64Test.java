/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.api.util;

import org.junit.Test;

import java.util.Base64;

/**
 * Base64Test
 *
 * @author liuzhen
 * @version 1.0.0 2021/7/13 17:58
 */
public class Base64Test {

    @Test
    public void encoder() {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode("123456hh你好".getBytes());
        System.out.println("encode: " + new String(encode));

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(encode);
        System.out.println("decode: " + new String(decode));

    }

}
