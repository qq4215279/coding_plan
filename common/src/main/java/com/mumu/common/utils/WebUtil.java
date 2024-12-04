/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reign.framework.log.InternalLoggerFactory;
import com.reign.framework.log.Logger;
import com.reign.sbtj.common.Configuration;
import com.reign.sbtj.common.Constants;
import com.reign.sbtj.common.LocalMessages;
import com.reign.sbtj.common.data.IDataGetter;
import com.reign.sbtj.common.data.PlayerDataManager;
import com.reign.sbtj.player.domain.PlayerSecret;
import com.reign.util.datetime.TimeConstants;
import com.reign.util.encr.Algorithms;
import com.reign.util.encr.Base64;
import com.reign.util.encr.CodecUtil;
import com.reign.util.random.RandomUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.crypto.Cipher;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.Key;
import java.security.Provider;
import java.security.Security;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.Inflater;

/**
 * J2ee工具类
 * @author wangys
 *
 * 2011-3-10 下午06:00:42
 * 2011-05-19 add by Blade 添加是否需要用户激活
 * 
 */
public final class WebUtil {	
	/** log */
	private static final Logger log = InternalLoggerFactory.getLogger(WebUtil.class);
	
	/**
	 * 构造函数
	 */
	private WebUtil() { }
	
	/**
	 * 检查是否有空参数
	 * @param json
	 * @param args
	 * @return
	 * $Date: 2015年3月8日 下午2:15:50
	 */
	public static String getEmptyStr(JSONObject json, String... args) {
		for (String arg : args) {
			if (StringUtils.isBlank(json.getString(arg))) {
				return arg;
			}
		}
		return "";
	}
	
	/**
	 * 获得总值
	 * @param array
	 * @return
	 * $Date: 2015年9月17日 下午4:24:32
	 */
	public static int getTotal(int[] array) {
		int total = 0;
		for (int i : array) {
			total += i;
		}
		return total;
	}
	
	/**
	 * 获得运营标识
	 * @return
	 * $Date: 2011-3-18 上午11:18:06
	 */
	public static String getYxFlag() {
		return Configuration.getProperty(Configuration.YX_FLAG);
	}
	
	/**
	 * 某个字符串中是否存在指定参数
	 * @param str
	 * @param args
	 * @return
	 * $Date: 2015年7月16日 下午5:24:55
	 */
	public static boolean contain(String str, String... args) {
		for (String arg : args) {
			if (!str.contains(arg)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获得参数
	 * @param expression--a=?;b=?;c=?
	 * @return
	 * $Date: 2015年7月16日 下午5:31:13
	 */
	public static Map<String, String> getParamMap(String expression) {
		Map<String, String> result = new HashMap<>();
		String[] array = expression.split("&");
		for (String info : array) {
			int index = info.indexOf("=");
			if (index == -1) {
				result.put(info, "");
			} else {
				result.put(info.substring(0, index), info.substring(index + 1));
			}
			
		}
		return result;
	}
		
	/**
	 * 打印异常
	 * @param log
	 * @param t
	 * $Date: 2011-4-14 下午04:52:19
	 */
	public static void printInvocationTargetException(Logger log, Throwable t) {
		if (t instanceof InvocationTargetException) {
			InvocationTargetException invocationTargetException = (InvocationTargetException) t;
			log.error(t.getMessage(), invocationTargetException.getTargetException());
			printInvocationTargetException(log, invocationTargetException.getTargetException());
		}
	}
	
	/**
	 * 打印异常
	 * @param log
	 * @param t
	 * $Date: 2011-4-14 下午04:52:19
	 */
	public static void print(Logger log, String msg, Throwable t) {
		if (t instanceof InvocationTargetException) {
			InvocationTargetException invocationTargetException = (InvocationTargetException) t;
			log.error(msg, invocationTargetException.getTargetException());
			printInvocationTargetException(log, invocationTargetException.getTargetException());
		} else {
			log.error(msg, t);
		}
	}
	
	
	/**
	 * 打印异常
	 * @param log
	 * @param t
	 * $Date: 2011-4-14 下午04:52:19
	 */
	public static void print(Logger log, Throwable t) {
		if (t instanceof InvocationTargetException) {
			InvocationTargetException invocationTargetException = (InvocationTargetException) t;
			log.error("", invocationTargetException.getTargetException());
			printInvocationTargetException(log, invocationTargetException.getTargetException());
		} else {
			log.error("", t);
		}
	}
		
	/**
	 * 获取验证的错误提示
	 * @param result
	 * @param length
	 * @return
	 * $Date: 2011-5-19 下午12:06:05
	 */
	public static String getValidateMsg(int result, int length) {
		switch (result) {
		case 1:
			return LocalMessages.getText("T_VALIDATE_NO_INPUT");
		case 2:
			return LocalMessages.getText("T_VALIDATE_LEN_LIMIT", length);
		case 3:
			return LocalMessages.getText("T_VALIDATE_CHARACTER_SET");
		case 4:
			return LocalMessages.getText("T_VALIDATE_FILTER");
		case 5:
			return LocalMessages.getText("T_VALIDATE_FILTER_NOT_FOUND");
		default:
			break;
		}
		return "";
	}
	
	/**
	 * 判断是否是单角色
	 * @return
	 * $Date: 2011-7-26 上午09:56:37
	 */
	public static boolean isSingleRole() {
		return getMaxPlayerNum() <= 1;
	}
	
	/**
	 * 允许最大的角色数
	 * @return
	 * $Date: 2012-4-10 上午11:13:18
	 */
	public static int getMaxPlayerNum() {
		return Integer.parseInt(Configuration.getProperty(Configuration.PLAYER_MAX_ROLE_NUM));
	}
	
	/**
	 * GM指令是否可用
	 * @return
	 * $Date: 2011-5-21 下午05:33:26
	 */
	public static boolean gmEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.GM_ENABLE));
	}

	/**
	 * 是否是脱机模式（不访问非本地服务功能）
	 * @return
	 */
	public static boolean standalone() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.STANDALONE));
	}

	/**
	 * 是否需要连接GW
	 * @return
	 */
	public static boolean connectToGw() {
		String flag = Configuration.getProperty(Configuration.CONNECT_TO_GW);
		if (StringUtils.isBlank(flag)) {
            return true;
        }
		return "1".equalsIgnoreCase(flag);
	}

	/**
	 * RSA加密验证是否开启
	 * @return
	 */
	public static boolean RsaEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.RSA_ENABLE));
	}
	
	/**
	 * 测试模式是否开启
	 * @return
	 * $Date: 2016年11月16日 下午7:54:34
	 */
	public static boolean testModeEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.TEST_MODE));
	}

	/**
	 * 单机模式是否开启
	 * @return
	 * $Date: 2016年11月16日 下午7:54:34
	 */
	public static boolean localModeEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.LOCAL_MODE));
	}

	/**
	 * 推送合并是否开启
	 * @return
	 * $Date: 2016年11月16日 下午7:54:34
	 */
	public static boolean pushMergeEnable() {
		return !"1".equalsIgnoreCase(Configuration.getProperty(Configuration.PUSH_MERGE));
	}
	
	/**
	 * 提审是否开启
	 * @return
	 * $Date: 2016年7月7日 下午2:02:05
	 */
	public static boolean reviewEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.REWIEW_OPEN));
	}
	
	/**
	 * 是否开启游戏时间检查
	 * @param yxSource 
	 * @param yx 
	 * @return
	 * $Date: 2015年11月3日 下午3:36:16
	 */
	public static boolean checkGameTimeEnable(String yx, String yxSource) {
        String checkYx = Configuration.getProperty(Configuration.GAME_CHECK_GAME_TIME_YX);
        if (StringUtils.isNotBlank(checkYx)
                && (checkYx.contains(yx) || (StringUtils.isNotBlank(yxSource) && checkYx.contains(yxSource)))) {
            return true;
        }
        return false;
	}
	
	/**
	 * 检查充值等级限制
     * @param defaultValue 
     * @return
     * $Date: 2016年2月25日 下午4:19:09
     */
    public static int checkPayLvLimit(int defaultValue) {
        String value = Configuration.getProperty(Configuration.GAME_CHECK_PAY_LVLIMIT);
        if (StringUtils.isNotBlank(value) && StringUtils.isNumeric(value)) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }
	
	/**
	 * 加密是否开启
	 * @return
	 * $Date: 2015年7月24日 下午6:04:51
	 */
	public static boolean aesEnable() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.AES_OPEN));
	}
	
	/**
     * 是否可以注册
     * @return
     * $Date: 2011-5-21 下午05:33:26
     */
    public static boolean canRegister(String ip) {
        boolean limit = "1".equalsIgnoreCase(Configuration.getProperty(Configuration.REGISTER_LIMIT));
        if (limit) {
            String passIp = Configuration.getProperty(Configuration.REGISTER_IP);
            return null != passIp && passIp.contains(ip);
        }
        return true;
    }
    
    /**
     * 获得数组
     * @param list
     * @return
     * $Date: 2015年5月12日 下午8:23:52
     */
    public static int[] toArray(List<Integer> list) {
    	if (list == null || list.isEmpty()) {
    		return new int[0];
    	}
    	
    	int[] array = new int[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		array[i] = list.get(i);
    	}
    	return array;
    }
    
    /**
     * 获取注册有效期
     * @return
     * $Date: 2015年5月6日 下午1:38:55
     */
    public static int registerValidDay() {
        boolean limit = "1".equalsIgnoreCase(Configuration.getProperty(Configuration.REGISTER_LIMIT));
        if (!limit) {
            return -1;
        }
        String day = Configuration.getProperty(Configuration.REGISTER_VALID_DAY);
        if (StringUtils.isBlank(day)) {
            return 7;
        } else {
            return Integer.valueOf(day);
        }
    }

	/**
	 * 拷贝数组
	 * @param poss
	 * @return
	 * $Date: 2012-5-25 下午01:48:05
	 */
	public static int[] copyArray(int[] poss) {
		int[] copy = new int[poss.length];

		System.arraycopy(poss, 0, copy, 0, poss.length);
		return copy;
	}
	
	/**
	 * 一个数组表达式中是否包含一个值
	 * @param key
	 * @return 数组下标，无则返回-1
	 * $Date: 2014年11月19日 下午2:34:34
	 */
	public static int contains(int[] array, int key) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == key) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * array是否包含 subArray
	 * @param array
	 * @param subArray
	 * @return
	 */
	public static boolean contains(int[] array, int[] subArray) {
		for (int v : subArray) {
			if (contains(array, v) == -1) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 一个数组表达式中是否包含一个值
	 * @param array
	 * @param key
	 * @return
	 * $Date: 2015年7月7日 下午5:10:47
	 */
	public static int contains(String[] array, String key) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] != null && array[i].equals(key)) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 一个数组表达式中是否包含一个值
	 * @param array
	 * @param key
	 * @return
	 * $Date: 2015年7月7日 下午5:10:47
	 */
	public static boolean has(String[] array, String key) {
		return contains(array, key) > -1;
	}
	
	/**
	 * 指定位置更新字符串,默认‘,’分割
	 * @param str
	 * @param pos
	 * @param value
	 * @return
	 * $Date: 2016年12月27日 下午1:18:05
	 */
	public static String getString(String str, int pos, int value) {
		return getString(str, pos, value, ",");
	}
	
	/**
	 * 更新是定位置值
	 * @param str
	 * @param pos
	 * @param value
	 * @param comma
	 * @return
	 * $Date: 2016年12月27日 下午1:18:08
	 */
	public static String getString(String str, int pos, int value, String comma) {
		int[] arr = WebUtil.getArray(str, comma, 0);
		if (arr.length < pos) {
			int[] tmp = new int[pos];
			System.arraycopy(arr, 0, tmp, 0, arr.length);
			arr = tmp;
		}
		arr[pos - 1] = value;
		return WebUtil.getString(arr, comma);
	}
	
	/**
	 * 获取指定位置值。默认‘,’分割
	 * @param str
	 * @param pos
	 * @return
	 * $Date: 2016年12月27日 下午1:24:15
	 */
	public static int getValue(String str, int pos) {
		return getValue(str, pos, ",");
	}
	
	/**
	 * 更新指定位置值
	 * @param str
	 * @param pos
	 * @param comma
	 * @return
	 * $Date: 2016年12月27日 下午1:24:53
	 */
	public static int getValue(String str, int pos, String comma){
		int[] arr = WebUtil.getArray(str, comma, 0);
		if (arr.length < pos) {
			return 0;
		}
		return arr[pos - 1];
	}
	
	/**
	 * 获得数组
	 * @param str
	 * @return
	 * $Date: 2014年9月22日 下午6:25:37
	 */
	public static int[] getArray(String str) {
		return getArray(str, ",");
	}
	
	/**
     * 获得数组
     * @param str
     * @return
     * $Date: 2014年9月22日 下午6:25:37
     */
    public static double[] getDoubleArray(String str) {
        return getDoubleArray(str, ",");
    }
	
	/**
	 * 获得数组
	 * @param str
	 * @param comma
	 * @return
	 * $Date: 2014年9月22日 下午6:26:03
	 */
	public static int[] getArray(String str, String comma) {
		if (null == str || StringUtils.isBlank(str)) {
			return new int[0];
		}
		
		String[] strs = str.trim().split(comma);
		int[] array = new int[strs.length];
		int i = 0;
		for (String line : strs) {
			if (StringUtils.isBlank(line)) {
				continue;
			}
			
			array[i++] = Integer.valueOf(line);
		}
		return array;
	}
	
	/**
	 * 获得数组，若为空则赋值nullValue
	 * @param str
	 * @param comma
	 * @return
	 * $Date: 2016年12月15日 上午11:51:41
	 */
	public static int[] getArray(String str, String comma, int nullValue) {
		if (null == str || StringUtils.isBlank(str)) {
			return new int[0];
		}
		
		String[] strs = str.trim().split(comma);
		int[] array = new int[strs.length];
		int i = 0;
		for (String line : strs) {
			if (StringUtils.isBlank(line)) {
				array[i++] = nullValue;
			} else {
				array[i++] = Integer.valueOf(line);
			}
		}
		return array;
	}
	
	/**
     * 获得数组
     * @param str
     * @param comma
     * @return
     * $Date: 2014年9月22日 下午6:26:03
     */
    public static double[] getDoubleArray(String str, String comma) {
        if (null == str || StringUtils.isBlank(str)) {
            return new double[0];
        }
        
        String[] strs = str.trim().split(comma);
        double[] array = new double[strs.length];
        int i = 0;
        for (String line : strs) {
            array[i++] = Double.valueOf(line);
        }
        return array;
    }
	
	/**
	 * 获得列表
	 * @param str
	 * @return
	 * $Date: 2014年12月3日 上午10:31:38
	 */
	public static List<Integer> getList(String str) {
		return getList(str, ",");
	}
	
	/**
	 * 获得列表
	 * @param str
	 * @param comma
	 * @return
	 * $Date: 2014年12月3日 上午10:31:09
	 */
	public static List<Integer> getList(String str, String comma) {
		if (null == str || StringUtils.isBlank(str)) {
			return new ArrayList<>();
		}

		String[] strs = str.trim().split(comma);
		List<Integer> list = new ArrayList<>();
		for (String line : strs) {
			list.add(Integer.valueOf(line));
		}

		return list;
	}

	/**
	 * 获得集合
	 * @param str
	 * @param comma
	 * @return
	 */
	public static Set<Integer> getSet(String str, String comma) {
		if (null == str || StringUtils.isBlank(str)) {
			return new HashSet<>();
		}

		String[] strs = str.trim().split(comma);
		Set<Integer> set = new HashSet<>();
		for (String line : strs) {
			set.add(Integer.valueOf(line));
		}

		return set;
	}

	/**
	 * array转set
	 * @param arr
	 * @return
	 */
	public static Set<Integer> getSet(int[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			return Collections.EMPTY_SET;
		}
		Set<Integer> set = new HashSet<>();
		for (int v : arr) {
			set.add(v);
		}
		return set;
	}
	
	/**
	 * 获得Map，相同key值仅返回一条记录
	 * @param str
	 * @return
	 * $Date: 2016年8月8日 下午7:59:49
	 */
	public static Map<String, String> getMap(String str) {
		return getMap(str, ",");
	}
	
	public static Map<String, String> getMap(String str, String comma) {
		if (null == str || StringUtils.isBlank(str)) {
			return new HashMap<>();
		}
		
		String[] strs = str.trim().split(comma);
		Map<String, String> map = new HashMap<>(strs.length);
		for (String line : strs) {
			map.put(line, "");
		}
		return map;
	}
	
	/**
	 * 获得列表
	 * @param str
	 * @param comma
	 * @return
	 * $Date: 2014年12月3日 上午10:31:09
	 */
	public static List<String> getStrList(String str, String comma) {
		if (null == str || StringUtils.isBlank(str)) {
			return new ArrayList<>();
		}
		
		String[] strs = str.trim().split(comma);
		List<String> list = new ArrayList<>();
		Collections.addAll(list, strs);
		
		return list;
	}
	
	/**
	 * 获取表达式，以，号连接
	 * @param array
	 * @return
	 * $Date: 2014年11月19日 下午2:03:58
	 */
	public static String getString(int[] array) {
		return getString(array, ",");
	}

	/**
	 * 获取表达式，以，号连接
	 * @param array
	 * @return
	 * $Date: 2014年11月19日 下午2:03:58
	 */
	public static String getString(short[] array) {
		return getString(array, ",");
	}
	
	/**
	 * 获取表达式，以，号连接
	 * @param array
	 * @return
	 * $Date: 2014年11月19日 下午2:03:58
	 */
	public static String getString(double[] array) {
		return getString(array, ",");
	}
	
	/**
	 * 获取表达式
	 * @param array
	 * @param comma
	 * @return
	 * $Date: 2014年11月19日 下午2:03:34
	 */
	public static String getString(int[] array, String comma) {
		if (null == array || array.length == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (int value : array) {
			builder.append(value).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * 获取表达式
	 * @param array
	 * @param comma
	 * @return
	 * $Date: 2014年11月19日 下午2:03:34
	 */
	public static String getString(short[] array, String comma) {
		if (null == array || array.length == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (int value : array) {
			builder.append(value).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * 获取表达式
	 * @param array
	 * @param comma
	 * @return
	 * $Date: 2014年11月19日 下午2:03:34
	 */
	public static String getString(double[] array, String comma) {
		if (null == array || array.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (double value : array) {
			builder.append(value).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * @param array
	 * @param comma
	 * @return
	 * $Date: 2015年9月6日 下午7:03:44
	 */
	public static String getString(String[] array, String comma) {
		if (null == array || array.length == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (String value : array) {
			builder.append(value).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * 获取表达式，以，号连接
	 * @param array
	 * @return
	 * $Date: 2014年11月19日 下午2:03:58
	 */
	public static String getString(List<?> array) {
		return getString(array, ",");
	}
	
	/**
	 * 获取表达式
	 * @param list
	 * @param comma
	 * @return
	 * $Date: 2014年11月19日 下午2:03:34
	 */
	public static String getString(List<?> list, String comma) {
		if (null == list || list.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (Object value : list) {
			builder.append(value.toString()).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * 获取表达式
	 * @param list
	 * @param comma
	 * @return
	 */
	public static String getString(Set<?> list, String comma) {
		if (null == list || list.size() == 0) {
			return "";
		}

		StringBuilder builder = new StringBuilder();
		for (Object value : list) {
			builder.append(value.toString()).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}


	/**
	 * map转为String输出
	 * @param map
	 * @param comma1
	 * @param comma2
	 * @return
	 * $Date: 2017年7月5日 下午6:30:42
	 */
	public static String map2Str(Map<Integer, Integer> map, String comma1, String comma2) {
		if (map == null || map.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			builder.append(entry.getKey()).append(comma1).append(entry.getValue()).append(comma2);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * 获取表达式
	 * @param array
	 * @param comma
	 * @return
	 * $Date: 2014年11月19日 下午2:03:34
	 */
	public static String getStringByStrList(List<String> array, String comma) {
		if (null == array || array.size() == 0) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		for (String value : array) {
			builder.append(value).append(comma);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * 是否now的hour值处于[startHour, endHour)
	 * @param now
	 * @param startHour
	 * @param endHour
	 * @return
	 * $Date: 2014年11月30日 上午11:05:17
	 */
	public static boolean isIn(Calendar now, int startHour, int endHour) {
		int hour = now.get(Calendar.HOUR_OF_DAY);
		return (hour >= startHour) && (hour < endHour);
	}
	
	/**
	 * 随机数组
	 * 
	 * @param array
	 */
	public static void randomArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int index = RandomUtils.nextInt(array.length - i) + i;
			swap(array, i, index);
		}
	}

	/**
	 * 数组转换
	 * @param array
	 * @return
	 * $Date: 2015年5月5日 下午12:01:50
	 */
	public static int[] toIntArray(Integer[] array) {
	    int[] value = new int[array.length];
	    for (int i = 0; i < array.length; i++) {
	        value[i] = array[i];
	    }
	    return value;
	}
	
	/**
	 * 获取当天某个整点时间
	 * @param hour
	 * @return
	 * $Date: 2015年4月29日 下午3:29:50
	 */
	public static Calendar getHour(int hour) {
	    Calendar cg = Calendar.getInstance();
	    cg.set(Calendar.HOUR_OF_DAY, hour);
	    cg.set(Calendar.MINUTE, 0);
	    cg.set(Calendar.SECOND, 0);
	    cg.set(Calendar.MILLISECOND, 0);
	    
	    return cg;
	}
	
	/**
	 * 获得对应日期那一天的某个整点
	 * @param date
	 * @param hour
	 * @return
	 * $Date: 2015年7月20日 上午11:07:46
	 */
	public static Calendar getHour(Date date, int hour) {
		 Calendar cg = Calendar.getInstance();
		 cg.setTimeInMillis(date.getTime());
		 cg.set(Calendar.HOUR_OF_DAY, hour);
		 cg.set(Calendar.MINUTE, 0);
		 cg.set(Calendar.SECOND, 0);
		 cg.set(Calendar.MILLISECOND, 0);
		    
		 return cg;
	}
	
	/**
     * 扩充表达式
     * @param expression
     * @param str
     * @return
     * $Date: 2014年9月24日 下午7:46:23
     */
    public static String appendArray(String expression, String str) {
        if (StringUtils.isBlank(expression)) {
            return str;
        } else {
            return expression + "," + str;
        }
    }
	
	/**
	 * 交换数据
	 * 
	 * @param array
	 * @param index1
	 * @param index2
	 * $Date: 2012-2-9 下午03:59:36
	 */
	public static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	/**
	 * 返回列表
	 * @param array
	 * @return
	 * $Date: 2015年5月16日 下午4:00:43
	 */
	public static List<Integer> asList(int[] array) {
		List<Integer> list = new ArrayList<>();
		if (array != null) {
			for (int i : array) {
				list.add(i);
			}
		}
		return list;
	}
	
	/**
	 * aes加密(加密byte统一向左移8位)
	 * @param s
	 * @param keyStr
	 * @return
	 * $Date: 2015年7月24日 下午5:57:56
	 */
	public static byte[] aesEncrypt(String s, String keyStr) {
		try {
			byte[] keysData = keyStr.getBytes("utf-8");
			for (int i = 0; i < keysData.length; i++) {
				keysData[i] = (byte)(keysData[i] << (i % 8));
			}
            Security.addProvider(Security.getProvider("SunJCE"));
            // 创建一个空的8位字节数组（默认值为0）
            byte[] keys = new byte[16];
            byte[] data = s.getBytes();
            // 将原始字节数组转换为8位
            for (int i = 0; i < keysData.length && i < keys.length; i++) {
                keys[i] = keysData[i];
            }

            // 生成密钥
            Key key = new javax.crypto.spec.SecretKeySpec(keys, Algorithms.AES);

            Cipher encryptCipher = Cipher.getInstance(Algorithms.AES);
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            return encryptCipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("encrypt error", e);
        }
	}
	
	/**
	 * 获得aes请求加密参数
	 * @param playerId
	 * @param data
	 * @param needParam
	 * @return
	 * $Date: 2015年7月24日 下午6:27:12
	 */
	public static Map<String, String> getAesParamMap(int playerId, String data, String... needParam) {
		if (WebUtil.aesEnable()) {
			IDataGetter dataGetter = PlayerDataManager.getInstance().getDataGetter();
			PlayerSecret ps = dataGetter.getPlayerSecretManager().memoryRead(playerId);
			
			try {
				// 解密
				byte[] param = CodecUtil.aesDecrypt(ps.getSecretKey(), Base64.decode(data));
				if (needParam.length == 0) {
					return null;
				}
				String paramStr = new String(param, "utf-8");
				if (!WebUtil.contain(paramStr, needParam)) {
					return null;
				}
				Map<String, String> paramMap = WebUtil.getParamMap(paramStr);
				String tmpKey = paramMap.get("param");
				if (!ps.getTmpKey().equals(tmpKey)) {
					return null;
				}
				//重置加密变量
				ps.setTmpKey(""); 
				dataGetter.getPlayerSecretManager().memoryUpdate(ps);
				return paramMap;
			} catch (Throwable e) {
				log.error("aes error, param:{}#{}#{}#{}", e, playerId, ps.getSecretKey(), ps.getTmpKey(), data);
				return null;
			}
		} else {
			if (!WebUtil.contain(data, needParam)) {
				return null;
			}
			return WebUtil.getParamMap(data);
		}

	}
	
	/**
	 * 获得二个时间中间间隔的小时数未满1小时不算
	 * @param startDate
	 * @param endDate
	 * @return
	 * $Date: 2015年8月27日 下午4:07:23
	 */
	public static int getIntervalHourNum(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || endDate.before(startDate)) {
			return 0;
		}
		
		Calendar now = Calendar.getInstance();
		now.setTime(endDate);

		Calendar last = Calendar.getInstance();
		last.setTime(startDate);

		return (int)((now.getTimeInMillis() - last.getTimeInMillis()) / 3600000);
	}
	
	/**
	 * 获得2个日期间隔的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 * $Date: 2015年9月17日 下午3:29:04
	 */
	public static int getIntervalDayNum(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || endDate.before(startDate)) {
			return 0;
		}
		
		Calendar now = Calendar.getInstance();
		now.setTime(endDate);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		Calendar last = Calendar.getInstance();
		last.setTime(startDate);
		last.set(Calendar.HOUR_OF_DAY, 0);
		last.set(Calendar.MINUTE, 0);
		last.set(Calendar.SECOND, 0);
		last.set(Calendar.MILLISECOND, 0);

		return (int)((now.getTimeInMillis() - last.getTimeInMillis()) / TimeConstants.DAY_SEC);
	}
	
	/**
	 * 判断两个时间中是否间隔了某个时间
	 * @param startTime
	 * @param endTime
	 * @param time
	 * @return
	 * $Date: 2015年11月3日 下午1:41:41
	 */
	public static boolean isInterval(Date startTime, Date endTime, Date time) {	
		if (time.after(startTime) && endTime.after(time) && startTime.before(endTime)) {
			return true;
		}
		return false;
	}
	
	/**
     * base64之后解压缩
     * @param reportInfo
     * @return
     * $Date: 2015年10月20日 上午10:26:39
     */
    public static String base64AndUncompressReport(String reportInfo) {
        if (StringUtils.isBlank(reportInfo)) {
            return reportInfo;
        }
        
        try {
            // 解压缩
            byte[] bytes = Base64.decode(reportInfo);
            Inflater inflater = new Inflater();
            inflater.reset();
            inflater.setInput(bytes);
            
            byte[] buff = new byte[2048];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (!inflater.finished()) {
                int len = inflater.inflate(buff);
                bos.write(buff, 0, len);
            }
            reportInfo = new String(bos.toByteArray());
        } catch (Exception e) {
            // Ignore
            reportInfo = "";
        }
        
        return reportInfo;
    }

	/**
	 * 发送异步http请求
	 * @param content
	 * @return
	 * $Date: 2015年11月4日 下午5:34:55
	 */
	public static void postHttpRequestAsyn(final String urlStr, final String content) {
		postHttpRequestAsyn(urlStr, content, false);
	}
    
    /**
     * 发送异步http请求
     * @param content
     * @return
     * $Date: 2015年11月4日 下午5:34:55
     */
    public static void postHttpRequestAsyn(final String urlStr, final String content, boolean force) {
		if (!force && standalone()) {
			return;
		}
		Constants.threadPool.execute(() -> {
			URL url = null;
			HttpURLConnection connection = null;
			try {
				url = new URL(urlStr);
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				// 设置连接超时时间: 5s
				connection.setConnectTimeout(1000 * 5);
				// 设置读取超时时间: 5s
				connection.setReadTimeout(1000 * 5);

				// 发送POST数据
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
				out.write(content);
				out.flush();
				out.close();
				int code = connection.getResponseCode();
				log.debug("url:" + url + " content:" + content + " returnCode:" + code);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
	}

	/**
	 * 异步post请求
	 * @param urlStr
	 * @param jsonStr
	 */
	public static void postJsonRequestAsyn(final String urlStr, final String jsonStr, NoInputConsumer timeoutHandle) {
		if (standalone()) {
			return;
		}
		try {
			Constants.threadPool.execute(() -> {
				URL url = null;
				HttpURLConnection connection = null;
				try {
					url = new URL(urlStr);
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoOutput(true);
					// 设置连接超时时间: 5s
					connection.setConnectTimeout(1000 * 5);
					// 设置读取超时时间: 5s
					connection.setReadTimeout(1000 * 5);
					connection.addRequestProperty("Content-Type", "application/json");

					// 发送POST数据
					OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
					out.write(jsonStr);
					out.flush();
					out.close();
					int code = connection.getResponseCode();
					log.debug("url:" + url + " content:" + jsonStr + " returnCode:" + code);
				} catch (SocketTimeoutException e) {
					if (timeoutHandle != null) {
                        timeoutHandle.accept();
                    }
					log.error("timeout url:" + url + " content:" + jsonStr);
				} catch (Exception e) {
					log.error("error url:" + url + " content:" + jsonStr, e);
				}
			});
		} catch (Throwable t) {
			log.error("threadPool.execute error", t);
		}
	}

	/**
     * 当前实际版本号 >= 期待的版本号有效， 否则无效
     * @param exceptVersion 期待的版本号
     * @param currentVersion 当前实际版本号
     * @return
     * $Date: 2011-8-10 下午03:33:13
     */ 
    public static boolean isValidateComponent(final String exceptVersion, final String currentVersion) {
        if (StringUtils.isBlank(exceptVersion)) {
            return true;
        } else if (StringUtils.isBlank(currentVersion) && StringUtils.isNotBlank(exceptVersion)) {
            return false;
        } else if (StringUtils.isBlank(currentVersion) && StringUtils.isBlank(exceptVersion)) {
            return true;
        }
        if (exceptVersion.equalsIgnoreCase(currentVersion)) {
            // 版本号相等
            return true;
        }
        
        // 获取版本
        int[] exceptVersions = StringArrayToIntArray(exceptVersion.split("\\."));
        int[] currentVersions = StringArrayToIntArray(currentVersion.split("\\."));
        
        int len = Math.min(exceptVersions.length, currentVersions.length);
        for (int i = 0; i < len; i++) {
            if (currentVersions[i] < exceptVersions[i]) {
                // 当前版本小于期待版本
                return false;
            } else if (currentVersions[i] > exceptVersions[i]) {
                // 当前版本大于期待版本
                return true;
            }
        }
        
        if (len == currentVersions.length && len < exceptVersions.length) {
            // 当前版本小于期待版本(当前版本的版本位小于期待版本的版本位数)
            return false;
        }
        
        // 正常版本
        return true;
    }
    
    /**
	 * 远程聊天过滤是否开启
	 * @return
	 * $Date: 2015年9月10日 下午1:29:40
	 */
	public static boolean remoteFilterOpen() {
		return "1".equalsIgnoreCase(Configuration.getProperty(Configuration.CHAT_OPEN_REMOTE_FILTER));
	}
    
    /**
     * 字符串数组转换为整型数组
     * @param strs
     * @return
     * $Date: 2011-8-10 下午03:37:30
     */ 
    private static int[] StringArrayToIntArray(String[] strs) {
    	return StringArrayUtil.stringArray2IntArray(strs);
    }

	/**
	 * 获取匹配的下标
	 * @return
	 */
	public static int indexOf(int[] array, int value) {
		if (null == array || array.length == 0) {
			return -1;
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * 根据概率获取掉落下标
	 * @param probArray
	 * @return
	 */
	public static int getRandomIndex(double[] probArray) {
		double total = 0;
		for (int i = 0; i < probArray.length; i++) {
			total += probArray[i];
		}

		double prob = RandomUtils.nextDouble() * total;
		double value = 0;
		for (int i = 0; i < probArray.length; i++) {
			value += probArray[i];
			if (prob < value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据概率随机
	 * @param result 结果集
	 * @param weightList  概率集
	 * @return 结果集中的一个
	 */
	public static int weightRandom(List<Integer> result, List<Double> weightList) {
		// 总概率
		double sumRate = 0;
		for (Double rate : weightList) {
			sumRate += rate;
		}

		List<Double> sortRates = new ArrayList<>();
		double tempRate = 0;
		for (Double rate : weightList) {
			tempRate += rate;
			sortRates.add(tempRate / sumRate);
		}

		double nextDouble = RandomUtils.nextDouble();
		for (int i = 0; i < sortRates.size(); i++) {
			if (nextDouble <= sortRates.get(i)) {
				return result.get(i);
			}
		}
		return result.get(0);
	}

	public static int getSum(int[] arr) {
		if (arr == null) {
			return 0;
		}
		return Arrays.stream(arr).sum();
	}

	/**
	 * 数组中是否包含负数
	 * @param arr
	 * @return
	 */
	public static boolean containsNegative(int[] arr) {
		return Arrays.stream(arr).anyMatch(v -> v < 0);
	}

	public static boolean contains(int v, int m) {
		return (v & m) == m;
	}

	/**
	 * 远程过滤屏蔽词
	 */
	public static boolean checkWordFilterRemote(String word) {
		if (WebUtil.localModeEnable()) {
			return true;
		}
		//拼接参数
		StringBuilder sb = new StringBuilder();
		sb.append("game=").append("sbtj").append("&");
		sb.append("word=").append(word).append("&");
		sb.append("country=").append("cn");

		//发送请求
		URL url = null;
		HttpURLConnection connection = null;
		BufferedInputStream bis = null;
		try {
			String urlStr = getURL(Configuration.getProperty(Configuration.CHAT_REMOTE_FILTER_URL), "chat/checkWord");
			url = new URL(urlStr);

			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			// 设置连接超时时间: 1s
			connection.setConnectTimeout(1000);
			// 设置读取超时时间: 1s
			connection.setReadTimeout(1000);

			// 发送POST数据
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			out.write(sb.toString());
			out.flush();
			out.close();

			int code = connection.getResponseCode();
			String rtn = "";
			if (code == HttpURLConnection.HTTP_OK) {
				bis = new BufferedInputStream(connection.getInputStream());
				int length = -1;
				byte[] buff = new byte[1024];
				StringBuilder builder = new StringBuilder("");
				while ((length = bis.read(buff)) != -1) {
					builder.append(new String(buff, 0, length));
				}
				rtn = builder.toString();
			}
			if (StringUtils.isNotBlank(rtn)) {
				log.info("filterWord#filterRtn1#{}", rtn);
				JSONObject json = (JSONObject) JSON.parse(rtn);
				String state = json.getString("state");
				if ("1".endsWith(state)) {
					JSONObject data = json.getJSONObject("data");
					// 0 正常 >0 不正常包含屏蔽词
					int retType = data.getIntValue("retType");

					log.info("filterWord#filterRtn2#{}", data.getIntValue("retType"), data.getString("retMsg"));
					return retType == 0;

				}
			}
			return true;
		} catch (Exception e) {
			log.error("RemoteFiter", e);
			return true;
		} finally {
			if (null != bis) {
				try {
					bis.close();
				} catch (IOException e) {
					// do nothing
				}
			}
		}
	}

	/**
	 * 构建url
	 * @param rootUrl
	 * @param action
	 * @return
	 * $Date: 2016年1月13日 下午5:13:01
	 */
	public static String getURL(String rootUrl, String action) {
		if (org.apache.commons.lang.StringUtils.isBlank(rootUrl)) {
			return null;
		}
		if (!rootUrl.endsWith("/")) {
			rootUrl += "/";
		}

		return rootUrl + action;
	}
}