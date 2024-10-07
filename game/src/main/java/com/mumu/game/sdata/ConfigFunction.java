/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.sdata;

import lombok.Data;

/**
 * ConfigFunction
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:38
 */
@Data
public class ConfigFunction {
  /** 功能id */
  private int functionId;
  /** 父功能id */
  private String parentId;
  /** 名称 */
  private String name;
  /** 描述 */
  private String desc;
  /** 是否关闭 */
  private boolean close;
  /** 渠道，不配为全渠道,若配则只有对应渠道开放 */
  private String[] channels;
  /** 等级条件 */
  private int lvLimit;
  /** vip条件 */
  private int vipLimit;
  /** 排序 */
  private int sort;
}
