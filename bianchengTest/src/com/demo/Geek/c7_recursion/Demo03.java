package com.demo.Geek.c7_recursion;

import java.util.LinkedList;
import java.util.Queue;

public class Demo03 { // LeetCode 226

    /**
     * 反转二叉树
     * <p>
     * 翻转一棵二叉树。
     * <p>
     * 示例：
     * 输入：
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1  3  6   9
     * <p>
     * 输出：
     *      4
     *    /   \
     *   7    2
     *  / \  / \
     *  9 6 3  1
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode invertTree(TreeNode root) { // 1.递归

        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    public TreeNode invertTree02(TreeNode root) { // 2.迭代

        if (root == null)
            return null;

        TreeNode cur = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(cur);
        while (!queue.isEmpty()) {
            TreeNode curtent = queue.poll();
            TreeNode temp = curtent.left;
            curtent.left = curtent.right;
            curtent.right = temp;

            if (curtent.left != null)
                queue.add(curtent.left);
            if (curtent.right != null)
                queue.add(curtent.right);
        }
        return root;
    }


}
