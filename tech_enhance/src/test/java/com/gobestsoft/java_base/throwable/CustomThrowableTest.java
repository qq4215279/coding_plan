/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.throwable;

import javax.security.auth.login.LoginException;

/**
 * CustomThrowableTest
 * 自定义异常
 * @author liuzhen
 * @version 1.0.0 2022/1/9 17:09
 */
public class CustomThrowableTest {

    /**
     * 自定义异常：
     * 为什么需要自定义异常类:
     * 我们说了Java中不同的异常类,分别表示着某一种具体的异常情况,那么在开发中总是有些异常情况是SUN没有定义 好的,此时我们根据自己业务的异常情况来定义异常类。例如年龄负数问题,考试成绩负数问题等等。
     * 在上述代码中，发现这些异常都是JDK内部定义好的，但是实际开发中也会出现很多异常,这些异常很可能在JDK中 没有定义过,例如年龄负数问题,考试成绩负数问题.那么能不能自己定义异常呢？
     * 什么是自定义异常类:
     *  在开发中根据自己业务的异常情况来定义异常类. 自定义一个业务逻辑异常: RegisterException。一个注册异常类。
     * 异常类如何定义:
     *      1. 自定义一个编译期异常: 自定义类 并继承于 java.lang.Exception 。
     *      2. 自定义一个运行时期的异常类:自定义类 并继承于 java.lang.RuntimeException 。
     *
     *
     * 自定义异常类:
     *         java提供的异常类,不够我们使用,需要自己定义一些异常类
     *     格式:
     *         public class XXXException extends Exception | RuntimeException {
     *             添加一个空参数的构造方法
     *             添加一个带异常信息的构造方法
     *         }
     *      注意:
     *         1.自定义异常类一般都是以Exception结尾,说明该类是一个异常类
     *         2.自定义异常类,必须的继承Exception或者RuntimeException
     *             继承Exception:那么自定义的异常类就是一个编译期异常,如果方法内部抛出了编译期异常,就必须处理这个异常,要么throws,要么try...catch
     *             继承RuntimeException:那么自定义的异常类就是一个运行期异常,无需处理,交给虚拟机处理(中断处理)
     */

    // 模拟数据库中已存在账号
    private static String[] names = {"bill", "hill", "jill"};

    public static void main(String[] args) {
        // 调用方法
        try { // 可能出现异常的代码
            checkUsername("nill");
            System.out.println("注册成功");
            // 如果没有异常就是注册成功
        } catch (RegisterException | LoginException e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    // 判断当前注册账号是否存在
    // 因为是编译期异常，又想调用者去处理 所以声明该异常
    public static boolean checkUsername(String uname) throws LoginException, RegisterException {
        for (String name : names) {
            if (name.equals(uname)) {
                //如果名字在这里面 就抛出登陆异常
                throw new RegisterException("亲" + name + "已经被注册了！");
            }
        }
        return true;
    }


    public static String[] usernames = {"张三","李四","王五"};

    // 定义一个方法,对用户输入的中注册的用户名进行判断
    public static void checkUsername2(String username) /*throws RegisterException*/ {
        // 遍历存储已经注册过用户名的数组,获取每一个用户名
        for (String name : usernames) {
            //使用获取到的用户名和用户输入的用户名比较
            if (name.equals(username)) {
                //true:用户名已经存在,抛出RegisterException异常,告知用户"亲，该用户名已经被注册";
                try {
                    throw new RegisterException("亲，该用户名已经被注册");
                } catch (RegisterException e) {
                    e.printStackTrace();
                    return; //结束方法
                }
            }
        }

        //如果循环结束了,还没有找到重复的用户名,提示用户"恭喜您,注册成功!";
        System.out.println("恭喜您,注册成功!");
    }

}

/**
 * 自定义注册异常类 -- 业务逻辑异常
 */
class RegisterException extends Exception {
    public RegisterException() {
    }

    /**
     * 添加一个带异常信息的构造方法
     * 查看源码发现,所有的异常类都会有一个带异常信息的构造方法,方法内部会调用父类带异常信息的构造方法,让父类来处理这个异常信息
     * @param message
     */
    public RegisterException(String message) {
        super(message);
    }
}