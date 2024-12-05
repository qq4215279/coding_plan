/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.common.utils;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * J2ee工具类
 * @author wangys
 *
 * 2011-3-10 下午06:00:42
 * 2011-05-19 add by Blade 添加是否需要用户激活
 *
 */
public final class WebUtil {

	/**
	 * 构造函数
	 */
	private WebUtil() { }

	/**
	 * 检查是否有空参数
	 * @param json 999999999999
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

		// return (int)((now.getTimeInMillis() - last.getTimeInMillis()) / TimeConstants.DAY_SEC);
		return (int)((now.getTimeInMillis() - last.getTimeInMillis()) / TimeUnit.DAYS.toSeconds(1));
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
	 * 字符串数组转换为整型数组
	 * @param strs
	 * @return
	 * $Date: 2011-8-10 下午03:37:30
	 */
	private static int[] StringArrayToIntArray(String[] strs) {
		return stringArray2IntArray(strs);
	}

	/**
	 * 字符串数组转换为整型数组
	 * @param strs
	 * @return
	 * $Date: 2011-8-10 下午03:37:30
	 */
	public static int[] stringArray2IntArray(String[] strs) {
		int[] ints = new int[strs.length];
		int index = 0;
		for (String str : strs) {
			ints[index++] = Integer.valueOf(str);
		}
		return ints;
	}

	public static List<Integer> stringArray2IntList(String[] strs){
		return CollectionUtils.arrayToList(stringArray2IntArray(strs));
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

		// double prob = RandomUtils.nextDouble() * total;
		double prob = org.apache.commons.lang.math.RandomUtils.nextDouble() * total;
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

		// double nextDouble = RandomUtils.nextDouble();
		double nextDouble = org.apache.commons.lang.math.RandomUtils.nextDouble();
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

}