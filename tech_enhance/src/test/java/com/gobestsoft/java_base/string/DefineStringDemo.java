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
     *    这一点可以用 javap -c StringEqualTest.class 命令获得 class 文件对应的 JVM 字节码指令就可以看出来。
     * @author liuzhen
     * @date 2021/9/27 14:58
     * @param
     * @return void
     */
    @Test
    public void DefineStringTest() {
        // Java虚拟机会将其分配到常量池中
        String s1 = "Programming";
        // 被分到堆内存中，并判断常量池中有没有"Programming"，没有的话会在常量池中创建
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s5); // true
        System.out.println(s1 == s6); // false
        System.out.println(s2 == s6); // false
        System.out.println(s2.intern() == s6.intern()); // true
        System.out.println(s1 == s6.intern()); // true
        System.out.println(s2 == s2.intern()); // false
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
    public void test02() {
        String s = "Hello";
        s = s + " world!";
        System.out.println("s：" + s);

        System.out.println("-------------->");

        String a = new String("aa");
        String b = new String(a);

        System.out.println("a：" + a + " b：" + b);
        System.out.println(a == b); // false 因为两个new出来的String对象，在堆中的地址是不一样的

        System.out.println("-------------->");

        String c = a;
        String d = a;
        System.out.println("c：" + c + " d：" + d);
        System.out.println(c == d); // true 因为都指向常量池的同一个地址

        System.out.println("==================================================>");

        // 简单总结下：对于那些在编译时就能确定的字面量都会存放在运行时常量池中
        String e = "hello " + "world"; // JVM会将此代码优化为 String c = "hello world";
        String f = "hello world";
        System.out.println(e == f); // true

        // str_1和str_2是变量，在编译时不能确定值，所以不会被放在运行时常量池中，而是在heap中重新new了一块儿内存。
        String str = "helloworld";
        String str_1 = "hello";
        String str_2 = "world";
        String g = str_1 + str_2;

        System.out.println(str == g); // false
        System.out.println(str == g.intern()); // true

        // 问：下面语句创建了几个StringObject？ 答：一个或者两个。
        // 解析：若果常量池中存在，就在堆里创建一个；如果不存在，就常量池，堆里个创建一个。
        String x = new String("xyz");
    }

    /**
     * 字符串相加test
     * @author liuzhen
     * @date 2021/9/27 18:17
     * @param
     * @return void
     */
    @Test
    public void test03() {
        // 1.字符串常量相加，jvm 会进行优化，不会创建 StringBuilder 对象
        String a = "Hello" + "world" + "!";

        // 2.字符串变量加上常量，会创建 StringBuilder 对象，然后调用 append 方法
        String b = "Hello";
        b += "top";
        b += "bottom";

        // 3. for 循环中的字符串变量加上常量，会被优化成 StringBuilder.append()，多次相加只会创建一个 StringBuilder 对象
        String c = "Hello";
        for (int i = 0; i < 5; i++) {
            c += "world";
        }
    }

    // todo  来自知乎
    @Test
    public void test04() {
        String a = "a";
        String param = new String("param" + a);
        String paramSame = param.intern();
        String param3 = new String("parama");

        System.out.println(param == paramSame); // true
        System.out.println(param == param3); // false
        System.out.println(param3 == paramSame); // false
    }

}
