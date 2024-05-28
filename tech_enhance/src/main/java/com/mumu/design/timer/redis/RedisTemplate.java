package com.mumu.design.timer.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cxx.hf.exception.CommonException;
import com.cxx.hf.exception.ErrorCode;
import com.cxx.hf.servercore.constant.CoreConstant;
import com.cxx.hf.servercore.mina.impl.InnerServer;
import com.cxx.hf.util.Utility;
import com.cxx.hf.util.json.GsonUtil;
import com.cxx.hf.util.other.ZLibUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.client.codec.ByteArrayCodec;
import org.redisson.client.codec.LongCodec;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.ScoredEntry;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.SubscriptionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName RedisTemplate
 * @Description Redis操作模版
 * @author cxx-cz
 * @Date 2017年8月17日 下午5:26:43
 * @version 1.0.0
 */
@Data
@Slf4j
public class RedisTemplate {
	private static final Logger redisLog = LoggerFactory.getLogger("redis");

	private static final boolean PROTOSTUFF_SERIALIZE_FALG = false;

	private static final String separator = "_";
	private static final short separatorLength = (short) separator.length();

	protected Redisson redisson;

	private String prefix;

	private short prefixLength;

	private int connectionPoolSize = 0;

	public RedisTemplate(RedisConfig redisConfig) {
		shutdown();
		prefix = redisConfig.getPrefix();
		prefixLength = (short) prefix.length();
		if (InnerServer.getServerConfig() != null) {
			connectionPoolSize = InnerServer.getThreadNum();
		} else {
			connectionPoolSize = CoreConstant.SERVER_PLATFORM_THREAD_NUM;
		}
		log.info("Redis config={},connectionPoolSize={}", GsonUtil.toJson(redisConfig), connectionPoolSize);
		Config config = new Config();
		// 同一个线程重入锁的情况下释放锁时unlock操作会判断加锁次数，如果加锁次数>减锁的次数时则重新设置锁时间
		// 这个时间默认为lockWatchdogTimeout(系统默认为30000MS)
		// 具体操作代码为RedissonFairLock(RedissonLock)中unlockInnerAsync方法
		config.setLockWatchdogTimeout(CoreConstant.REDIS_KEY_LOCK_TIME);
		// config.setNettyThreads(connectionPoolSize)
		if (redisConfig.isCluster()) {
			// 集群模式
			ClusterServersConfig csc = config.useClusterServers();
			csc.addNodeAddress(redisConfig.getRedisAddresses());
			csc.setScanInterval(5000);
			csc.setRetryInterval(500);
			csc.setReadMode(ReadMode.MASTER);
			csc.setSubscriptionMode(SubscriptionMode.MASTER);
			// 设定连接数
			csc.setMasterConnectionPoolSize((int) (connectionPoolSize / 0.75F));
			csc.setMasterConnectionMinimumIdleSize(connectionPoolSize / 2);
			// csc.setSubscriptionConnectionPoolSize((int) (connectionPoolSize / 4));
			// csc.setSubscriptionConnectionMinimumIdleSize(connectionPoolSize / 10);
		} else {
			// 单例模式
			SingleServerConfig ssc = config.useSingleServer();
			ssc.setAddress(redisConfig.getRedisAddresses()[0]);
			// 设定连接数
			ssc.setConnectionPoolSize((int) (connectionPoolSize / 0.75F));
			ssc.setConnectionMinimumIdleSize(connectionPoolSize / 2);
			// ssc.setSubscriptionConnectionPoolSize((int) (connectionPoolSize / 0.5F));
			// ssc.setSubscriptionConnectionMinimumIdleSize(connectionPoolSize);
			ssc.setTimeout(redisConfig.getTimeout());
			if (!StringUtils.isEmpty(redisConfig.getPass())) {
				ssc.setPassword(redisConfig.getPass());
			}
		}
		redisson = (Redisson) Redisson.create(config);
		log.info("Start the Redisson template={}", this);

//		// 连接监听器
//		ConnectionListener connectionListener = new ConnectionListener() {
//			@Override
//			public void onConnect(InetSocketAddress addr) {
//				log.warn("redission onConnect to {}", addr.getAddress().getHostAddress());
//			}
//			@Override
//			public void onDisconnect(InetSocketAddress addr) {
//				log.warn("redission onDisconnect to {}", addr.getAddress().getHostAddress());
//			}
//		};
//		// 添加连接状态监听器和连接监听器
//		redisson.getConnectionManager().getConnectionEventsHub().addListener(connectionListener);

	}

	/**
	 * @Title shutdown
	 * @Description 关闭
	 * @return void
	 */
	public void shutdown() {
		try {
			if (redisson != null) {
				log.info("Shutdown the Redisson");
				redisson.shutdown();
			}
		} catch (Exception e) {
			log.error("Shutdown the redisson=> [Fail] cause={}", Utility.getTraceString(e));
		}
	}

	/**
	 * @Title buildKey
	 * @Description key加上通用配置前缀
	 * @param nativeKey
	 * @return String
	 */
	public String buildKey(String nativeKey) {
		if (nativeKey == null || nativeKey.length() <= 0) {
			return nativeKey;
		}
		StringBuilder key = new StringBuilder(prefixLength + separatorLength + nativeKey.length());
		if (!nativeKey.contains(prefix)) {
			key.append(prefix);
			key.append(separator);
			key.append(nativeKey);
		} else {
			key.append(nativeKey);
		}
		return key.toString().replaceAll(" ", separator);
	}

	/**
	 * @Title buildLockKey
	 * @Description 创建key的对应lockkey
	 * @param key
	 * @return String
	 */
	public String buildLockKey(String key) {
		return MessageFormat.format(CoreConstant.LOCK_KEY_PREFIX, key);
	}

	/**
	 * @Title acquireFairLock
	 * @Description 获取分布式公平锁-默认过期时间为 CoreConstant.REDIS_KEY_LOCK_TIME
	 * @param lockKey
	 * @return boolean
	 */
	public boolean acquireLock(String lockKey) {
		return acquireLock(lockKey, CoreConstant.REDIS_KEY_LOCK_TIME, TimeUnit.MILLISECONDS);
	}

	/**
	 * @Title acquireLock
	 * @Description 获取分布式公平锁
	 * @param lockKey 锁名
	 * @param expire 过期时间
	 * @param unit 时间单位
	 * @return boolean
	 */
	public boolean acquireLock(String lockKey, long expire, TimeUnit unit) {
		redisson.getFairLock(lockKey).lock(expire, unit);
		// redisLog.info("acquireLock key={},thread={},time={}", lockKey,
		// Thread.currentThread().getName(),
		// System.currentTimeMillis());
		return true;
	}

	/**
	 * 获取分布式公平锁
	 * @param lockKey
	 * @param waitTime
	 * @param expire
	 * @param unit
	 * @return
	 */
	public boolean tryLock(String lockKey, long waitTime, long expire, TimeUnit unit) {
		try {
			return redisson.getFairLock(lockKey).tryLock(waitTime, expire, unit);
		} catch (InterruptedException e) {
			log.error(Utility.getTraceString(e));
		}
		return false;
	}

	/**
	 * @Title releaseLock
	 * @Description 释放分布式锁
	 * @param lockKey 锁名
	 * @return void
	 */
	public void releaseLock(String lockKey) {
		redisson.getFairLock(lockKey).unlock();
		// redisLog.info("releaseLock key={},thread={},time={}", lockKey,
		// Thread.currentThread().getName(),
		// System.currentTimeMillis());
	}

	/**
	 * 异步释放掉锁
	 * @param lockKey
	 */
	public void releaseLockAsync(String lockKey) {
		redisson.getFairLock(lockKey).unlockAsync();
		// redisLog.info("releaseLock key={},thread={},time={}", lockKey,
		// Thread.currentThread().getName(),
		// System.currentTimeMillis());
	}

	/**
	 * 获取KEY 不反序列化
	 */
	public Object get(String key) {
		try {
			return redisson.getBucket(buildKey(key)).get();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 设置KEY
	 */
	public void set(String key, Object value) {
		try {
			redisson.getBucket(buildKey(key)).setAsync(value);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public void set(String key, Object value, long timeToLive, TimeUnit timeUnit) {
		try {
			redisson.getBucket(buildKey(key)).setAsync(value, timeToLive, timeUnit);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除KEY
	 */
	public void delete(String key) {
		try {
			redisson.getBucket(buildKey(key)).delete();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除key
	 * @param key
	 */
	public void deleteByKey(String key) {
		try {
			redisson.getBucket(key).delete();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 批量删除key
	 * @param keys
	 */
//	public void mulDeleteKeys(String[] keys) {
//		try {
//			if (keys.length > 0) {
//				RBatch batch = redisson.createBatch();
//
//				for (int i = 0; i < keys.length; i++) {
//					String key = keys[i];
//
//					batch.getBucket(buildKey(key)).deleteAsync();
//				}
//
//				batch.execute();
//			}
//		} catch (Exception e) {
//			redisLog.error(Utility.getTraceString(e));
//			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
//		}
//	}

	/**
	 * 是否存在KEY
	 */
	public boolean exits(String key) {
		try {
			RFuture<Boolean> future = redisson.getBucket(buildKey(key)).isExistsAsync();
			return future.get(250, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 设置key过期时间
	 */
	public void expire(String key, long period, TimeUnit timeUnit) {
		try {
			redisson.getBucket(buildKey(key)).expireAsync(period, timeUnit);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 重命名某个key
	 * @param oldKey
	 * @param newKey
	 */
	public void rename(String oldKey, String newKey) {
		try {
			redisson.getBucket(buildKey(oldKey)).rename(buildKey(newKey));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 自增KEY
	 */
	public Long increment(String key) {
		try {
			return redisson.getAtomicLong(buildKey(key)).incrementAndGet();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 自增KEY int
	 */
	public Long incrby(String key, int delta) {
		try {
			return redisson.getAtomicLong(buildKey(key)).addAndGet(delta);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 自增KEY long
	 */
	public Long incrby(String key, long delta) {
		try {
			return redisson.getAtomicLong(buildKey(key)).addAndGet(delta);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 自增KEY long
	 */
	public void incrbyASync(String key, long delta) {
		try {
			redisson.getAtomicLong(buildKey(key)).addAndGetAsync(delta);// .addAndGet(delta);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * setsync
	 */
	public void setAtomicLongAsync(String key, long newValue) {
		try {
			redisson.getAtomicLong(buildKey(key)).setAsync(newValue);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取某自增key的当前值
	 * @param key
	 * @return
	 */
	public Long getIncrementValue(String key) {
		try {
			return redisson.getAtomicLong(buildKey(key)).get();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 设置新值 返回老值
	 * @param key
	 * @param newValue
	 * @return
	 */
	public Long getAndSetAtomicLongValue(String key, long newValue) {
		try {
			return redisson.getAtomicLong(buildKey(key)).getAndSet(newValue);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 批量获取某自增key的当前值 不建议在集群环境使用批处理获取方法
	 * @param keys
	 * @return
	 */
//	public List<?> multiGetIncrementValue(String[] keys) {
//		try {
//			if (keys.length > 0) {
//				RBatch batch = redisson.createBatch();
//
//				for (int i = 0; i < keys.length; i++) {
//					String key = keys[i];
//
//					batch.getAtomicLong(buildKey(key)).getAsync();
//				}
//
//				return batch.execute();
//			}
//			return null;
//		} catch (Exception e) {
//			redisLog.error(Utility.getTraceString(e));
//			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
//		}
//	}

	public void listAdd(String key, Object value) {
		try {
			redisson.getList(buildKey(key)).add(value);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public Object listGet(String key, int index) {
		try {
			RList<Object> list = redisson.getList(buildKey(key));
			if (list == null || list.size() < index + 1) {
				return null;
			}
			return list.get(index);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public <V> List<V> listGet(String key, int start, int end) {
		try {
			RList<V> list = redisson.getList(buildKey(key));
			if (list == null) {
				return null;
			}
			int size = list.size();
			return list.subList(start, Math.min(end, size));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public int listSize(String key) {
		try {
			RList<Object> list = redisson.getList(buildKey(key));
			if (list == null) {
				return 0;
			}
			return list.size();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public List<Object> getList(String key) {
		try {
			RList<Object> list = redisson.getList(buildKey(key));
			if (list == null) {
				return null;
			}
			return list.stream().collect(Collectors.toList());
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public boolean listRemove(String key, Collection<?> values) {
		try {
			RList<Object> list = redisson.getList(buildKey(key));
			if (list != null) {
				return list.removeAll(values);
			}
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
		return false;
	}

	/**
	 * 设置HASH表的值 不序列化
	 */
	public void hashSet(String key, Object field, Object value) {
		try {
			redisson.getMap(buildKey(key)).put(field, value);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取HASH表的值 不反序列化
	 */
	public Object hashGet(String key, Object field) {
		try {
			return redisson.getMap(buildKey(key)).get(field);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public Long hashGetLong(String key, Object field) {
		Object o = hashGet(key, field);
		return o == null ? 0 : Long.parseLong(o.toString());
	}

	public Map<String, Object> hashGetAll(String key, Set<String> fieldSet) {
		try {
			RMap<String, Object> map = redisson.getMap(buildKey(key));
			return map.getAll(fieldSet);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * hash 添加并返回 异步
	 * @param key
	 * @param field
	 * @param delta
	 */
	public void hashAddAndGetASync(String key, Object field, Long delta) {
		try {
			redisson.getMap(buildKey(key)).addAndGetAsync(field, delta);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 一次性写入map中全部field
	 * @param key
	 * @param map
	 */
	public void hashAddMap(String key, Map<String, Long> map) {
		redisson.getMap(buildKey(key)).putAllAsync(map);
	}

	public void multiplyHashAdd(String key, Map<String, Long> finalMap) {
		RBatch batch = redisson.createBatch();

		for (Map.Entry<String, Long> entry : finalMap.entrySet()) {
			batch.getMap(buildKey(key)).addAndGetAsync(entry.getKey(), entry.getValue());
		}

		batch.execute();
	}

	/**
	 * hash 添加并返回 同步
	 * @param key
	 * @param field
	 * @param delta
	 */
	public long hashAddAndGetSync(String key, Object field, Long delta) {
		try {
			Object obj = redisson.getMap(buildKey(key)).addAndGet(field, delta);
			if (obj != null) {
				return Long.parseLong(obj + "");
			}
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
		return 0l;
	}

	/**
	 * 移除HASH表的值
	 */
	public void hashDelete(String key, Object field) {
		try {
			redisson.getMap(buildKey(key)).remove(field);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 移除hash表的值并返回
	 * @param key
	 * @param field
	 * @return
	 */
	public Object hashDeleteWithReturn(String key, Object field) {
		try {
			return redisson.getMap(buildKey(key)).remove(field);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	public Map<Long, Long> hashGetAll2Long(String key) {
		try {
			RMap<Long, Long> rMap = redisson.getMap(buildKey(key), LongCodec.INSTANCE);
			return rMap.readAllMap();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取HASH表的所有的键和值 不反序列化
	 */
	public Map<Object, Object> hashGetAll(String key) {
		try {
			return redisson.getMap(buildKey(key)).readAllMap();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 批量获取HASH表的键和值 不反序列化
	 */
	public Map<Object, Object> hashMultiGet(String key, Set<Object> fields) {
		if (fields.isEmpty())
			return Maps.newHashMap();
		try {
			return redisson.getMap(buildKey(key)).getAll(fields);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 批量设置HASH表的键和值 不序列化
	 */
	public void hashMultiSet(String key, Map<Object, Object> keyValues) {
		try {
			redisson.getMap(buildKey(key)).putAllAsync(keyValues);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * HASH表中是否存在key
	 */
	public boolean hashExists(String key, Object field) {
		try {
			return redisson.getMap(buildKey(key)).containsKey(field);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * HASH表中field数
	 */
	public long hashLength(String key) {
		try {
			return redisson.getMap(buildKey(key)).size();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * @Title keys
	 * @Description Get all keys by pattern using iterator. Keys traversed with SCAN operation. Each SCAN operation loads up to count keys per request.
	 *              Supported glob-style patterns: h?llo subscribes to hello, hallo and hxllo h*llo subscribes to hllo and heeeello h[ae]llo subscribes to hello
	 *              and hallo, but not hillo
	 * @param pattern - match pattern
	 * @return TreeSet<String>
	 */
	public List<String> keys(String pattern) {
		try {
			RKeys keys = redisson.getKeys();
			int totalCount = (int) keys.count();
			int perCount = totalCount > 10000 ? (int) (keys.count() / 10) : totalCount;
			return Lists.newArrayList(redisson.getKeys().getKeysByPattern(buildKey(pattern), perCount));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * @Title keys
	 * @Description Get all keys by pattern using iterator. Keys traversed with SCAN operation. Each SCAN operation loads up to count keys per request.
	 *              Supported glob-style patterns: h?llo subscribes to hello, hallo and hxllo h*llo subscribes to hllo and heeeello h[ae]llo subscribes to hello
	 *              and hallo, but not hillo
	 * @param pattern - match pattern
	 * @return TreeSet<String>
	 */
	public List<String> keys(String pattern, int count) {
		try {
			return Lists.newArrayList(redisson.getKeys().getKeysByPattern(buildKey(pattern), count));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 设置用户分数
	 */
	public void zAdd(String key, double score, Object member) {
		try {
			redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).addAsync(score, member);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 设置用户分数
	 */
	public void zAdd(String key, Map<Object, Double> scoreMembers) {
		try {
			redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).addAllAsync(scoreMembers);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 用户分数自增
	 */
	public double zIncrement(String key, double scoreIncr, Object member) {
		try {
			return redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).addScore(member, scoreIncr);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 移除用户
	 */
	public void zRemove(String key, Object member) {
		try {
			redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).removeAsync(member);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 移除用户
	 */
	public void zRemove(String key, Collection<?> members) {
		try {
			redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).removeAllAsync(members);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 根据排行移除用户
	 */
	public void zRemoveByRank(String key, int start, int end) {
		try {
			redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).removeRangeByRankAsync(start, end);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 升序列表 value long 解码
	 */
	public List<ScoredEntry<?>> zRangeAsc(String key, int start, int end) {
		try {
			return Lists.newArrayList(redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).entryRange(start, end));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 升序列表 value string 解码
	 */
	public List<ScoredEntry<?>> zRangeAscString(String key, int start, int end) {
		try {
			return Lists.newArrayList(redisson.getScoredSortedSet(buildKey(key), StringCodec.INSTANCE).entryRange(start, end));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 降序列表
	 */
	public List<ScoredEntry<?>> zRangeDesc(String key, int start, int end) {
		try {
			return Lists.newArrayList(redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).entryRangeReversed(start, end));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 降序列表 value string 解码
	 */
	public List<ScoredEntry<?>> zRangeDescString(String key, int start, int end) {
		try {
			return Lists.newArrayList(redisson.getScoredSortedSet(buildKey(key), StringCodec.INSTANCE).entryRangeReversed(start, end));
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 获取某用户排行 升序 无则返回null
	 */
	public Integer zRankAsc(String key, Object member) {
		try {
			return redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).rank(member);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 获取某用户排行 降序 无则返回null
	 */
	public Integer zRankDesc(String key, Object member) {
		try {
			return redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).revRank(member);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 获取某用户分数 无则返回null
	 */
	public Double zScore(String key, Object member) {
		try {
			return redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).getScore(member);
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 有序集合 获取排行榜内用户总数
	 */
	public Integer zCard(String key) {
		try {
			return redisson.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).size();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

//	/**
//	 * 批量获取排行榜分数
//	 * @param rankKeys
//	 * @param member
//	 * @return
//	 */
//	public List<?> multiGetRankScore(String[] rankKeys, Object member) {
//		try {
//			if (rankKeys.length > 0) {
//				RBatch batch = redisson.createBatch();
//
//				for (int i = 0; i < rankKeys.length; i++) {
//					String key = rankKeys[i];
//
//					batch.getScoredSortedSet(buildKey(key), LongCodec.INSTANCE).getScoreAsync(member);
//				}
//
//				return batch.execute();
//			}
//			return null;
//		} catch (Exception e) {
//			redisLog.error(Utility.getTraceString(e));
//			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
//		}
//	}
	/**
	 * 存储单个对象
	 */
	public void setObject(String key, Object value) {
		try {
			redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE).setAsync(serialize(value));
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 存储单个对象
	 */
	public void setObjectSync(String key, Object value) {
		try {
			redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE).set((serialize(value)));
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 存储单个对象-有超时时间-秒
	 */
	public void setObject(String key, Object value, int expire, TimeUnit unit) {
		try {
			redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE).setAsync(serialize(value), expire, unit);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 存储列表对象
	 */
	public void setObjectList(String key, List<?> value) {
		try {
			if (value == null || value.isEmpty()) {
				return;
			}
			redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE).setAsync(serializeList(value));
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 存储多个对象 事务 objectMap:类名=>model
	 */
	public void setObjectMap(HashMap<String, Object> objectMap) {
		if (!objectMap.isEmpty()) {
			try {
				RBatch batch = redisson.createBatch();
				for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
					batch.getBucket(buildKey(entry.getKey()), ByteArrayCodec.INSTANCE).setAsync(serialize(entry.getValue()));
				}
				batch.execute();
			} catch (Exception e) {
				redisLog.error(Utility.getTraceString(e));
				throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
			} finally {
				objectMap.clear();
				// PoolableObjectFactory.getInstance().returnObject(objectMap);
			}
		}
	}

	/**
	 * 获取单个对象
	 */
	public <T> T getObject(String key, Class<T> clas) {
		T obj = null;
		try {
			RBucket<byte[]> bucket = redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE);
			obj = unserialize(bucket.get(), clas);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
		return obj;
	}

	/**
	 * 获取单个对象
	 */
	public <T> T getObjectNoBuild(String key, Class<T> clas) {
		T obj = null;
		try {
			RBucket<byte[]> bucket = redisson.getBucket(key, ByteArrayCodec.INSTANCE);
			obj = unserialize(bucket.get(), clas);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
		return obj;
	}

	/**
	 * 获取列表对象
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectList(String key, Class<T> clas) {
		T obj = null;
		try {
			RBucket<byte[]> bucket = redisson.getBucket(buildKey(key), ByteArrayCodec.INSTANCE);
			obj = unserializeList(bucket.get(), clas);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
		return (List<T>) obj;
	}

	/**
	 * 添加对象到set集合
	 * @param key
	 * @param obj
	 * @return
	 */
	public void setObjectSet(String key, Object obj) {
		try {
			redisson.getSet(buildKey(key)).add(obj);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取set集合所有对象
	 * @param key
	 * @return
	 */
	public Set<Object> getObjectSet(String key) {
		try {
			return redisson.getSet(buildKey(key)).readAll();
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 移除对象从set集合
	 * @param key
	 * @param obj
	 * @return
	 */
	public void removeObjectSet(String key, Object obj) {
		try {
			redisson.getSet(buildKey(key)).remove(obj);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 获取set集合大小
	 * @param key
	 * @return
	 */
	public int sizeObjectSet(String key) {
		try {
			return redisson.getSet(buildKey(key)).size();
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除set集合
	 * @param key
	 */
	public void delObjectSet(String key) {
		try {
			redisson.getSet(buildKey(key)).delete();
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 批量删除排行榜数据
	 * @param key
	 * @param fields
	 */
	public void zBatchRemoveField(String key, List<Long> fields) {
		try {
			RBatch batch = redisson.createBatch();
			for (Long field : fields) {
				batch.getScoredSortedSet(key, LongCodec.INSTANCE).removeAsync(field);
			}
			batch.execute();
		} catch (Exception e) {
			redisLog.error(Utility.getTraceString(e));
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 删除set集合
	 * @param key
	 */
	public void delObjectSetByKey(String key) {
		try {
			redisson.getSet(key).delete();
		} catch (Exception e) {
			throw new CommonException(ErrorCode.SYSTEM_REDIS_ERROR, e.getMessage());
		}
	}

	/**
	 * 序列化对象
	 * @throws IOException
	 */
	public byte[] serialize(Object obj) {
		if (PROTOSTUFF_SERIALIZE_FALG) {
			// return ProtoStuffUtil.serialize(obj);
		}
		return ZLibUtil.compress(JSON.toJSONBytes(obj, SerializerFeature.WriteClassName));
	}

	/**
	 * 序列化列表对象
	 */
	private byte[] serializeList(List<?> obj) {
		if (PROTOSTUFF_SERIALIZE_FALG) {
			// return ProtoStuffUtil.serializeList(obj);
		}
		return ZLibUtil.compress(JSON.toJSONBytes(obj, SerializerFeature.WriteClassName));
	}

	/**
	 * 反序列化对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unserialize(byte[] objBytes, Class<T> clas) {
		if (objBytes == null)
			return null;
		if (PROTOSTUFF_SERIALIZE_FALG) {
			// return (T) ProtoStuffUtil.deserialize(objBytes, clas);
		}
		return (T) JSON.parseObject(ZLibUtil.decompress(objBytes), clas);
	}

	/**
	 * 反序列化列表对象
	 */
	@SuppressWarnings("unchecked")
	private <T> T unserializeList(byte[] objBytes, Class<T> clas) {
		if (objBytes == null)
			return null;
		if (PROTOSTUFF_SERIALIZE_FALG) {
			// return (T) ProtoStuffUtil.deserializeList(objBytes, clas);
		}
		return (T) JSON.parseObject(ZLibUtil.decompress(objBytes), clas);
	}

}
