package com.demo.common.entity;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TreeNode
 * 定义二叉树
 * @author liuzhen
 * @version 1.0.0 2021/8/6 15:52
 */
public class TreeNode implements INode {

    public int value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * 层次遍历二叉树打印
     */
    @Override
    public void print() {
        TreeNode cur = this;
        if (cur == null) {
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            TreeNode pollNode = queue.poll();

            System.out.println(pollNode.value);

            if (pollNode.left != null) {
                queue.offer(pollNode.left);
            }
            if (pollNode.right != null) {
                queue.offer(pollNode.right);
            }
        }

    }

}
