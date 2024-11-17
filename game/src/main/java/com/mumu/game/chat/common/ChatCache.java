/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.chat.common;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * ChatCache
 * 聊天缓存
 * @author liuzhen
 * @version 1.0.0 2024/11/3 18:01
 */
public class ChatCache<K extends Serializable, CHAT extends ChatInfo> {
    /** 过期策略 -- 访问 */
    private static final int EXPIRE_TYPE_ACCESS = 1;
    /** 过期策略 -- 写入 */
    private static final int EXPIRE_TYPE_WRITE = 2;
    /** 过期策略 -- 永不 */
    private static final int EXPIRE_TYPE_NEVER = 3;

    /** 消息缓存 */
    private final Cache<Object, Object> cache;
    /** 每个队列默认消息数 */
    private int eachQueueSize = 15;
    /** 空队列 */
    private final Queue<CHAT> emptyQueue = new ConcurrentLinkedQueue<>();


    public ChatCache(int queueSize, int capacity) {
        this.eachQueueSize = queueSize;
        cache = CacheBuilder.newBuilder().maximumSize(capacity).build();
    }

    public ChatCache(int queueSize, int capacity, int expireType, int hour) {
        this.eachQueueSize = queueSize;
        if (expireType == EXPIRE_TYPE_ACCESS) {
            cache = CacheBuilder.newBuilder().maximumSize(capacity).expireAfterAccess(hour, TimeUnit.HOURS).build();
        } else if (expireType == EXPIRE_TYPE_WRITE) {
            cache = CacheBuilder.newBuilder().maximumSize(capacity).expireAfterWrite(hour, TimeUnit.HOURS).build();
        } else if (expireType == EXPIRE_TYPE_NEVER) {
            cache = CacheBuilder.newBuilder().maximumSize(capacity).build();
        } else {
            cache = CacheBuilder.newBuilder().maximumSize(capacity).build();
        }
    }

    /**
     * 移除信息
     * @param key key
     * @date 2024/11/3 18:03
     */
    public void remove(K key) {
        cache.invalidate(key);
    }

    /**
     * 添加信息
     * @param key key
     * @param info info
     * @date 2024/11/3 18:03
     */
    public void add(K key, CHAT info) {
        Queue<CHAT> q = (Queue<CHAT>) cache.getIfPresent(key);
        if (q == null) {
            cache.put(key, new ConcurrentLinkedQueue<CHAT>());
        }

        Queue<CHAT> queue = (Queue<CHAT>) cache.getIfPresent(key);
        while (queue.size() >= eachQueueSize) {
            queue.poll();
        }
        queue.add(info);
    }

    /**
     * 获得消息队列
     * @param key key
     * @return java.util.Queue<org.apache.poi.ss.formula.functions.T>
     * @date 2024/11/3 18:03
     */
    public Queue<CHAT> getQueue(K key) {
        Queue<CHAT> q = (Queue<CHAT>) cache.getIfPresent(key);
        if (q == null) {
            return emptyQueue;
        }
        return q;
    }
}
