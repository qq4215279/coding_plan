/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.attrsync;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Main
 *
 * @author liuzhen
 * @version 1.0.0 2024/10/6 15:35
 */
public class Main {

    private static final Set<Class<?>> WRAPPER_TYPES;

    static {
        Set<Class<?>> tempSet = new HashSet<>();
        tempSet.add(Boolean.class);
        tempSet.add(Character.class);
        tempSet.add(Byte.class);
        tempSet.add(Short.class);
        tempSet.add(Integer.class);
        tempSet.add(Long.class);
        tempSet.add(Float.class);
        tempSet.add(Double.class);
        tempSet.add(Void.class);

        WRAPPER_TYPES = Collections.unmodifiableSet(tempSet);
    }

    public static boolean isWrapperType(Object obj) {
        if (obj == null) {
            return false;
        }
        return WRAPPER_TYPES.contains(obj.getClass());
    }

    public static void main(String[] args) {
        System.out.println(isWrapperType(123)); // true
        System.out.println(isWrapperType(123.45)); // true
        System.out.println(isWrapperType("Hello")); // false
        System.out.println(isWrapperType(true)); // true
    }

}
