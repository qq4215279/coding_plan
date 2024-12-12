/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RedisConfig
 * @Description Redis配置
 * @author cxx-cz
 * @Date 2017年8月17日 上午9:54:49
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisConfig {
	private boolean isCluster;

	private String pass;

	private String[] redisAddresses;

	private int timeout;

	private int poolMaxTotal;

	private int poolMaxIdle;

	private int maxRedirections;

	private String prefix;
}
