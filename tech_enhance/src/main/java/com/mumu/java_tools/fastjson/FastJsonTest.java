package com.mumu.java_tools.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * FastJsonTest
 *
 * @author liuzhen
 * @version 1.0.0 2021/8/3 18:01
 */
public class FastJsonTest {

    /**
     * astJson是啊里巴巴的的开源库，用于对JSON格式的数据进行解析和打包。
     * 特点如下：
     * 1. 能够支持将java bean序列化成JSON字符串，也能够将JSON字符串反序列化成Java bean。
     * 2. 顾名思义，fastjson操作 JSON的速度是非常快的。
     * 3. 无其他包的依赖。
     * 4. 使用比较方便。
     *
     * fastjson常用API
     * fastjson API 入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
     *
     * 1. 把JSON文本parse为JSONObject或者JSONArray: public static final Object parse(String text);
     * 2. 把JSON文本parse成JSONObject: public static final JSONObject parseObject(String text);
     * 3. 把JSON文本parse为JavaBean: public static final <T> T parseObject(String text, Class<T> clazz);
     * 4. 把JSON文本parse成JSONArray: public static final JSONArray parseArray(String text);
     * 5. 把JSON文本parse成JavaBean集合: public static final <T> List<T> parseArray(String text, Class<T> clazz);
     *
     * 6. 将JavaBean序列化为JSON文本: public static final String toJSONString(Object object);
     * 7. 将JavaBean序列化为带格式的JSON文本: public static final String toJSONString(Object object, boolean prettyFormat);
     *
     * 注意，序列化的类必须有一个无参构造方法
     * 被序列化的类需要有一个无参的构造方法。否则会报错:
     * Exception in thread "main" com.alibaba.fastjson.JSONException: default constructor not found. class User
     *
     * 如果你没有重写构造方法，那么每个类都自带一个无参的构造方法，但是如果你重写了一个有参的构造方法，那么默认的无参构造方法会被覆盖，
     * 这时候就需要你手动写一个无参的构造方法进去。所以我建议保险起见，需要被json序列化的类最好都手动写一个无参的构造方法进去。
     * 在低版本中转换的时候会直接抛以上异常信息（测试版本：fastjson-1.1.12）。但是高版本(fastjson-1.2.58)就不会报错。
     * 建议在定义javabean时都把无参和有参定义。
     */

    /**
     * java对象转换为json字符串
     */
    @Test
    public void objToJson() {
        // 1. 简单对象转换
        User user = new User("root", "123456");
        //调用toJSONString()
        String userJson = JSON.toJSONString(user);
        System.out.println("java类转换为json串：" + userJson);

        // 2. 集合转json串
        User user1 = new User("zhangsan", "123456");
        User user2 = new User("lisi", "000");
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        // 调用toJSONString()
        String usersjson = JSON.toJSONString(users);
        System.out.println("集合转json串：" + usersjson);

        // 3. 复杂java类转换对象
        UserGroup userGroup = new UserGroup("userGroup", users);
        //调用toJSONString()
        String userGroupJson = JSON.toJSONString(userGroup);
        System.out.println("复杂java类转换json串：" + userGroupJson);
    }

    /**
     * json字符串转为java类
     * 注：字符串中使用双引号需要转义 (" --> \"),这里使用的是单引号,易读性会好很多。
     * json串以“{}”包裹，转换为java类时，使用：parseObject();
     * json串以“[]”包裹，转换为java类时，使用：parseArray();
     */
    @Test
    public void jsonToObj(){
        /*
         * 1.
         * json字符串转简单java对象
         * 字符串：{"password":"123456","username":"dmego"}
         */
        String jsonStr1 = "{'password':'123456','username':'ggf'}";
        // 调用parseObject()
        User user = JSON.parseObject(jsonStr1, User.class);
        System.out.println("json字符串转简单java对象:"+user.toString());

        /*
         * 2.
         * json字符串转List<Object>对象
         * 字符串：[{"password":"123123","username":"zhangsan"},
         *        {"password":"321321","username":"lisi"}]
         */
        String jsonStr2 = "[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]";
        // 调用parseArray()将字符串转为集合
        List<User> users = JSON.parseArray(jsonStr2, User.class);
        System.out.println("json字符串转List<Object>对象:"+users.toString());

        /*
         * 3.
         * json字符串转复杂java对象
         * 字符串：{"name":"userGroup","users":[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]}
         * */
        String jsonStr3 = "{'name':'userGroup','users':[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]}";
        UserGroup userGroup = JSON.parseObject(jsonStr3, UserGroup.class);
        System.out.println("json字符串转复杂java对象:"+userGroup);
    }


    @Test
    public void demo01() {
        JSONObject parse = JSON.parseObject("{name:'aaa'}");
        String name = parse.getString("name");
        System.out.println(name);
    }

}

class User {
    public String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", password='" + password + '\'' + '}';
    }
}

class UserGroup {
    private String name;
    private List<User> users = new ArrayList<User>();

    public UserGroup() {
    }

    public UserGroup(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
               "name='" + name + '\'' +
               ", users=" + users +
               '}';
    }
}

