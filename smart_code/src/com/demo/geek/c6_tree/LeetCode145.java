/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c6_tree;

import com.demo.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的遍历: 前序：  中序：   后序：T145
 * @author liuzhen
 * @date 2021/8/6 18:02
 */
public class LeetCode145 { // 二叉树遍历： 前序：  中序：   后序：T145

    List<Integer> list = new ArrayList<>();
    public List<Integer> postOrderTraversal(TreeNode root) { // 递归后序遍历

        if (root == null)
            return list;

        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            list.add(root.value);
        }
        return list;
    }

}
