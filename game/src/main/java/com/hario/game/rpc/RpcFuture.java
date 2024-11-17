/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.hario.game.rpc;

import com.mumu.common.expcetion.NetworkTimeoutException;
import com.mumu.game.common.response.Cmd;

import lombok.Data;

/**
 * RpcFuture
 *
 * @author liuzhen
 * @version 1.0.0 2024/11/17 17:39
 */
@Data
public class RpcFuture<V> {
    private static final RuntimeException EXPIRED_CAUSE = new NetworkTimeoutException("rpc request expired");

    /** 是否已执行 */
    private volatile boolean isDone;
    /** 是否成功 */
    private boolean isSuccess;
    /** 异常 */
    private RuntimeException cause;

    /** 返回值 */
    private V result;

    private int requestId;

    private long playerId;
    private Cmd cmd;

    private Object channelFuture;
    /** 回调 */
    private RpcCallBack callback;

    public RpcFuture(int requestId, long playerId, Cmd cmd) {
        this.requestId = requestId;
        this.playerId = playerId;
        this.cmd = cmd;
    }

    public void await(long timeout) {
        if (!this.isDone) {
            synchronized(this) {
                if (!this.isDone) {
                    try {
                        this.wait(timeout);
                        if (this.isDone) {
                            return;
                        }

                        this.isDone = true;
                        this.cause = new NetworkTimeoutException("rpc request timeout");
                        this.callback();
                    } catch (InterruptedException exception) {
                    }

                }
            }
        }
    }

    public void setCallback(RpcCallBack callback) {
        this.callback = callback;
        if (this.isDone) {
            this.callback();
        }

    }

    public void setExpire() {
        if (!this.isDone) {
            this.isDone = true;
            this.cause = EXPIRED_CAUSE;
            synchronized(this) {
                this.notifyAll();
            }

            this.callback();
        }
    }

    public void setResult(V result) {
        if (!this.isDone) {
            this.isDone = true;
            this.isSuccess = true;
            this.result = result;
            synchronized(this) {
                this.notifyAll();
            }
        }
    }


    public void callback() {
        if (null != this.callback) {
            this.callback.callback(this);
        }

    }
}
