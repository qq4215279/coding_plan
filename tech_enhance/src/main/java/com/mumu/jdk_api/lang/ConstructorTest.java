/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.jdk_api.lang;

import com.mumu.common.pojo.User;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;

/**
 * ConstructorTest
 *
 * @author liuzhen
 * @version 1.0.0 2023/7/8 16:35
 */
public class ConstructorTest {

    /**
     * newInstance(Object... initargs)   创建并返回一个新的对象实例，使用给定的参数初始化构造方法。
     *
     * getName()  获取构造方法的名称(全限定类名)。
     * getModifiers()  获取构造方法的修饰符，返回一个表示修饰符的整数值。
     * Class<?>[] getParameterTypes()   获取构造方法的参数类型，返回一个Class对象数组，表示参数类型列表。
     * toGenericString() 获取构造方法的泛型签名字符串表示。该方法返回一个包含构造方法完整泛型签名的字符串。eg: public com.mumu.common.pojo.User()
     * getParameterCount() 获取当前构造函数中参数的个数。
     * isVarArgs()   检查构造方法是否使用了可变参数。
     * isSynthetic() 既校验是否是内部类。
     * isAccessible() 是否是私有构造方法
     * setAccessible(boolean flag)   检查或设置构造方法的可访问性。
     *
     * getAnnotation(Class<A> annotationClass)   获取指定注解类型的注解对象。
     * Annotation[] getDeclaredAnnotations()  获取构造方法上声明的所有注解。
     * Annotation[][] getParameterAnnotations() 获取构造方法参数上的注解信息。该方法返回一个二维数组，其中每个数组元素表示对应参数上的注解数组。row: 一个参数; col: 一个参数上的多个注解。
     * getDeclaringClass()   获取声明该构造方法的类，返回一个 Class 对象。
     *
     * getAnnotatedReceiverType() 返回一个 AnnotatedType对象，该对象表示使用类型来指定此 Executable对象所表示的方法/构造函数的接收器类型。
     * getAnnotatedReturnType() 返回一个 AnnotatedType对象，该对象表示使用类型来指定此可执行文件所表示的方法/构造函数的返回类型。
     * Type[] getGenericExceptionTypes() 获取构造方法声明的异常类型。该方法返回一个 Type[] 数组，表示构造方法可能抛出的异常类型。
     * Type[] getGenericParameterTypes() 获取构造方法的泛型参数类型。该方法返回一个 Type[] 数组，表示构造方法的参数类型，包括泛型类型信息。
     * TypeVariable<构造器<T>>[] getTypeParameters() 获取构造方法的类型参数。返回一个 TypeVariable<Constructor<?>>[] 数组，表示构造方法声明的类型参数。
     *
     */

    @Test
    public void test01() throws NoSuchMethodException {
        Class<User> userClass = User.class;
        Constructor<User> constructor = userClass.getConstructor();

        System.out.println(constructor.getName());
        int modifiers = constructor.getModifiers();
        System.out.println(Modifier.isPrivate(modifiers));

        // constructor.getDeclaredAnnotations()

        Class<?>[] parameterTypes = constructor.getParameterTypes();

        System.out.println("是否是私有构造方法 isAccessible()  " + constructor.isAccessible());

        System.out.println("是否是私有构造方法 isAccessible()  " + constructor.getParameterCount());

        System.out.println(" toGenericString()  " + constructor.toGenericString());

        System.out.println("---------------->");

        Constructor<User> constructor1 = userClass.getConstructor(String.class, int.class);
        constructor1.setAccessible(true);
        try {
            User user = constructor1.newInstance("testName", 18);
            System.out.println(user.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() throws NoSuchMethodException {
        Class<?> clazz = MyClass22.class;
        Constructor<?> constructor = clazz.getConstructor();

        TypeVariable<? extends Constructor<?>>[] typeParameters = constructor.getTypeParameters();

        System.out.println("Type Parameters: ");
        for (TypeVariable<? extends Constructor<?>> typeParameter : typeParameters) {
            System.out.println("   " + typeParameter.getName());
        }
    }
}

class MyClass22<T, U> {
    public MyClass22() {
        // Constructor implementation
    }
}