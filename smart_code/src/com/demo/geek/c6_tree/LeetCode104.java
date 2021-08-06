/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c6_tree;

import com.demo.common.entity.TreeNode;

public class LeetCode104 {  // LeetCode T104

    /**
     * 二叉树的最大深度
     * <p>
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，x
     */

    /**
     * 递归实现二叉树最大深度
     * 时间复杂度O(n)
     * 空间复杂度:线性表最差O(n)、二叉树完全平衡最好O(logn)
     *
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        int h = Math.max(leftMaxDepth, rightMaxDepth) + 1;
        return h;
    }

}
