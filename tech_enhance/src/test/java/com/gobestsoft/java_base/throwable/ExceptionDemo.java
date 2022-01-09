/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

/**
 * ExceptionDemo
 *
 * @author liuzhen
 * @version 1.0.0 2020/8/10 17:04
 */
public class ExceptionDemo {

    private static final ExceptionDemo EXCEPTION_TEST = new ExceptionDemo();

    private ExceptionDemo(){

    }

    public static ExceptionDemo getInstance() {
        return EXCEPTION_TEST;
    }

    /**
     * 不处理
     * @author liuzhen
     * @date 2021/9/27 13:49
     * @param n
     * @return int
     */
    public int division_NoTreatment(int n) {
        int denominator = 0;
//        if (denominator == 0) {
//            throw new RuntimeException("分母异常~~~~~");
//        }

        int res = n / denominator;

        return res;
    }

    /**
     * try catch
     * @author liuzhen
     * @date 2021/9/27 13:48
     * @param n
     * @return int
     */
    public int division_Try_Catch(int n) {
        int a = 0;
        try {
            a = n / 0;
        } catch (Exception e) {
            System.out.println("e.printStackTrace() ->  无");
            e.printStackTrace();  // 无
            System.out.println("getMeesage()->" + e.getMessage());
            System.out.println("e.toString() ->" + e.toString());
            System.out.println("不能除0。。。。。");
        } finally {
        }
        return a;
    }

    /**
     * throws
     * @param n
     * @return
     * @throws ArithmeticException
     */
    public int division_Throws(int n) throws ArithmeticException {

        return n / 0;
    }

    public static void main(String[] args) throws ArithmeticException {
        // 1.不做处理
        //        int division = ExceptionTest.getInstance().division_NoTreatment(10);
        //        System.out.println("结果1---------------> " + division);

        // 2.try catch
        int division2 = ExceptionDemo.getInstance().division_Try_Catch(10);
        System.out.println("try catch 可以继续往下走。。。");
        System.out.println("结果2--------------> " + division2);

        // 3.throws
        int division3 = ExceptionDemo.getInstance().division_Throws(10);
        System.out.println("结果3--------------> " + division3);

    }

}
