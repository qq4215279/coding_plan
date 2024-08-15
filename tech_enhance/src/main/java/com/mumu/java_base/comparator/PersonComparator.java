/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.comparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 比较器：Comparator<T>使用
 *  升序：return o1 - o2;
 *  降序: return o2 - o1;
 */
public class PersonComparator {
    private int age;
    private String name;

    public PersonComparator(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersonComparator{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}

class AseAgePerson implements Comparator<PersonComparator> {
    @Override
    public int compare(PersonComparator o1, PersonComparator o2) {
        // 如下为错误写法！！！因为不满足比较器的规则：自反性，对称性，传递性
        // return o1.getAge() > o2.getAge() ? 1 : -1; // 升序

        return o1.getAge() - o2.getAge(); // 升序
        // return o2.getAge() - o1.getAge(); // 降序
    }
}

class DescAgePerson implements Comparator<PersonComparator> {
    @Override
    public int compare(PersonComparator o1, PersonComparator o2) {
        return o2.getAge() - o1.getAge();
    }
}

class Test {
    public static void main(String[] args) {
        List<PersonComparator> list = new ArrayList<>();
        list.add(new PersonComparator("ccc", 20));
        list.add(new PersonComparator("AAA", 30));
        list.add(new PersonComparator("EEE", 99999));
        list.add(new PersonComparator("bbb", 10));
        list.add(new PersonComparator("ddd", 40));
        // list.add(new PersonComparator("eee", 40));

        System.out.println("max: " + list.stream().max(new AseAgePerson()).get().toString());

        // 使用方式1：
        // 打印list的原始序列
        System.out.println("排序前list: " + list);
        list.sort(new AseAgePerson());
        // list.sort(new DescAgePerson());
        System.out.println("排序后list: " + list);

        System.out.println("----------------------------------------------------------------->");

        // 使用方式2：
        System.out.println("排序前list: " + list);
        List<PersonComparator> list2 = list.stream().sorted(new DescAgePerson()).collect(Collectors.toList());
        System.out.println("排序后list2: " + list2);
        System.out.println("----------------------------------------------------------------->");

        // 使用方式3:
        PersonComparator[] arr = list.toArray(new PersonComparator[0]);
        Arrays.sort(arr, new DescAgePerson());
        System.out.println("arr: ");
        for (PersonComparator p : arr) {
            System.out.println(p.toString());
        }

        System.out.println("----------------------------------------------------------------->");

        // 使用方式4:
        PriorityQueue<PersonComparator> queue = new PriorityQueue<>(new AseAgePerson());
        queue.add(new PersonComparator("zhangsan", 400));
        queue.add(new PersonComparator("lisi", 30));
        queue.add(new PersonComparator("lihua", 31));
        queue.add(new PersonComparator("ming", 100));

        while (!queue.isEmpty()) {
            PersonComparator personComparator = queue.poll();
            System.out.println(personComparator.toString());
        }

        PriorityQueue<PersonComparator> queue2 = new PriorityQueue<>((o1, o2) -> o1.getAge() > o2.getAge() ? 1 : -1);


        // Comparator 自带比较器
        List<Integer> list3 = new ArrayList<>();
        list3.add(2);
        list3.add(-100);
        list3.add(999);

        // 方式1
        list3 = list3.stream().sorted(Comparator.comparingInt(Integer::intValue)).collect(Collectors.toList());
        // 方式2
        // list3.sort(Comparator.comparingInt(Integer::intValue));
        System.out.println("list3排序后：" + list3);
    }
}
