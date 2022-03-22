/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c6_tree;

import com.mumu.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode098 {  // LeetCode T98

    /**
     * 验证二叉搜索数树
     * <p>
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     */

    /**
     * 递归实现
     * @author liuzhen
     * @date 2021/8/6 17:37
     * @param root
     * @return boolean
     */
    public boolean isValidBST(TreeNode root) {
        return recurse(root, null, null);
    }

    /** 递归 */
    private boolean recurse(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }

        int value = root.value;
        if (lower != null && value <= lower) {
            return false;
        }
        if (upper != null && value >= upper) {
            return false;
        }

        if (!recurse(root.left, lower, value)) {
            return false;
        }
        if (!recurse(root.right, value, upper)) {
            return false;
        }

        return true;
    }

    /**
     * 方法2：排序二叉树的中序遍历是递增
     * @author liuzhen
     * @date 2021/8/6 17:37
     * @param root
     * @return boolean
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }

        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            int cur = list.get(i);
            int next = list.get(i + 1);
            if (cur >= next) {
                return false;
            }
        }

        return true;
    }

    /**
     * 中序遍历
     * @author liuzhen
     * @date 2021/8/6 18:10
     * @param root
     * @return java.util.List<java.lang.Integer>
     */
    public void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.value);
        inOrder(root.right, list);
        return;
    }

    public static void main(String[] args) {

    }

}
