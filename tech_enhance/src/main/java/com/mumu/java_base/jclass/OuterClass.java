/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.jclass;

import com.mumu.common.pojo.AbstractPerson;
import com.mumu.common.pojo.IPerson;
import com.mumu.common.pojo.User;
import org.junit.Test;

/**
 * OuterClass
 * 内部类 Demo
 * @author liuzhen
 * @version 1.0.0 2021/10/21 17:19
 */
public class OuterClass {

    /**
     * 内部类分类：
     * 1. 成员内部类
     * 2. 局部内部类
     * 3. 匿名内部类
     * 4. 静态内部类
     */

    private String name = "tom";
    private int age = 18;
    private static boolean isAdult = true;

    private OuterClass() {
    }

    private OuterClass(String name) {
        this.name = name;
    }

    public int getMemberInnerClassAgeMethod() {
        MemberInnerClass memberInnerClass = new MemberInnerClass();
        return memberInnerClass.getAge();
    }

    /**
     * 1. 成员内部类：
     * a. 当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员。
     * 如果要访问外部类的同名成员，需要以下面的形式进行访问：外部类.this.成员变量; 外部类.this.成员方法
     * b. 成员内部类可以无条件地访问外部类的成员。外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问。
     * c. 成员内部类是依附外部类而存在的，也就是说，如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。
     * d. 内部类可以拥有private访问权限、protected访问权限、public访问权限及包访问权限。比如上面的例子，
     * 如果成员内部类Inner用private修饰，则只能在外部类的内部访问，如果用public修饰，则任何地方都能访问；
     * 如果用protected修饰，则只能在同一个包下或者继承外部类的情况下访问；如果是默认default访问权限，则只能在同一个包下访问。这一点和外部类有一点不一样，
     * 外部类只能被public和包访问两种权限修饰。我个人是这么理解的，由于成员内部类看起来像是外部类的一个成员，所以可以像类的成员一样拥有多种权限修饰。
     */
    public class MemberInnerClass {
        private boolean isAdult = false;

        public MemberInnerClass() {
        }

        public int getAge() {
            System.out.println("外部类访问：" + OuterClass.this.name);

            System.out.println("跟外部类变量名称相同，默认访问的是内部类变量：" + isAdult);

            // 内部类没有同名变量，默认访问的是外部类的成员变量
            return age;
        }

    }

    /**
     * 2. 局部内部类（普通内部类，在方法内定义使用）：
     * a. 局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
     * b. 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的。
     *
     * 访问普通内部类，需要先创建外部类的对象，然后通过 外部类名.new 创建内部类的实例
     */
    public void LocalInnerClassDemo() {
        class LocalInnerClass {
            private int age = 28;
        }

        System.out.println("局部内部类：" + new LocalInnerClass().age);
    }

    /**
     * 3. 匿名内部类：
     * - 概念：没有名字的局部内部类。
     * - 作用：方便创建子类的对象，最终的目的是简化代码的编写。
     * - 格式(在方法内定义使用)：
     *        T t = new 接口名 | 抽象类名 | 或类() {
     *                  重写方法
     *              }
     *
     * a. 匿名内部类是没有访问修饰符的。
     * b. new 匿名内部类，这个类首先是要存在的。如果我们将那个InnerClass接口注释掉，就会出现编译出错。
     * c. 注意getInnerClass()方法的形参，第一个形参是用final修饰的，而第二个却没有。同时我们也发现第二个形参在匿名内部类中没有使用过，
     * 所以当所在方法的形参需要被匿名内部类使用，那么这个形参就必须为final。
     * d. 匿名内部类是没有构造方法的。因为它连名字都没有何来构造方法。
     *
     */
    @Test
    public void noNameInnerClassDemo() {
        // 1. new 一个接口
        // new 一个接口写法1
        /*IPerson p = new IPerson() {
            @Override
            public void eat() {
                System.out.println("人 吃 人 ");
            }
        };*/
        // new 一个接口写法2
        IPerson p = () -> System.out.println("人 吃 人 ");
        p.eat();

        // 2. new 一个抽象类
        // 等号右边：是匿名内部类，定义并创建该接口的子类对象。等号左边：是多态赋值，接口类型引用指向子类对象
        AbstractPerson abstractPerson = new AbstractPerson() {
            @Override
            public void eat() {
                System.out.println("人 吃 人 ");
            }
        };
        abstractPerson.eat();

        // 3. new 一个类
        User user = new User() {
            @Override
            public void eat(String food) {
                super.eat(food);
            }

            @Override
            public void play() {
                System.out.println("看电影");
            }
        };
        user.eat();
    }


    /**
     * 4. 静态内部类：
     * a. 静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。静态内部类是不需要依赖于外部类。
     * b. 它不能被外部类的非static成员变量或者方法访问。
     * 静态内部类的好处是：外部类可以访问内部类的所有⽅法和属性，包括私有⽅法和私有属性。
     */
    public static class StaticInnerClass {
        public static final int bb = 999;
        public int a = 1;

        public int getInt() {
            System.out.println("静态内部类 非静态方法");
            return 999;
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass("xingxing");

        // 1. 成员内部类
        System.out.println("1. 成员内部类 ------------------------>");
        System.out.println(outerClass.getMemberInnerClassAgeMethod());

        // 2. 局部内部类
        System.out.println("2. 局部内部类 ------------------------>");
        outerClass.LocalInnerClassDemo();


        // 3. 匿名内部类
        System.out.println("3. 匿名内部类 ------------------------>");
        outerClass.noNameInnerClassDemo();

        // 4. 静态内部类
        System.out.println("4. 静态内部类 ------------------------>");
        int bb = OuterClass.StaticInnerClass.bb;
        int bbb = StaticInnerClass.bb;
        System.out.println("bb: " + bb);
        System.out.println("bbb: " + bbb);

        StaticInnerClass staticInnerClass = new StaticInnerClass();
        int anInt = staticInnerClass.getInt();
        System.out.println("anInt: " + anInt);
    }

}

