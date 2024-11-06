package com.mumu.common.tuple;

import java.io.Serializable;

/**
 * Tuple 是一个包含2个对象的简单对象 一般用于一个函数需要返回2个值时方便使用,或者需要把一些成对的内容放入容器时使用
 * @author liuzhen
 * @version 1.0.0 2024/11/6 13:53
 */
public class Tuple<L, R> implements Serializable {
  private static final long serialVersionUID = 1;

  /** left对象 */
  public L left;
  /** right对象 */
  public R right;

  /**
   * 构造函数
   */
  public Tuple() {

  }

  /**
   * 构造函数
   */
  public Tuple(L left, R right) {
    this.left = left;
    this.right = right;
  }

  /**
   * 返回2个对象组合的哈希
   */
  public int hashCode() {
    return left.hashCode() ^ right.hashCode();
  }

  /**
   * 重载equals方法
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (other instanceof Tuple) {
      Tuple to = (Tuple) other;
      return (left.equals(to.left) && right.equals(to.right));
    } else {
      return false;
    }
  }

  /**
   * 重载toString方法
   */
  public String toString() {
    return "[left=" + left + ", right=" + right + "]";
  }

}
