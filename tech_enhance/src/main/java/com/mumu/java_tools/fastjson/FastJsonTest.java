/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.java_tools.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mumu.common.pojo.User;
import com.mumu.common.pojo.UserGroup;
import com.mumu.common.redis.ZLibUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * FastJsonTest
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
   *
   * 序列化: JSON.toJSONBytes(obj, SerializerFeature.WriteClassName)
   * 反序列化: (T) JSON.parseObject(ZLibUtil.decompress(objBytes), clas)
   *
   */

  /**
   * java对象转换为json字符串
   */
  @Test
  public void objToJson() {
    // 1. 简单对象转换
    User user = new User("root", "123456");
    // 调用toJSONString()
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
    // 调用toJSONString()
    String userGroupJson = JSON.toJSONString(userGroup);
    System.out.println("复杂java类转换json串：" + userGroupJson);
  }

  /**
   * json字符串转为java类 注：字符串中使用双引号需要转义 (" --> \"),这里使用的是单引号,易读性会好很多。
   * json串以“{}”包裹，转换为java类时，使用：parseObject(); json串以“[]”包裹，转换为java类时，使用：parseArray();
   */
  @Test
  public void jsonToObj() {
    /*
     * 1.
     * json字符串转简单java对象
     * 字符串：{"password":"123456","username":"dmego"}
     */
    String jsonStr1 = "{'password':'123456','username':'ggf'}";
    // 调用parseObject()
    User user = JSON.parseObject(jsonStr1, User.class);
    System.out.println("json字符串转简单java对象:" + user.toString());

    /*
     * 2.
     * json字符串转List<Object>对象
     * 字符串：[{"password":"123123","username":"zhangsan"},
     *        {"password":"321321","username":"lisi"}]
     */
    String jsonStr2 = "[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]";
    // 调用parseArray()将字符串转为集合
    List<User> users = JSON.parseArray(jsonStr2, User.class);
    System.out.println("json字符串转List<Object>对象:" + users.toString());

    /*
     * 3.
     * json字符串转复杂java对象
     * 字符串：{"name":"userGroup","users":[{"password":"123123","username":"zhangsan"},{"password":"321321","username":"lisi"}]}
     * */
    String jsonStr3 = "{'name':'userGroup','users':[{'password':'123123','username':'zhangsan'},{'password':'321321','username':'lisi'}]}";
    UserGroup userGroup = JSON.parseObject(jsonStr3, UserGroup.class);
    System.out.println("json字符串转复杂java对象:" + userGroup);
  }

  @Test
  public void mapToJson() {
    // 1. 将 Map<String, Object> 转换为 JSON 字符串：
    // 创建一个示例 Map
    Map<String, Object> map = new HashMap<>();
    map.put("name", "Alice");
    map.put("age", 30);
    map.put("isStudent", false);

    // 将 Map 转换为 JSON 字符串
    String jsonString = JSON.toJSONString(map);
    System.out.println("JSON String: " + jsonString);

    System.out.println("================>");

    // 2. 将 JSON 字符串转换回 Map<String, Object>：
    // 假设这是你的 JSON 字符串
    String jsonString2 = "{\"name\":\"Alice\",\"age\":30,\"isStudent\":false}";

    // 将 JSON 字符串转换为 Map
    // TypeReference 是一种在 Java 中处理泛型类型参数的常用技巧，尤其在需要反射或序列化/反序列化操作涉及泛型时非常有用。
    Map<String, Object> map2 = JSON.parseObject(jsonString2, new TypeReference<>() {});
    Map<String, Object> map3 = JSON.parseObject(jsonString2, Map.class);
    System.out.println("Map2: " + map2);
    System.out.println("Map3: " + map3);

    // 访问 Map 中的元素
    String name = (String) map2.get("name");
    Integer age = (Integer) map2.get("age");
    Boolean isStudent = (Boolean) map2.get("isStudent");

    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Is Student: " + isStudent);

  }

  @Test
  public void ComplexTypeWithoutTypeReference() {
    String jsonString = "{\"numbers\":[1,2,3,4,5],\"moreNumbers\":[6,7,8,9,10]}";

    // 使用 Fastjson 解析复杂泛型类型，不使用 TypeReference 的例子。这将展示类型信息丢失的问题。
    // 不使用 TypeReference 来解析 JSON 字符串
    Map<String, List<Integer>> map = JSON.parseObject(jsonString, Map.class);

    // 打印结果
    System.out.println("Map: " + map);

    // 尝试访问其中的一个列表
    List<Integer> numbers = map.get("numbers");
    System.out.println("Numbers: " + numbers);

    System.out.println("=================>");
    System.out.println("正确学法如下：");

    // 使用 TypeReference 来解析 JSON 字符串
    Map<String, List<Integer>> map2 = JSON.parseObject(jsonString, new TypeReference<Map<String, List<Integer>>>() {});

    // 打印结果
    System.out.println("Map2: " + map2);

    // 安全访问其中的一个列表
    List<Integer> numbers2 = map2.get("numbers");
    System.out.println("Numbers2: " + numbers2);
  }



  @Test
  public void demo01() {
    JSONObject parse = JSON.parseObject("{name:'aaa'}");
    String name = parse.getString("name");
    System.out.println(name);
    System.out.println(parse.toJSONString());


    JSONObject parse2 = JSON.parseObject("");
    parse2.put("name", "111");
    System.out.println(parse2);
  }

  @Test
  public void jsonObjectDemo() {
    String param = StringUtils.EMPTY;
    JSONObject jsonObject;

    if (StringUtils.isNotEmpty(param)) {
      jsonObject = JSON.parseObject(param);
    } else {
      jsonObject = new JSONObject();
    }

    // int值
    jsonObject.put("int1", 1);
    jsonObject.put("int2", 2);

    // long值
    jsonObject.put("long1", 1000L);
    jsonObject.put("long2", 2000L);

    // String值
    jsonObject.put("String1", "String1");
    jsonObject.put("String2", "String2");

    // list值
    List<Long> list = List.of(1000L, 2000L);
    jsonObject.put("list", list);

    // set值
    Set<Integer> set = Set.of(1, 2, 3);
    jsonObject.put("set", set);

    // map值
    Map<String, Integer> map = Map.of("key1", 1, "key2", 2);
    jsonObject.put("map", map);

    param = jsonObject.toJSONString();
    System.out.println("param: " + param);

  }

  @Test
  public void jsonObjectDemo2() {
    String param = "{\"long2\":2000,\"int2\":2,\"int1\":1,\"long1\":1000,\"set\":[2,1,3],\"list\":[1000,2000],\"String2\":\"String2\",\"String1\":\"String1\",\"map\":{\"key1\":1,\"key2\":2}}";
    JSONObject jsonObject = JSON.parseObject(param);

    System.out.println("int1: " + jsonObject.getIntValue("int1"));

    System.out.println("int11: " + jsonObject.getLongValue("int1"));


    System.out.println("不存在int key: " + jsonObject.getLongValue("int11"));

    // 抛转换异常：java.lang.NumberFormatException: For input string: "String1"
    // System.out.println("string2int: " +jsonObject.getIntValue("String1"));
    System.out.println("String1: " + jsonObject.getString("String1"));

    // 获取list 方式1
    List<Long> list = jsonObject.getObject("list", new TypeReference<List<Long>>() {});
    System.out.println("list: " + list);
    // 获取list 方式2
    List<Long> list2 = jsonObject.getJSONArray("list").toJavaList(Long.class);
    System.out.println("list2: " + list2);

    JSONArray list4 = jsonObject.getJSONArray("list3");
    System.out.println("list4: " + list4);

    // 写法错误！！！无法解析 com.alibaba.fastjson.JSONException: can not cast to : java.util.Set
    // Set<Integer> set = jsonObject.getObject("set", Set.class);
    // System.out.println("set: " + set);


    // 方法2：直接转成Set（不推荐，因为TypeReference不能直接传Set接口，会用HashSet）
    Set<Integer> set2 = jsonObject.getObject("set", new TypeReference<Set<Integer>>(){});
    System.out.println("set: " + set2);

    // 获取map
    Map<String, Integer> map = jsonObject.getObject("map", new TypeReference<Map<String, Integer>>() {});
    System.out.println("map: " + map);

    JSONObject mapJsonObject = jsonObject.getJSONObject("map");
    Map<String, Object> innerMap = mapJsonObject.getInnerMap();
    System.out.println("innerMap: " + innerMap);
  }

  /**
   * 序列化压测
   */
  @Test
  public void serializeTest() throws InterruptedException {
    /*
    *
      1.1. json序列化大小: 96 json序列化平均耗时: 0.05
      1.2. json反序列化平均耗时: 1.2

      2.1. 压缩序列化json大小: 95 压缩序列化json平均耗时: 0.15
      2.2. 解压平均耗时: 0.0

      1.1. json序列化大小: 34929 json序列化平均耗时: 0.55
      1.2. json反序列化平均耗时: 0.15

      2.1. 压缩序列化json大小: 26329 压缩序列化json平均耗时: 0.9
      2.2. 解压平均耗时: 0.2
    *  */
    forceSerializeTest(new User("xiaoming", "123456", 18, 1), 20);


    Thread.sleep(2000);
    System.out.println("------------------------------->");

    forceSerializeTest(getLargeUser(), 20);

    Thread.sleep(2000);
  }

  private void forceSerializeTest(User user, int times) {
    byte[] jsonBytes00 = JSON.toJSONBytes(user, SerializerFeature.WriteClassName);

    // 1. json序列化 大小 时间
    new Thread(() -> {
      byte[] jsonBytes = null;
      long total = 0L;
      for (int i = 0; i < times; i++) {
        long start = System.currentTimeMillis();
        jsonBytes = JSON.toJSONBytes(user, SerializerFeature.WriteClassName);
        long end = System.currentTimeMillis();
        total += end - start;
      }

      System.out.println("1.1. json序列化大小: " + jsonBytes.length + " json序列化平均耗时: " + total / (double) times);
    }).start();

    // 1.2. 反序列化时间
    byte[] jsonBytes2 = JSON.toJSONBytes(user, SerializerFeature.WriteClassName);
    new Thread(() -> {
      long total = 0L;
      for (int i = 0; i < times; i++) {
        long start = System.currentTimeMillis();
        User user1 = JSON.parseObject(jsonBytes2, User.class);
        long end = System.currentTimeMillis();
        total += end - start;
      }

      System.out.println("1.2. json反序列化平均耗时: " + total / (double) times);
    }).start();

    // 2.1. 压缩序列化json后大小 压缩时间
    new Thread(() -> {
      long total = 0L;
      byte[] compress = null;
      for (int i = 0; i < times; i++) {
        long start = System.currentTimeMillis();
        compress = ZLibUtil.compress(jsonBytes00);
        long end = System.currentTimeMillis();
        total += end - start;
      }

      System.out.println("2.1. 压缩序列化json大小: " + compress.length + " 压缩序列化json平均耗时: " + total / (double) times);
    }).start();

    // 2.2. 解压时间
    new Thread(() -> {
      long total = 0L;
      byte[] compress = ZLibUtil.compress(jsonBytes00);
      for (int i = 0; i < times; i++) {
        long start = System.currentTimeMillis();
        ZLibUtil.decompress(compress);
        long end = System.currentTimeMillis();
        total += end - start;
      }

      System.out.println("2.2. 解压平均耗时: " + total / (double) times);
    }).start();
  }

  private User getLargeUser() {
    User user = new User("xiaoming", "123456", 18, 1);
    // 36k 30000多字符
    user.setText("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAHlAfQDASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD1OGFRg4qztGMbB6cDpVaFyUHrWP4q8RRaLp/DjznHAzXQcRkeNfE6WcJ0+1P71+G2/wD1q5PSbPajSy8u/JzVK0jk1K+a8uSTuOee1dHHGEUADoK1hFbnPUn0Q9VH90cewpwAI+6PyoApR1rQwuxAqnqB+VPCKB0FJt5zTsGgd33AAZ6UuB6UmDRj3NOwXfcCoPG2msoH8I/IUuT1zRyaLBdkbKD/AAj8qYyDpgflUp461Gy807BchZR0wKYyD0FSkUxge1GgXIto9BQVGelOII6im4JPSi0QuGxc9BTwoz0FIq8VIBgUrILjkAPYVKqDIOB+VMUcA96lUEnk/pRbsBIqjuoqRFGegpinNSrwaTDUcoUHoKsKqY5UVEo56VKOlId2SKi/3RUyKndR+VRJkVOo796kd2PVFJ4A/IVOqgjG1fyqFR0xU6UrId2OVAOgFSbFHUDn2pMe1OA55pWRVwCr2A/Kl2j0FPGB2oJyDRZBcYVGTwtJhf7opepNIemaNBjCB/dH5U1kH9wU/v1prUrAR7Bn7o/KlKjPKijPNOC+pp2QXE2g/wAAp3lr/cFAGBTsGiyC4bF/uD8qeAP7opBSikA8IuOVFAUf3RRnilz9KLBcXavoBTlVfQflTcc09QPxosguP2r6D8qUBR/DTQacBk07ILkmxT/CKd5a56D8qTtRkioYXQpjT0H5UeWnov5UmTRk0BdB5a5+6PypCi9do/KlB5paenULke1P7o/KkKr6CnMPpTDmkwuIUXH3V/KkwAPuj8qXNFNW6hcTav8AdH5UYX+6KKM0aCYbF/uj8qKTNFPQCpqepQ6RYvcSsOM4XPU15HeXtz4h1N5pSfLz+lSeIteuPEGoi3iP7odAOlaGm2K28QAGTTijSc+VWLdpbLBCEA4AxVvINNUAcU6tkrI5mxcilHWmU8dqCR1OApgp46UABGTxSge9FKKpANK0H2pxFIOtAEZXg5phHvUzDFNZdw4pMCuRgU0j1qUr2prYApARHHY0ypDzSEcdKAE5A6U9aTtSjPWgCZRgCnqT3NRJUy4xz+FHQocoPWpxzUaj0x/jSmWKPl5EQY6k4qW9B2bJwPSpVFYlx4n0azY+bfxZHZcn+VVG8faOn+qWeY9tkZwakrlZ1i8d6mT61xQ8cGQ4t9HvZT2yMVZTxTq7qDH4cuM+jSAUah7q3Z2KZqVeDXHJ4g8Rycp4fRP9+epU1fxUz4/sqyRf9qY0rN9Bc8F1OxGT0FPVSSOn86459X8WRjA03T3+kppP+Ei8UjroNsR7TUOD7AqkO52o6ZxQRx2/OuK/4TDXISBP4Yk2jqY5c04fEBY/+PrRdQi9SF3UuVl80XszsSMdvyoyOmK5aP4h6A+PNmmgbuJYzWnbeJ9FvMCDUoGJ6Ddg/rS5WUaRxzSEZp25G5RlYf7JzSEY/GqDlsMK0vWlIxgHvSYNTdki4p3ekpcGkDFpwpMUdKAHgU7HGKQUvei4C05RTP4hUgoAXvT1poFPWi4DqKKKACiinAcUAJj3oFKRSc5oAa3Q1GfpUhOaYx7YoAbSZpTSDmgAoopM4oAWikJ5ooA8g0fTRBEGbmQ9TW+ihAPYVDboVAFWOlbpWMpt3FA9KKKdimSJ6U7tTOM09TnrTGhy4707Ipo4pygHrSEL2paQmnZ4pjEpQOaM0Z9qAEIyabj8qdRjjBpMGRMozUbDjFTlaiPGc0CICMdqSpmQY/yKa2yNC7sFQckk4xRddRpX2G9PelCjH/165++8W2Nu5gs0e8uDwFiHH59arppPinxCQ80q6dbHgL0OP51KbeiKcVHWTNu81vTtPVvtFzHuH8AbLH8KxP8AhMbu7lKaRpU05zgMy8GtvTvBOjadh5la8m6lpjn9K30eG2jEcKJEo6BRgCtFTmznni6cdFqcSNL8ZauP30qWMTdhwatQ+AIWUHUNTuZ36sAxA/CupkvVAILe+MdaqSXoHXvyOatUV1OaWNqP4SraeFNBsxlLNZP9qQ5NaccdrbjENtCuPSMVkyarFHnfNGD/ALTioBrEMr4WeMn0DA1ahTMZSryV7M6E3XOeAR7YphucnO/3rBN8WHByPUEU37U49a1jTi9jmlUmtzeN0AB81BulzgmsA3Tmm/aXNX7LQj2x0P2oAdad9sHrXOC5bvQbo560ezD2zOlF1/tU/wC19MsDXNC7IH1/SnLdkjIHHr2pOmio1mdCwtrniW3hce6CqFx4Z0G7JZrFEbsUOMVSW8wMZFWEvgAACM1DpRNY4moupX/4Q+KFt2n6ve2zA5GXLAVLnxjpmXgu7bUYx/C42satLenuQf1xU8d5k8n6DNZPDpnRTx81uynD48ktQV1nSLu0YcF1TKiug0vxHpWrpus7yJz3Qthh+FVhco6FHVDGRyG6H6+tZN34X0bUG3/Zxby9pIDsYf0rF0Gtjtp46EviR2uCACe/qKN3PJ/CuAWLxT4ey1ldpqlovPlT/eA9Aa0dK+IGm3Uv2XUIn066zykowufY/wD1qwacXZo6lKMtYs7AHNPwMVDFIsyB43V1PO5TkGpQe4GR7c1LHYkopAaWgBR1zUgFRipRQgAdaeKYDzUlADhSgUgpV6GgBwApRRRSGGKaRinUnWgCM+mKYfQ1Kx4qM80AMIppHfNOxwajYk0AxSTjqKYRk9aDQRk/SmIWiiigDgUOKdUYOKeOe1dBi0OpT0pKMmgQU7tSAcU7FMaF9KeOg9aaOSKeAM0hCjmloFLQFwAzRgU4cUYzQNBtGKbj86k6CmH72OM0mHUYe2O9RvtVd7EAYzk1T1nWrLRYDLcSgPj5EH3m98VyK/2/41kYQA2mnA43f/X71HNroW4pK8nZGlqni+0tpGt7JDd3WdoCHIB/rVW18O674icT6vcSW9qeREDzj0xXSaR4X0rRIkZIhJcL1mfrmtKW8xnB/TrW8KLl8Rx1sdGCtAq6ZoWlaMn+i2y+Z/ff5m/OrU15j0/Cs+a9PPNUXui3HNdUKJ5dTEyk9WaMl6CD0FVZL7CsSwwO5rPeX5SzHAHUnpVbSdI1DxzqBs7Jmh06M7ZrnHUeg96itVjSRrhMLPEy12I5/EE9zP8AZNKtpby4PH7tSQPyrc034ZeKNbUTarqX2CJuREnLY/lXqfh7wtpfhuyS2sLdUKj5pCPmc+pNbY+bqRxXkVcTOTPqKOBpUlax5hF8EdEwGuL++lfvmTGavf8ACmvC6xMoN0GP8Yl5FehbaXp3rB1JnT7OJ5RefB14FL6Nrc8bYyFuPmXP4Vymp6frfhqTy9Zs90J4F1CCU/H0r6A4B6VBeWdve27wXUSywyLtKsMg1rSxE4O5z1sFRqrlaPB0dZUDo4ZWHBBp3bipvF3h2bwTqqyxI76LcN8pxnyWPbNQqysoKnINe5QxCqrQ+TxuDeHl5CUY60vApLLTL/xHqJ0zSxhR/r7k9Ih/U1VevGktTPCYaVeVkU7nU4oZBDEj3E5P+qhG5v0q7aeHPGOrHNvpiWsZ6G4OCK9b8MeDNJ8N24NtAJJyMPcSDLMf6fhXSAKBwK8epjZyeh9PQyujTjdq54kPhj4yMW86pZq+fu8kVDP4K8b2CGQx215gfdQnJr3T3LA0qhR6Vl9Zqdzp+p0Xpynzu93eWMgh1OzuLGU/89R8p+hq4tycBg2eOOa9wvtPstSgMN5bxzxnqrDNec6z8OXspJbnSJWa3xn7M3O36E9q7qGNu+WZ5OOytJc1M56K9boT16VcivunNZDpJFKySqUkB+63Bz9KRXYV6XuyV0eBeUHZnSwX3P3ulF/p2na1D5d5bI/+3jDD8a55LgoetX4L7kYNZSpJm1PEyi9GZ76Jr3hxvtHh6+lnizlraRs5HsK2tD+INneyCy1aNrC/Xg+ZwpPsant744I3VDquh6Z4hi23UISX+GVB8wP9a5auHa+E9WhjlLSf3nZxuHUMhDKehB4NP3HHJAryNLvxJ4Cdd7nUNJzjB/hH9K9D0DxNp3iK1WW1l/egfPCx+ZT+Nctmtz0FrqtjcU1KO1RKOnvUgJNJBsL3qQeopg608YoEOFKvQ0gpV6GjoA+kPWgnAo680igJooooAic1GScVI/WozQJjMmmE8mnk8Uzpk0xBz3pPU96TcaMmgBdx9KKTcaKAOCAp6+9MA5p4rpsY3HUUCgH6UaiFBp47UwdaUdcUASAAEU7vTBnNSY75pALS0mMnrTwKQBg0uM5xSgZ/KhiFUsSAAM5J4/OgduwDjBPHHX0rkfEHjKKyf7Hpqfabw/KCBkKfbHWs/X/Et7rF9/Y2ggkMdrSL3+h7Ct/w54UttCgW4n2zXrAFmP8ACfapjGU3YdWcaMby3MnRfBkl/INT8QytJI/zCEnr9f8ACuwaaG2iWKFUSNRgKox+HFQ3V1j5Qay5rlm71206KR41fGSm7Msz3uM7eBVB7ksTULuWPtTa6VFI4ZTuKzE9TSUVBeXiWNpLO3VV+X3Paic+SLbHSpupNRRVeC68Qa1b6BYht8rfvWH8Cete+aFodl4f0u306ziCpEAGOOWPck1yHwu8NDTtFOsXUf8Ap198xJ6ovYV6AK+dr1XOTPtsLQVGmooUgZyOPpR3oornOpC9KM0h60hpsBTS9cU2nDrUgzN1zSYdZ0e50+dA6SoQA3Y+teC6eJLdbjT5j+/s5DGw9getfRp5rwzxnYjTviRcsi7Uu4RJx6jrXbg6rhM87MqCq0b9jMlS5uJoLKyG66uW2IPQdziva/DPh208OaPFZW6guRmV8fM7dya8/wDhhpBvdcvdbnw0UGYYQfXua9aOMZoxVZ1JlZfhlRpLzEH1z70ZJopa5DvYmKMYopaQXEPNJt4I7UvSlpvYXQ4Lx94almtP7V09Mzwcyxj+NO/4153DIs0ayoflPIr6AYAryMgjBHY14n4q0YeHfE8kEQItLzMsZPRW7rXo4HEtPkkeJmmCUoe0iZtKCV6UnbmivbVj5daOzLMdwyAEmtC3vckc9qxs05XKmk1dFc7R1UdysyFJFVkbhlP3SPcVy2reEprO4/tXw1O1tcr8xhBwrfSrkF0VIANa9tdgryT0OR3rlqUFJXO/DYuUGM8K/ECHUpV07U1+y6iDtIYYVz6D0Nd0ucDivNvEfhe215BcW5WG+TBSQDGfr/jTPB3jO6069XQPEJ8uZTsjnk4Htk/1rzpwcGe5TqRrRvHc9QB/+tSjrTYyrqpB6jjHenAjipKHDpTl6Gm5GKcvSjoA6ij0opFBQelFIehoAjf7tRH1NStzUXtTJYxiM8U2nHpTM0ABXimmlLUw9KADNFJRQBw2acOtNxTwveutmAoooHWjFSIcM0q84PFIBSj6UxkgHPBp4FMXqKkHSkx9B4HFO700dM08YxnNSCV3YBhQWY4VQTk8Y4rhtY1a88T3x0XQiwtlwJpxwPfn0/nU2u6jea9qH9h6Ru2YxcyqeAO4z2rqNI0mz8P6ettbDnGXc/eY+uaUYub8iatWNCPmVtF0Gy8P2arCoac/6yYjkn29qfdXuScUXt1njf8AhWNLJuJFejTp2PCr4hzd2STTbucmq+TTcc5p2K35bHNzAKKSlpITYVWh09/EHinTNGUZQv50/oFFWOvSun+E9gLnVNV1phnEn2eInsB1rhx9TlhZHsZNS5qnN2PVIoUhhSFAAiABR6VJtpQKC1eBe59Zaw2ijNJQMKdxjrTaXOBimAY/Kgfn9K8m+JnxQn0S+GkaIyfalwZpjzt/2RXE6N8YvEllqEcl/Mt3bk4kR0AOPYinYD6R6jPNeO/GU/YNX0q/HGUeMkd+K9U0XVrbXNJg1C0bdDKoYGvNvjXbfao9BjAy0l3sA+tNPlJlFSVmdj4C0xdL8HafHt+eRPNc+pPNdL1+tQ2sItrKCIDCoigAewrjPGvxN07wlMLNY2ur0jJhU4C/U1OrdxpWVkdweDil6CvLPDXxo07VtQjstStvsTyHCybvlz75r1EOrruU5U8gjvTBi5ozSUUxC5oHNJQKLAKTgcjNcV8TdNe98NG8iH76zcSqRycDqK7Xqaq6napeaXdWzjIliZcdulVTfLK5FSKnBpnhEcgkiWRT8rDIp9U9OykD2z58y2kaI59jxVyvpKTvG58LiafJUcWBooorVGIoYg5FW7e4wwyeap0UNAnY6O0uuVqDX/D9p4jsyr/JcRrmOVQM59KzIpyMDn61r2d4VKnIyK5qlNSR10MRKm00Z/g7xldaTd/8I74jOySMhYZ2OAR2BJ7V6hkMwIOQeQa808T+HIfE1kChCXkYzFJ7+/tUfgDxlNFcDw3ru5LqL5YHc4yPQ15k4ODsz6OjVjWjzI9SB+UfSnrUZOee554qQEqMioK6jqKMk9aKBhTWbilJxUZOaAEYk8Co2Bp560x+lAmRmmGnGmMeKYhCaSmnJpc4oAMUUm+igRw4NSA5FMABFPA568V1sxFAFKBmk4BowKkQ6nKD3ptPA5FAx+ORThTccU5f1pNjHjI9PXmua8U63LFImk6ad95dfJx1QH+Vamu6qmjaZJcucuOI07ljVHwpojWsD6tfrm/ujuBY52jsKSTk7IHNUo8zNHQ9Hg0DS/LHzTuMyyHqzd+aS6vc7uealvbknI7msOaXJNd9Omoqx4Veu5u7HTzb+/NV6OSaK3RxN3ClzSUVQJBS0UCl1BkcrGOB37qpNemfC2wFr4JtpMENOzTHPfJry+8OLGcn/nma9m8DJs8FaQh4/wBGT+VePmT1R9PkkdGzoATSE0uKaa8hHvhRRRTAKpaxeHT9Hu7sFd0UTMAeOcVexXNfEJyngTVmQc+TTQHy1czXGq6pLO7NLPcTEk9SST0q/rnhjVfDhgXUrXyvPTfGc5z7ZqfwNHHP410lZvufaFJB6da9b+PCxLoGng4EnnNtB644qwHfAvVWn0K+09myIJMoD2BrS+JafaNX8KW4AO6/zk+wrmPgLAf+JtcgcfKuK7PxXELjxz4TgOMCV5MfRaTA7DUrtbDTbm6fIWCNn/IV8h6pqN1r2t3F7JulmuJDtUDP0Ar6g+IUjQ+BdVdc/wCqIrwH4UWUd/49sUlClI8vg88j2pIDk7q0ubGcx3MEkUg52uuCK+lvhRr8uu+C4fOYtNbHymY98Vw/x6s4Y7nTLlABK6uj4HJxjmtL4CyMdH1SM/dEoZfemSz16inY4HvTaACiiigBaCMjFApe4pdR9DwHUoPsfjTXbUD5PP8AMH4ikq54tQJ8R9V7boUaqdfR4N81JHxeaR5cQwooorqPPCiiigBQcVYgnwarUnQUrXCLsdHZ3PHXjpWP4w8NDV4P7RsBsv7f5gVOC4FFvOQcZrdtJ94x781y1qXOrHdhcTKnNNFj4feL/wDhItMa1vCF1G1XEgJ++o4zXbHrg8HFeK+JLGfw1rkHifSsqNw89FHHufoa9W0XVbbWtIt9QtWzHIuSM8qe4P415couDsz6KMozXNE1cgCjdUYb2p2cigBSeKjPrSnIpvNIWoFqYx4pSeKjY0wGseKjJ5xTiaZ/FQAjDNJg0u6m7uKewBRTaKQHICF8U8QPUaO3+TT/ADH9f1rrZzjvIagQvnpSCRh1NHmv/epAOMLjtT1jb+6c0wSvnNSLOwGM0DF8twehp6oRy3Ax1Pajzn4rE8T6nLBpwsbY/wCl3rCJAOoB6ms5eRcVqrmbFGfFfiMzN82mWDbUHaR66m6uAF2qQAOOPSoNN0+LRNLjs48FlX52/vN3qhdznOAePSuuhT5Vdnj4zEc0mlsQXNwWc85+lU8mns25qbXXFHmSEFLRQcd6psSVwpSMduagur63s0LSyKD2XPJqfTdM8ReIQG0rTmjhPSe5+UfgK56mJhDqd+Hy+tV6aCHgZOQKryXsEBw8vPoo3H9K77SfhSi4l1vUJLpz1jjJVB/jXa2HhjR9NUC20+ED1Zcn9a4amYfynq0slX2meIXFte3Wj3M0FhcvEIWbeVwMYr2fwY4bwdpBHUWyfyrUu7SO4sp7XaAskZTA6YIrB8AsB4Ut7cg77ZngbPqpIrz61aVXVnr4fCww6tE6cU007OaQ9TXOdSG0uKSnUDDn2rK8Rae2q+Hb+yXrLCwUe9atAXmhPUD5b0vQIlRJFlkhvYJfvAco6muh1+S/8RNHNq95532ePbGAoCj1Y+5r0XxN8NhqGoPqekXItLiQgyRsMpIff0rP034a6hLextq11B9lQ/NHDnMn49hVJiLnwk0FtH8MSzSLta6lMg4/h7UutXqt8XPD9seqQSMR9RXfRQxwW6QwqFRF2qo7CvHdbnkf4t2mqxufs9rOlq2BkZI55pjPUPFOn/2r4Yv7TGTLCQB79q+evDVnPo19b6pZsEubdyNrDg84IIr6Zx8vByBXm3iH4dXh1KbUNBmiXzmzLbykhc+o9KAOG8Vy3fi+8F7qWyFYkKRwochffNdz8FtJk0/wrPM8ZUXExZSf4h61T074e6zeXKLqhht7VfvrE25nHpXqFpbQ2VpHbW6BIowFVQOgoE0TZ4ptLSUCCgUUooYwHWlPFIOtK3c+nNSB4X4tk874makQc7IFFVc8/WkOnax4j+IPiG60qKGVYZNjiR8Aj0FTT6br2nhvt2iXCqD9+L5x+lezg8RCEOVnzeZYGrUqc8EQ0VAl3BJIU3bHHVX4I/A1PjHPUetejGrCWzPEnRqQ+JBRRRzWhkFFFFIVhVOOlaFpOQwx1rOqSJ9rA0WHF2Z0u2C+s5bW4UPFIu0g+lcx4S1KTwZ4rm0O9Yiwu2/cMTwpzwf6VsWswIAzzVHxhpB1bRvtMAP2uz/eRkdSB1FediqV1dHs5fibPllsz1UY6/l9KXPFcj8PfELa/wCG4/OcG6tz5UoxycDg11vOM9jXCey1YM89aQtmg8U2gm4jNnjFRMfSn5z1ApjH6UCGE+1NJpTTWz7VSAbTelOx9KZnJpgLRSZFFFkI49B7CnflTRTh6d66DAXrSYwaVR69aQ9aAHqaeDz05pi9akU+3WgaHcBST25z6VzukINZ8Rz6y+TbWv7q3B6Me5q34lvWstHZYs/aLhhDGB71c0yyTSdEt7QfeRdzn1bqaUI80iMTP2dP1JLufGfm9aw5n3uSau303BHfpWdnNeikfPVJXYmPSg8daMZ/CoL27hs7YzyngdF7k+lOUlBXYUqUqkuVErOqIzOyqoGTuOKr6fDq/ie7+yaBbv5YxvunGFX/ABrS8J+BtQ8YzLqOr+Za6WDmOEcNL/8AWr2zTtKs9KtUtrKCOGJAAFQYryMRjnLSJ9Rg8qjT96epyHhv4Y6VpBFzqH/ExvycmWbop9hXcKqouFAUAYAAp2MUhrzpSctWeuoqKshR6UuTg03FHNK9g3DOOe9YukWMunaxqUKxn7JcSfaEbsGb7w/MfrWzSjOaV7hYUdaGqvaahbX8Re1mWVQxVipzgjtVggUAIBSGlFBpFWDFL0opKBLQfuHXvRgfjUZdY1LswUAZyeK4rXPip4f0aRoI5Wvbgf8ALODnB+tFyowlL4UdncSrBbyyscKiFic4AwOteSLp0k3w/vtdyTI+ofa84/hVsD9KxvEvxc1PV9LnsbDSXt0mXaZDkkCrGh/Eyxh8MR6Hq2kzQweR5RlTkHjrii5s8NVS1ie1WkyXVlBcIQVkjDAj3FTFeK4f4X+IrfWPDC26Sh5bNjEc9SoPyn8q7rtTMH2I9oOM4PHQ0uABwKU8GkpIGJRRRVkhSikpRQMMZqrql7Hp2l3V1K+1IoyxPpxVvtXnXxX1CRtNs/D9mT9r1GZY9qnnZnmp6jE+ENo39h3+quoDahdNKCR1Ga9FIDfe6dxVDRNMi0bRLTT41Ci3jVOO57/rWgetMVjL1Lw7o+rxlL3ToJc/xFOR9DXB6t8LbiFjLoGoNGuc/ZpxuX8+or1DFJjFXGcou6ZnOjCekkfP9/barosvl6vpskA/57R5eP8AMdPxoikSaMSRuGU91Ne+T28VzGUnjSRCMFWGQa4LX/hpBPI95oMv2O5IOYT/AKp/qK7qGOadpHkYrKIz1pnBHIPPFFQzPcaffvYarbm0uVPG77r/AENTnAxg8GvWp1YzV4nz1fDTou0hKUGk+lFbHNcs28pB2it6xcHGQDngg1zKsVYGteymOV5rGa6G1GVmZWjznwj8SfJBK6fqS5XPQE//AF69f3g/dHavLfF+mnUtD+0wA/arU+ZGw68dq7LwjrP9ueGrS8YjzNmyXHZh1ryqseWVj6elV9pTTOg3ewphcHqKMgVGTWZY5mUVGWXNI3SmGgBSy56U0lO/NIe9Rn61SAkJT0puEqIk5pDzQIlynpRUXHrRT1DQ5EGnjk5pgFPyMAc1uYBSHrS4560mMtigB49qkQEn+n9aYBQ7iKKSRs4VST+HNJscXroYtwh1TxjbW5G6Cyj81/TeelbV5JjrWX4Xy9ld6lIP3t3MxX/cHSp7uXIOTya3w60ueZmNS8rLoUJ33MahoY0x3WJGdztVQST7V1ydldnnRi5y5UR3l1HZWrTTHCgfr6Vu+BvA0viKdNd1xGWyVs21q38X+0fasrwZ4auPG2tLqF6jLots2VU8eaR0H0r3aNUiRURQqoAAoGMV4WLxPtHZbH12X4BUY3a1JIUSJBGgCoBgKBxTwcd8+lQtMB0pnne9cNz1vZtlng0dqrCXJqUSA00wdNofzRzSBgadQyLWE5o7cdaDQOtIDz/W7bU/Bmpy65o8BudMlbdd2a9VPdxXVaB4l0vxLYi60+4VzjDxk/Mh9CK1WUMpVgGBGCCM1wmsfDqF9S/tXQL2XS748sIjiNz7r9aYjvR9OfTNIRzXn48VeK/D6GPXdD+2wqMfabM8n6rV2D4n6DIgM8d7bt3ElseKQztMUx5FjVmchVUZJJxgetcmPiX4bk+WOe4kf+6sDGuQ8f8Aj25udMj0vTbG6tvthIM8y7SV7gCk3YuEHOSiih428b3XiS8k0nRpWi06MlZrhTgyH0HtXM2mn29qgCxgt1LHk5+tS2tulrCkcYxgVN7elYSm+h9jhcFCjBNrUTOB90cdTimOkcg2sq9PTrXTeDtDj1rVis+fJiG5l9TXqL6Bpb25g+xw7D6oMmnFOxzYvMIUqns+W54BZy3nhbUV1TRmKlT+8hH3XX0xXvvhnxFaeJdGj1C14z8skZPKN3BryXxNpS6RrUlsn+rPK59DWX4X8Ty+C/EpUxyTafenDRICdp9QKqEr6M87MMNF01Xp9T6Fbg4/yaKzNJ13TdaiD2V2khIyU6MvsRWmPQZFbI8RjcUuKcRz0oOPX9aoBuKDwOKcSB9aw9c8V6R4etnmv7yNWXgRK2XJ9MUAi9qmp22kadNe3sixQRLklj1rznwVZXfi3xZceMdSjYWyZjsI3Hbs1UI7LXfihrMdxqMElj4cgfKQkkNKOxr1q1s4bK2it7aNY4ol2qqjAApWGT+nrS5pKMZoAKKbvAqIzClcap3LGcCkqt549aTzc96V9bl+xKWveHNM8RWT2uoQK2fuyDhlPqDXi3iDQdV8D3JFzvu9IZsJcAcp9a94Ev8AOory2ttRtZLW7hSWCUYZWGc1tSrSpvQ5q+FjUjyyR4XDLHPGrxMGVu4qT29+BTfF3hW68CX4vLLzJdFlbLdzEfT6VHb3Md1EJYjuRuc+te7h8Uqi13PkcfgJUJXWxL2q3aSENiqp6U+B9kg5711M81bnTWjBkw3IIxiszwBcHTPEOraBKQF3efCvYjvVi2l6Y/WsvVZ/7J8ZaLq6J8kjGCU+ueM15+Jj1Pcy+pq4PqepFu/U+tRmlJ9xyTSVxHpgelMNPpjGgRGx4qM5xTmPOKaelMGxmaKQ00nNVYTFzRTKKBHLCn9qaB6CnY4rYyBRxyKPu0DrTsc4pghVBx0rL8T3BttAuQpO+bES49yP6ZrXXrWDrxN1rWkaeBlXmEzfRc1Db2Kjpqa1rbrY6XbW6nASIL+lZ14cmtS6fBIHQcViXD7n616FKNkfP4md5NkXWqVvpl34t12LRbAkQrh7qUdFX0p2o3f2OykkAy3Rfc+leo/DTwv/AGFoAu7lT/aF7+8mJ/hHYfhXHja/L7qPXyjC3ftZHT6dptto+mwWNqgSKFQoA/nUjy4HNTSjAzk4NZtxdRRuEeaNXJ4BcAmvDl3PrKUe5JLNx1qEznA5qtJNnkdD0qISE5pHoRppo0Fm+Y5NTJJxWWrZIq3CTRdkVIWNBGJ6GrKjjnOaqp2xxVpDkDmqR59WNhw6Ud6DzQOtNmSA5oVepz+FLSZxSGL1GMDHfNQPa27k77eI5/2BU1FO1xFaPT7SNmMVrChPogryL4pXDP41sLVlxHFAWX0ya9n9T614/wDGG2MGr6XqIXAZTGze9TNOx2YCSVeLZy/UUGmxuJIw4PFOzn0rlPt1rZnSeCtZi0jVW884jmGC3pXqw1KzMPmLOmzHB3V4IRnOKkFxKBtEjhfTccVUZ2Vjy8Xlka9TnvY2fF+ow6prkktuwKKNgI71gWjeV4o0Nwu5vtIA4z1p5HetHwrZLqHjnSoyNwhJlPtgd6cPiuLHQjRw3Iepar4L0rUpjcRo1pd9p7dipB/CqA0fxnp6hbHXIbtQPu3UfOPrXZkZbOT7mj+ddKPkTjV1LxzajE2jWlyMdYpMZqoNf8d3LEQ+HILf0Mkua74nJoJzTA82n0f4h6ySlzq9vYQv1WBeQPrVzQfhZpWmXIvNQkl1K6B3B52yAfpXeGjP+cUANSNURVA2gDAAp9JzS0rgJkYqNm/CpAPWoZelNlxWpBJKRkA8VWklwetLMxGKpSOTyB2z1qWd1OEepOZ/ehZxj/69Yd5rVjZLuuLuFBnG0uM1kP4wE0oj03T7m9Y/xBSqj8TUG7hE7tJgDyc54qxG65wzAD3rh4P+Env0XzJbfT0J/g+d/wBa2dP0BI8SXd5dXUwP3nkwPyHFUjlqRsb95Z2+oWkltcxrLbyrtZGGQRXz74k0G48A+JFiy/8AZN0SYm67fb619ERAKigcDGMVj+K/DVr4n0OexuEBYqTE/dGHQitqNVwkjzcTRjVi4yR46HDKGUgg8gilB6H3rK0triyubjSL5THdWrbQD3Fa3UCvo6VTnjc+IxVB0ajTNS0kyvH1qPxTbm78LzPGP3tsRKh9MHNRWj4b9K2ERbi3khYZWRSpB+mKyrq8TbBz5ZpnS6JfLqWhWF4OTNCrN7HHP65q+OBXHfDS4L+F3tHbMlncvCfp2/rXZE8V5h9A9xuec0wnrTiab60CImIzTCeRT2IzUR9KaAU4zxUZpxyO9MNUiQooooA5kcdKdyOoNIPY0GtjJCgg07FNVaeoNMB69c1hoRcePARz9ns8/Qk//XrcUAcdq53R3M/irXJVH3VSMf5/CpWs0hVHak2at02dxNY7DL9eprTuicNmsiRgiO5OAoyc16LaUbngSXPNJE+gaOfEnjO0s2G61swLib0Ldga92AAG0YCjoK4f4V6R9l8OPqci/wCkahIZcnrs/hH5V3eACfevnq8+abZ9thaSp01Ehk6Vh6noVhqozdQB3HAboR9K6BgCDxVd0BOcVzM7oSscLJ4Tu7VmbTNWu4j/AAxyPvQfnVOafxdpY3S2NvqESj5vJJVvyr0JoweOmaYsJPPOe4FI6VUdjzy38f6esoh1Cyu7Jv4jJHwPxrrtN1XS9QjV7S/glBGflcZrSl0q0uE2T2sUg6HcoNYGqfDfQ74+bbRyWM39+2bZj8KdiJVuh0saEHOcirSDjHT2rzs+EPF2jgHRfELXEYPEd3zn8aD4s8a6MCur+HVuUT/lranP6VSRzTlc9I7UgrjtM+Jfh+92R3Mz2Nw3BjuEK8/Wust7y1u4xJbXEcykZ3IwIp2IJu9Jil49cZozSsA2iiihOwC1y/xA8Pv4i8Lz20C/6TH+8h9yO1dPS4z2obuEW4u6Pl2wupLaRoLlWR0O11I6GthJEkAZGyDXpPjr4cQa8xv9M2waio5AGFl9j/jXjl/aaz4dumh1KymhZT94DKn8awlDsfSYTNYuKjUN2iudj8Qrg/vB9TUketzXDiO1geeUnCoik1n7NnoyzCgle5uSyLDGzuQqgZJru/hRokgjudfu0dHucJArjonrWF4U+HOqa7Kl74hVrazUhltwfmf6+1ezW8UUESwwoEjQBVUDGBW9OFtz5/MMeq75Y7EzDmk7UE0ma1seU2FFFFMkBSjrQOtIWwMnCgdSaAHH64oxWBqXjLRNLYRTXglm7RQgu36Vjya/4q1hdmjaL9lhI4nuzg/UCpsUdqzoikuwUdSxPFc5qXjPQrGTymvFnm/55QfO35CsuPwNqOot5mva/dT55MEDbE+ldHpvhnRtK/49bCBWx98plvzpsVzkpfEXiHV8DR9BMMZJImvOAPwqAeFvEeqA/wBtaw0cR6RWQ2D869GZR0UYAGMConj3DI5qWdMKhxtj4M0iwwRaCWQfxzHef1rWS2WP5Y41UegFazRHqRTPJAOe9Kx0xqpFaKLABKnirkaDtSrH65qZYwKDKpUTHp0qXPFNVRS00cktWeQ/FrQjZX9r4otY/lUiK5A9Oxrno5FkiR15DAMD6ivb9c0uHWtFurCdA6TRlce+OK+f9JEtv9o0+fcJrOUxHI5I7V62X1fss8DOMPzQ9ouhsQnEg5rcsmyVOfSsCPhhWzZnGPTNelNXR8/QdpEngrFr4m8Q2I4RpFnUf71duRjIzXC6LG0HxHl+b5bmxD8+qsK7onOW9a8mekmfTJ3imNNNJ5px6VG1SMa9Rmnt1phpgxh6U3NOamVRIuaKSigdzm8AdKXGRRSitmzEUZpwpFp2BkUDHgZIHuK5zw4A1zrsvQtdkD8K6Mdea53wzGUs9RZjndeyZ+nFFPWojPEO1Flu8PymsS/Vpkis48+ZdSrCp9MnH8q2bxsLtIqvoFr/AGh460a3zlInNw4/3en6104qXLTbPMy+nz4hXPbdNs00/Tba0iXbHDEqKPQAYqxk+tKf60nevnmz7NBTdmafnmjPp0pFJkfl+9L5fHWnUtCQ+ewgUClzkc0U2qSJbuKOlIRnr0pSaAcUxXM+/wBD0vU49t7YwSn1ZBmufPw40q3nMul3N5YSZyPJmO0fga7AntSUAjmra28U6WzJ9ptdSg7b1KP+ddBazSzW6vPCYX7pnODUwozx15zUsLgaSndqaaQwpwNNooAfwahubS3u4jFcQJKp7OoIqTNA5Of09aaFex5nN4Q0O++J4g+wQCCGz8x4woClie4rvbTQdJ08g2dhBEw6FUArmPD7/aviN4juCMiEJEtdv0FAOVxuT70pPpSUtOwriUtJS0xiUUtJQIM4rn77w5davdu2oarOLIcLbwfICP8AaPU10FLQMzNO8PaVpY/0SxhRupcrlj+NaYJPHHXsKOgxRQITNKKMUlAxaQgelFLU2GJtyOlIIl9KXNLk07A2xDGM8ijYM0vJoFKwJh0oHWjNFFgDv9Oa8a+IeljSPGlvfxLiHU4yj/8AXReh/GvZDXDfFXTmuvCDXsa5msZVnB7gZ5/StqFTkqJnPiYe0pNHngznPocH2rYtG4FYcMolgjkHR1BrYtM7B+FfQ35kmfEpcs2i3bhU8b6VLzmW3lQk98AHFdu2BmuCeRo/E/h9uOZZE/Nf/rV3hHBOOe9eVV+Nn0dHWmmNyaYaeaY9RY0I3pmeac3WmkU0hMa3WmHvTzmmHoaoBMiilA4ooEc9Rj2pcZpRWqRmIBzTse9ApQeelMY4DK4xzg81z/h040ick8tdSk/nXQqCenuBXOaFxZ3qHot5KuPxzV0v4iMcT/BZLesS2T681qfDS287xvqFwQf9GtVQexY5/pWVe/e9q6b4Sx75tduz1aaOMfgDSx7tAxyeN6jZ6YM4zRR0HJ/Gg9a8Vo+oEpe1LjikPSiwXF7UlApcUgCmgZp3SjGOapCEIxRinAZOaXb6UxMjop2OnFJg0AGaTjpS4pKTGLmkooqRhRRRQAU5R81NpWzsYjspNNCZw/gNzN4g8VXHY3hQH6V3Pb8a4T4Xqz2GsXDHIl1CQg+vNd0eKdwsBpMUUUXCwYpcUlLmjmCwYpKXNJRcLBRRQKYgpRQe470DFAC0nalPTn0pAcUDEopwGeQDTWZV++Qv1OKBBSjp0qjcavptpn7RfW8YHdpBWdP438M2xw+rwFuwUk/yFAzoMDqKOn/165R/iJ4dziK5mlHcpAx/pQvjuxkYrBp2oyqem23I/nQI6vHFJ3xXOJ4quZeIdBv2HYsQtaWnahe3spW60x7VQMgtIGz+VIEaNZ+u2A1LQb+zI/10Dpz7itAYoxu4I470LcHsfOekh002NG+8jMhHoQcV0NmfkFZMkX2bW9XtD0jvZCB7E1rWX3PxFfRUJXpJnxeKjy4iSLPl+Z4g0MkcrPI34BDXdHua4NnkHijQVBx88h/DbXd9hXn1fjZ7WHf7qIh6VGetPqM8VKNGN7004yacTimd6aBjX7Uw09iKZQIKKTn1ooAwBSjpTacpya2MxRmngCmjgU4Ke9Fx2HpwR9a5rSQEl1WPPK3rHH1Arp4x1z6Vy1o3leJdbhPB3q4H1FOi/wB4jHEL9yye8PNdd8IUxoGozY5e9b9BXJXS5Q+wrtPhIhHhWZj91ryQ/wAqnMPhROS/FI7w/cPrjisabWNQhchdGlcequK2s9AfSkHHSvJufR3ObbxTqC5z4eujj0IqB/GF5Gfm8N6gfoM11nSkz6ZoEccfHk0Z+fw5qf4JUMvxKjiIDeH9UB/65V24NIVyckD8qGhnDD4o2XPm6PqcY9TDmhfitoWOYL5PYwGu4aKJ+GjVvqtRiytgP+PaL/vkUJAcrB8UPDEi5e5lj9njINW4fiF4ZnXK6mi/7wxW0+ladIxZ7C3Oe5jFRN4f0aT72mWx/wC2QoEQW/izQLkDytVtj7F8Veh1KxuP9VeQP/uuDWe/hHw+/J0m2B9lxTY/CGgxNuTT0THQqSKLjNtSrDIIP0NJUdtaw2sPlQqVQcgE5qSh6jCilxS4qbANop1NNABTLglbOd89I2/kafVXVG8vSbx+wgfP5GgTOW+Fy48Iu+OXupGP512hrkPhkP8AiiLU4+87Mfzrrz0FNghKKKKQwooooAKKKKa3AKUHHXpSUdetUSYmo+L9H06VopZ98o4KRoWOapnxhJcLmw0W+nY9CybRXRCztVORbRBycltgzmp8gHAoGckdU8X3IJt9Et4AP+e0uTUf2XxxdnMl9ZWyk9I0JxXZbs0Zx0FIDjJfCeu3JJuvFN2oPUQjaB7UR/D20kx9t1TUbnH96cgGuwPJzRTA5tPAXh1cB7ASY7yMWq7b+E9At2DR6VbAjoSgNa9LQK5Wi0+xtz+6s4FAPZAKnwq42qAB7U6igdwBxSk0gOKDzQIXPT6Uh6daWgdanqM8F1xPK8ea6nbzVf8AMVfsh8o/CqniIE/EnXE7EJ/KrtmD/Svfwv8ACSPj8wX+1MtWgEvjTSlwMRQyynP0Artf/wBVcXooEnjvdg7YbHHtlm/+tXaE8ZH41x1PjZ6lBfu0IeKYehpzdKYTilsaDTimEU9jxTCDQAxhxmmHipHwBimGgBhzmilPWigDnwBmnjAOaNtLitTMX3p6nFMAwaeBzSBD1+9XL3Sm28ayEkbbm0B/EGuoA5rm/FGLXVdGvScATGIn2Ioi7TQTjzQkiaYZVj7YrsPhI5bwtdIf4LyT9cVyUgyCePwrpvhQ+2DWrbP3LpXA+o/+tTzD4UY5PpOSPRTx9aM+9IfWivIsfRXDtSUtJVIVwpR0pKKAF4zS02igdxeKT8qKKAuLRmkopNDFzQaSihIQoopKXNDCwc0lLmkqRhVDXOdA1AZx/o7/AMjV+s3xCdvh3UG/6YN/KgTMn4cKU8DafkAEqTx9a6k1zvgRdvgrTcdPKrojTYISiiikMKKKKACiilwMZzQgEoooqrisLn2FKDxim0UXQWHZpCaSii6CwUoxSU7jt1ouFhtFKetJRcLBRRRRcLBRRS4ouFgoyRS0nHektw2R4X4hUn4ka04HGEX9KvWwPl8cZHNV9U2yeMNanXp5+z8gM1dttqqCegGTXv4e6pI+RxnvYplrwkBLretXPUI8cKn6DP8AWutzwPrXL+AVzotxdHk3V5JJn1HAH8q6joBXFJ+8z10klYQ80w9KeaYaVwZGaTNOIptMQw9TTKkbpimHFMBCKKKKAMOiilxVmYopw6mkFKvegEPUe9YXjO2M/h2aRRl7ciVD9D/hmt9RkUy6txdWk1s/3JEKn8RUt21NInNWF0LrTorgdHjB/Gul+Gj+T4l1m23YEsUcoH0yK4Tw05t4bnT5MmS2lK4Ppmuo8NXv9n+ONPc8LdK0De56j9RWmJXPRucmEfssW4dz2LOeelFIOBilryLH0lwNJRRTQgooooEFFKBmk+lABRS7aT9KBi0YpGYKpZiFx1JrPvNc0qwH+k6jBHjnbvGaAsaOPzoxXOW3jKz1C9EFhbXVyM4aZY8IPxrowcjmkAlFKKD1pMYlFFFIAFZfifP/AAi2pkdfsz/yrUrJ8VHHhLVf+vZ/5UCZX8EqU8F6V7wCt81ieC12+DNJB5/0da2zTYISiiikMKKKKAClyaFJB4oNACUDmij8cUALijHvWfq1/eafCjWmnveEn5lQ4IrDPjeW3bF7oGoxL3KpuFAHWYoxXLQfEDQZDiW4ltj6TIVrWtvEejXf+p1O1f6SCgDTxS4AHFRR3EEvMUyP6bWzUm70HNACHrRS5BIpcZ5oAbRTsUmKAEpw6CkxSjtQAlI52ozHoBmnVleJL0ad4b1K73bWit3Kn3xxVJXZMnZHjNrKby7v7rtNdyt/48RV7Ubk2WjXEw+8I8L9TwKp6TbmLT4R3K7z7k81Nfr9svdN0wH/AI+J1L/7q8/0r3n7lFI+Th+8xbZ2XhmybTvDdhbMMOkKlh7nk1rc00FQMDoOBSg1wM9ZsDxzTGNPbpUZNCQhpI9abSmkJxVDEJFMpc5NBGAaBDMUUtFFwMLNOFJilGBWhmKKco5pKcOe9A0SJ0FOOcdPakTjin54qWhnA66h0fxglwo2298u1j23VZvHe3EV7GCXtZUmQ/Q5/lWj420s3+gmaMHzrVvNTHesPTb1NR0uMsMkrtYd89K2pe9CUGc2JXLONaJ9A2lwt3YQXKHKyIHBHuKmrjfhrqn2zwv9jcjz7CUwNjuAflP5GuyHIryJK0mj6CnLmimGKMUvrSH9fSkWBFAFISFPLAe54rE1PxbpGmny3nM8+f8AVQDe35CgDbbgA058IpJIHqTXInXfEmqDGlaMbZG/5b3vHHrtFA8HX1/tfXdbnuR1MMP7pP05pBY0dQ8YaHpxKTXqNJ/zyjyzZ9OKxR4r8Qaodui6BIsTfdnu/kH1xXRaf4e0jSwBZ2MMRPO7GT+ZrTPGMdPQUDOKXwp4g1jLa5rrxxkcwWgx+taemeANA05g/wBmNzID9+di7frXRr05H6U7d/8AqoJGx28UKBIo0RR2VcCnNS5ppNAxBQetKKQ9akYlFFLigBKyfFA3eFdUH/Ts/wDKtfFZ3iIBvDGpr3Ns/wDKgCv4N/5E3Sv+vdf5VsmsXwgR/wAIjpeDx5C1smgAooooAKKKKAFBx0ozmkooAKUdaSlFADqOCMHH400nmlFBLK1zpWn3gxcWUEox/FGDXPX/AMOvDN8+7+zhC2c5gbZXWAilLA0DPOZPhXDbymXStb1GzbsPMyKYNC+IOlqfsWswX6Dok64J/GvRvlozQM88/wCEt8W6WVXV/DbSL3e1cNitG3+JmgSSLFdSTWcn8QniK4/SuwwPz61BdadZ6gm27tIZl/20BqiRlnqljqMYeyuorhW6FGzVzOOvauWuPh/oMk7TWsUtjMR962kKfpVVvDnifTiTpniATIOkd5Hu/UUBqdkSOvanDHFcnba14itQw1XRfNC4/e2jZB/A1r6drlrqD7VSWKX/AJ5yoVIpDNQkDt2rhviheFNBttPU4N9cKjD/AGRyf5V3OQSCOfp2ryjxreDUPGfkqcxafDt+jv8A/WFb4aPPUSOXG1PZ0WzMgQKoA7DA5p3hiH+0PF11fH5orKIRJ6bm61Hc3MdlYyXD4CopOPU+1bngvTW0/wAPI8n+tuiZnz23dBXrYiVmonhYGFk6j6nRA/M3p2pRTetP7Vxs7xGPFRmnmmEcChLQBp6VH1PNPNBFMZGBzTmANIOtKSKBDKKXd9KKAMPFLipM+1N4rQiwgHNPHSmDrUq9KAsKKfjNJt5FOFIYFFdGRxlWBBHtjFeXRxN4f8T3Wluf3TnzIT2P0/OvVEz+Fcf4/wBHe4sYtUthmazJLY6lf/rUKXK7jcVODi+po+CtX/sfxYkbnFtqCiN/QSDkH8a9k79vwr5vtrn+09MSWI7ZVIdfZxyK9z8Ja2uvaBBdnCy42SjPIYcGufGRXNzI2y+o+V05bo2ncRxs5+6oyfpWa+pXN0CNOtmYHpLLwv8A9etMgN1GQODSjpgdK40emYraLc3wH9p3jSDP+qiO1auWWj6fYA/ZrSJCerbfmP41fAA/+tRnimAuMDtikzS5GKaetSwDP+RSGlzSU0AoozRSUMELmikpcYqRijoKQ9aM0HrQAlKRSUooAKo60ofQtQBHW3f+RrQxVa/Xdp9yvrC4x+BoCzMfwS4fwfpxHQR4rfrmPh9Jv8HWgP8ACWH610/FMQlFLigjFIYlFFFABRRRQAUUUUAFKDSUUAOzRmjijimIaaKDRSGFOHBptOpoAJGaQ5zQaKQAd2OPypvlgkHaAfXFSDpSEgHpTQFDVdQi0rSbq+lOEijLH3rxq1ke5Mt5PnzbmQyyZ9zwPyxXWfEzVjNLa6FC3Uia4x/cHQfia5RpktrZ53bbGi5NergaainNnz+aVnOapRGSQDVtcsdK5Manzp8dlHSvRdoUbVwAOMDoAOmK5HwNp8n2efWbgETXjfID/DH2rr8YAAHPelOV5OTNIU1CCihA3NScgVEeOacDxmpL6Cn1ppalIyKZ3ouAGoz1qQ9KjNACd6GHPSlpDmgBKKMGigDIpp68dKfjBpMVoZgBT1po64NO70iiQHNKKavSndqAHqKVkWVDHIAyMCpUjrmgDmnEc9zUsadjyy9sT4W8RPakkadcnfET/CfSuv8ABevL4e1828xZbC/PLZ4SXp+tXfEWhxa7pLW7j96g3RMOoNedWdxM6y6Xf/u7mNtqs3YjoadlKPKxawmqsfmfTikEZpwNcV8OfE/9t6SbK9cf2hZ/u5c/xjs1dqOa86UeV2PYjJSSYHikpT0ptIoKM0UUCCiiigAozRRQAuaUj3ptLzSY0JRRRUjFApc4pue3esjxJ4gtfDmjzX90eFGEXPLt6CgEm3ZFvVNYsNFs2vNQuUhhHc9c15prPxqs18yLSdPe6BBHmOdq+nFeZ63rep+Kr1rrUZX8rcTHAD8qj6VnmaOBtickdlFZudj1KWB05qh2nh74raloWnLZ/wBkxyxb2fO4g8npXf6B8W9E1N0hvlksJm/56D5T+NeGF71+Y7VznoStNeO+KHzrJiPpR7Qc8LT2ifWcU0U6rJFIrowyGU5BFSHkZr5s8HfEC98K3iQztI+nOcSRSE5X3FfRGnX9vqljFd2sgkhlG5WB4NaJpnm1KbhKzLNFLSUEBRRRQAUUUUAFFFFABmjNFFABRRRQAUtJTuv5UAHak5oFLx6UAJnFU9W1GHSNNuL64cJHCpY57+gq4ePxFeVfEPXxqeprodu262t2D3LKerdlrejDnkkc2JrKlByZzCTT395cajdEtcXT7wCfur2FRJbN4i1qLSoifs0RD3cgPAA/hqvf3v2K2IjBeaT5I1HXJruPCWhHRtJHn83dziWZvc9q9OrJRhyRPEw0HNutPqb8MccMMcMa7UQBVXHQCpSBTaDWB1tikgCjtxTetKTgdaliAnimGjqaKSGJTT0oIFIQatAApp4anDrmg8dcUCYmaKbk0UCMuj60vakrQkARmnAZpABjrTgMUmUh3SnKpJ7UAYFPjFFgF6U4dKD1oqQF5xkHp7VyPjLww+pqNSsPlvoRkr/z0A/rXXA8UfSi5UWeSaRr15pGrwatbArLB+7uYTwXXuCP8819GaPq1trWmQ39nIJIZRkYPI9jXjnivwq0pbVNOULcgZljA4cDnOPUVR8DeMD4Y1Jo5i39l3LbXXvE/rjtXNVjzanVRqcrsfQGc0lR208VxCksLh0cZUg8EVLiufY7VZhim0tGDQhiUUYooEFFFFACijv1pKUdaTHYWig0VIxD19a8E+JviD+3PErafE26zsG24HRn7mvbNavRpui3t5z+5hZuO5xxXy4ryzEyDLT3DlsnryamTsjuwNPmnzPoTJBNeXAgtyBj779lFb9lpdvZD5EBkP3mPWpdK0020IijXdK3329a3rbSxnMv5ZriqVGfRU6XNqzKVCx+RSfYVJ9luOvlNiujSCOMYjUL+FSd6x5zf2ascdeaXHdRFbi249SuK6z4V6udNvJ/Ds8paJv3lszfqKe6K64YAism9gXSr+z1m3AD2kwLKOjKeDW1Ko07M83HYSMoOSR7aTkdeaQ9KZA6ywpKo+V1DD6EU813nzVtRKKKXFACUUuKXHNADaKKKACijFFABRRRQAUoPX3pKXFADh0oNJmqep6lb6VYS3ty4WKNcnJ61SV3YTaim2YnjXxPH4d0zERD3842wR+/c/QV5AD9mhklmkBYkySSN/Ex5Jq3qGoT65qsurXWVLfLCh/5Zpn+ZqjZadN4p1ZbSLcLCA5nkXoT6CvVpU1Rp3e7PArTeKq2Xwo1PB+jNq98uvXkf+jp8tshHU/3q9Dz/wDXqC1torO2itoFCQxqFVe1Tr0POe9ZSdzeyWiDpTs5FNxR2zTQgzgUmc0ZyelJ3oEFIaU0meaBoaetKe9BooGIOlJxTu1JzQJiYooxRQIzTgU3HGKc3Sm1oSwUZp3bHpSL1p3ekxoUE4py8GkFPA4FJjHd+tFA60ppAAGRSkGm04UFIMkDnn0+tch4p8KLcrJf6fHmXGZYRwHHc+xrr6cOvPek0h3szi/AHjhtMcaVeykRA7YWb+E/3TXs1lqEV4MqQCQOD/OvIPEvg6PUg13YYiu/vMAdqyfX0NReEvF8+n3KaJrTNFcJ8sUrdz6E/liuapTO2jUWx7iRikzWNaawGwk4wP7wrXV1dcqc/Sue1jqUrjjTadSGhAwFFJRQ1cBaXvTe9LSsAppKKO1A3scv8RpzB4B1VgDlo8ce5FeD+H7T7TeeZ5ZPljYv1r6B8a2Dap4P1S0TO9oSVHuOf6V494Hs2TTVmkHzEn86wru0bnrZXZyaZ0VpaLbQgHlz1P8ASrOOff8AlScnrThivMu3qfSpWE+tFL3oxxmmUKB71U1OETabcRYzlDx71ZFR3B220rE4whNOHxIwr602egeEZ2n8J6ZI5JYwAEnvjitkjvWH4LjMfg/TFJyfKzz7k1unpXrrY+MnuxtOptO7UECUmaWkoAKBRS4oAKSjNFABRRS0IApaKZLKkETSSMqooySTimk3sJtLViSyJBG8khVUUbmJPavHPFXiN/E195cWV06Bj5ak/wCtP94+1WPF/i+TXJXtLKQx6Wh2vITt84+n+7/OuNgjvfEF3/Z+lRlYAcSTkfKo9BXfQpKK55HlYmtOt7kNhc3WvX39maYSecTS44Qd69O0bSbfRdMis7YYCjLH+8fU1Boeh2mhWK21ogBP+sfu59TWqBg1pKTk7szjCMVaIYxxnNLRnNFR1AKXsaT3oqhsKDmgmmnnNBIE03OTR1ooAO9LSDrSnJoGgopBS0DCiiigDLamnpinsBimVoZsVeppw5pq9TThxQNDhTxTRTl6CkxijrTsU0dadUiClpKdQWgzRQaTsaAbDqax9f8ADdp4gttsoMdwCAkq9V5rZGMjmlIwOvT3qZK40+V3OO03WdR8KNHp+vt5tmxCQXg5+gavQrHU3CrJbuGjYcc5GK89+IsRfw4p5KrMpbB49OlcpoXiXUvD7BYH8+2/55Sfwj2rjrWiz2MJRlWheJ9FWmqwz8P8jehrQDAjg/jXl2h+MtN1oCMP5Vz1MbcGusttQlhX5SJF9zxUW0CUZQ0aOlXpTT1qpa6lBOoXdtbpg+tXAQelJC3EoowaXvTAKKKSpYIUqCpUjIPBB7ivGtSsm8JeIrmznjZdPupDJbS/wjPVa9mFUdU0my1m0e0v4FkjcYyRyPcehrOcFNWZ0YfEOlK6PNs8g54bpS9e1F74L8RaFkaYy6nZgnEbnEqD096xZteksfl1HS761YcNuiJFcUqDi7I+jo5lSmtWbXSjPFYP/CYaSBy8o9jEw/pViHxBa3cipapPNI3RViY/0rN0ZnQ8dR7mtmqt5DcXwj0+zUtNcsEGP4R3Jrd0rw/qF8qyXFu9qh/hk+9+VdhpmjW+mjMaBpD1cjmtaVF3uzzsZmMORxgXLC2WzsYLVPuxRqv5Cp2oGM5pDzXcj567EozRijFMAooxRigBcn2pcn0ptLQAlFGKUUAGKM9eeDzzSnGKoarq1lo1hJd306QwoOWY9f8A69Aa9CzPcR2sTzTyBI15YscYFeReMvG/9s7rS2kaLTUbDt0M59B7VQ1jxhqPje+ez0uF2s0OQmcAj1c+ntWho/hCK2dbvUmW4uR92Mf6tPoK7KMElc8/EVG3ymFpfhu+8QOk11m10sHiPo0g+npXf2Vlbafbpb2sQjiToBUwHft24pe/Wtzk2VkPzk89qcKZ6U4dKAFpe1JQaTQ7i5FJnFITig57UWGLxRj9aQ8ijtRYAIwtNFOPTrTT7U7CA8mnY9qYDk06gYYpaSg0AFFFFAGacFTUfepDyMYFJjFWRuMzg04c0EClFFxjl96cM4pBTl6c0MBRmnGm45pfpUiFAyaWkAJoxQUh1Jx3o7Udeh70bD6iheQe3+etVLzUrPT4vNu51jU9s8n6d6x9e8SppzGztMTX7jhR0T3NcukDyzefeyme4xjLdFHtWlOk5vQ569eNJal3xRrMms6XNa20GyFv45B8x71xFpMskJU5EiHkV2JVcEYyM5rn9S0oxzGe3O0nk+9TisFzL3NzqyXO1Qq2qbMqGNWzwQc5GDyDWxpvi3W9JKpHKLmAHlJeT+B61hR3SlvKcbH/ALpqxwBkH6V40lKm9T72Kw+KjzKx6TpXxC0y8IjvN1nMDk+Zyv4NXbadrYaNXt51miP91s/rXz+VRhggfTHWi2nu7Jt9ldzQEcgK3y/lT9pc4K2VO94M+mbbV4XO2T5D69q0FkSRQ0bhvoc189WPxB1ezAS7t0ulXHzrw1dTpXxM0uV1EzyWkmed4OPzq+ZM4J4SrDdHr/HFFctp3i61ulBjuYp1xwytW1Dq1vMFydpPrQznal2NAdaQ9femLIj8owI9afjBzSEIAO4Gc0jKrD51DfWhmCgscYHNZc2rMzEWse4ZxvY8Vz18RCjrI0jBy2L5s7U8m1gz7xj/AAqRI4oc7I0Qf7KgZrG87UWORcIgPYLmk+0aip/4+I39iuK4f7WpG3sJM3OowaTpWXHq5i2pdR7CxwHByK1M5Oe1d9CtCrG8TCcHF2YoNLQKU4HHetyRKM0H1GKTGBQAuc0Ug5PtQzKgyxCgdyaASuGTQAcc1lah4m0bTlJudQgjI5IL5rktS+LOmw5TTLaW+k/vAbVH4mk5I1hh6k9kehA5HH41m6pr+m6NA01/eQwqB0LDJ+gryG/8deJtTcgTpYQt/DEMtj61hmITSGW4ke4kzktKck1DqWPUo5RUlZz0R32rfFcODFodjJMxH+vm+VR+HeuB1Vr/AMSXCza3dmcrysQO1F/CpOCcg8eo4xVO6vBGfs8fzzOMBf6n2pwUqkrRR2VaGEwdJyqHTfDe0EFlqEqrtV5yq44yBXagA5Pf1rzvR9avPDljFbvbrc26sWZlOGGefxruNK1ix1iAS2koJ/iQ8EH3FerGm4RtI+DqVYVpucNi6QBwKM0MOc0gpmY4GnKeKb3p4HFAC0lBoPSgY0nNLz2pFGadigdxBnvS0uKSgLjTycU08GpMDNMbrQAKcDFKcZ4zQB3zSkHrQAckZxiijPtRQAfgaKXI96KAMwnimkntUvamHrVozGjnrSgc0v4UAH1oKFPbinqOOhpopRkdOlDAec8EUvFNPUU6pEBwKM0hPFIFJBwf/rUFXHHp0z2x3rlPFPihrD/iWWGGvpOGPXyx6/WtHxTr8WgaWZs7riQFIo/VvWvP9JtZctfXTb7qf5iTzgGqhBzlYmvUVKDky/Z2ot18x28y4k5eRuSTVkcHFJmlHUV6kIqGiPnatSU5czA8UEbgQec+tFLkYqrJozu1qY+oaVHMvKZX26isWSK8smGB5sQ9Oorssg1XltI5jleGrjr4SE1qetgs2rYd6PQ5WO7gkbDMFc9mNWAARkHNWr3R1cnzIw3+0vBrOOn3UIJt5dw/uv2ryq2XOOsD7PB8TQkkqhPj/wDVTWjDDBwfaqq3kkUvlzQtvI6KM/jVgXMT8BsH3rz3CUXZn0VPGUKyumILdUIaMvEw6FCR/wDWrTtdc1uxI+zalKVH8MmGH61RUg9GBpzDGdw6UnKUS5UaE1ex1Fl4+163IEghmAHYEH9M11Nh8VXjAW+tZoz/AHgpYfpS+BdAtv7IS7niDvJyM11s+j6fcRFHtlweteVUzZwm4pHl1aVHmskZtv4/0/W0+yW0pE0hAxjBA71vxgeXtUYVeABXC33g02OoRajpeAY2yyAdRXY210txbKy8P/EM9K83HYhV2pIlU4x+Eu5wD7Ug571CVfD4OMjinAlQgzzjmvOUQaGXKq0Em/BULk5os/FWiR2yLNqtqjqMFWlGR+FUNcvDbWEiRfNK4wijua5Cx+HdvfHz9TiTcx3FQOa9jL8RGgm5Ezo88btnoL+M/DqH/kMWf/fwVXn+IHhm3UyPq1sQTj5XBxWAnw68NRoB/Z6scdyTXEeJfCFhpN6Hit08l+QCvSvVo5lTquyRFHA+0drnoF18WvC8HyxXEs7dhFExrIn+MQkUiy0K6fngyEKPrXCxwRRY2oq46YFP6fjxzXV7U9Snk0Le8zen+I/iy9JEEdpZR9Pm+ZhWFeXus6mxe/1i6kH9xG2L+lB4+lJz/wDrqXN9Dsp5fQp7lZLC3UhvL3sP4nJJ/M1a4AwuB9Bio5Jo4ELuwUD1NVW1OInECSTP6IKuNGpPZDrY3CYVayRdAzk46dTUMt7BbY8xwCeg7mokh1K7JD4tosZ6ZY/4VYttMtrVvMVDJKeS8nJr0KGWzl8Wh8xmHF1KC5aGrKqi+1EOIlNtBjl3HzH6CtC1sLezQ+WNzk5LNyx/GpwSOaUAlDnFexh8JCmtD4XHZtXxb9+QmcHNUpreWOcXmnTfZ7peRt+631q6QeppADgY7dK2nT5lZnDRrzpS5kdH4a8Wpqx+x3qiDUEGSpPyv7j/AArpsBcA15NqenvdItxbOYrmI7kdTg5rrPBviv8AtWI2F+RHqEPBH/PQetedVg4M9+hWjXhzR3OuxzTx0pvOQKUcVBaQtFFFIYKMZpaQdTS0CCgjNFFADf4qD1FITg0YOAaBhS5pKKAEp24elJTsCgA3j0opuBRQBRao8dalIphU9cirRA0dKWkwaOv4UihwXJ60oGPekB5pw5FMB2MdqWk570tSAHpTZJFgjaZ22pGCzk9h604k49Pf0ri/iDq8ttpsen27kT3bYKg87fejcqKuzmb28fxT4ikvJCTaQHZEvQbc1qDsQKqWNmtlaRxKBuxlsdzVvNehRp8sbs8TG13Vnboh2eM04Go+tGePbvWzOO12RXE2J4UBPzNzj0qwBwPSs5m8zWIh2jj/AFrSz0xSg7s3rx5VFAOKdjgmm8fjSg1pZHLbqLgEc1Ru4gWSKCMvdTNtijXqSfarUs0cMLySEBFGSa7b4d+FmDDxFqKH7RKMW0TD/Voehx6muDF11CNkexlmDlUlzPYseGfh7/ZNl592sM9/MMucZC+wqbUfB2nXQIutNj5/iVMH867s9Qe9Jnkn+deG531Z9TBcqsjySb4a6MzExG4g9lbNUX+GaBv3WqsATwJRkYr2Z7eGU5MS5+lRf2baNn90B+NKVnFmsatSL0Zz3h+2+x6PFbA7jF8pPrWkDtH070yWAWN80aDbFL8yc9+9SEArggH27V8XjKbhVZ6MZcyuxpfLYZTjsRWVfIEul+zMElYZc+30rTX5G+6Rn3rKdkN/cg/eDAZ/Csaaub01d2F3TEfPO5+nFN2MwC+bLjPHNNMyhGc5wnbFMgvIpydmQfetbHTyIs2EUbXBaf5pRwpb+la+RuwDjFY27a8RxnDcGtVmIOQGx6gVlM5qsUmSkAnI781xnj8RQ6bDO7gYbHPeuyBPUcfhVWXTbXVdQS2vLdJ4FQs0bjINdWXxcqqM/bey95Hhj6xZxjJmB56Col1oSSbLa1uJiegRM5r6Ej8JeHY+Ro1lwP8AnkDir8FhZ2mBBaQRcfwIBivq1TSRM81qtaHgNtovirUgPsmiSoh6NKNorpLH4V6/dkNqOowWiEcrBliK9hIznPT0oJIHf8Kagjlnja095HhniX4fx+GJ4riWSW+tJcKZJP8Almw9R6GqkcaRjEaqo9BXuepafb6rp89ldLuilTB9vevEr+wuNF1KXTbokmPmN/76djXr4GrF+6fLZxRqSXtLjCT0pPwpOvWjNesfMtNDugpc03NFaCRC82ydIyPvdOakBqpflk8qUD7rirQ6VmpXbR01IWgpDvX+VZGqWssNxHqNnlLiE5+XjcBWtn5gaRgCD6EVNSmpxHha7pTTO80HV4dc0mG8iPLLiQHqGHWtIZ6V5X4c1KTw14g8iZyNNvTtJ7K3r+dep5wxHcdcV5TTi7H0SakuZD6KQHiigQDqaWkHU0tAgpu6nUmKAGnk5oJzQeDS4oAQUcUUZxQAUGilagBuaKKKAKpphBNO570hGKtBYjwe5o59afgHOTTCpHQ0AIM5qRTUa55zTx0FAD6dkcCm0Z7VIDuCDyMCvJrq4bWvFVxfnJghby4gfavQfE2o/wBleH7i5XHmMPLQepPFcFp1ubayjQj5zy2fU1rQjzTMsRU9nSbW7LoOfzopM0vWvSPBkKKCcUdKbI6pGzEcAZ/KpbsOnG8kjPsmM2o3cnZTsBrSGazdFANq8veSQmtLoKVL4bmuLf7y3YcDk80u7sKZk4qvfXItLOWY/wAKnFVOpyxbZnRp881FGn4d0geKPFMdjIc2VoBLcYPDei/nXuSRoihY1Cqo2qB0Arhfhfozab4XF5OmLm+bznJ/u/w13IbHSvnK1RzlqfaYakqUFFEuaMAUwMSDShq5joHDhsUEUA0ZprcZBeWaXluUckc5DDqDWb9i1CL5QIZQvQnjIrZ3HNUNSuXjjWKLiWXIB/uj1rgxlGk4uc0bU5PRGJc3t3G7RJChYDlgchaxpmuYJTckh8n5gOtdKY0ji8sdCMlvWoHs4ZwWB2k9MV8w6iUvd2PShLlMZNRgdPm4PoRSm8tYWLLjJ7AVJdaOpR5AB8vJwcZplnpiSKkgTcD6mtVONjo9ohI557kqVVVGcjPetK01H94IpkcSDoMdalgtoogCg3MOmamdPNCuvyyLyrY5+lZcycrMwqSuP+1oeEVmb+6FrR063MUbTS/62Tkj0o0+4FxbhsASKSr8dCKudPevpMHhKcEpx1PNq1HJ2H5H1NIDSdaBXpnOKetITx1o6mgjigVxpPFcb4/0E6hpQ1C1UfarP5sAcuncV2FDKHRkIyGBU8djV05uDujKpTU4OLPBY5FlRWXoRxmnfgKs6zpbaH4hurBiRFnzIP8AdJ6fnVX1r6SjNTgmj4vF0XSqOIdTS5pM0cV0HLYraiM2UuOoGakgcvAjHuopt1hreQf7JxUOnnNhF7D+tYrSZ12vQLlGewpmeaQmrOaxV1O1F3Zui58xfmQ+hru/BesNq/h6JpD/AKTCfKmz1yOhrjic4GPrU/hC8OmeK5bRztgvV3LnpvFcOJhbU9vAVbwcGemUtIM96WuQ7RB1NLSCloEFIRS0UAN289aDxTicU080AJSUtJz6UAGaX6mm0poAKKOPWigCqaQ0pHPtSYqgG00HB6U896jLe3tTvqIMc5FOwMZpF9e1PyMYoGL2oHynNFJznA7nn6VLA4nxtdNc6lY6SvKRjz5Md/T+VZ30pl1P9s17Ubv+EymOP/dXgU4nNd2GjaN2eVjat58q6C9qM00etOrpOIdkVV1CbybGd/RCMH34/rVg9KytfkxZKoGC8gH5VnU+Fm+FjeqizpEZi02EHqRmr3OPeo4V2QquegxTycVUFaKRlWfNUbFBPpWfextqWs6bpMYz58w3/TNXwas+A7T+0fiRJIwylpESD2Brkxs+WFj0cpoqda76HttrEltaQwRjEcaBF9sCpxyOlMU8DJpwPFeCz6qwtAaj8KSkUSA0u4+1MDCl3UCAuc9qy9W+SWCcj5RlSRWlnmo5FWRGSRQysMEVhiKXtabgXCXK7mZw646jpxTSu4g9AOnHSh9PuLcH7Mwkjz/q3PI+lRiWSMhZLaYMPbgV8xWwFWD2O6NWLQ26DEJCCcykLx6UsEQiEtvjBibhfb1q5ZWjyXP2qddgUYjQ9R7mpb2zeRhLCF8wcMP71dqy6fsPMzddc1imgAwM9DUnHVse59KgY3ZYL9lf03DpVhNOmmIN04EY/wCWS9/qa5aeXVXKzLdaNh+jod1xNgbJH+T6CtbtUaBUQKgAUdAOlLmvp6FNU4KJwzldjwc0bvemE0ZrUgk3c9aCc9Ki69sUewNArDiaQH2pKQnHei4HA/E/Tz9httZjB32r7ZdvdD1rhM/LnsQDXter2a6hpF5aMoImhZcHqSRXhVozrb+VKPnjYxt+BxXrZfUv7p8/m9DRTLGaMnPWkFFeqfP2EkwyOD0waq6XIGsQCeAxFWiM1Q0gjyJQegkNZNv2iOuC/cM0KKM5orVHKwJxVS8lNtcWl+gO62lDHHcd6tGo5ovMgkQn7ykVlWjzRZ04WbhUTPVLeZZoUkX7rqHH41LXP+DLv7V4Xt0ZgXgJjb2x0roB29a8ux7z7oWiiimSFJuGaWkxQMD0poJp1JwWoASj6mlPWm55oEFJRS0AGfeijn1FFAFY4IptOz6U05pgIxphPtTjgdeaQnPQVVgEA7U7g01Tg9KVevIoYElUtWvDp+k3N318qNjgVc6VzvjSYp4faEZ/fypGeffP9KkOhyNkAbONscsMk+pPWpsgCl4GQOn9KTnivVgrRSPBqPmm2GaUUnajpVsgdn+VYevSF7qyi7eZk/pW11rn9YbfrtlFjkcn865670sdmBXvt+R0HA/CjmlIGTik6VvFaHJLcUHArf8Ag9D517rV4R/GIwa58nCk+xrrfg1H/wASDUH/AL121eXmL0R7mTR1kz03GOlOB4qPPFKrV5B9AyTdS5qMDml/CgVxwPNOzTO9IOtA7kmaQ89KSjdSsFxQCPY0cjrn86KShrQLjqDnPJpu78KXIosIcRnk/Tim855oJ4HWjdntQkgHA0UgNGfamA6im5560E0riFJpOOtNyKM55piTH5pMjNJSZ96EFxcgH+deFaxH9m8XavbAYUTeYo9m5r3I+1eMeOojbfENv7lxbKx9yK68HLlqI4cxhzUWZw60pPoKaMClzX0J8g73DPbpWZpJGLoH+GWtPFZWksftd8pHAkzXPOX7xHZSV6EjU/i4oyPXmlPHSkHLVucdgzS9+aDRjp6jpSktCo7o3vAspiutRtMD5sSjJ/Cu3/nXnHhhxB4rj5ws0TJz3PWvRieM4x7V5M1aR9BSlzQTHZopoI7U6kWJkUZHrSY60lACnkUnSjrSUALSYopD96gQUuaSkNADsiim0UAQfL0zzSU5uD0plMAppFOop3AaAdwx1o6gHj60oPPTn+lcl4l8aR6PI9lZR/aNQ4Cxj5sE+w79OKTkluVCDm7I61mVAGchV7kmuL8XavaXcljYW9xFKwn3uqnnAHetHR/AWsa+Fv8AxRfyxRPgizgbBx/tdh/OqXj7w1pPhv8AsWTTLPyfMmeORsk5445rONVOaRvUwzjTbMbqTj14pQOaU/e9TSbuea9uLPlpbgaQgUppKbJuJ2Nc7qIz4ptB6YrpORXMX758Vwe2K5cT0PRy9ayfkdP0Jpc5FNyOBzTjwtdSeh50txhGQR7V1/wacf2Bfx91u2rkgK6f4QMIxrVrk5WcP9RXlZitEz3smlbmR6iBxQBz0oB9KBk8V5B747v9eKRgRkYx+FYOuzzS6lpulwzSQrclmlkjPzBR2FMk8LzLzaa9qELg8FmD/hgigDouKMiuVOmeLrbaLfXbO4TPS4hIP5g1Ib7xVYpm40e3uwOpt5wP0bFBJ0+aaevWuQl8exWbhL7RNVtz3IgLj8xUkXxH8MScPfmFvSaMqR+lA9jrBmgEZrFt/Fvh67z5Ws2bEc8SD+uK0I9SsJVDR3cDA9xIDQDZa56gUc0wSxODiRT9GpQRmgY7JHSlBNIePT6UZGaBXHA0hJopTxzg5HsaBISjOT1puecHP0NQ3dzHZWzzyhiiDJCKWJ/AUFExPvRnjNcwfFV3OzLp/h/UJ27NIgjU++SahE/jiUmRLTS7cZyI5JWZsfUcUWZB1wYml9Ky9F1OfUbeRbmHyLuBtk8ecgH2Pp71qUIY2vJPiftXxlpTH+KEivXK8i+Khz4p0XpkRsf1row/8RGGLX7qRhHr0oyaXvQORX0aPipPUOelZOkc6jqHpurW7ZrG0hs6jqH+8Kwn/ER24f8AgzNo9KavWl5xQK3OICaAaRjSClbUaJLaYW2tadcE4AnUE5xgGvUeSc9RnAPqK8c1ZBJZohJG+VEyOoya7u6+Heu2CGfQvEUzsACsNxyD7V5OJmo1Gj6LA0XOkmdQDzTwc1w1r41udLvxpfiaxNpcA7RNzsb39q7WORJUWSNg4YA5H6VmqiZpOm4jyaSkpaszCkpaaTzQAUY56UooHcUAJQw6Up4pCcn6UAJz6UUuaKAIHUU09KKKYDcnrS0UUAYnizVJtE8PS3duAZXyq9tp6Z96o/B/wzZXVtLr93me7Zyqbxwnqfc+9FFc1Zs7sMj1k9RycE4xXIfErTIb7wXdSudsls3nRsB0I/xoornpv3jqq7M8r0+drjT4pH+8VAzVn3oor6Oi7xR8fi0lUdgoxzRRWzOQcfu/hXJXbFvFELf7QFFFcuI6HqZftL0Osxg0D7tFFdKPNl8Qdq2PhrcPD411K2XGySAOfrmiivOx/wAKPYyj+Iz13eSacDRRXiM+kZz1/MW8eaVAR8ptpGHsc10Ib5qKKYCjrj1pD+npRRSJYv3s5AIHTIzVWbTrG7U/abOCXP8AfjBoopiMebwL4Yu8iTRrX6hcfyrMm+FvhiVgYbWWA9Mxyt/jRRQyuhVPwp05XIg1TUYVHOElNU5vh3JECYfEuqoc/wB/P9aKKBoWPwRqKn5PFuqLx7f40reCdS3Dd4u1Q59MD+tFFBKLFp4EmlRjN4l1V9p6eZjNb1h4TS0ZJG1TUZiOcPOcUUUFHSKNoGMAemKXrmiigkTA4OBk+1Irbm25PHvkUUUhGfpr51PVVAwFkU+vatKiimgENeN/FJy3jbSk7LDn9aKK3w38VGOL/gyMvpxQBRRX0kT4qW4frWJovN9qD99/9aKK55/xEd2H/gTN3PANMJoordHAIDSg4x9eaKKUjSO5Xtk+1+MtHsmOEMyuTjrg19Auenoe1FFfP4t/vWfYYNWoqxk614e07xHZta6jAJF6I/RkPqDXlng/UrvS/FV74XeZri0t2YRO/DKBziiiohuaVVdHoec84xx0/CnAZ4oorrTPNa1ExjIpP4QfWiiqEJnAzTm4YiiigTGg8UmeRRRSQxc0UUUxH//Z");
    return user;
  }

}



