/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c6_tree;

import java.util.ArrayList;
import java.util.List;

public class Day03Demo04erchashu { // 二叉树遍历： 前序：  中序：   后序：T145

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {//递归后序遍历

        if (root == null)
            return list;

        if (root != null) {
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            list.add(root.val);
        }
        return list;

    }

}
