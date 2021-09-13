/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c3_2_linkedList;

import com.demo.common.entity.Node;

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
    public Node reverseList(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        // 前一个结点
        Node pre = null;
        // 头结点
        Node cur = head;
        while (cur != null) {
            Node nextNode = cur.next; // 保留下一个结点
            cur.next = pre; // 指针反转
            pre = cur; // 前结点后移
            cur = nextNode; // 当前结点后移

        }
        return cur;
    }

    /**
     * 方法二：递归         时间复杂度是 O(n)    空间复杂度：O(1)O(1)。
     * @author liuzhen
     * @date 2021/9/13 17:31
     * @param head
     * @return com.demo.common.entity.Node
     */
    public Node reverseList02(Node head) {
        if(head == null || head.next == null) {
            return head;
        }

        Node temp = head.next;
        Node newHead = reverseList02(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {

    }

}
