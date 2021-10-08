package com.gobestsoft.java_base.access.private_;

/**
 * PrivateDemo
 *
 * @author liuzhen
 * @version 1.0.0 2021/10/8 8:41
 */
public class PrivateDemo {

    /**
     * 修饰符       当前类       同 包	     子 类       其他包
     * public	     √           √       	  √           √
     * protected     √           √       	  √           ×
     * default    	 √           √            ×           ×
     * private       √           ×            ×        	  ×
     */

    private String name;

    public PrivateDemo(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }
}
