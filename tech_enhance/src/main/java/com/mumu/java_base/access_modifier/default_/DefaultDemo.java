package com.mumu.java_base.access_modifier.default_;

/**
 * default_DefaultDemo
 * default访问修饰符
 * @author liuzhen
 * @version 1.0.0 2021/10/8 8:48
 */
public class DefaultDemo {

    /**
     * 修饰符       当前类       同 包	     子 类（在其他包）       其他包
     * public	     √           √       	  √                      √
     * protected     √           √       	  √                      ×
     * default    	 √           √            ×                      ×
     * private       √           ×            ×        	             ×
     */

    String name;

    public DefaultDemo(String name) {
        this.name = name;
    }

}
