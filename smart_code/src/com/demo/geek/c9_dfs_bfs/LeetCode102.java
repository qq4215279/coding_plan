package com.demo.geek.c9_dfs_bfs;

import com.demo.common.entity.TreeNode;

import java.util.List;

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

    public List<List<Integer>> levelOrder(TreeNode root) {

        return null;
    }
}
