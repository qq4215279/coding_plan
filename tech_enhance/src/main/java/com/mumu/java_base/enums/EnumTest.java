/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.enums;

import org.junit.Test;

/**
 * EnumTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/6 16:38
 */
public class EnumTest {

    /**
     *  https://mp.weixin.qq.com/s/VN4v6lU5JzIJ-YsAvG8Lfg
     * 在数学和计算机科学理论中，一个集的「枚举」 是列出某些有穷序列集的所有成员的程序，或者是一种特定类型对象的计数。这两种类型经常（但不总是）重叠。
     * 是一个被命名的整型常数的集合。
     *
     * 枚举在日常生活中很常见，例如表示星期的 SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY、SATURDAY 就是一个枚举。
     * 由此映射到 Java 语言中，即可定义一个表示星期的枚举类：
     * public enum Week {
     *     SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
     * }
     *
     * 定义枚举类的关键字是 enum, 枚举类对象不能通过 new 出来，里面的 SUNDAY、MONDAY...这些其实就相当于是枚举类 Week 的实例。
     * 固定的就这几个，不能在外部创建新的实例。引用的时候直接类.实例名 eg: Week w = Week.MONDAY;
     *
     * api:
     * 1. values()：该方法返回一个包含枚举类型中所有枚举常量的数组。例如：Day.values()返回包含Day枚举类型中所有常量的数组。
     * 2. valueOf(String name)：该方法返回具有指定名称的枚举常量。如果找不到具有指定名称的常量，则会抛出IllegalArgumentException异常。例如：Day.valueOf("MONDAY")返回Day.MONDAY枚举常量。
     * 3. name()：该方法返回枚举常量的名称。例如：Day.MONDAY.name()返回字符串"MONDAY"。
     * 4. ordinal()：该方法返回枚举常量在枚举类型中的顺序位置（从0开始）。例如：Day.MONDAY.ordinal()返回0。
     *
     * 5. 自定义方法：枚举可以定义自己的方法，用于执行特定的操作或返回与枚举常量相关的信息。这些自定义方法可以根据枚举类型的需要进行定义和实现。
     * 6. 实现接口：枚举可以实现接口，从而具备接口所定义的行为和功能。枚举可以覆盖接口中的方法，并为每个枚举常量提供特定的实现。
     *
     * Java 中其实还有专门用于枚举的集合类，EnumSet 和 EnumMap
     */

    /**
     * values()
     * @date 2023/7/6 16:49
     * @param
     * @return void
     */
    @Test
    public void valuesTest() {
        WeekEnum[] values = WeekEnum.values();
        for (WeekEnum weekEnum : values) {
            weekEnum.setIntro("change Intro... 111111");
            System.out.println(weekEnum.toString());
        }
    }

    /**
     * valueOf(String name)  value()  name()  ordinal()
     * @date 2023/7/6 16:50
     * @param
     * @return void
     */
    @Test
    public void apiTest() {
        // 1. 获取方式1
        WeekEnum monday = WeekEnum.valueOf("MONDAY");
        // 2. 获取方式2
        // Week monday = Week.MONDAY;

        System.out.println("Enum name: " + monday.name());
        System.out.println("Enum ordinal: " + monday.ordinal());

        System.out.println("weekNum: " + monday.getWeekNum());
        System.out.println("weekNum: " + monday.getWeekNum());
    }

    /**
     * 使用场景：switch 中使用
     * @date 2023/7/6 17:34
     * @param
     * @return void
     */
    @Test
    public void switchUseEnum() {
        WeekEnum w = WeekEnum.MONDAY;
        switch (w) {
            case MONDAY:
                System.out.println("周一");
                break;
            case TUESDAY:
                System.out.println("周二");
                break;
            default:
        }
    }
}
