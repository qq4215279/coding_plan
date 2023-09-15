/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.comparator;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * IllegalArgumentExceptionDemo
 * 解析 java.lang.IllegalArgumentException 异常
 * @author liuzhen
 * @version 1.0.0 2023/9/15 16:27
 */
public class IllegalArgumentExceptionDemo {

    /**
     * java.lang.IllegalArgumentException: Comparison method violates its general contract!异常分析及解决办法
     * 在 JDK7 版本以上，Comparable 和 Comparator 要满足自反性，对称性，传递性，否则 Arrays.sort，Collections.sort 都会报 IllegalArgumentException 异常。
     * 自反性：当两个相同的元素相比时，compare必须返回0，也就compare(o1, o1) = 0;
     * 对称性：如果compare(o1, o2) = 1，则compare(o2, o1)必须返回符号相反的值也就是 -1；
     * 传递性：如果 a>b, b>c, 则 a必然大于c。也就是compare(a,b)>0,compare(b,c)>0, 则compare(a,c)>0。
     */

    public static void main(String[] args) {
        intSort(5);
        intSort(8);
        intSort(31);
        intSort(32);

        for (int i = 0; i < 10000; i++) {
            obSort();
        }
    }

    /**
     * int 排序
     * @date 2023/9/15 18:18
     * @param count
     * @return void
     */
    private static void intSort(int count) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(RandomUtil.randomInt(1, 4));
        }

        // 正确写法：
        list.sort((o1, o2) -> o1 > o2 ? 1 : -1);

        // 报错写法！没有返回 0 的情况，排序数据量大了一定报错！
        list.sort((o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } /*else if (o1.equals(o2)) {
                return 0;
            }*/ else {
                return 1;
            }
        });

		System.out.println(list);
	}

	/**
	 * 对 Demo 对象列表进行排序
	 * @date 2023/9/15 18:18
	 * @param
	 * @return void
	 */
    private static void obSort() {
        List<Ob> list = getObList();

        // error 没有返回 0 的情况，排序数据量大了一定报错！
        /*list.sort((o1, o2) -> {
            if (o1.getScore() < o2.getScore()) {
                return -1;
            } *//*else if (o1.getScore() == o2.getScore()) {
				return 0;
			}*//* else {
                return 1;
            }
        });*/


        // 正确写法：没有嵌套if，且最终的else 一定返回0。
        list.sort((o1, o2) -> {
            if (o1.getScore() < o2.getScore()) {
                return 1;
            } else if (o1.getScore() > o2.getScore()) {
                return -1;
            } else if (o1.getTimes() < o2.getTimes()) {
                return -1;
            } else if (o1.getTimes() > o2.getTimes()) {
                return 1;
            } else if (o1.getAge() < o2.getAge()) {
                return -1;
            } /*else if (o1.getAge() > o2.getAge()) {
                return 1;
            }*/ else {
                return 0;
            }
        });

        // for (Demo demo : list) {
        //     System.out.println(demo);
        // }
    }

	private static List<Ob> getObList() {
        List<Ob> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            int score = RandomUtil.randomInt(98, 101);
            int times = RandomUtil.randomInt(1, 4);
            int age = RandomUtil.randomInt(11, 14);
            list.add(new Ob(score, times, age));
        }

        return list;
    }

    @AllArgsConstructor
    private static class Ob {
        @Getter
        public int score;
        @Getter
        public int times;
        @Getter
        public int age;

        @Override
        public String toString() {
            return "Demo{" +
                    "score=" + score +
                    ", times=" + times +
                    ", age=" + age +
                    '}';
        }
    }

}
