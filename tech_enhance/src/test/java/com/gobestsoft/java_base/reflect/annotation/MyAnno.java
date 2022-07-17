/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.gobestsoft.java_base.reflect.annotation;

import com.sun.corba.se.spi.orbutil.threadpool.Work;

public @interface MyAnno {

     int value();
    Person per();
    MyAnno2 anno2();
    String[] strs();
     /*String name() default "张三";*/
     /*String show2();

     Person per();
     MyAnno2 anno2();

     String[] strs();*/


}
