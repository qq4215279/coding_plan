/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

/**
 * Java关键字介绍
 */
package com.mumu.java_base.keyword;

/**
 * 知识点：
 * 常见关键字：
 * 1. instanceof
 * 　instanceof 严格来说是Java中的⼀个双⽬运算符，⽤来测试⼀个对象是否为⼀个类的实例，⽤法为：boolean result = obj instanceof Class
 *   其中 obj 为⼀个对象，Class 表示⼀个类或者⼀个接⼝，当 obj 为 Class 的对象，或者是其直接或间接⼦类，或者是其接⼝的实现类，结果result 都返回 true，否则返回false。
 * 　注意：编译器会检查 obj 是否能转换成右边的class类型，如果不能转换则直接报错，如果不能确定类型，则通过编译，具体看运⾏时定。obj 必须为引⽤类型，不能是基本类型
 *
 * 2. native
 *  　native ⽤来修饰⽅法，⽤ native 声明的⽅法表示告知 JVM 调⽤，该⽅法在外部定义，我们可以⽤任何语⾔去实现它。 简单地讲，⼀个native Method就是⼀个 Java 调⽤⾮ Java 代码的接⼝。
 *    native 语法：
 * 　　1. 修饰⽅法的位置必须在返回类型之前，和其余的⽅法控制符前后关系不受限制。
 * 　　2. 不能⽤ abstract 修饰，也没有⽅法体，也没有左右⼤括号。
 * 　　3. 返回值可以是任意类型
 * 　　我们在⽇常编程中看到native修饰的⽅法，只需要知道这个⽅法的作⽤是什么，⾄于别的就不⽤管了，操作系统会给我们实现。
 *
 *  Object类中
 *  未被native修饰的方法：equals(Object obj);  finalize();  toString();
 *  被native修饰的方法：clone();  getClass();  hashCode();  notify();  notifyAll();  wait(long timeoutMillis);  registerNatives();
 *
 * 3. static
 *   　static 是Java的⼀个关键字，可以⽤来
 *     1. 修饰成员变量
 *     2. 修饰成员⽅法
 *     3. 构造静态代码块
 *     4. 实现静态内部类
 *     5. 实现静态导包
 *
 * 4. final
 *  1. 修饰变量
 *  2. 修饰⽅法
 *  3. 修饰类
 *
 * 5. this
 *  - 调⽤成员变量：this.name
 *  - 调⽤普通⽅法：this.getName();
 *  - 调⽤构造⽅法：this();
 *  - 返回当前对象：return this;
 *
 * 6. super
 *  - 调⽤⽗类的成员属性：super.age
 *  - 调⽤⽗类的⽅法：super.getAge();
 *  - 调⽤⽗类的构造⽅法：super()
 *  - 返回当前对象：return this;
 *  this 和 super 不能出现在同⼀个构造⽅法中！！
 *
 * 7. synchronized
 * 通过 synchronized 修饰的⽅法或代码块，能够同时保证这段代码的原⼦性、可⻅性和有序性，进⽽能够保证这段代码的线程安全。
 * 可重⼊：字⾯意思就是⼀个线程获取到这个锁了，在未释放这把锁之前，还能进⼊获取锁。
 *
 * 8. volatile
 *
 * 9. default
 *
 * 10. transient 将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会被序列化。
 *   静态变量不管是不是transient关键字修饰，都不会被序列化
 *
 *   Java序列化提供两种方式：
 *   1. 实现Serializable接口。
 *   2. 实现Exteranlizable接口。需要重写writeExternal和readExternal方法，它的效率比Serializable高一些，
 *      并且可以决定哪些属性需要序列化（即使是transient修饰的），但是对大量对象，或者重复对象，则效率低。
 *      实现了Externalizable接口，哪一个属性被序列化使我们手动去指定的，即使是transient关键字修饰也不起作用。
 *   transient关键字总结
 *
 * transient关键字总结：为我们提供了便利，你只需要实现Serilizable接口，将不需要序列化的属性前添加关键字transient，序列化对象的时候，
 *                      这个属性就不会序列化到指定的目的地中。像银行卡、密码等等这些数据。这个需要根据业务情况了。
 */
