/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.hario.game.rpc;

import lombok.Getter;

/**
 * RpcResponse
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 17:47
 */
public class RpcResponse {
    public String command;
    public int requestId;
    private byte[] resultBytes;

    /** MessageProxy */
    @Getter
    private Object result;

    public RpcResponse(String command, int requestId, byte[] resultBytes) {
        this.command = command;
        this.requestId = requestId;
        this.resultBytes = resultBytes;

        unpack();
    }

    private void unpack() {
        // TODO 解析 resultBytes 赋值 result
        // this.result = deserialize(this.resultBytes);
    }
}
