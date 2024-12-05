/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.framework.mvc.servlet;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ParamHelper
 * 参数帮助类
 * @author liuzhen
 * @version 1.0.0 2024/12/5 22:41
 */
public class ParamHelper {
    /** DEFAULT_VALUE_MAP */
    private static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new HashMap<Class<?>, Object>();
    /** SIMPLE_CLASS */
    private static final Set<Class<?>> SIMPLE_CLASS = new HashSet<Class<?>>();
    /** DEFAULT_WRAPPERCLASS_MAP */
    private static final Map<Class<?>, Class<?>> DEFAULT_WRAPPERCLASS_MAP = new HashMap<Class<?>, Class<?>>();


    static {
        DEFAULT_VALUE_MAP.put(boolean.class, false);
        DEFAULT_VALUE_MAP.put(byte.class, 0);
        DEFAULT_VALUE_MAP.put(char.class, 0);
        DEFAULT_VALUE_MAP.put(short.class, 0);
        DEFAULT_VALUE_MAP.put(int.class, 0);
        DEFAULT_VALUE_MAP.put(long.class, 0);
        DEFAULT_VALUE_MAP.put(float.class, 0);
        DEFAULT_VALUE_MAP.put(double.class, 0);

        DEFAULT_WRAPPERCLASS_MAP.put(boolean.class, Boolean.class);
        DEFAULT_WRAPPERCLASS_MAP.put(byte.class, Byte.class);
        DEFAULT_WRAPPERCLASS_MAP.put(char.class, Character.class);
        DEFAULT_WRAPPERCLASS_MAP.put(short.class, Short.class);
        DEFAULT_WRAPPERCLASS_MAP.put(int.class, Integer.class);
        DEFAULT_WRAPPERCLASS_MAP.put(long.class, Long.class);
        DEFAULT_WRAPPERCLASS_MAP.put(float.class, Float.class);
        DEFAULT_WRAPPERCLASS_MAP.put(double.class, Double.class );

        for (Class<?> clazz : DEFAULT_VALUE_MAP.keySet()) {
            SIMPLE_CLASS.add(clazz);
        }

        for (Class<?> clazz : DEFAULT_WRAPPERCLASS_MAP.values()) {
            SIMPLE_CLASS.add(clazz);
        }
    }

    /**
     * 获取指定类型对象的默认值
     * @param clazz clazz
     * @return java.lang.Object
     * @date 2024/12/5 22:42
     */
    public static Object getDefaultValue(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return DEFAULT_VALUE_MAP.get(clazz);
        }
        return null;
    }

    /**
     * 判断Clazz是否是简单Class
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:42
     */
    public static boolean isSimpleClass(Class<?> clazz) {
        return SIMPLE_CLASS.contains(clazz);
    }

    /**
     * 获得包装类
     * @param clazz clazz
     * @return java.lang.Class<?>
     * @date 2024/12/5 22:43
     */
    public static Class<?> getWrapper(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return DEFAULT_WRAPPERCLASS_MAP.get(clazz);
        }
        return clazz;
    }

    /**
     * 类型转换
     * @param src 要转换的对象
     * @param clazz 目标类型
     * @return T
     * @date 2024/12/5 22:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T castTo(Object src, Class<T> clazz) {
        if (null == src) {
            return (T) getDefaultValue(clazz);
        }

        return castTo(src, src.getClass(), clazz);
    }

    /**
     * 将src从原始类型转换为目标类型
     * @param src src
     * @param fromType fromType
     * @param toType toType
     * @return T
     * @date 2024/12/5 22:44
     */
    @SuppressWarnings("unchecked")
    public static <T, F> T castTo(Object src, Class<F> fromType, Class<T> toType) {
        if (fromType.getName().equals(toType.getName())) {
            return (T) src;
        } else if (toType.isAssignableFrom(fromType)) {
            return (T) src;
        }

        if (fromType == String.class) {
            return String2Object((String)src, toType);
        }

        if (fromType.isArray() && !toType.isArray()) {
            return castTo(Array.get(src, 0), toType);
        }

        if (fromType.isArray() && toType.isArray()) {
            int len = Array.getLength(src);
            Object result = Array.newInstance(toType.getComponentType(), len);
            for (int i = 0; i < len; i++) {
                Array.set(result, i, castTo(Array.get(src, i), toType.getComponentType()));
            }

            return (T) result;
        }

        return (T) getDefaultValue(toType);
    }

    /**
     * 将Str类型转换为指定类型
     * @param str str
     * @param type type
     * @return T
     * @date 2024/12/5 22:44
     */
    @SuppressWarnings("unchecked")
    public static <T> T String2Object(String str, Class<T> type) {
        try {
            if (isBoolean(type)) {
                return (T) Boolean.valueOf(str);
            } else if (isByte(type)) {
                return (T) Byte.valueOf(str);
            } else if (isChar(type)) {
                return (T) Character.valueOf(str.charAt(0));
            } else if (isInteger(type)) {
                return (T) Integer.valueOf(str);
            } else if (isFloat(type)) {
                return (T) Float.valueOf(str);
            } else if (isLong(type)) {
                return (T) Long.valueOf(str);
            } else if (isDouble(type)) {
                return (T) Double.valueOf(str);
            } else if (isShort(type)) {
                return (T) Short.valueOf(str);
            } else if (isString(type)) {
                return (T) str;
            } else if (isStringLike(type)) {
                return (T) str;
            } else {
                Constructor<T> constructor = (Constructor<T>) getWrapper(type).getConstructor(String.class);
                if (null != constructor) {
                    return constructor.newInstance(str);
                }
            }
        } catch (Throwable t) {
            // 不处理
        }

        return (T) getDefaultValue(type);
    }

    /**
     * 是否是布尔型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isBoolean(Class<?> clazz) {
        return is(clazz, boolean.class) || is(clazz, Boolean.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isByte(Class<?> clazz) {
        return is(clazz, byte.class) || is(clazz, Byte.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isChar(Class<?> clazz) {
        return is(clazz, char.class) || is(clazz, Character.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isShort(Class<?> clazz) {
        return is(clazz, short.class) || is(clazz, Short.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isInteger(Class<?> clazz) {
        return is(clazz, int.class) || is(clazz, Integer.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isLong(Class<?> clazz) {
        return is(clazz, long.class) || is(clazz, Long.class);
    }


    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isFloat(Class<?> clazz) {
        return is(clazz, float.class) || is(clazz, Float.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isDouble(Class<?> clazz) {
        return is(clazz, double.class) || is(clazz, Double.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isString(Class<?> clazz) {
        return is(clazz, String.class);
    }

    /**
     * 是否是指定类型
     * @param clazz clazz
     * @return boolean
     * @date 2024/12/5 22:45
     */
    public static boolean isStringLike(Class<?> clazz) {
        return CharSequence.class.isAssignableFrom(clazz);
    }


    /**
     * 判断两个是否为同一类型
     * @param clazz1 clazz1
     * @param clazz2 clazz2
     * @return boolean
     * @date 2024/12/5 22:45
     */
    private static boolean is(Class<?> clazz1, Class<?> clazz2) {
        return clazz1 == clazz2;
    }

}
