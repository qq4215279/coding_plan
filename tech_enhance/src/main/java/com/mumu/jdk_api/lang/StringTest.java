/*
 * Copyright 2020-2022, 上海木木网络科技有限公司.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import org.junit.Test;

/**
 * StringTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/12/6 22:11
 */
public class StringTest {

    /**
     * String 类的常用方法（55212）：
     * 	1.与获取相关的常用方法：
     * 		int length()：返回字符串长度。
     * 		String concat(String str): 将当前字符串和参数字符串拼接成返回值新的字符串。
     * 		char charAt(int num)：返回指定索引处的字符，索引范围为从0到length()-1。	eg：String s = "abc";  char result = s.charAt(2); //结果为c
     * 		int indexOf(String str)：返回指定字符的索引。																											  3
     * 		String substring(int index)/substring(int begin,int end)：截取字符串。eg: "unhappy".substring(2) returns"happy";  String s4 = "aaa?";s4 = s4.substring(0,s4.indexOf("?"));//aaa
     * 	2.String中与转换相关的方法：
     * 		toLowerCase()：将字符串转成小写字母。
     * 		toUpperCase()：将字符串转成大写字符。
     * 		byte[] getBytes(): 获取当前字符串的字节数组。
     * 		char[] toCharArray(): 将当前字符串拆分从成字符数组作为返回值。
     *   	String replace("","")：字符串替换。eg：
     * 	3.分割字符串的方法：
     * 		String[] split(String str)：分割字符串，返回一个分割后的字符串数组。eg:	切割字符串：split("=")[1];
     * 		String trim()：去除字符串两端空白。
     * 	4.比较：
     * 		equals()：字符串比较。
     * 	5.测试字符串是否以指定的前缀开始或后缀结束：
     * 		boolean	startsWith(String str);
     * 		boolean endsWith(String str);
     *
     *  Java中字符串中子串的查找:共有四种方法，如下：（若指定字符串中没有该字符则系统返回-1）
     * 		int indexOf(String str) ：返回第一次出现的指定子字符串在此字符串中的索引。 	eg: String s = "abcdebbfg"; string.indexOf("c") //2
     * 		int indexOf(String str, int startIndex)：从指定的索引处开始，返回第一次出现的指定子字符串在此字符串中的索引（包含当前位置 ）。
     * 		int lastIndexOf(String str) ：返回在此字符串中最右边出现的指定子字符串的索引。	eg: s.lastIndexOf("b") //6
     * 		int lastIndexOf(String str, int startIndex) ：从指定的索引处开始向后搜索，返回在此字符串中最后一次出现的指定子字符串的索引（包含当前位置 ）。
     */

    /**
     * 其他常用api
     * contains(CharSequence s)  当且仅当此字符串包含指定的char值序列时，才返回true。
     * intern()  返回字符串对象的规范表示。
     * isBlank()  如果字符串为空或仅包含 white space代码点，则返回 true ，否则 false 。
     * isEmpty()  返回 true ，当且仅当， length()是 0 。
     */
    @Test
    public void otherApi() {
        String str1 = "abcdefg";
        System.out.println(str1.contains("ad")); // false

        String str2 = " ";
        System.out.println(str2.isBlank()); // true

        String str3 = " ";
        System.out.println(str3.isEmpty()); // true
    }

    /**
     * 静态api：eg: String.join()
     * join(CharSequence delimiter, CharSequence... elements) 参数1：表示连接的符 参数2：表示被连接的数组（也可以是集合），或者是要连接的多个字符串。eg：String.join(",", arr[])
     * valueOf(char c) 返回 char 参数的字符串表示形式。  ...重载api...
     * copyValueOf​(char[] data)  相当于 valueOf(char[])
     * format​(String format, Object... args)  如下
     */
    @Test
    public void staticApi() {
        String[] arr = {"1", "2", "4", "8"};
        String join = String.join(",", arr);
        System.out.println("after join: " + join);

    }

    /**
     * format api 详解：
     * format(String format, Object... args)  使用指定的格式字符串和参数返回格式化字符串。
     * format​(Locale l, String format, Object... args) 使用指定的语言环境，格式字符串和参数返回格式化的字符串。
     * String.format() 详解：
     * 转换符	 详细说明	                                              示例
     *  %s	    字符串类型	                                            “喜欢请收藏”
     *  %c	    字符类型	                                             ‘m’
     *  %b	    布尔类型	                                             true
     *  %d	    整数类型（十进制）	                                      88
     *  %x	    整数类型（十六进制）	                                      FF
     *  %o	    整数类型（八进制）	                                      77
     *  %f	    浮点类型	                                             8.888
     *  %a	    十六进制浮点类型	                                        FF.35AE
     *  %e	    指数类型	                                            9.38e+5
     *  %g	    通用浮点类型（f和e类型中较短的）	                    不举例(基本用不到)
     *  %h	    散列码	                                            不举例(基本用不到)
     *  %%	    百分比类型	                                    ％(%特殊字符%%才能显示%)
     *  %n	    换行符	                                            不举例(基本用不到)
     *  %tx	    日期与时间类型（x代表不同的日期与时间转换符)	        不举例(基本用不到)
     */
    @Test
    public void formatApi() {
        // String.format() 详解
        String str = null;
        str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
    }

}
