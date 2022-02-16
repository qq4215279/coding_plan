package com.gobestsoft.java_base.comparator;

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
//        return o1.getAge() - o2.getAge(); // 升序
//        return o2.getAge() - o1.getAge(); // 降序

        return o1.getAge() > o2.getAge() ? 1 : -1; // 升序
//        return o1.getAge() > o2.getAge() ? 1 : -1; // 升序
    }
}

class DescAgePerson implements Comparator<PersonComparator> {
    @Override
    public int compare(PersonComparator o1, PersonComparator o2) {
        int result;
        result = o2.getAge() - o1.getAge();
        return result;
    }
}

class Test {
    public static void main(String[] args) {
        ArrayList<PersonComparator> list = new ArrayList<>();
        list.add(new PersonComparator("ccc", 20));
        list.add(new PersonComparator("AAA", 30));
        list.add(new PersonComparator("bbb", 10));
        list.add(new PersonComparator("ddd", 40));

        // 使用方式1：
        // 打印list的原始序列
        System.out.println("排序前list: " + list);
        list.sort(new AseAgePerson());
        //list.sort(new DescAgePerson());
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

    }
}
