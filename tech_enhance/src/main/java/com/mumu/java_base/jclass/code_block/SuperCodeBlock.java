/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.jclass.code_block;

/**
 * SuperCodeBlock
 * 代码块执行顺序: 静态代码块 > 构造代码块 > 构造函数 > 普通代码块　
 * @author liuzhen
 * @version 1.0.0 2021/8/2 16:11
 */
public class SuperCodeBlock {

    /**
     * 代码块执行顺序: 静态代码块 > 构造代码块 > 构造函数 > 普通代码块　
     * 1. 静态代码块: 在java类中（方法中不能存在静态代码块）使用static关键字和{}声明的代码块
     *  一般情况下，如果有些代码需要在项目启动的时候就执行，这时候就需要静态代码块。比如一个项目启动需要加载的很多配置文件等资源，我们就可以都放入静态代码块中。
     *
     * 2. 构造代码块: 在java类中使用{}声明的代码块（和静态代码块的区别是少了static关键字）
     *  构造代码块在创建对象时被调用，每次创建对象都会调用一次，但是优先于构造函数执行。
     *  和构造函数的作用类似，都能对对象进行初始化，并且只要创建一个对象，构造代码块都会执行一次。但是反过来，
     *  构造函数则不一定每个对象建立时都执行（多个构造函数情况下，建立对象时传入的参数不同则初始化使用对应的构造函数）。
     *  利用每次创建对象的时候都会提前调用一次构造代码块特性，我们可以做诸如统计创建对象的次数等功能。
     *
     * 3. 构造函数:
     *  a. 构造函数的命名必须和类名完全相同。在java中普通函数可以和构造函数同名，但是必须带有返回值；
     *  b. 构造函数的功能主要用于在类的对象创建时定义初始化的状态。它没有返回值，也不能用void来修饰。这就保证了它不仅什么也不用自动返回，而且根本不能有任何选择。
     *      而其他方法都有返回值，即使是void返回值。尽管方法体本身不会自动返回什么，但仍然可以让它返回一些东西，而这些东西可能是不安全的；
     *  c. 构造函数不能被直接调用，必须通过new运算符在创建对象时才会自动调用；而一般的方法是在程序执行到它的时候被调用的；
     *  d. 当定义一个类的时候，通常情况下都会显示该类的构造函数，并在函数中指定初始化的工作也可省略，不过Java编译器会提供一个默认的构造函数.此默认构造函数是不带参数的。
     *      而一般的方法不存在这一特点
     *
     * 4. 普通代码块：普通代码块和构造代码块的区别是，构造代码块是在类中定义的，而普通代码块是在方法体中定义的。且普通代码块的执行顺序和书写顺序一致。
     *      方法被调用时执行
     *
     * 父类与子类执行顺序: 静态代码块内容先执行，接着执行父类构造代码块和构造方法，然后执行子类构造代码块和构造方法。
     * 对象的初始化顺序：首先执行父类静态的内容，父类静态的内容执行完毕后，接着去执行子类的静态的内容，当子类的静态内容执行完毕之后，再去看父类有没有构造代码块，
     * 如果有就执行父类的构造代码块，父类的构造代码块执行完毕，接着执行父类的构造方法；父类的构造方法执行完毕之后，它接着去看子类有没有构造代码块
     * 如果有就执行子类的构造代码块。子类的构造代码块执行完毕再去执行子类的构造方法。
     * 总结（静静父父子子）：父类静态代码块 -> 子类静态代码块 -> 父类构造代码块 -> 父类构造方法 -> 子类构造代码块 -> 子类构造方法
     */


    /** 父类静态代码块 */
    static {
        System.out.println("静 - 父类静态代码块");
    }

    /** 父类构造代码块 */
    {
        System.out.println("父 - 父类构造代码块");
    }

    public SuperCodeBlock() {
        System.out.println("父 - 父类无参构造函数执行~");
    }

    public SuperCodeBlock(String str) {
        System.out.println("父 - 父类有参构造函数执行~" + str);
    }


    /**
     * 普通代码块 -- 方法调用时执行
     */
    public void normalBlock(){
        // 定义普通代码块
        {
            System.out.println("普通 - 父类普通代码块");
        }
    }

    public static void main(String[] args) {
        System.out.println("开始执行main方法====>");

        SuperCodeBlock superCodeBlock = new SuperCodeBlock();
        superCodeBlock.normalBlock();
        System.out.println("--------------->");
        SuperCodeBlock superCodeBlock1 = new SuperCodeBlock("我有参数");
        superCodeBlock1.normalBlock();

        // 结果
       /*
            静 - 父类静态代码块
            开始执行main方法====>
            父 - 父类构造代码块
            父 - 父类无参构造函数执行~
            普通 - 父类普通代码块
            --------------->
            父 - 父类构造代码块
            父 - 父类有参构造函数执行~我有参数
            普通 - 父类普通代码块
        */
    }
}






