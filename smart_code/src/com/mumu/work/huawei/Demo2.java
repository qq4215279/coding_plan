/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.work.huawei;

import java.util.*;

/**
 * Demo2
 *
 * @author liuzhen
 * @version 1.0.0 2022/8/10 21:33
 */
public class Demo2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Map<Integer, Integer> numTimesMap = new HashMap<>();
        Map<Integer, List<Integer>> timesSetMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Integer num = sc.nextInt();

            int lastTimes = numTimesMap.getOrDefault(num, 0);
            numTimesMap.put(num, lastTimes + 1);

            if (lastTimes > 0) {
                List<Integer> list = timesSetMap.getOrDefault(lastTimes, Collections.emptyList());
                if (!list.isEmpty() && list.contains(num)) {
                    list.remove(num);
                }
            }

            List<Integer> list = timesSetMap.computeIfAbsent(lastTimes + 1,  k -> new ArrayList<>());
            list.add(num);
        }

        for (int i = 100; i >= 0; i--) {
            List<Integer> list = timesSetMap.getOrDefault(i, Collections.emptyList());

            if (list.isEmpty()) {
                continue;
            }

            for (int num : list) {
                System.out.print(num + ",");
            }
        }
    }

    public static Map<Integer, List<Integer>> demmo(List<Integer> arrList) {
        Map<Integer, Integer> numTimesMap = new HashMap<>();
        Map<Integer, List<Integer>> timesSetMap = new HashMap<>();

        for (Integer num : arrList) {
            int lastTimes = numTimesMap.getOrDefault(num, 0);
            numTimesMap.put(num, lastTimes + 1);

            if (lastTimes > 0) {
                List<Integer> list = timesSetMap.getOrDefault(lastTimes, Collections.emptyList());
                if (!list.isEmpty() && list.contains(num)) {
                    list.remove(num);
                }
            }

            List<Integer> list = timesSetMap.computeIfAbsent(lastTimes + 1,  k -> new ArrayList<>());
            list.add(num);
        }

        return timesSetMap;
    }

}
