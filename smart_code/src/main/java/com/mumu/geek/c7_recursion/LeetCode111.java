package com.mumu.geek.c7_recursion;

import com.mumu.common.entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode111 { // LeetCode 111

    /**
     * 二叉树的最小深度
     * 给定一个二叉树，找出其最小深度。
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * 给定二叉树 [3,9,20,null,null,15,7],
     *      3
     *     / \
     *    9  20
     *      /  \
     *     15   7
     * 返回它的最小深度  2.
     */


    public static int minDepth(TreeNode root) { // 1.递归
        if (root == null) {
            return 0;
        }
        if ((root.left == null) && (root.right == null)) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(min_depth, minDepth(root.left));
        }
        if (root.right != null) {
            min_depth = Math.min(min_depth, minDepth(root.right));
        }

        return min_depth + 1;
    }

    /**
     * TODO  题目???
     * @author liuzhen
     * @date 2021/9/14 14:35
     * @param root
     * @return int
     */
    public static int minDepth2(TreeNode root) { // 1.递归
        if (root == null) {
            return 0;
        }

        int l = minDepth(root.left);
        int r = minDepth(root.right);

        int minRes = Math.min(l, r) + 1;

        return minRes;
    }

//    public static int minDepth02(TreeNode root) { // 2.迭代
//        int count = 0;
//        TreeNode cur = root;
//
//        return 1;
//    }

    public static int minDepth02(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<QueueNode> queue = new LinkedList<>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }

        return 0;
    }

    private static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    /**
     * root：[3,9,20,null,null,15,7]
     *      3
     *     / \
     *    9  20
     *      /  \
     *     15   7
     *
     * root2：[2,null,3,null,4,null,5,null,6]
     *      2
     *       \
     *        3
     *         \
     *          4
     *           \
     *            5
     *             \
     *              6
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(15);
        root.left.right = new TreeNode(7);

        System.out.println("minDepth: " + minDepth(root));
        System.out.println("minDepth2: " + minDepth2(root));

        System.out.println("-------------->");

        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);

        System.out.println("minDepth: " + minDepth(root2));
        System.out.println("minDepth2: " + minDepth2(root2));
    }


}
