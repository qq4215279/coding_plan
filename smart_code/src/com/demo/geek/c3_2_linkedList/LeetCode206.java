/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.demo.geek.c3_2_linkedList;

import com.demo.common.entity.Node;
import com.demo.common.utils.LinkedListUtil;

/**
 * 反转链表
 * @author liuzhen
 * @version 1.0.0 2020/7/28 17:25
 */
public class LeetCode206 {

    /**
     * 反转链表
     * 反转一个单链表。
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */

    /**
     * 方法一：迭代          时间复杂度是 O(n)    空间复杂度：O(1)O(1)。
     * @author liuzhen
     * @date 2021/9/13 17:31
     * @param head
     * @return com.demo.common.entity.Node
     */
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 前一个结点
        Node pre = null;
        // 头结点
        Node cur = head;
        while (cur != null) {
            // 保留下一个结点
            Node nextNode = cur.next;
            // 指针反转
            cur.next = pre;
            // 前结点后移
            pre = cur;
            // 当前结点后移
            cur = nextNode;
        }

        return pre;
    }

    /**
     * 方法二：递归         时间复杂度是 O(n)    空间复杂度：O(1)O(1)。
     * @author liuzhen
     * @date 2021/9/13 17:31
     * @param head
     * @return com.demo.common.entity.Node
     */
    public static Node reverseList02(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node newHead = reverseList02(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Node head = LinkedListUtil.generateNodeList(new int[] {1, 2, 3, 4});

        reverseList(head);
        head.print();
    }

}
