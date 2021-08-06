/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c3_2_linkedList;

import com.demo.common.entity.Node;

public class ReverseList {

    /**
     * 反转链表
     *Reverse a singly linked list.
     *
     * Example:
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     * Follow up:
     *
     * A linked list can be reversed either iteratively or recursively. Could you implement both?
     */

    public Node reverseList(Node head) {// 1.迭代
        Node cur = head;
        Node pre = null;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public Node reverseList02(Node head) { // 2.递归

        Node cur = head;
        Node pre = null;

        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }



}
