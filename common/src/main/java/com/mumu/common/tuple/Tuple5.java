package com.mumu.common.tuple;

import java.io.Serializable;

/**
 * Tuple5
 * 是一个包含5个对象的简单对象 一般用于一个函数需要返回5个值时方便使用,或者需要把一些5个对象组合的内容放入容器时使用
 * @author liuzhen
 * @version 1.0.0 2024/11/6 13:55
 */
public class Tuple5<T1, T2, T3, T4, T5> implements Serializable {
  /** serialVersionUID */
  private static final long serialVersionUID = 1L;
  /** 第一个对象 */
  private T1 _1;
  /** 第二个对象 */
  private T2 _2;
  /** 第三个对象 */
  private T3 _3;
  /** 第四个对象 */
  private T4 _4;
  /** 第五个对象 */
  private T5 _5;

  /**
   * 构造函数
   */
  public Tuple5() {

  }

  /**
   * 构造函数
   * @param _1_ _1_
   * @param _2_ _2_
   * @param _3_ _3_
   */
  public Tuple5(T1 _1_, T2 _2_, T3 _3_, T4 _4_, T5 _5_) {
    this._1 = _1_;
    this._2 = _2_;
    this._3 = _3_;
    this._4 = _4_;
    this._5 = _5_;
  }

  /**
   * 获得第一个对象
   */
  public T1 get_1() {
    return _1;
  }

  /**
   * 获得第二个对象
   * @return the _2
   */
  public T2 get_2() {
    return _2;
  }

  /**
   * 第三个对象
   * @return the _3
   */
  public T3 get_3() {
    return _3;
  }

  /**
   * 第四个对象
   * @return the _4
   */
  public T4 get_4() {
    return _4;
  }

  /**
   * 第五个对象
   * @return the _5
   */
  public T5 get_5() {
    return _5;
  }

  /**
   * @param _1 the _1 to set
   */
  public void set_1(T1 _1) {
    this._1 = _1;
  }

  /**
   * @param _2 the _2 to set
   */
  public void set_2(T2 _2) {
    this._2 = _2;
  }

  /**
   * @param _3 the _3 to set
   */
  public void set_3(T3 _3) {
    this._3 = _3;
  }

  /**
   * @param _4 the _4 to set
   */
  public void set_4(T4 _4) {
    this._4 = _4;
  }

  /**
   * @param _5 the _5 to set
   */
  public void set_5(T5 _5) {
    this._5 = _5;
  }

  /**
   * 获取对象
   * @param index index
   */
  public Object getValue(int index){
    if(1 == index){
      return this.get_1();
    }else if(2 == index){
      return this.get_2();
    }else if(3 == index){
      return this.get_3();
    }else if(4 == index){
      return this.get_4();
    }else if(5 == index){
      return this.get_5();
    }
    return null;
  }

  /**
   * 返回3个对象组合的哈希
   */
  public int hashCode () {
    return _1.hashCode() ^ _2.hashCode() ^ _3.hashCode() ^ _4.hashCode() ^ _5.hashCode();
  }

  /**
   * 重载equals方法
   * @param other other
   * @return boolean
   */
  @SuppressWarnings("rawtypes")
  public boolean equals(Object other) {
    if (other instanceof Tuple5) {
      Tuple5 to = (Tuple5)other;
      return (_1.equals(to._1) && _2.equals(to._2) && _3.equals(to._3)) && _4.equals(to._4) && _5.equals(to._5);
    } else {
      return false;
    }
  }

  /**
   * 重载toString方法
   * @return String
   */
  public String toString () {
    return "[1=" + _1 + ", 2=" + _2 + ", 3=" + _3 + ", 4=" + _4 + ", 5=" + _5 +"]";
  }
}
