package com.gobestsoft.java_base.access_modifier.public_;

/**
 * PublicDemo
 * public访问修饰符
 * @author liuzhen
 * @version 1.0.0 2021/10/8 10:51
 */
public class PublicDemo {

    /**
     * 修饰符       当前类       同 包	     子 类（在其他包）       其他包
     * public	     √           √       	  √                      √
     * protected     √           √       	  √                      ×
     * default    	 √           √            ×                      ×
     * private       √           ×            ×        	             ×
     */

    public String name;

    public PublicDemo(String name) {
        this.name = name;
    }
}
