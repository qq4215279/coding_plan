/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.geek.c6_tree;

import com.mumu.common.entity.TreeNode;

import java.util.Stack;

/**
 * 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式
 */
public class BinaryTreeNodeOrder { // 后续遍历 LeetCode145

    /**
     * 递归的方式实现先序遍历二叉树
     * @author liuzhen
     * @date 2021/8/6 17:19
     * @return
     */
    public static void preOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 非递归的方式实现先序遍历二叉树：
     * @author liuzhen
     * @date 2021/8/6 17:22
     * @param head
     * @return void
     */
    public static void preOrderUnRecur(TreeNode head) {
        System.out.print("pre-order: ");
        if (head != null) {
            // 准备一个栈 ，因为需要从底层回到上层去，所有借用了栈技术。
            Stack<TreeNode> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                // 弹出当前节点，即为头节点。右左孩子压完栈后，此时左节点来到栈顶，即为弹出当前节点了
                head = stack.pop();
                System.out.print(head.value + " ");
                // 有右孩子，就先把右孩子压栈，
                if (head.right != null) {
                    stack.push(head.right);
                }
                // 再把左孩子压栈。
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 递归的方式实现中序遍历二叉树
     * @author liuzhen
     * @date 2021/8/6 17:19
     * @return
     */
    public static void inOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 非递归中序遍历（00：22）
     * @author liuzhen
     * @date 2021/8/6 17:25
     * @param head
     * @return void
     */
    public static void inOrderUnRecur(TreeNode head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            // 栈不为空且有当前节点时
            while (head != null || !stack.isEmpty()) {
                // 当前节点存在，开始进栈处理
                if (head != null) {
                    // 则当前节点进栈
                    stack.push(head);
                    // 有左孩子时，当前节点变为左孩子继续进栈
                    head = head.left;
                } else { // 直到当前节点为空（即左孩子为空）时，开始出栈处理
                    // 开始从栈中弹出当前孩子
                    head = stack.pop();
                    // 弹出并且打印
                    System.out.print(head.value + " ");
                    // 当前节点变为右孩子
                    head = head.right;
                }
            }
        }    // 总的思路为：当前节点为空，从栈中拿一个，打印，当前节点不为空当前节点压入栈，向右移动，当前节点为左。

        System.out.println();
    }

    public static void inOrderUnRecur2(TreeNode head) {
        System.out.print("in-order2: ");

        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

    }

    /**
     * 后续遍历
     * @author liuzhen
     * @date 2021/8/6 17:22
     * @param head
     * @return void
     */
    public static void postOrderRecur(TreeNode head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 非递归后续遍历（00：38）
     * 思路：采用先中，再右，再左的思路；中用新生成的辅助栈存起来。
     *      后续遍历特点：head最后出来，过了head.right节点，过了head.right.right节点...,
     *      所以在根据栈先进后出特性，先进head,再进head.right,再进head.right.right,...,
     *
     *
     * @author liuzhen
     * @date 2021/8/6 17:26
     * @param head
     * @return void
     */
    public static void postOrderUnRecur1(TreeNode head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<TreeNode> s1 = new Stack<TreeNode>();
            // 辅助栈，存所有进去的"中"
            Stack<TreeNode> s2 = new Stack<TreeNode>();
            s1.push(head);
            // 此步跟非递归前序遍历很像
            while (!s1.isEmpty()) {
                // 弹出当前节点，即为头节点。右左孩子压完栈后，此时左节点来到栈顶，即为弹出当前节点了
                head = s1.pop();
                // 辅助栈把当前节点（被认为是“中”）进入辅助栈
                s2.push(head);
                // 有左孩子，就先把左孩子压栈，
                if (head.left != null) {
                    s1.push(head.left);
                }
                // 有右孩子，就先把右孩子压栈，
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            // 打印所有节点
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 不用辅助栈的方式 后续遍历
     * @author liuzhen
     * @date 2021/8/6 17:28
     * @param head
     * @return void
     */
    public static void postOrderUnRecur2(TreeNode head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(head);
            TreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(5);
        head.left = new TreeNode(3);
        head.right = new TreeNode(8);
        head.left.left = new TreeNode(2);
        head.left.right = new TreeNode(4);
        head.left.left.left = new TreeNode(1);
        head.right.left = new TreeNode(7);
        head.right.left.left = new TreeNode(6);
        head.right.right = new TreeNode(10);
        head.right.right.left = new TreeNode(9);
        head.right.right.right = new TreeNode(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        postOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        postOrderUnRecur1(head);
        postOrderUnRecur2(head);

        System.out.println("------------------------------------------------------------------");
//        System.out.println(inOrderRecurIsBST(head));

    }

}
