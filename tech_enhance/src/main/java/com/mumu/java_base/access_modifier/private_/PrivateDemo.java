package com.mumu.java_base.access_modifier.private_;

/**
 * PrivateDemo
 * private 访问修饰符
 * @author liuzhen
 * @version 1.0.0 2021/10/8 8:41
 */
public class PrivateDemo {

    /**
     * 修饰符       当前类       同 包	     子 类（在其他包）       其他包
     * public	     √           √       	  √                      √
     * protected     √           √       	  √                      ×
     * default    	 √           √            ×                      ×
     * private       √           ×            ×        	             ×
     */

    private String name;

    public PrivateDemo(String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }
}
