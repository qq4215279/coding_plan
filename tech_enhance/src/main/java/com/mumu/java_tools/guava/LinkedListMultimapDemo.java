package com.mumu.java_tools.guava;

import com.google.common.collect.LinkedListMultimap;
import java.util.Map.Entry;
import org.junit.Test;

/**
 * LinkedListMultimapDemo
 * @author liuzhen
 * @version 1.0.0 2025/5/26 17:13
 */
public class LinkedListMultimapDemo {


  @Test
  public void test() {
    LinkedListMultimap<String, String> multimap = LinkedListMultimap.create();

    multimap.put("fruit", "apple");
    multimap.put("fruit", "banana");
    multimap.put("color", "red");
    multimap.put("color", "blue");

    // 1. 遍历所有 entry（key-value 对）
    System.out.println("遍历 entrySet:");
    for (Entry<String, String> entry : multimap.entries()) {
      System.out.println(entry.getKey() + " -> " + entry.getValue());
    }

    // 2. 遍历所有 key，及对应的 values 集合
    System.out.println("\n遍历键和值集合:");
    for (String key : multimap.keySet()) {
      System.out.println(key + " -> " + multimap.get(key));
    }

    // 3. 遍历所有 key（包含重复）
    System.out.println("\n遍历所有key（包括重复）:");
    for (String key : multimap.keys()) {
      System.out.println(key);
    }

    // 4. 遍历所有 value（包括重复）
    System.out.println("\n遍历所有value:");
    for (String value : multimap.values()) {
      System.out.println(value);
    }
  }
}
