package com.gobestsoft.java_base.comparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 比较器：Comparable使用
 *  升序：return this.- o2;  return 1
 *  降序: return o1 - this.; return -1
 *  自然排序：               return 0
 *
 *  理解：
 *  eg 需求：升序排序：
 *  当 this > o2 时，因为需要升序，所以需要 this 与 o2 发生交换，所以 return 1（1代表发生交换！）。
 *  当 this < o2 时，因为需要升序，所以需要 this 与 o2 不发生交换，所以 return -1（-1代表不发生交换！）。
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
     *  compareTo(T o): 比较此对象与指定对象的顺序。如果该对象小于、等于或大于指定对象，则分别返回负整数、零或正整数。参数：o 要比较的对象。
     * @author liuzhen
     * @date 2021/8/12 14:22
     * @param personComparable
     * @return int
     */
    @Override
    public int compareTo(PersonComparable personComparable) {
//        return getAge() - personComparable.getAge(); // 升序
//        return person.getAge() - getAge(); // 降序
        return getAge() > personComparable.getAge() ? 1 : -1; // 升序
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
