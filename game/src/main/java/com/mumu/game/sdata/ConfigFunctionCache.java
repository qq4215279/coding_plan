/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.sdata;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.google.common.collect.ImmutableMap;
import com.mumu.game.activity.core.ActivityManager;
import java.util.List;
import lombok.Getter;

/**
 * ConfigFunctionCache
 * @author liuzhen
 * @version 1.0.0 2024/10/7 16:38
 */
public class ConfigFunctionCache {

  /**
   * 功能id:config
   */
  @Getter
  private ImmutableMap<Integer, ConfigFunction> mainMap;

  /**
   * 全功能的id树
   */
  @Getter
  private Tree<Integer> functionTree;


  public void init() {
    // TODO 加载配置表
    List<TreeNode<Integer>> nodeList = CollUtil.newArrayList();

    mainMap.values().forEach(
        o -> {
          TreeNode<Integer> treeNode = new TreeNode<>(o.getFunctionId(),
              Integer.parseInt(o.getParentId()), o.getName(), o.getFunctionId());
          nodeList.add(treeNode);
        });
    functionTree = TreeUtil.buildSingle(nodeList);

    ActivityManager.reloadConfig(functionTree);
  }


}
