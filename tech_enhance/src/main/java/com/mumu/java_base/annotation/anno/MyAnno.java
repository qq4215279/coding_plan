/*
 * Copyright 2020-2023, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_base.annotation.anno;

import com.mumu.java_base.annotation.PersonEnum;

public @interface MyAnno {

    int value();

    PersonEnum per();

    MyAnno2 anno2();

    String[] strs();
    /*String name() default "张三";*/
    /*String show2();
    
    Person per();
    MyAnno2 anno2();
    
    String[] strs();*/

}
