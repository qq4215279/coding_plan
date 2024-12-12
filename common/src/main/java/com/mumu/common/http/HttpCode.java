package com.mumu.common.http;

/**
 * HttpCode
 * http错误码
 * @author liuzhen
 * @version 1.0.0 2024/11/26 11:36
 */
public interface HttpCode {
  /** 成功 */
  int SUCCESS = 0;
  /** 失败 */
  int FAIL = 1;
  /** 订单不存在 */
  int ORDER_NOT_EXIST = 2;
  /** 服务器异常 */
  int SERVER_EXCEPTION = 500;

}
