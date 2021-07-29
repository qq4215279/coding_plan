/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.javaBase.exception;

/**
 * ExceptionTest
 *
 * @author liuzhen
 * @version 1.0.0 2020/8/10 17:04
 */
public class ExceptionTest {

    private static final ExceptionTest EXCEPTION_TEST = new ExceptionTest();

    private ExceptionTest(){

    }

    public static ExceptionTest getInstance() {
        return EXCEPTION_TEST;
    }

    /*
    * 不处理
    * */
    public int division_NoTreatment(int n) {
        int denominator = 0;
//        if (denominator == 0) {
//            throw new RuntimeException("分母异常~~~~~");
//        }

        int res = n / denominator;

        return res;
    }

    /*
    * try catch
    * */
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
    public int division_Throws(int n) throws ArithmeticException{

        return n / 0;
    }

    public static void main(String[] args) throws ArithmeticException {

        // 不做处理
//        int division = ExceptionTest.getInstance().division_NoTreatment(10);
//        System.out.println("结果1---------------> " + division);

        // try catch
        int division2 = ExceptionTest.getInstance().division_Try_Catch(10);
        System.out.println("try catch 可以继续往下走。。。");
        System.out.println("结果2--------------> " + division2);
//
//        // throws
        int division3 = ExceptionTest.getInstance().division_Throws(10);
        System.out.println("结果3--------------> " + division3);

    }

}
