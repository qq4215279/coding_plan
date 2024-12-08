package com.mumu.java_tools.hutool_all;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * HttpTest
 * @author liuzhen
 * @version 1.0.0 2024/11/28 10:18
 */
public class HttpTest {

  /**
   * HttpUtil，可以轻松地发送 HTTP 请求。以下是一些常用的 HTTP 请求示例，包括 GET、POST、PUT 和 DELETE 请求。
   * 1. GET  HttpUtil.get(url);
   * 2. POST  HttpUtil.post(url, params)  Map<String, Object> params = new HashMap<>(); params.put("title", "foo");
   * 3. PUT  HttpUtil.put(url, params);
   * 4. DELETE
   *
   * 自定义请求头:
   * HttpResponse response = HttpRequest.get(url)
   *        .header("User-Agent", "Mozilla/5.0").header("Accept", "application/json")   // 设置请求头，可以穿map
   *        .body("{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}") // 设置请求体    // 设置请求体，使用json
   *        .execute()  // 发送请求
   *  response.body()   获取服务器返回的响应内容。
   *
   * 注意事项
   *    确保服务器端能够处理你发送的请求头和请求体格式。
   *    如果请求体是 JSON 格式，确保设置 Content-Type 为 application/json。
   *
   * @date 2024/11/28 10:23
   */


  /**
   * 发送http请求
   * @date 2024/11/28 10:22
   */
  @Test
  public void test() {
    // 1. 请求头
    Map<String, String> headerMap = new HashMap<>();
    headerMap.put("Content-Type", "application/json; charset=UTF-8");

    // 2. 请求体
    Map<String, Object> bodyMap = new HashMap<>();
    bodyMap.put("purchaseToken", "");
    bodyMap.put("productId", "gold_100000");

    // 转json
    String msgBody = map2Json(bodyMap);

    HttpResponse httpResponse = HttpRequest.post(
            "https://orders-drcn.iap.cloud.huawei.com.cn/applications/v2/purchases/confirm")
        .headerMap(headerMap, false)
        .body(msgBody)
        .execute();

    String response = httpResponse.body();
    System.out.println("response: " + response);
  }

  private String map2Json(Map<String, Object> bodyMap) {
    String msgBody = com.alibaba.fastjson.JSONObject.toJSONString(bodyMap);
    System.out.println("msgBody: " + msgBody);
    String msgBody2 = JSONObject.toJSONString(bodyMap);
    System.out.println("msgBody2: " + msgBody2);
    String msgBody3 = JSONUtil.toJsonStr(bodyMap);
    System.out.println("msgBody3: " + msgBody3);

    return msgBody3;
  }

  @Test
  public void map2JsonTest() {
    Map<String, Object> bodyMap = new HashMap<>();
    bodyMap.put("purchaseToken", "");
    bodyMap.put("productId", "gold_100000");

    // 转json
    String msgBody = map2Json(bodyMap);
  }

}
