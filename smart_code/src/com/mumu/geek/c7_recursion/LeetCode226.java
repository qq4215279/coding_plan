package com.mumu.geek.c7_recursion;

import com.mumu.common.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode226 { // LeetCode 226

    /**
     * 反转二叉树（镜像二叉树）
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

    /**
     * 递归
     * @author liuzhen
     * @date 2021/10/15 15:43
     * @param root
     * @return com.demo.common.entity.TreeNode
     */
    public static TreeNode invertTree(TreeNode root) { // 1.递归
        // 9 的左右子节点直接返回了，所以没有再调用递归。
        if (root == null)
            return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // 左右子节点发生交换
        root.left = right;
        root.right = left;

        return root; // 返回当前节点给上一级调用
    }

    /**
     * 迭代
     * @author liuzhen
     * @date 2021/10/15 15:43
     * @param root
     * @return com.demo.common.entity.TreeNode
     */
    public static TreeNode invertTree02(TreeNode root) {
        if (root == null) {
            return null;
        }

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

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(4);
        treeNode.left = new TreeNode(2);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right = new TreeNode(7);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(9);

//        TreeNode result1 = invertTree(treeNode);
//        result1.print();

        TreeNode result2 = invertTree02(treeNode);
        result2.print();

    }


}
