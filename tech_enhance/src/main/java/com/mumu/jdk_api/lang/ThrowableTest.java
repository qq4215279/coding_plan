/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * ThrowableTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/13 15:49
 */
public class ThrowableTest {
    /**
     Throwable中的常用方法：
     *  void printStackTrace(): 在控制台打印异常的详细信息。 包含了异常的类型,异常的原因,还包括异常出现的位置,在开发和调试阶段,都得使用printStackTrace。
     *    调用重载的方法 printStackTrace(PrintStream stream) 或 printStackTrace(PrintWriter writer)，将堆栈信息输出到指定的流或写入器。
     *  String getMessage(): 返回异常的详细描述信息。 提示给用户的时候,就提示错误原因。
     *  String toString(): 获取异常的类型和异常描述信息(不用)。
     *  Throwable getCause() 返回引发当前异常的原因（根本原因），如果没有指定原因，则返回 null。 该方法用于获取异常的根本原因，通常用于异常链中的异常嵌套。
     *
     *  Throwable initCause(Throwable cause)  设置当前异常的根本原因（引起当前异常的原因）。该方法返回当前异常对象，并且只能在异常对象被创建后调用一次，用于将根本原因与当前异常关联起来。
     *  StackTraceElement[] getStackTrace()  返回异常的堆栈跟踪信息，以数组形式返回 StackTraceElement 对象。 该对象包含了异常发生的类名、方法名和行号等详细信息。
     *  Throwable fillInStackTrace()  更新异常的堆栈跟踪信息，通常用于异常重新抛出时更新异常的堆栈信息(即新异常的堆栈跟踪信息指向 fillInStackTrace() 方法被调用的地方)。该方法返回更新后的异常对象。产生一定的性能开销。
     *
     */


    /**
     * initCause(Throwable cause)
     * @date 2023/7/13 16:47
     * @param
     * @return void
     */
    @Test
    public void initCauseTest() {
        try {
            IOException rootCause = new IOException("Root cause");
            Exception exception = new Exception("Custom exception");
            exception.initCause(rootCause);
            throw exception;
        } catch (Exception e) {
            System.out.println("Exception message: " + e.getMessage());
            System.out.println("Root cause: " + e.getCause());
        }
    }

    public static void main(String[] args) {
        try {
            Class<ThrowableTest> tClass = ThrowableTest.class;
            Method method = tClass.getMethod("getUser");

            ThrowableTest obj = tClass.getConstructor().newInstance();
            method.invoke(obj);
        } catch (Exception e) {
            // 控制台打印异常
            // e.printStackTrace();
            System.out.println("----------------------------------->");
            // 更新堆栈跟踪信息（即新异常的堆栈跟踪信息指向 fillInStackTrace() 方法被调用的地方）
            // at com.mumu.jdk_api.lang.ThrowableTest.main(ThrowableTest.java:45)
            Throwable newException = e.fillInStackTrace();
            newException.printStackTrace();

            System.out.println("异常的名字: " + e.getClass().getSimpleName());
            System.out.println("getMessage: " + e.getMessage());
            System.out.println("toString: " + e.toString());

            // 根本异常
            Throwable cause = e.getCause();
            if (cause != null) {
                System.out.println();
                System.out.println("异常根本原因的名字: " + cause.getClass().getSimpleName());
                System.out.println("根本原因 getMessage: " + cause.getMessage());
                System.out.println("根本原因 toString: " + cause.toString());
            }

            Throwable subCause = cause.getCause();
            if (subCause != null) {
                subCause.printStackTrace();
            }

            // getStackTrace()
            StackTraceElement[] stackTrace = e.getStackTrace();
            printStackTrace(stackTrace);

        }
    }

    private static void printStackTrace(StackTraceElement[] stackTrace) {
        System.out.println();
        System.out.println("start print StackTrace =====>");

        for (StackTraceElement element : stackTrace) {
            System.out.println("  ModuleName: " + element.getModuleName());
            System.out.println("  ModuleVersion: " + element.getModuleVersion());
            System.out.println("  isNativeMethod: " + element.isNativeMethod());
            System.out.println("  FileName: " + element.getFileName());
            System.out.println("  ClassLoaderName: " + element.getClassLoaderName());
            System.out.println("  ClassName: " + element.getClassName());
            System.out.println("  MethodName: " + element.getMethodName());
            System.out.println("  FileName: " + element.getFileName());
            System.out.println("  LineNumber: " + element.getLineNumber());
            System.out.println("-------------------------");
        }
        System.out.println("end print StackTrace ====>");
    }

    public User getUser() {
        try {
            // int a = 10 / 0;
            printName();
        } catch (Exception e) {
            throw new ArithmeticException();
        } finally {
        }
        return new User();
    }

    private void printName() {
        User user = null;
        System.out.println("性别是：" + user.getSex());

    }

}
