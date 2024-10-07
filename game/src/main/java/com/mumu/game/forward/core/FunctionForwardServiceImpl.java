/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.forward.core;

import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doActivityEndService;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doBuyGoods;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doChargeStamps;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doChargeValue;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doGetClosedFunction;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doGetRedPointStatus;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doItemChangeService;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doItemOverdueService;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doPlayerLogin;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doPlayerLogout;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doRefreshRedPoint;
import static com.mumu.game.forward.core.FunctionChangeService.MethodName.doTaskChange;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AbstractService;
import com.mumu.design.timer.redis.Utility;
import com.mumu.game.common.Player;
import com.mumu.game.forward.anno.FunctionType;
import com.mumu.game.forward.anno.ShopName;
import com.mumu.game.shop.sdata.ConfigShop;
import com.mumu.java_tools.dom4j.config.ConfigItemInfo;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;

/**
 * FunctionForwardServiceImpl
 * @author liuzhen
 * @version 1.0.0 2024/10/7 19:17
 */
@Slf4j
public class FunctionForwardServiceImpl implements FunctionForwardService {

  /** methodName:包含对应方法的类列表 **/
  private static final Map<String, List<Class<? extends FunctionChangeService>>> functionChangeServiceMap = Maps.newHashMap();

  // 初始化 FunctionChangeService子类
  public static void initAllFunctionService() {
    initAllFunctionServiceClass(new String[] { "com.cxx.hf.api.service.impl", "com.cxx.hf.serverplayer.service", "com.cxx.hf.servergame.service" });
  }

  private static void initAllFunctionServiceClass(String[] packageNames){
    Reflections reflections = new Reflections(packageNames);
    // FunctionChangeService子类扫描
    Set<Class<? extends FunctionChangeService>> sets = reflections.getSubTypesOf(FunctionChangeService.class);
    // 过滤未实现AbstractService的class
    List<Class<? extends FunctionChangeService>> list = sets.stream()
        .filter(AbstractService.class::isAssignableFrom)
        .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
        .collect(Collectors.toList());
    // 根据接口的方法名分类处理
    List<String> methodNames = Arrays.stream(FunctionChangeService.class.getDeclaredMethods()).map(Method::getName).collect(Collectors.toList());
    for (Class<? extends FunctionChangeService> aClass : list) {
      Method[] declaredMethods = aClass.getDeclaredMethods();
      for (Method method : declaredMethods) {
        String methodName = method.getName();
        if (methodNames.contains(methodName)) {
          List<Class<? extends FunctionChangeService>> methodList = functionChangeServiceMap.computeIfAbsent(methodName, k -> Lists.newArrayList());
          methodList.add(aClass);
        }
      }
    }
    for (String methodName : methodNames) {
      functionChangeServiceMap.computeIfAbsent(methodName, k -> Lists.newArrayList());
    }
  }


  private List<Class<? extends FunctionChangeService>> getFunctionServiceClassList(String methodName){
    List<Class<? extends FunctionChangeService>> functionServiceClassList = FunctionForwardServiceImpl.functionChangeServiceMap.get(methodName);
    if (functionServiceClassList == null) {
      log.error("错误的methodName:" + methodName);
      functionServiceClassList = Collections.emptyList();
    }
    return functionServiceClassList;
  }


  @Override
  public void doPlayerLogin(long playerId) {
    String methodName = doPlayerLogin.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    long startTime = System.currentTimeMillis();
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        functionChangeService.doPlayerLogin(playerId);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
      // 指定时间内处理一个批次业务，合并提交事务
      if(System.currentTimeMillis() - startTime > 400) {
        // getServiceContainer().save();
        startTime = System.currentTimeMillis();
      }
    }
  }

  @Override
  public void doPlayerLogout(long playerId) {
    String methodName = doPlayerLogout.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    long startTime = System.currentTimeMillis();
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        functionChangeService.doPlayerLogout(playerId);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
      // 指定时间内处理一个批次业务，合并提交事务
      if(System.currentTimeMillis() - startTime > 400) {
        // getServiceContainer().save();
        startTime = System.currentTimeMillis();
      }
    }
  }

  /**
   * 关闭功能入口Id处理
   * @param playerId
   */
  public void handlerClosedFunction(long playerId, final Player playerLocal, Set<Integer> ret) {
    String methodName = doGetClosedFunction.name();
    for (Class<? extends FunctionChangeService> clazz : getFunctionServiceClassList(methodName)) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        List<Integer> closeIds = functionChangeService.doGetClosedFunction(playerId, playerLocal);
        if (CollectionUtils.isNotEmpty(closeIds)) {
          ret.addAll(closeIds);
        }
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    }

  }

  public void getRedPointStatus(long playerId, Map<Integer, Boolean> redPointStatusMap) {
    String methodName = doGetRedPointStatus.name();
    for (Class<? extends FunctionChangeService> clazz : getFunctionServiceClassList(methodName)) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        functionChangeService.doGetRedPointStatus(playerId, redPointStatusMap);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    }
  }

  /**
   * 刷新红点业务处理转发
   * @param playerId
   */
  public void refreshRedPoint(long playerId) {
    String methodName = doRefreshRedPoint.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    long startTime = System.currentTimeMillis();
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        functionChangeService.doRefreshRedPoint(playerId);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
      //这里做的好的话 统计下已经执行的时间  到（500-100）=400ms时 save一次
      // 指定时间内处理一个批次业务，合并保存
			/*if(System.currentTimeMillis() - startTime > 400) {
				getServiceContainer().getModelContainer().notResetSave();
				startTime = System.currentTimeMillis();
			}*/
    }
  }

  @Override
  public void itemChangeService(long playerId, int itemId, long changeCount, long nowCount) {
    ConfigItemInfo configItemInfo = new ConfigItemInfo();
    if (configItemInfo != null) {
      itemChangeService(playerId, configItemInfo, itemId, changeCount, nowCount);

      // 金币和经验道具不触发doTaskChange（已有专用的【GET_GOLD-获得金币】类任务）
      if (changeCount > 0 && itemId != 1001 && itemId != 1002) {
        // 玩家获得道具类任务 更新分发
        doTaskChange(playerId, 1, itemId, changeCount);
        doTaskChange(playerId, 2, itemId, nowCount);
      }
    }
  }
  /**
   * 道具变化(获得道具)业务处理转发
   * @param playerId
   * @param configItemInfo
   */
  private void itemChangeService(long playerId, ConfigItemInfo configItemInfo, int itemId, long changeCount, long nowCount) {
    String methodName = doItemChangeService.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    long startTime = System.currentTimeMillis();
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        functionChangeService.doItemChangeService(playerId, configItemInfo, itemId, changeCount, nowCount);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
      // 指定时间内处理一个批次业务，合并提交事务
      if(System.currentTimeMillis() - startTime > 300) {
        // getServiceContainer().save();
        startTime = System.currentTimeMillis();
      }
    }
  }

  /**
   * 商品购买付费成功回调业务处理转发
   * <p>
   * 用于购买商品后，进行额外逻辑处理：可以是加成、额外奖励等
   * </p>
   */
  public void doBuyGoods(Player writePlayer, String shopName, int goodsId, Map<Integer, Long> getItems) {
    String methodName = doBuyGoods.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        Method method = clazz.getDeclaredMethod(methodName);
        ShopName annotation = method.getAnnotation(ShopName.class);
        if (annotation == null) {
          continue;
        }
        // 无注解条件 或 包含指定商城
        ConfigShop configShop = new ConfigShop();
        if (annotation.value().length == 0 || Arrays.asList(annotation.value()).contains(configShop.getName())) {
          functionChangeService.doBuyGoods(configShop);
        }
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    }
  }

  @Override
  public void chargeValue(long playerId, String shopName, int goodsId, String orderId, int chargeValue, long lastChargeTime) {
    String methodName = doChargeValue.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    // TODO 通过并行流执行操作,保证每个操作都没有关联
    long startTime = System.currentTimeMillis();
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      FunctionChangeService functionChangeService = getConventService(clazz);
      try {
        functionChangeService.doChargeValue(playerId, shopName, goodsId, orderId, chargeValue, lastChargeTime);
      }catch(Exception e){
        log.error(Utility.getTraceString(e));
      }
      // 指定时间内处理一个批次业务，合并提交事务
      if(System.currentTimeMillis() - startTime > 300) {
        // getServiceContainer().save();
        startTime = System.currentTimeMillis();
      }
    }
  }

  @Override
  public void chargeStamps(long playerId, int goodsId, String orderId, int chargeValue) {
    String methodName = doChargeStamps.name();
    // long lastChargeTime = getReadModel(playerId, PlayerModel.class).getPlayer().getOldChargeTime();
    long lastChargeTime = System.currentTimeMillis();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      FunctionChangeService functionChangeService = getConventService(clazz);
      try {
        functionChangeService.doChargeStamps(playerId, goodsId, orderId, chargeValue, lastChargeTime);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
      // getServiceContainer().save();
    }

    // 充值点券任务
    doTaskChange(playerId, 3, -1, chargeValue);
  }

  public static void main(String[] args) {
    System.out.println(111);
  }

  public void doTaskChange(long playerId, int type, int t, long p){
    String methodName = doTaskChange.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    // TODO 通过并行流执行操作,保证每个操作都没有关联
    functionServiceClassList.forEach(clazz -> {
      FunctionChangeService functionChangeService = getConventService(clazz);
      try {
        functionChangeService.doTaskChange(playerId, type, t, p);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    });
  }

  /***
   * 道具过期处理
   * @param uid
   * @param overdueList 过期的道具列表
   */
  public void itemOverdueService(long uid, List<Integer> overdueList) {
    String methodName = doItemOverdueService.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    // TODO 通过并行流执行操作,保证每个操作都没有关联
    functionServiceClassList.forEach(clazz -> {
      FunctionChangeService functionChangeService = getConventService(clazz);
      try {
        functionChangeService.doItemOverdueService(uid, overdueList);
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    });
  }


  /**
   * 指定注解类型的活动结束业务处理
   * 重要：【每个玩家会调度一次，只能处理玩家个人相关的清理流程】
   * @param playerId
   * @param functionId 注解的功能类型
   * @param subType	子类型
   * @return
   */
  public void activityEndService(long playerId, int functionId, int subType) {
    String methodName = doActivityEndService.name();
    List<Class<? extends FunctionChangeService>> functionServiceClassList = getFunctionServiceClassList(methodName);
    for (Class<? extends FunctionChangeService> clazz : functionServiceClassList) {
      try {
        FunctionChangeService functionChangeService = getConventService(clazz);
        Method method = clazz.getDeclaredMethod(methodName, long.class, int.class, int.class);
        FunctionType annotation = method.getAnnotation(FunctionType.class);
        if (annotation != null && Arrays.stream(annotation.value()).anyMatch(v-> v == functionId)) {
          functionChangeService.doActivityEndService(playerId, functionId,subType);
        }
      } catch (Exception e) {
        log.error(Utility.getTraceString(e));
      }
    }
  }



  @Override
  public <T> T getConventService(Class<T> clazz) {
    /*AbstractService abstractService = getService(clazz.asSubclass(AbstractService.class));
    if (abstractService != null) {
      return (T) abstractService;
    }*/
    return null;
  }


  @Override
  public <T> void runInnerFunction(Class<T> clazz, Consumer<T> consumer) {
    consumer.accept(getConventService(clazz));
  }
}
