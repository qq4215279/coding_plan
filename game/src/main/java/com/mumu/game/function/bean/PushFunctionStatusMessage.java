/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.function.bean;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * PushFunctionStatusMessage
 * @author liuzhen
 * @version 1.0.0 2024/10/7 17:54
 */
@ProtobufClass
@Data
public class PushFunctionStatusMessage {
  /** 功能id(0为全部功能) */
  private int functionId;
  /** 功能列表(此功能以及所有子功能) */
  private List<SingleFunctionInfoBean> functionStatus = new ArrayList<>();
}
