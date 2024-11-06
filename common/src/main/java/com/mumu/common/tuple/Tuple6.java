package com.mumu.common.tuple;

import java.io.Serializable;

/**
 * Tuple6 是一个包含6个对象的简单对象 一般用于一个函数需要返回6个值时方便使用,或者需要把一些6个对象组合的内容放入容器时使用
 * @author liuzhen
 * @version 1.0.0 2024/11/6 13:55
 */
public class Tuple6<T1, T2, T3, T4, T5, T6> implements Serializable {

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
  /** 第六个对象 */
  private T6 _6;

  /**
   * 构造函数
   */
  public Tuple6() {

  }

  /**
   * 构造函数
   */
  public Tuple6(T1 _1_, T2 _2_, T3 _3_, T4 _4_, T5 _5_, T6 _6_) {
    this._1 = _1_;
    this._2 = _2_;
    this._3 = _3_;
    this._4 = _4_;
    this._5 = _5_;
    this._6 = _6_;
  }

  /**
   * 获得第一个对象
   * @return _1
   */
  public T1 get_1() {
    return _1;
  }

  /**
   * 获得第二个对象
   * @return _2
   */
  public T2 get_2() {
    return _2;
  }

  /**
   * 第三个对象
   * @return _3
   */
  public T3 get_3() {
    return _3;
  }

  /**
   * 第四个对象
   * @return _4
   */
  public T4 get_4() {
    return _4;
  }

  /**
   * 第五个对象
   * @return _5
   */
  public T5 get_5() {
    return _5;
  }

  /**
   * 第六个对象
   * @return _6
   */
  public T6 get_6() {
    return _6;
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
   * @param _6 the _6 to set
   */
  public void set_6(T6 _6) {
    this._6 = _6;
  }

  /**
   * 获取对象
   * @return Object
   */
  public Object getValue(int index) {
    if (1 == index) {
      return this.get_1();
    } else if (2 == index) {
      return this.get_2();
    } else if (3 == index) {
      return this.get_3();
    } else if (4 == index) {
      return this.get_4();
    } else if (5 == index) {
      return this.get_5();
    } else if (6 == index) {
      return this.get_6();
    }
    return null;
  }

  /**
   * 返回3个对象组合的哈希
   * @return int
   */
  public int hashCode() {
    return _1.hashCode() ^ _2.hashCode() ^ _3.hashCode() ^ _4.hashCode() ^ _5.hashCode()
        ^ _6.hashCode();
  }

  /**
   * 重载equals方法
   * @return boolean
   */
  @SuppressWarnings("rawtypes")
  public boolean equals(Object other) {
    if (other instanceof Tuple6) {
      Tuple6 to = (Tuple6) other;
      return (_1.equals(to._1) && _2.equals(to._2) && _3.equals(to._3)) && _4.equals(to._4)
          && _5.equals(to._5) && _6.equals(to._6);
    } else {
      return false;
    }
  }

  /**
   * 重载toString方法
   */
  public String toString() {
    return "[1=" + _1 + ", 2=" + _2 + ", 3=" + _3 + ", 4=" + _4 + ", 5=" + _5 + ", 6=" + _6 + "]";
  }
}
