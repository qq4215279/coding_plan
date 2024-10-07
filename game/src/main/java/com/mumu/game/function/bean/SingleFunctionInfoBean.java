/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.bean;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

/**
 * SingleFunctionInfoBean
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:53
 */
@ProtobufClass
@Data
public class SingleFunctionInfoBean {
  /** 功能id" */
  private int mainFunction;
  /** 是否存在红点 1存在0不存在 */
  private byte redPoint;
 /** 是否开启   1开启0关闭 */
  private byte open;
  /** 是否闪烁   1开启0关闭 */
  private byte twinkle;
  /** 是否开启商城0关闭 1开启 */
  private int openShop;
  /** 是否有特卖活动0关闭 1开启 */
  private int inActivity;
  /** 闪烁的关闭时间yyyyMMddHHmm */
  private String twinkleEndTime;
  private byte twinkleEndTimeFlag;
  /** 配置表的showType */
  private byte showType;
}
