package com.demo.nodeLearnning;

import java.util.*;

public class RandomArray {

    /**
     * 随机打乱一个数组，输入int[] array, 返回随机打乱过的array
     */

    public static int[] randomArray(int[] array) {

        int maxIndex = array.length ;
        int i = 0;

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        int[] newArr = new int[maxIndex];
        while (i < array.length) {
            int key = (int) (Math.random()*(maxIndex - 0) + 0);
            if (!set.contains(key)) {
                set.add(key);
                newArr[i] = array[key];
                i++;
            }
        }
        return newArr;
    }


    public static void main(String[] args) {
        System.out.println("随机数组打印结果：");
        int[] arr = {1,2,3,4,5};
        int[] ints = randomArray(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
