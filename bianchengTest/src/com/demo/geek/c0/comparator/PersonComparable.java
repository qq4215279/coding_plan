package com.demo.geek.c0.comparator;

import java.util.ArrayList;
import java.util.Collections;

public class PersonComparable implements Comparable {
    private int age;
    private String name;

    public PersonComparable(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof PersonComparable) {
            PersonComparable personComparable = (PersonComparable) o;
            int result;
            result = getAge() - personComparable.getAge();
            //result =person.getAge() -  getAge();
            return result;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
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


    public static void main(String[] args) {
        ArrayList<PersonComparable> list = new ArrayList<PersonComparable>();
        list.add(new PersonComparable("ccc", 20));
        list.add(new PersonComparable("AAA", 30));
        list.add(new PersonComparable("bbb", 10));
        list.add(new PersonComparable("ddd", 40));
        // 打印list的原始序列
        System.out.printf("Original sort, list:%s\n", list);
        Collections.sort(list);
        System.out.printf("Original sort, list:%s\n", list);
    }
}
