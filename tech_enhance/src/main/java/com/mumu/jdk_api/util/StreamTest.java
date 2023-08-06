/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.util;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * StreamTest
 * 流操作
 * @author liuzhen
 * @version 1.0.0 2021/6/21 7:43
 */
public class StreamTest {

    /**
     * 定义流
     * @throws FileNotFoundException
     */
    @Test
    public void defineStreamTest() throws FileNotFoundException {
        // 1.集合创建流 Stream stream() : 返回一个顺序流  Stream parallelStream() : 返回一个并行流
        Stream<Integer> stream = new ArrayList<Integer>().stream();

        // 2. 数组创建流 Arrays.stream(T[] arr)
        // 注：引用类型的数组返回Stream流；而基本数据类型(int\long\double)的数组返回IntStream\LongStream\DoubleStream流
        Stream integerStream = Arrays.stream(new Integer[] {1, 2, 3});
        IntStream IntStream = Arrays.stream(new int[] {1, 2, 3});

        // 3. 由值创建流 Stream.of(T... values); 实际实现：return Arrays.stream(values);
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3);

        // 4. 从文件中获得流 使用BufferedReader的lines方法从文件中获得行的流
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));
        Stream<String> linesStream = reader.lines();
    }

    /**
     * count: 统计元素个数
     * distinct：通过流中元素的 hashCode() 和 equals() 去除重复元素
     * filter：过滤流中的某些元素
     * forEach():
     * limit(n)：获取n个元素
     * skip(n)：跳过n元素，配合limit(n)可实现分页
     * peek()：如同于map，能得到流中的每一个元素。但map接收的是一个Function表达式，有返回值；而peek接收的是Consumer表达式，没有返回值。
     *
     * max()：返回流中元素最大值
     * min()：返回流中元素最小值
     * allMatch(() -> {})：接收一个 Predicate 函数，当流中每个元素都符合该断言时才返回true，否则返回false
     * anyMatch：接收一个 Predicate 函数，只要流中有一个元素满足该断言则返回true，否则返回false
     * noneMatch(() -> {})：接收一个 Predicate 函数，当流中每个元素都不符合该断言时才返回true，否则返回false
     * findFirst：返回流中第一个元素
     * findAny：返回流中的任意元素
     *
     * map()：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap()：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     * mapToInt(): 转成int数组
     * mapToLong()
     * mapToDouble():
     *
     * sorted(Comparator com)：定制排序，自定义Comparator排序器
     *
     */
    @Test
    public void apiTest() {
        Stream<Integer> stream1 = Stream.of(6, 4, 6, 7, 3, 9, 8, 10);
        long count = stream1.filter(s -> s > 5) // 6 6 7 9 8 10
                                          .distinct() // 6 7 9 8 10
                                          .skip(2) // 9 8 10
                                          .limit(2) // 9 8
                                          .count(); // 2
        System.out.println("------------------------------->");

        int[] intArr = {6, 4, 6, 7, 3, 9, 8, 10};
        Integer[] integerArr = {6, 4, 6, 7, 3, 9, 8, 10};
        // IntStream 求最大值
        int max = Arrays.stream(intArr).max().getAsInt();
        // Stream 求最小值
        int min = Arrays.stream(integerArr).min(Integer::compareTo).get();
        System.out.println("max: " + max + " min: " + min);

        boolean allMatch = Arrays.stream(intArr).allMatch(e -> e > 10); // false
        boolean anyMatch = Arrays.stream(intArr).anyMatch(e -> e > 4); // true
        boolean noneMatch = Arrays.stream(intArr).noneMatch(e -> e > 10); // true

        OptionalInt optionalInt1 = Arrays.stream(intArr).findFirst();
        int asInt1 = optionalInt1.getAsInt(); // 1
        int findFirst2 = Arrays.stream(integerArr).findFirst().get(); // 1

        OptionalInt optionalInt2 = Arrays.stream(intArr).findAny();
        int asInt2 = optionalInt2.getAsInt(); // 1
        Integer findAny2 = Arrays.stream(integerArr).findAny().get(); // 1

        System.out.println("------------------------------->");

        User p1 = new User("张三", 18, 1);
        User p2 = new User("李四", 6, 0);
        User p3 = new User("王五", 99, 1);
        User p4 = new User("王五", 88, 1);
        Stream<User> stream2 = Stream.of(p1, p2, p3, p4);

        // map
        List<String> list = stream2.map(User::getUserName).collect(Collectors.toList()); // 张三 李四 王五 王五
        System.out.println("list: " + list);

        // 排序
        Stream<User> stream3 = Stream.of(p1, p2, p3, p4);
        stream3.sorted(Comparator.comparingInt(User::getAge));

        // 自定义排序：先按姓名升序，姓名相同则按年龄升序
        Stream<User> stream4 = Stream.of(p1, p2, p3, p4);
        stream4.sorted((o1, o2) -> {
            if (o1.getUserName().equals(o2.getUserName())) {
                return o1.getAge() - o2.getAge();
            } else {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });

    }

    /**
     * 收集操作:
     * 1. 装成list: Collectors.toList()
     * 2. 转成set: Collectors.toSet()
     * 3. 转成map,注:key不能相同，否则报错。Collectors.toMap(params1, params2, params3) eg: Collectors.toMap(Person::getSex, v -> v, (v1, v2) -> v1)
     * 4. 字符串分隔符连接: Collectors.joining("", "", "")
     * 5. 聚合操作:
     *      求总数: Collectors.counting()
     *      求最大: Collectors.maxBy(Integer::compare)
     *      求最小: Collectors.summingInt(Person::getAge)
     *      求平均: Collectors.averagingDouble(Person::getAge) averagingInt() averagingLong()
     * 6. 分组: Collectors.groupingBy(Person::getAge)
     * 7.分区: Collectors.partitioningBy(person -> person.getAge() > 30)
     */
    @Test
    public void collectTest() {
        User p1 = new User("aa", 18, 1);
        User p2 = new User("bb", 6, 0);
        User p3 = new User("cc", 99, 1);
        User p4 = new User("cc", 6, 1);
        List<User> list = Arrays.asList(p1, p2, p3);

        // 1. 装成list Collectors.toList()
        List<Integer> ageList = list.stream().map(User::getAge).collect(Collectors.toList()); // [18, 6, 99,6]

        // 2. 转成set
        Set<Integer> ageSet = list.stream().map(User::getAge).collect(Collectors.toSet()); // [18, 6, 99]

        // 3. 转成map,注:key不能相同，否则报错。 处理key相同：处理第三个参数
        Map<Integer, User> sexMap = list.stream().collect(Collectors.toMap(User::getSex, v -> v, (v1, v2) -> v1));
        for (Map.Entry<Integer, User> entry : sexMap.entrySet()) {
            System.out.println("key:" + entry.getKey() + " value: " + entry.getValue());
        }

        // 4. 字符串分隔符连接 Collectors.joining(",", "(", ")")
        String joinName = list.stream().map(User::getUserName).collect(Collectors.joining(",", "(", ")")); // (aa,bb,cc)

        // 5. 聚合操作:
        // 5.1.总数 Collectors.counting()
        Long count = list.stream().collect(Collectors.counting()); // 4
        // 5.2.最大年龄 (最小的minBy同理) Collectors.maxBy(Integer::compare)
        Integer maxAge = list.stream().map(User::getAge).collect(Collectors.maxBy(Integer::compare)).get(); // 99
        // 5.3.所有人的年龄 Collectors.summingInt(Person::getAge)
        int sumAge = list.stream().collect(Collectors.summingInt(User::getAge)); // 123
        System.out.println("sumAge: " + sumAge);
        // 5.4.平均年龄 Collectors.averagingDouble(Person::getAge)
        Double averageAge = list.stream().collect(Collectors.averagingDouble(User::getAge)); // 40.0
        System.out.println("averageAge: " + averageAge);

        // 6. 分组
        Map<Integer, List<User>> ageMap = list.stream().collect(Collectors.groupingBy(User::getAge));

        // 多重分组,先根据类型分再根据年龄分
        Map<Integer, Map<Integer, List<User>>> typeAgeMap = list.stream().collect(
            Collectors.groupingBy(User::getSex, Collectors.groupingBy(User::getAge)));

        // 7.分区
        // 分成两部分，一部分大于30岁，一部分小于等于30岁
        Map<Boolean, List<User>> partMap = list.stream().collect(Collectors.partitioningBy(user -> user.getAge() > 30));
    }

}
