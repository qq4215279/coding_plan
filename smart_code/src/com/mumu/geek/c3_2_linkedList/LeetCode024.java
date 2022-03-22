/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.geek.c3_2_linkedList;

import com.mumu.common.entity.Node;
import com.mumu.common.utils.LinkedListUtil;

/**
 * 两两交换链表中的节点
 * @author liuzhen
 * @version 1.0.0 2020/7/28 17:25
 */
public class LeetCode024 {

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 示例:
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     *
     */

    public static Node swapPairs(Node head) {
        // 定义前去结点技巧：！！！！
        Node dummy = new Node(-1);
        dummy.next = head;
        Node pre = dummy;

        Node cur = head;

        while (cur != null && cur.next != null) {
            Node firstNode = cur;
            Node secondNode = cur.next;

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

    public static void main(String[] args) {
        Node head = LinkedListUtil.generateNodeList(new int[] {1, 2, 3, 4});

        swapPairs(head);

        head.print();

    }




}
