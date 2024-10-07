/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.activity.core;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ClassUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mumu.design.timer.redis.CommonException;
import com.mumu.game.activity.anno.ActivityFunction;
import com.mumu.game.activity.impl.DefaultTemplateActivity;
import com.mumu.game.sdata.ConfigFunction;
import com.mumu.game.sdata.ConfigFunctionCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ActivityManager
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:32
 */
public class ActivityManager {

  /** 活动id 与 活动 映射 */
  public static HashMap<Integer, Class<? extends Activity>> ACTIVITY_MAP = Maps.newHashMap();
  /** 活动类 与 活动id 映射 */
  public static HashMap<Class<?>, Integer> CLAZZNAME_ACTIVITY_MAP = Maps.newHashMap();
  /** 功能id树 */
  public static Tree<Integer> functionIdTree = new Tree<>();

  static {
    Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation("com.mumu.game.activity.impl", ActivityFunction.class);
    classes.forEach(c -> {
      ActivityFunction annotation = c.getAnnotation(ActivityFunction.class);
      ACTIVITY_MAP.put(annotation.id(), (Class<? extends Activity>) c);
      CLAZZNAME_ACTIVITY_MAP.put(c, annotation.id());
    });

    loadConfigActivity();
  }

  /**
   * 加载配置
   * @param config config
   * @date 2024/10/7 16:55
   */
  public static void reloadConfig(Tree<Integer> config) {
    functionIdTree = config;

    loadConfigActivity();
  }

  /**
   * 加载配置表Activity
   * @date 2024/10/7 16:59
   */
  private static void loadConfigActivity() {
    // TODO 初始化
    ConfigFunctionCache configFunctionCache = new ConfigFunctionCache();
    ImmutableMap<Integer, ConfigFunction> functionIdItemInfoMap = configFunctionCache.getMainMap();

    for (int functionId : functionIdItemInfoMap.keySet()) {
      if (ACTIVITY_MAP.containsKey(functionId)) {
        continue;
      }

      Class<DefaultTemplateActivity> c = DefaultTemplateActivity.class;
      ACTIVITY_MAP.put(functionId, c);
      CLAZZNAME_ACTIVITY_MAP.put(c, functionId);
    }
  }

  /**
   * 获取功能id所在模块的所有功能id
   * @param myId 功能id
   * @return java.util.List<java.lang.Integer>
   * @date 2024/10/7 16:59
   */
  public static List<Integer> getModuleAllActivityId(int myId) {
    int parentId = getModuleActivityId(myId);

    List<Integer> res = new ArrayList<>();
    getAllSubActivityId(parentId, res);

    return res;
  }

  /**
   * 获取功能id所在模块的父功能id
   * @param myId 功能id
   * @return int
   * @date 2024/10/7 16:59
   */
  public static int getModuleActivityId(int myId) {
    int parentId = myId;
    // 循环30次！避免死循环
    for (int i = 0; i < 30; i++) {
      Tree<Integer> node = functionIdTree.getNode(parentId);
      // 顶级活动id
      if (node == null || node.getParentId() == null || node.getParentId() == 0) {
        break;
      }

      parentId = node.getParentId();
    }

    return parentId;
  }

  /**
   * 获取指定功能id下的所有子功能id
   * @param myId 功能id
   * @param res 结果
   * @date 2024/10/7 16:59
   */
  private static void getAllSubActivityId(int myId, List<Integer> res) {
    res.add(myId);

    List<Integer> subActivityIdList = getSubActivityIdList(myId);
    if (subActivityIdList.isEmpty()) {
      return;
    }

    for (int subId : subActivityIdList) {
      getAllSubActivityId(subId, res);
    }
  }

  /**
   * 获取子功能ids
   * @param myId 功能id
   * @return java.util.List<java.lang.Integer>
   * @date 2024/10/7 16:59
   */
  public static List<Integer> getSubActivityIdList(int myId) {
    Tree<Integer> node = functionIdTree.getNode(myId);
    if (node != null) {
      List<Tree<Integer>> children = node.getChildren();
      if (children != null) {
        return children.stream().filter(Objects::nonNull).map(Tree::getId).collect(Collectors.toList());
      }
    }
    return Lists.newArrayList();
  }

  /**
   * loadActivity
   * @param playerId 玩家id
   * @param clazz clazz
   * @return T
   * @date 2024/10/7 17:00
   */
  @SuppressWarnings("unchecked")
  public static <T extends Activity> T loadActivity(long playerId, Class<T> clazz) {
    Integer id = CLAZZNAME_ACTIVITY_MAP.get(clazz);
    if (id != null) {
      Activity activity = loadActivity(playerId, id);
      return (T) activity;
    }
    throw new CommonException("无效的活动类:" + clazz.getName());
  }

  /**
   * loadActivity
   * @param playerId 玩家id
   * @param activityId 活动id
   * @return com.mumu.game.activity.core.Activity
   * @date 2024/10/7 17:00
   */
  public static Activity loadActivity(long playerId, int activityId) {
    return loadActivity(playerId, activityId, true);
  }

  /**
   * loadActivity
   * @param playerId 玩家id
   * @param activityId 活动id
   * @param checkSubActivity 是否校验子activity
   * @return com.cxx.hf.domain.activity.Activity
   * @date 2024/10/7 17:00
   */
  public static Activity loadActivity(long playerId, int activityId, boolean checkSubActivity) {
    Class<? extends Activity> aClass = ACTIVITY_MAP.get(activityId);
    if (aClass == null) {
      return null;
    }
    Activity o = null;
    try {
      // if (serviceContainer == null) {
      //   o = (Activity) getCglibProxyService(aClass);
      // } else {
        Activity abstractServiceCommon = aClass.newInstance();
        // abstractServiceCommon.setServiceContainer(serviceContainer);
        o = (Activity) abstractServiceCommon;
      // }

      o.bindPlayer(playerId,activityId);
      o.initData();
      doCheck(o, checkSubActivity);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return o;
  }

  /**
   * doCheck
   * @param activity activity
   * @param checkSubActivity 是否校验子activity
   * @date 2024/10/7 17:01
   */
  public static void doCheck(Activity activity, boolean checkSubActivity) {
    if (activity.isOpen()) {
      activity.setOpen(true);
      if (activity.checkRedPoint()) {
        activity.setRedPoint(true);
      }
      if (activity.hasShop()) {
        activity.getStatus().setOpenShop(true);
      }
      if (activity.isSeasonActivity()) {
        activity.getStatus().setInActivity(true);
      }

      if (checkSubActivity) {
        activity.checkSubActivity();
      }
    }
  }
}
