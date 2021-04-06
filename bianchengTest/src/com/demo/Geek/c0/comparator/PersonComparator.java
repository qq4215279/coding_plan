package com.demo.Geek.c0.comparator;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 比较器：Comparator使用
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
        return "Person{" + "age=" + age + ", name='" + name + '\'' + '}';
    }
}

class AseAgePerson implements Comparator<PersonComparator> {
    @Override
    public int compare(PersonComparator o1, PersonComparator o2) {
        int result;
        result = o1.getAge() - o2.getAge();
        return result;
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

        // 打印list的原始序列
        System.out.println(list);
        list.sort(new AseAgePerson());

        //list.sort(new DescAgePerson());
        System.out.println(list);
    }
}
