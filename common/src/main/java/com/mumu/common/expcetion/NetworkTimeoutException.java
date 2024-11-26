/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.expcetion;

/**
 * NetworkTimeoutException
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 17:40
 */
public class NetworkTimeoutException extends RuntimeException {
    private static final long serialVersionUID = -2945594596866850703L;

    public NetworkTimeoutException(String msg) {
        super(msg);
    }
}
