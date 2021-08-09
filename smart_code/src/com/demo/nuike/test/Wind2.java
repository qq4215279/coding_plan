/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.nuike.test;

import com.demo.common.entity.Node;

import java.util.Stack;

public class Wind2 {

    /**
     * 题目：给定一个单链表（无环），请判断是否是回文结构。在删除倒数第K个节点后，是否为回文结构。
     */

    public static boolean ishuiwei(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    public static boolean ishuiwei2(Node head, int k) {
        // 删除头结点
        if (k == 1) {
            head = head.next;
            return ishuiwei(head);
        }

        Node pre = head;
        Node cur = pre.next;
        int i = 1;
        while (cur != null) {
            if (i == k) {
                pre.next = cur.next;
                return ishuiwei(pre);
            }
            pre = pre.next;
            cur = cur.next;
            i++;
        }
        return ishuiwei(pre);
    }

    public static void main(String[] args) {
        Node head1 = null;
        head1 = new Node(1);
        head1.next = new Node(8);
        head1.next.next = new Node(4);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(8);
        head1.next.next.next.next.next = new Node(1);

        System.out.println(ishuiwei(head1)); // true
        System.out.println(ishuiwei2(head1, 1)); // false

    }
}
