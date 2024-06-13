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
    /** ConfigXXXInfo 对象 */
    private final Object target;

    public UserInterceptor(Object target) {
        this.target = target;
    }

    /**
     * 在CGLIB库中，intercept 方法是由 MethodInterceptor 接口的实现类提供的，用于拦截对代理对象方法的调用。MethodInterceptor 是CGLIB中的一个回调接口，当在代理实例上调用方法时，这个方法会被调用。
     * 注：被代理类需要有无参构造方法
     * CGLIB的优缺点：
     * 优点：
     * 不需要接口就可以进行代理。
     * 性能通常比JDK动态代理更好（尽管在大多数现代JVM上，这个差异很小）。
     * 缺点：
     * 无法代理final方法，因为CGLIB是通过继承的方式创建代理类，无法覆盖final方法。
     * 代理类是目标类的子类，因此如果目标类是final的，那么它也不能被代理。
     *
     *
     * intercept 方法通常用于在不修改原始类的情况下，在方法调用前后添加附加行为（例如日志记录、性能监视或事务管理）。它还可以用于修改方法调用的参数、改变返回值或者抛出异常。
     * @param obj obj 这个参数是代理对象的实例，也就是方法调用的对象。你可以通过这个对象访问代理对象的属性和其他方法，但要小心，直接使用这个对象调用方法可能会导致递归调用 intercept 方法本身。
     * @param method method 这个参数是一个 java.lang.reflect.Method 对象，它代表了原始对象上被调用的方法。这个对象包含了方法的所有元数据，例如方法名、返回类型、参数类型等。
     * @param args args 这个参数是一个对象数组，包含了传递给方法的参数。如果方法没有参数，则它将是一个空数组。这个数组与方法声明的参数顺序相匹配。
     * @param proxy proxy 这是一个CGLIB提供的方法代理对象，在 intercept 方法中，你可以使用这个代理对象来调用原始对象的方法。MethodProxy 提供了 invokeSuper 方法，它可以被用来调用原始对象上的方法，而不是代理对象上的方法，这是它与 java.lang.reflect.Method 对象最大的不同。
     *
     * invokeSuper:
     * invokeSuper 方法是 MethodProxy 类的一个方法，仅存在于CGLIB中。
     * 它用于调用被代理类的原始方法（即超类中的方法）。
     * 这种调用会绕过所有的拦截器，直接调用超类中的实现，因此不会导致对拦截器的递归调用。
     * 当你在CGLIB的MethodInterceptor中实现方法拦截时，应该使用 invokeSuper 来调用被代理实例的原始方法。
     *  Object result = proxy.invokeSuper(obj, args);
     *
     * invoke:
     * invoke 方法是 java.lang.reflect.Method 类的一个方法，它是Java反射API的一部分。
     * 使用 invoke 可以在任何对象上调用任何方法，包括私有方法（前提是设置了相应的访问权限）。
     * 当你使用 invoke 调用一个方法时，如果这个方法被代理，那么任何相关的拦截器都会被触发。
     * 这可能导致递归调用拦截器，如果你不小心处理你的拦截逻辑的话。
     * Object result = method.invoke(obj, args);
     *
     * @return java.lang.Object
     * @date 2024/4/11 17:17
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        try {
            // 在原始方法调用之前的逻辑
            System.out.println("Before: " + method.getName());

            // 1.1. 使用 proxy.invokeSuper 调用原始对象的方法，不会再次拦截。注意这里是用 proxy 而不是 method 对象来调用
            Object result = proxy.invokeSuper(obj, args);

            // 1.2. 使用 method.invoke 调用方法，如果 obj 是代理对象，可能会触发拦截逻辑
            // Object result = method.invoke(obj, args); // 这样做可能导致递归调用拦截器

            // 在原始方法调用之后的逻辑
            System.out.println("After: " + method.getName());

            // 返回方法调用的结果
            return result;
        } catch (Exception e) {
            // 异常处理逻辑
            throw e;
        }
    }
}
