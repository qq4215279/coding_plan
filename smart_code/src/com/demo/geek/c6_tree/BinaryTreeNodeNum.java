/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c6_tree;

import com.demo.common.entity.TreeNode;

public class BinaryTreeNodeNum {  // 代替test

    /**
     * 问：求二叉树节点数量
     */



    public static int nodeNum(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    public static int bs(TreeNode treeNode, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (mostLeftLevel(treeNode.right, l + 1) == h) {
            return (1 << (h - l)) + bs(treeNode.right, l + 1, h);
        } else {
            return (1 << (h - l - 1)) + bs(treeNode.left, l + 1, h);
        }
    }

    public static int mostLeftLevel(TreeNode treeNode, int level) {
        while (treeNode != null) {
            level++;
            treeNode = treeNode.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);

        System.out.println(nodeNum(head));
    }

}

