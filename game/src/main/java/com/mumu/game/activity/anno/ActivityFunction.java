/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ActivityFunction
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ActivityFunction {
  /**
   * 活动id
   * @return int
   * @date 2024/10/7 16:18
   */
  int id();
}
