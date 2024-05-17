package com.mumu.dp2_7_proxy.cjlibDynamic;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * UserInterceptor
 *
 * @author liuzhen
 * @version 1.0.0 2024/4/11 17:12
 */
public class UserInterceptor implements MethodInterceptor {


    /**
     * 在CGLIB库中，intercept 方法是由 MethodInterceptor 接口的实现类提供的，用于拦截对代理对象方法的调用。MethodInterceptor 是CGLIB中的一个回调接口，当在代理实例上调用方法时，这个方法会被调用。
     *
     * intercept 方法通常用于在不修改原始类的情况下，在方法调用前后添加附加行为（例如日志记录、性能监视或事务管理）。它还可以用于修改方法调用的参数、改变返回值或者抛出异常。
     * @param obj obj 这个参数是代理对象的实例，也就是方法调用的对象。你可以通过这个对象访问代理对象的属性和其他方法，但要小心，直接使用这个对象调用方法可能会导致递归调用 intercept 方法本身。
     * @param method method 这个参数是一个 java.lang.reflect.Method 对象，它代表了原始对象上被调用的方法。这个对象包含了方法的所有元数据，例如方法名、返回类型、参数类型等。
     * @param args args 这个参数是一个对象数组，包含了传递给方法的参数。如果方法没有参数，则它将是一个空数组。这个数组与方法声明的参数顺序相匹配。
     * @param proxy proxy 这是一个CGLIB提供的方法代理对象，在 intercept 方法中，你可以使用这个代理对象来调用原始对象的方法。MethodProxy 提供了 invokeSuper 方法，它可以被用来调用原始对象上的方法，而不是代理对象上的方法，这是它与 java.lang.reflect.Method 对象最大的不同。
     * @return java.lang.Object
     * @date 2024/4/11 17:17
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("Before method call");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("After method call");
        return result;
    }
}
