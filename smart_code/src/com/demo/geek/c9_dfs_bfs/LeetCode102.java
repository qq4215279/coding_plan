package com.demo.geek.c9_dfs_bfs;

import com.demo.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode102
 * 二叉树的层次遍历
 * @author liuzhen
 * @version 1.0.0 2021/8/16 13:59
 */
public class LeetCode102 {

    /**
     * 给你一个二叉树，请你返回其按层序遍历得到的节点值。 （即逐层地，从左到右访问所有节点）。
     *
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * 返回其层序遍历结果：
     * [
     *   [3],
     *   [9,20],
     *   [15,7]
     * ]
     *
     * 思路：
     * 1. BFS:
     * 2. DFS: 因为深度遍历时，能够知道每一层级，通过每一层层级时存储value值，eg： Map<level,list>
     */

    /**
     * 层次遍历
     * @author liuzhen
     * @date 2021/10/8 16:51
     * @param root
     * @return java.util.List<java.util.List<java.lang.Integer>>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点入队
        queue.add(root);

        while (!queue.isEmpty()) {
            // 一层的结果
            List<Integer> level = new ArrayList<>();
            int levelCount = queue.size();

            // 添加节点到一层的List中去
            for (int i = 0; i < levelCount; i++) {
                // 节点出队
                TreeNode node = queue.remove();

                // 节点的左子树入队
                if (node.left != null) {
                    queue.add(node.left);
                }

                // 节点的右子树入队
                if (node.right != null) {
                    queue.add(node.right);
                }

                level.add(node.value);
            }
            res.add(level);
        }
        return res;
    }
}
