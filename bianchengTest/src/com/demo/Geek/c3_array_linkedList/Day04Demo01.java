/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c3_array_linkedList;

import java.util.List;

public class Day04Demo01 { // LeetCode 24

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode swapPairs(ListNode head) {
        // 定义前去结点技巧：！！！！
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        ListNode cur = head;

        while (cur != null && cur.next != null) {
            ListNode firstNode = cur;
            ListNode secondNode = cur.next;

            // 1.即: 前一个节点、当前节点、后一个节点的next节点分别等于谁谁谁！！
            pre.next = secondNode;  // 让之前指向1节点的现在指向交换后的2节点
            firstNode.next = secondNode.next;  // 1节点的next 指向 2节点的next（即3节点）
            secondNode.next = firstNode; // 此时2节点的next 指向 1节点   完成交换

            // 2.下一个节点判断
            pre = firstNode;
            cur = firstNode.next;
        }

        return dummy.next;
    }







}
