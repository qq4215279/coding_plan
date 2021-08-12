package com.demo.common.utils;

import com.demo.common.entity.TreeNode;

/**
 * TreeNodeUtil
 * 二叉树工具类
 * @author liuzhen
 * @version 1.0.0 2021/8/12 21:13
 */
public class BinaryTreeUtil {

    /**
     * "H"表示头节点，"^"表示左子节点，左上离最近的节点，"v"表示右子节点，右下离最近的节点
     * @author liuzhen
     * @date 2021/8/12 21:10
     * @param head
     * @return void
     */
    public static void printTree(TreeNode head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(TreeNode head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        // 加上符号表示的二叉树的其中一个值
        String val = to + head.value + to;
        // 字符串所占的空格
        int lenM = val.length();
        // 与子树左边界的空格个数
        int lenL = (len - lenM) / 2;
        // 与子树右边界的的空格个数
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        // 为什么height+1？ 他的递归策略是什么？
        printInOrder(head.left, height + 1, "^", len);
    }

    /**
     * 打印父节点与子树之间的空格
     * @author liuzhen
     * @date 2021/8/12 21:10
     * @param num
     * @return java.lang.String
     */
    private static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(-222222222);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(Integer.MIN_VALUE);
        head.right.left = new TreeNode(55555555);
        head.right.right = new TreeNode(66);
        head.left.left.right = new TreeNode(777);
        printTree(head);

        head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);
        head.left.left.right = new TreeNode(7);
        printTree(head);

        head = new TreeNode(1);
        head.left = new TreeNode(1);
        head.right = new TreeNode(1);
        head.left.left = new TreeNode(1);
        head.right.left = new TreeNode(1);
        head.right.right = new TreeNode(1);
        head.left.left.right = new TreeNode(1);
        printTree(head);

    }

}
