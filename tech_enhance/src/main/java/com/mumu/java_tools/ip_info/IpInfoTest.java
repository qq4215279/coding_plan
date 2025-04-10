/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.ip_info;

import com.jthinking.common.util.ip.IPInfo;
import com.jthinking.common.util.ip.IPInfoUtils;

/**
 * IpInfoTest
 * ip解析工具test
 * @author liuzhen
 * @version 1.0.0 2024/3/5 15:18
 */
public class IpInfoTest {

	/**
	 * 介绍：https://blog.csdn.net/HoloLens/article/details/122137224
	 * 源码地址：https://gitee.com/jthinking/ip-info
	 * @param args args
	 * @return void
	 * @date 2024/3/5 15:33
	 */
	public static void main(String[] args) {
		// 获取IP信息
		String ip = "2408:840d:1320:3b8d:6cc5:8ff:fe8b:ff6";
		IPInfo ipInfo = IPInfoUtils.getIpInfo(ip);
		// IPInfo ipInfo = IPInfoUtils.getIpInfo("37.61.54.158");

		// 国家中文名称
		System.out.println(ipInfo.getCountry());
		// 中国省份中文名称
		System.out.println(ipInfo.getProvince());
		// 详细地址
		System.out.println(ipInfo.getAddress());
		// 互联网服务提供商
		System.out.println(ipInfo.getIsp());
		// 是否是国外
		System.out.println(ipInfo.isOverseas());
		// 纬度
		System.out.println(ipInfo.getLat());
		// 经度
		System.out.println(ipInfo.getLng());


		System.out.println(IPInfoUtils.getIpInfo("54.213.132."));

	}
}
