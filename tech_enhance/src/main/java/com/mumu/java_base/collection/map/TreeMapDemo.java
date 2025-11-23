package com.mumu.java_base.collection.map;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * TreeMapDemo
 * @author liuzhen
 * @version 1.0.0 2024/10/29 11:17
 */
public class TreeMapDemo {

  /**
   * treeMap.floorEntry(): TreeMap 类的一个方法，用于获取小于或等于给定键的最大键值对。
   * TreeMap 是一种基于红黑树的实现，提供了按自然顺序或指定比较器排序的键值对的映射。
   * @param args args
   * @return void
   * @date 2024/10/29 11:20
   */
  public static void main(String[] args) {
    // 创建一个 TreeMap，并添加一些键值对
    TreeMap<Integer, String> treeMap = new TreeMap<>();
    treeMap.put(50, "Level 0");
    treeMap.put(100, "Level 1");
    treeMap.put(200, "Level 2");
    treeMap.put(300, "Level 3");
    treeMap.put(400, "Level 4");
    treeMap.put(500, "Level 5");

    // 查找小于或等于 250 的最大键
    Map.Entry<Integer, String> entry = treeMap.floorEntry(250);
    if (entry != null) {
      System.out.println(
          "小于或等于 250 的最大键值对: " + entry.getKey() + " = " + entry.getValue());
      
    } else {
      System.out.println("没有找到小于或等于 250 的键值对。");
    }

    // 查找小于或等于 100 的最大键
    entry = treeMap.floorEntry(100);
    if (entry != null) {
      System.out.println(
          "小于或等于 100 的最大键值对: " + entry.getKey() + " = " + entry.getValue());
    } else {
      System.out.println("没有找到小于或等于 100 的键值对。");
    }

    // 查找小于或等于 50 的最大键 ( 找到 >= diff 的第一条)
    entry = treeMap.floorEntry(50);
    if (entry != null) {
      System.out.println(
          "小于或等于 50 的最大键值对: " + entry.getKey() + " = " + entry.getValue());

    } else {
      System.out.println("没有找到小于或等于 50 的键值对。");
    }

    // 找到 > key 的下一条
    Entry<Integer, String> higherEntry = treeMap.higherEntry(50);

    // 找到 < key 的下一条
    Entry<Integer, String> lowerEntry = treeMap.lowerEntry(50);
  }
}
