package com.mumu.user;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Mianshi {

    public static int[] randomArray(int[] array) {

//        int ran2 = (int)(Math.random()*(max-min)+min);

        int maxIndex = array.length - 1;
        int i = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (i < array.length) {
            int key = (int) (Math.random()*maxIndex);
            if (!map.containsKey(key)) {
                map.put(key, array[key]);
                i++;
            }
        }

        int[] newArr = new int[array.length];
        Set<Integer> set = map.keySet();
        Iterator iterator = set.iterator();
        int k = 0;
        while (iterator.hasNext()){
            int key = (int) iterator.next();
            newArr[k] = map.get(key);
            k++;
        }

        return newArr;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] ints = randomArray(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

}
