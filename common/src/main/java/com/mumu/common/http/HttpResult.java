package com.mumu.common.http;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * HttpResult
 * 构建返回
 * @author liuzhen
 * @version 1.0.0 2024/11/28 11:39
 */
@Data
public class HttpResult implements Serializable {
  private static final long serialVersionUID = 1L;

  /** 错误码 */
  private int code = HttpCode.SUCCESS;
  /** 消息 */
  private String msg = "success";
  /** data */
  private Map<String, Object> data = new HashMap<>();

  private HttpResult() {
  }

  private static HttpResult of() {
    return new HttpResult();
  }

  /**
   * 创建成功返回
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:55
   */
  public static HttpResult success() {
    return of();
  }

  /**
   * 创建成功返回
   * @param msg msg
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:55
   */
  public static HttpResult success(String msg) {
    HttpResult httpResult = of();
    httpResult.msg = msg;
    return httpResult;
  }

  /**
   * 创建成功返回
   * @param msg msg
   * @param data data
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:55
   */
  public static HttpResult success(String msg, Map<String, Object> data) {
    HttpResult httpResult = of();
    httpResult.msg = msg;
    httpResult.data = data;
    return httpResult;
  }

  /**
   * 创建失败返回
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:55
   */
  public static HttpResult error() {
    return error(HttpCode.SERVER_EXCEPTION, "未知异常，请联系管理员");
  }

  /**
   * 创建失败返回
   * @param msg msg
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:56
   */
  public static HttpResult error(String msg) {
    return error(HttpCode.SERVER_EXCEPTION, msg);
  }

  /**
   * 创建失败返回
   * @param code code
   * @param msg msg
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:56
   */
  public static HttpResult error(int code, String msg) {
    HttpResult httpResult = of();
    httpResult.code = code;
    httpResult.msg = msg;

    return httpResult;
  }

  /**
   * 追加返回数据
   * @param key key
   * @param value value
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:51
   */
  public HttpResult appendData(String key, Object value) {
    this.data.put(key, value);
    return this;
  }

  /**
   * 设置msg
   * @param msg msg
   * @return com.game.framework.http.common.HttpResult
   * @date 2024/11/28 11:54
   */
  public HttpResult setMsg(String msg) {
    this.msg = msg;
    return this;
  }

}
