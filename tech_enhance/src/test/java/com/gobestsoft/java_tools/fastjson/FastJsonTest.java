package com.gobestsoft.java_tools.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * FastJsonTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/8/3 18:01
 */
public class FastJsonTest {

    @Test
    public void demo01() {
        JSONObject parse = JSON.parseObject("{name:'aaa'}");
        String name = parse.getString("name");
        System.out.println(name);
    }

}

class User {
    public String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
