/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c6_tree;

import java.util.ArrayList;
import java.util.List;

public class Day04Demo02 {  //LeetCode T98

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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isValidBST(TreeNode root) { // *****递归实现！！！！！
        return recurse(root, null, null);
    }

    public boolean recurse(TreeNode root, Integer lower, Integer upper) {
        if (root == null)
            return true;

        int value = root.val;
        if (lower != null && value <= lower)
            return false;
        if (upper != null && value >= upper)
            return false;

        if (!recurse(root.right, value, upper))
            return false;
        if (!recurse(root.left, lower, value))
            return false;
        return true;
    }

    public boolean isValidBST2(TreeNode root) { //方法2：排序二叉树的中序遍历是递增

        if (root == null)
            return true;

        List<Integer> list = inOrder(root);
        for (int i = 0; i < list.size() - 1; i++) {
            int cur = list.get(i);
            int next = list.get(i + 1);
            if (cur >= next) {
                return false;
            }
        }

        return true;
    }

    List<Integer> list = new ArrayList<>();

    public List<Integer> inOrder(TreeNode root) {
        if (root == null)
            return list;

        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
        return list;
    }

    public static void main(String[] args) {

    }

}
