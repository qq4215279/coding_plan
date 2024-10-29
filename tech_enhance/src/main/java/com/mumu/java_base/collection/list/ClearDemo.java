/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 * ClearDemo
 * @author liuzhen
 * @version 1.0.0 2024/9/4 15:21
 */
public class ClearDemo {
  public static void main(String[] args) {
    List<String> finalList = new ArrayList<>() {{
      add("1111");
      add("2222");
    }};

    List<String> subList = new ArrayList<>() {{
      add("555");
      add("66666");
    }};

    finalList.addAll(subList);
    System.out.println("subList: " + subList.toString());
    System.out.println("finalList: " + finalList.toString());

    subList.clear();
    System.out.println("clear....");
    System.out.println("subList: " + subList.toString());
    System.out.println("finalList: " + finalList.toString());
  }

}
