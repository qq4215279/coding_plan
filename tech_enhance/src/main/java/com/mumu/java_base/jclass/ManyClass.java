/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.jclass;

/**
 * ManyClass
 * 一个java文件多个类
 * @author liuzhen
 * @version 1.0.0 2021/8/2 14:06
 */

/**
 * 一个.java文件中可以定义多个class，但只能有一个public类。可以有多个类，且若这个.java文件中有一个public类型的class，则这个class名需与.java文件名一致。
 * final类、abstract类、class类可以有多个。
 * 使用跟正常使用类的方式一样
 */
public class ManyClass {

}

/**
 * 报错：不能有多个public外部类
 */
// public class ManyClass2 {
// }

/**
 * 可以有多个class类
 */
class C1 {

}
class C2 {

}

/**
 * 可以有多个final类
  */
final class F1 {

}
final class F2 {

}

/**
 * 可以有多个abstract类
 */
abstract class A1{

}
abstract class A2{

}
