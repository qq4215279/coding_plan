/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.hario.game.rpc;

import com.mumu.common.expcetion.NetworkTimeoutException;
import com.mumu.game.common.response.Cmd;

import io.netty.util.concurrent.*;
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
    public RpcCallBack callback;

    /** 回调 */
    private Promise<V> promise;

    public RpcFuture(int requestId, long playerId, Cmd cmd) {
        this.requestId = requestId;
        this.playerId = playerId;
        this.cmd = cmd;

        // TODO 获取当前线程所在 EventExecutor
        EventExecutor executor = new DefaultEventExecutor();
        this.promise = new DefaultPromise<>(executor);
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
                        this.doCallback();
                    } catch (InterruptedException exception) {
                    }

                }
            }
        }
    }

    /**
     * 设置回调
     * @param callback callback
     * @date 2024/11/24 22:42
     */
    public void setCallback(RpcCallBack callback) {
        this.callback = callback;
        if (this.isDone) {
            this.doCallback();
        }


        // TODO  addListener
        promise.addListener(new GenericFutureListener<Future<V>>() {
            @Override
            public void operationComplete(Future<V> future) throws Exception {
                if (future.isSuccess()) {
                    V result = future.get();
                    RpcFuture.this.result = result;
                    RpcFuture.this.doCallback();
                }
            }
        });
    }

    public void setExpire() {
        if (!this.isDone) {
            this.isDone = true;
            this.cause = EXPIRED_CAUSE;
            synchronized(this) {
                this.notifyAll();
            }

            this.doCallback();
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

            // TODO set Success
            promise.setSuccess(result);
        }
    }


    public void doCallback() {
        if (null != this.callback) {
            this.callback.callback(this);
        }

    }
}
