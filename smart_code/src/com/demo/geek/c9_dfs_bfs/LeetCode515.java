package com.demo.geek.c9_dfs_bfs;

import com.demo.common.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode515
 * 在每个树行中找最大值
 * @author liuzhen
 * @version 1.0.0 2021/8/16 14:04
 */
public class LeetCode515 {

    /**
     * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
     *
     * 示例1：
     * 输入: root = [1,3,2,5,3,null,9]
     * 输出: [1,3,9]
     * 解释:
     *           1
     *          / \
     *         3   2
     *        / \   \
     *       5   3   9
     *
     * 示例2：
     * 输入: root = [1,2,3]
     * 输出: [1,3]
     * 解释:
     *           1
     *          / \
     *         2   3
     *
     * 示例3：
     * 输入: root = [1]
     * 输出: [1]
     *
     * 示例4：
     * 输入: root = [1,null,2]
     * 输出: [1,2]
     * 解释:
     *            1
     *             \
     *              2
     *
     * 示例5：
     * 输入: root = []
     * 输出: []
     *  
     * 提示：
     * 二叉树的节点个数的范围是 [0,104]
     * -231 <= Node.val <= 231 - 1
     */

    /**
     * BFS遍历
     * @author liuzhen
     * @date 2021/10/8 17:34
     * @param root
     * @return java.util.List<java.lang.Integer>
     */
    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxVuale = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                maxVuale = Math.max(maxVuale, poll.value);

                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }

            result.add(maxVuale);
        }

        return result;
    }

}
