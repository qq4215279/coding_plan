package com.mumu.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 * StringUtil
 * 字符串工具类
 * @author liuzhen
 * @version 1.0.0 2024/12/4 10:22
 */
public class StringUtil {

  /**
   * str分割 转 int数组（默认"," 分割）
   * @date 2023/7/24 16:06
   * @param str str
   * @return int[]
   */
  public static int[] str2IntArray(String str) {
    return str2IntArray(str, ",");
  }

  /**
   * str分割 转 int数组
   * @date 2023/7/24 16:07
   * @param str str
   * @param comma 分隔符
   * @return int[]
   */
  public static int[] str2IntArray(String str, String comma) {
    if (StringUtils.isEmpty(str)) {
      return new int[] {};
    }

    String[] split = str.split(comma);
    int[] array = new int[split.length];

    for (int i = 0; i < split.length; i++) {
      array[i] = Integer.parseInt(split[i]);
    }

    return array;
  }

  /**
   * str分割 转 List集合（默认"," 分割）
   * @date 2023/7/24 16:12
   * @param str str
   * @return java.util.List<java.lang.Integer>
   */
  public static List<Integer> str2List(String str) {
    return str2List(str, ",");
  }

  /**
   * str分割 转 List集合
   * @date 2023/7/24 16:12
   * @param str str
   * @param comma 分隔符
   * @return java.util.List<java.lang.Integer>
   */
  public static List<Integer> str2List(String str, String comma) {
    if (StringUtils.isEmpty(str)) {
      return Collections.emptyList();
    }

    List<Integer> res = new ArrayList<>();
    String[] split = str.split(comma);

    for (String s : split) {
      res.add(Integer.parseInt(s));
    }

    return res;
  }

  /**
   * str分割 转 Set集合（默认"," 分割）
   * @date 2023/7/24 16:12
   * @param str str
   * @return java.util.List<java.lang.Integer>
   */
  public static Set<Integer> str2Set(String str) {
    return str2Set(str, ",");
  }

  /**
   * str分割转Set集合
   * @date 2023/7/24 16:12
   * @param str str
   * @param comma 分隔符
   * @return java.util.List<java.lang.Integer>
   */
  public static Set<Integer> str2Set(String str, String comma) {
    return new HashSet<>(str2List(str, comma));
  }

  /**
   * List集合 转 str（默认"," 分割）
   * @date 2023/7/24 16:27
   * @param list list
   * @return java.lang.String
   */
  public static <T> String list2Str(List<T> list) {
    return list2Str(list, ",");
  }

  /**
   * List集合 转 str
   * @date 2023/7/24 16:27
   * @param list list
   * @param comma 分隔符
   * @return java.lang.String
   */
  public static <T> String list2Str(List<T> list, String comma) {
    if (list == null || list.isEmpty()) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    for (T i : list) {
      sb.append(i).append(comma);
    }

    return sb.substring(0, sb.length() - 1);
  }

  /**
   * Set集合 转 str（默认"," 分割）
   * @date 2023/7/24 16:27
   * @param set set
   * @return java.lang.String
   */
  public static <T> String set2Str(Set<T> set) {
    return set2Str(set, ",");
  }

  /**
   * Set集合 转 str
   * @date 2023/7/24 16:27
   * @param set set
   * @param comma 分隔符
   * @return java.lang.String
   */
  public static <T> String set2Str(Set<T> set, String comma) {
    if (set == null || set.isEmpty()) {
      return "";
    }

    StringBuilder sb = new StringBuilder();
    for (T i : set) {
      sb.append(i).append(comma);
    }

    return sb.substring(0, sb.length() - 1);
  }
}
