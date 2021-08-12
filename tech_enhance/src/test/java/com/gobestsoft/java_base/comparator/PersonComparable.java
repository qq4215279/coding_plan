package com.gobestsoft.java_base.comparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 比较器：Comparable使用
 *  升序：return this.- o2;
 *  降序: return o1 - this.;
 */
public class PersonComparable implements Comparable<PersonComparable> {
    private int age;
    private String name;

    public PersonComparable(String name, int age) {
        this.age = age;
        this.name = name;
    }

    /**
     * 写法1：在类上：implements Comparable
     * @author liuzhen
     * @date 2021/8/12 14:22
     * @param o
     * @return int
     */
   /* @Override
    public int compareTo(Object o) {
        if (o instanceof PersonComparable) {
            PersonComparable personComparable = (PersonComparable) o;
            int result = getAge() - personComparable.getAge(); // 升序
            // int result = person.getAge() -  getAge(); // 降序
            return result;
        }
        return 0;
    }*/

    /**
     * 写法2：在类上：implements Comparable<PersonComparable>
     * @author liuzhen
     * @date 2021/8/12 14:22
     * @param personComparable
     * @return int
     */
    @Override
    public int compareTo(PersonComparable personComparable) {
        int result = getAge() - personComparable.getAge(); // 升序
        // int result = person.getAge() -  getAge(); // 降序
        return result;
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
        ArrayList<PersonComparable> list = new ArrayList<>();
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
