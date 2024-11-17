/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.hario.game.rpc;

import lombok.Data;

import java.io.IOException;

/**
 * RpcResult
 * MessageProxy
 * @author liuzhen
 * @version 1.0.0 2024/11/17 18:03
 */
@Data
public class RpcResult {
    private static final long serialVersionUID = -170090240901978289L;
    public static final int STATE_SUCC = 1;
    public static final int STATE_ERROR = 2;
    public int state;
    public String msg;
    public Object result;

    public RpcResult() {
    }

    public RpcResult(int state, String error) {
        this.state = state;
        this.msg = error;
    }

    public RpcResult(Object result) {
        this.state = 1;
        this.result = result;
    }

    public void readFrom() throws IOException {
        // TODO 解析
        // this.result = MsgpackSerializeUtil.readObject(unpacker);
    }

    public void writeTo(byte[] bytes) throws IOException {
       // TODO
    }
}
