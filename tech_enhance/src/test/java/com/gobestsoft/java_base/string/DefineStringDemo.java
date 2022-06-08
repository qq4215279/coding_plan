/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.string;

import org.junit.Test;

/**
 * DefineStringDemo
 * 定义字符串demo
 * @author liuzhen
 * @version 1.0.0 2021/9/27 14:49
 */
public class DefineStringDemo {

    /**
     * 补充：解答上面的面试题需要知道如下两个知识点：
     * 1. String 对象的 intern()方法会得到字符串对象在常量池中对应的版本的引用（如果常量池中有一个字符串与 String 对象的 equals 结果是 true），
     *    如果常量池中没有对应的字符串，则该字符串将被添加到常量池中，然后返回常量池中字符串的引用；
     * 2. 字符串的 + 操作其本质是创建了 StringBuilder 对象进行 append 操作，然后将拼接后的 StringBuilder 对象用 toString 方法处理成 String 对象，
     *    这一点可以用 javap -c StringEqualTest.class 命令获得 class 文件对应的 JVM 字节码指令就可以看出来。（注：toString 即创将了一个new String()）
     * @author liuzhen
     * @date 2021/9/27 14:58
     * @param
     * @return void
     */
    @Test
    public void DefineStringTest() {
        String pre = "Program";
        String post = "ming";
        // Java虚拟机会将其分配到常量池中
        String s1 = "Programming";
        // 被分到堆内存中，并不会将其分配到常量池中，除非调用intern()进行 池化
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        // pre和post都是变量，此时相加相当于是new String()操作
        String s4 = pre + post;
        // "Program"是常量，post是变量，此时相加相当于是new String()操作
        String s5 = "Program" + post;

        System.out.println("s1 == s2: " + (s1 == s2)); // false
        System.out.println("s1 == s3: " + (s1 == s3)); // true
        System.out.println("s1 == s4: " + (s1 == s4)); // false
        System.out.println("s1 == s5: " + (s1 == s5)); // false
        System.out.println("s1 == s4.intern(): " + (s1 == s4.intern())); // true
        System.out.println("s1 == s5.intern(): " + (s1 == s5.intern())); // true

        System.out.println("s2 == s4: " + (s2 == s4)); // false
        System.out.println("s2 == s2.intern(): " + (s2 == s2.intern())); // false
        System.out.println("s2.intern() == s4.intern(): " + (s2.intern() == s4.intern())); // true

    }

    /**
     *
     * 总结：对于在字符传常量池中没有出现过的字符串str，当其调用池话方法(str.intern())时，会将改字符串str的引用从堆的引用变成常量池的引用
     *       这时，str.intern() == str , 返回true。
     * 这种字符串的定义可以为:
     *       String str = s1 + s2;
     *       String str = s1 + "aa";
     *       String str = new String(s1 + s2);
     *       String str = new String(s1 + "aa");

     * 总结：
     * 当使用 String str =  new String(变量 + 常量) 或 String str = 变量 + 常量  (eg: new String(a + "param")) 创建字符创时，
     *  如果 (a + "param") 在常量池中没有相同的字符串，会在堆中创建，在intern()到常量池中，并返回常量池中的地址。eg1
     *  如果 (a + "param") 在常量池中有相同的字符串，则返回堆中的地址； eg2

     * 总结：
     * 1. 类中的字符串字面量会于编译期被解析，然后存储于字节码的constant pool中，在类加载完后会进入常量池。
     * 2. 在编译期无法确定具体值的字符串变量，若要加入到字符串常量池，可以调用intern()方法。
     *    这时如果池中没有与之字面量相同的字符串，它会进入到常量池当中，所有之前指向这块内存的引用，都会指向常量池。
     * 3. 通过new的方式是无法实现把新的字符串放到常量池当中的。创建的时候没与常量池扯上任何关系。
     * @author liuzhen
     * @date 2022/3/1 11:26
     * @param
     * @return void
     */
    @Test
    public void testIntern() {
        // String bb = "aaming";
        // String bb = new String("aaming");
        String aa = "aa";
        String str = aa + "ming";
        // 等价于
//        String str = new String(aa + "ming");

        // 池中没有 "aaming"，调用池话挨批后str的引用会变成池的引用
        System.out.println(str.intern() == "aaming"); // true
        System.out.println(str == "aaming"); // true

//        String strIntern = str.intern();
//        System.out.println(strIntern == "aaming"); // true
//        System.out.println(str == "aaming"); // true
    }

    /**
     * String s = "Hello";s = s + " world!";这两行代码执行后，原始的 String 对象中的内容到底变了没有？
     * 没有。因为 String 被设计成不可变(immutable)类，所以它的所有对象都是不可变对象。在这段代码中，s 原先指向一个 String 对象，内容是 "Hello"
     *
     * 然后我们对 s 进行了“+”操作，那么 s 所指向的那个对象是否发生了改变呢？
     * 没有。这时，s 不指向原来那个对象了，而指向了另一个 String 对象
     *
     * 内容为"Hello world!"，原来那个对象还存在于内存之中，只是 s 这个引用变量不再指向它了。
     *
     * 通过上面的说明，我们很容易导出另一个结论，如果经常对字符串进行各种各样的修改，或者说是不可预见的修改，那么使用 String 来代表字符串的话会引起很大的内存开销。
     * 因为 String 对象建立之后不能再改变，所以对于每一个不同的字符串，都需要一个 String 对象来表示。这时，应该考虑使用 StringBuffer 类，它允许修改，
     * 而不是每个不同的字符串都要生成一个新的对象。并且，这两种类的对象转换十分容易。同时，我们还可以知道，如果要使用内容相同的字符串，不必每次都 new 一个 String。
     * @author liuzhen
     * @date 2021/9/27 15:24
     * @param
     * @return void
     */
    @Test
    public void testStringAdd() {
        // 1.0. 证明原来String对象没有变
        String s = "Hello";
        s = s + " world!";
        System.out.println("s：" + s);

        // 1.1. 字符串常量相加，jvm 会进行优化，不会创建 StringBuilder 对象
        String aaaa = "Hello" + "world" + "!";

        // 1.2. 字符串变量加上常量，会创建 StringBuilder 对象，然后调用 append 方法
        String bbbb = "Hello";
        bbbb += "top";
        bbbb += "bottom";

        // 1.3. for 循环中的字符串变量加上常量，会被优化成 StringBuilder.append()，多次相加只会创建一个 StringBuilder 对象
        String cccc = "Hello";
        for (int i = 0; i < 5; i++) {
            cccc += "world";
        }

        System.out.println("1 ----------------------------------------------------->");

        // 2. new出来的对象都在堆中，地址都不一样，所以不会相等
        String a = new String("a");
        String b = new String(a); // 等价于 String b = a;
        String aIntern = a.intern();
        String bIntern = b.intern();

        System.out.println("a：" + a + " b：" + b); // a：a b：a
        System.out.println(a == b); // false 因为两个new出来的String对象，在堆中的地址是不一样的
        System.out.println(a == bIntern); // false
        System.out.println(b == bIntern); // false
        System.out.println("aIntern == bIntern ? " + (aIntern == bIntern)); // true

        System.out.println("2 ----------------------------------------------------->");


        // 3. 这种给c、d的赋值方式，都会在字符穿常量池中（常量池没有则创建），所以会指向同一地址
        String c = a;
        String d = a;
        System.out.println("c：" + c + " d：" + d);
        System.out.println(c == d); // true 因为都指向堆中的同一个地址
        System.out.println(c.intern() == d.intern()); // true 因为都指向常量池中的同一个地址
        System.out.println(c == c.intern()); // false 因为c指向堆中的地址, d.intern() 指向常量池中的地址
        System.out.println(c == d.intern()); // false 因为c指向堆中的地址, d.intern() 指向常量池中的地址

        System.out.println("3 ----------------------------------------------------->");

        // 4. 简单总结下：对于那些在编译时就能确定的字面量都会存放在运行时常量池中
        String e = "hello " + "world"; // JVM会将此代码优化为 String c = "hello world";
        String f = "hello world";
        System.out.println(e == f); // true

        System.out.println("4 ----------------------------------------------------->");

        // 5. str_1和str_2是变量，在编译时不能确定值，所以不会被放在运行时常量池中，而是在heap中重新new了一块儿内存。
        String str = "helloworld";
        String str_1 = "hello";
        String str_2 = "world";
        String g = str_1 + str_2;

        System.out.println(str == g); // false
        System.out.println(str == g.intern()); // true

        System.out.println("5 ----------------------------------------------------->");

        // 6.
        // 问：下面语句创建了几个StringObject？ 答：一个或者两个。
        // 解析：若果常量池中存在，就在堆里创建一个；如果不存在，就常量池，堆里个创建一个。
        String x = new String("xyz");
        System.out.println("6 ----------------------------------------------------->");
    }

    /**
     * 比较字符长内容是否相等
     */
    @Test
    public void testStringEquals() {
        String x = "abb";
        String y = "abb";
        // System.out.println("x == y " + x == y); // false  因为没用括号括起来 注：要用括号括起来才能是true！！！
        System.out.println("x == y " + (x == y)); // true
        System.out.println("x.equals(y) " + x.equals(y)); // true
        System.out.println("x.equalsIgnoreCase(y) " + (x.equalsIgnoreCase(y))); // true
    }

}
