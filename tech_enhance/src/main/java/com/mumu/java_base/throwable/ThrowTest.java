/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.throwable;

/**
 * ThrowTest
 * 抛出异常throw
 * @author liuzhen
 * @version 1.0.0 2021/3/19 22:16
 */
public class ThrowTest {

    /**
     * 抛出异常throw：
     * 在编写程序时，我们必须要考虑程序出现问题的情况。比如，在定义方法时，方法需要接受参数。那么，当调用方 法使用接受到的参数时，首先需要先对参数数据进行合法的判断，
     * 数据若不合法，就应该告诉调用者，传递合法的 数据进来。这时需要使用抛出异常的方式来告诉调用者。
     * 在java中，提供了一个throw关键字，它用来抛出一个指定的异常对象。那么，抛出一个异常具体如何操作呢？
     *      1. 创建一个异常对象。封装一些提示信息(信息可以自己编写)。
     *      2. 需要将这个异常对象告知给调用者。怎么告知呢？怎么将这个异常对象传递到调用者处呢？通过关键字throw 就可以完成。throw 异常对象。
     *      throw用在方法内，用来抛出一个异常对象，将这个异常对象传递到调用者处，并结束当前方法的执行。
     * 使用格式：throw new 异常类名(参数);
     *      eg: throw new NullPointerException("要访问的arr数组不存在");
     *          throw new ArrayIndexOutOfBoundsException("该索引在数组中不存在，已超出范围");
     *
     *  注意：如果产生了问题，我们就会throw将问题描述类即异常进行抛出，也就是将问题返回给该方法的调用者。
     *      那么对于调用者来说，该怎么处理呢？一种是进行捕获处理，另一种就是继续讲问题声明出去，使用throws 声明处理
     */


    public static int method(int type) {
        switch (type) {
            case 1:
                System.out.println("哈哈哈");
                break;
            case 2:
                System.out.println("呵呵呵");
                break;
            default:
                throw new RuntimeException("类型错误，嘤嘤嘤！");
        }
        return type;
    }

    public static int getElement(int[] arr, int index) {
        //判断 索引是否越界
        if (index < 0 || index > arr.length - 1) {
            /*
              判断条件如果满足，当执行完throw抛出异常对象后，方法已经无法继续运算。
              这时就会结束当前方法的执行，并将异常告知给调用者。这时就需要通过异常来解决。
            */
            throw new ArrayIndexOutOfBoundsException("哥们，角标越界了~~~");
        }
        int element = arr[index];
        return element;
    }


    public static void main(String[] args) {
        // 4. throw
        System.out.println("开始");
        int type = ThrowTest.method(4);
        System.out.println("type: " + type);
        System.out.println("结束~~~~~");
    }

}
