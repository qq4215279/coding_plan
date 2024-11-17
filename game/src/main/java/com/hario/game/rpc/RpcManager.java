/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.hario.game.rpc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;

import com.baloot.game.regin.ServerGroup;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mumu.common.thread.StandardThreadFactory;
import com.mumu.game.common.response.Cmd;

import io.netty.buffer.ByteBuf;

/**
 * RpcManager rpc管理器
 * 
 * @author liuzhen
 * @version 1.0.0 2024/11/17 17:34
 */
public class RpcManager {
    private static final Logger log = null;

    /** 超时时间(毫秒值) */
    private long timeout;
    /** 压缩 */
    private boolean compress = false;
    /** 初始化 */
    private boolean initFlag = false;

    /** 自增协议id */
    private final AtomicInteger innerRequestId = new AtomicInteger(0);
    /** requesId 与 RpcFuture 映射 */
    private final Cache<Integer, RpcFuture<RpcResponse>> futureCache;
    /** 回调线程池 */
    private ExecutorService asyncExecutor;

    private static final RpcManager instance = new RpcManager();

    private RpcManager() {
        this.futureCache =
            CacheBuilder.newBuilder().expireAfterWrite(2L, TimeUnit.MINUTES).removalListener((removalNotification) -> {
                ((RpcFuture)removalNotification.getValue()).setExpire();
            }).build();
    }

    /**
     * 获取单例
     * @date 2024/11/17 18:43
     */
    public static RpcManager getInstance() {
        return instance;
    }

    /**
     * 初始化
     * @param timeout timeout
     * @param compress compress
     * @date 2024/11/17 19:33
     */
    public synchronized void init(long timeout, boolean compress) throws Exception {
        if (!this.initFlag) {
            this.initFlag = true;
            this.timeout = timeout;
            this.compress = compress;
        }
    }

    /**
     * 同步发送rpc请求，并获得返回值
     * @param server server
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @return java.lang.Object
     * @date 2024/11/17 19:33
     */
    public Object sendRequest(ServerGroup server, long playerId, Cmd cmd, Object rpcMsg) {
        long startTime = System.currentTimeMillis();
        boolean error = false;
        boolean finish = false;
        RpcFuture<RpcResponse> future = this.sendRequestAsync(server, playerId, cmd, rpcMsg);

        Object result;
        try {
            finish = true;
            future.await(this.timeout);
            if (!future.isSuccess()) {
                throw future.getCause();
            }

            RpcResponse response = (RpcResponse)future.getResult();
            result = this.unpackResponse(response);
            finish = false;
        } catch (RuntimeException exception) {
            error = true;
            throw exception;
        } finally {
            if (finish) {
                this.futureCache.invalidate(future.getRequestId());
                long endtime = System.currentTimeMillis();
                // TODO record log
            }
        }

        this.futureCache.invalidate(future.getRequestId());
        
        long endtime = System.currentTimeMillis();
        // TODO record log
        
        return result;
    }

    /**
     * 同步发送rpc请求，并获得返回值
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @return java.lang.Object
     * @date 2024/11/17 19:34
     */
    public Object sendRequest(long playerId, Cmd cmd, Object rpcMsg) {
        long startTime = System.currentTimeMillis();
        boolean error = false;
        boolean finish = false;
        RpcFuture<RpcResponse> future = this.sendRequestAsync(playerId, cmd, rpcMsg);

        Object result;
        try {
            finish = true;
            future.await(this.timeout);
            if (!future.isSuccess()) {
                throw future.getCause();
            }

            RpcResponse response = (RpcResponse)future.getResult();
            result = this.unpackResponse(response);
            finish = false;
        } catch (RuntimeException exception) {
            error = true;
            throw exception;
        } finally {
            if (finish) {
                this.futureCache.invalidate(future.getRequestId());
                long endtime = System.currentTimeMillis();
                // TODO record log
            }
        }

        this.futureCache.invalidate(future.getRequestId());
        
        long endtime = System.currentTimeMillis();
        // TODO record log
        
        return result;
    }

    private Object unpackResponse(RpcResponse response) {
        RpcResult rpcResult = (RpcResult)response.getResult();
        if (rpcResult.state != 1) {
            throw new RuntimeException("call kf server error, msg:" + rpcResult.msg + ", command:" + response.command);
        } else {
            return rpcResult.getResult();
        }
    }

    /**
     * 异步发送rpc请求
     * @param server server
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @param callback 回调
     * @date 2024/11/17 19:34
     */
    public void sendRequestAsync(ServerGroup server, long playerId, Cmd cmd, Object rpcMsg, RpcCallBack callback) {
        RpcFuture<RpcResponse> future = this.sendRequestAsync(server, playerId, cmd, rpcMsg);
        future.setCallback(callback);
    }

    /**
     * 异步发送rpc请求
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @param callback 回调
     * @date 2024/11/17 19:35
     */
    public void sendRequestAsync(long playerId, Cmd cmd, Object rpcMsg, RpcCallBack callback) {
        RpcFuture<RpcResponse> future = this.sendRequestAsync(playerId, cmd, rpcMsg);
        future.setCallback(callback);
    }

    /**
     * 异步发送rpc请求
     * @param server server
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @return com.hario.game.rpc.RpcFuture<com.hario.game.rpc.RpcResponse>
     * @date 2024/11/17 19:35
     */
    public RpcFuture<RpcResponse> sendRequestAsync(ServerGroup server, long playerId, Cmd cmd, Object rpcMsg) {
        return this.doSendRequestAsync(server, playerId, cmd, rpcMsg, true);
    }

    /**
     * 异步发送rpc请求
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @return com.hario.game.rpc.RpcFuture<com.hario.game.rpc.RpcResponse>
     * @date 2024/11/17 19:35
     */
    public RpcFuture<RpcResponse> sendRequestAsync(long playerId, Cmd cmd, Object rpcMsg) {
        return this.doSendRequestAsync(playerId, cmd, rpcMsg, true);
    }

    /**
     * do 异步发送rpc请求
     * @param server server
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @param printLog 是否打印日志
     * @return com.hario.game.rpc.RpcFuture<com.hario.game.rpc.RpcResponse>
     * @date 2024/11/17 19:35
     */
    private RpcFuture<RpcResponse> doSendRequestAsync(ServerGroup server, long playerId, Cmd cmd, Object rpcMsg,
        boolean printLog) {
        int requestId = this.innerRequestId.decrementAndGet();

        RpcFuture<RpcResponse> future = new RpcFuture<>(requestId, playerId, cmd);
        this.futureCache.put(requestId, future);

        Object buffer;
        if (printLog) {
            boolean error = false;

            try {
                // buffer = this.wrapperRequest(channel.channelType(), requestId, server.moduleId, server.serverId,
                // command, args);
                // Object channelFuture = channel.write(buffer);
                Object channelFuture = null;
                future.setChannelFuture(channelFuture);
            } catch (RuntimeException exception) {
                RuntimeException e = exception;
                error = true;
                throw e;
            } finally {
                // TODO record log
            }
        } else {
            // buffer = this.wrapperRequest(channel.channelType(), requestId, server.moduleId, server.serverId, command,
            // args);
            // Object channelFuture = channel.write(buffer);

            Object channelFuture = null;
            future.setChannelFuture(channelFuture);
        }

        // TODO record log

        return future;
    }

    /**
     * do 异步发送rpc请求
     * @param playerId playerId
     * @param cmd cmd
     * @param rpcMsg rpcMsg
     * @param printLog printLog
     * @return com.hario.game.rpc.RpcFuture<com.hario.game.rpc.RpcResponse>
     * @date 2024/11/17 19:36
     */
    private RpcFuture<RpcResponse> doSendRequestAsync(long playerId, Cmd cmd, Object rpcMsg, boolean printLog) {
        int requestId = this.innerRequestId.decrementAndGet();
        RpcFuture<RpcResponse> future = new RpcFuture<>(requestId, playerId, cmd);
        this.futureCache.put(requestId, future);
        Object buffer;
        if (printLog) {
            boolean error = false;

            try {
                // buffer = this.wrapperRequest(channel.channelType(), requestId, command, args);
                // Object channelFuture = channel.write(buffer);
                Object channelFuture = null;
                future.setChannelFuture(channelFuture);
            } catch (RuntimeException exception) {
                error = true;
                throw exception;
            } finally {
                // TODO record log
            }
        } else {
            // Object buffer = this.wrapperRequest(channel.channelType(), requestId, command, args);
            // buffer = channel.write(buffer);
            // future.setChannelFuture(buffer);
            Object channelFuture = null;
            future.setChannelFuture(channelFuture);
        }

        // TODO record log
        
        return future;
    }

    /**
     * TODO 处理响应
     * @param requestId requestId
     * @param buff buff
     * @param dataLen dataLen
     * @date 2024/11/17 19:36
     */
    public void handleResponse(int requestId, ByteBuf buff, int dataLen) {
        try {
            RpcFuture<RpcResponse> future = (RpcFuture)this.futureCache.getIfPresent(requestId);
            if (null != future) {

                future.setResult(decode(requestId, buff, dataLen));
                this.rpcAsyncCallback(future);
                return;
            }

            buff.skipBytes(dataLen + 4);
        } finally {
            // TODO record log
        }

    }

    /**
     * do 异步回调
     * @param future future
     * @date 2024/11/17 19:45
     */
    private void rpcAsyncCallback(RpcFuture future) {
        if (null != future.getCallback()) {
            if (null == this.asyncExecutor) {
                synchronized (this) {
                    if (null == this.asyncExecutor) {
                        this.asyncExecutor =
                            Executors.newFixedThreadPool(2, new StandardThreadFactory("rpc-async-thread"));

                    }
                }
            }

            this.asyncExecutor.execute(() -> future.callback());
        }
    }

    private RpcResponse decode(int requestId, ByteBuf buff, int dataLen) {
        // TODO decode
        return new RpcResponse("", 1, null);
    }

    public void discardRpcFuture(RpcFuture<RpcResponse> future) {
        this.futureCache.invalidate(future.getRequestId());
    }

}
