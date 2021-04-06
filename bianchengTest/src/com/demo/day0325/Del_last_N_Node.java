/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */
package com.demo.day0325;

/**
 * DelNode
 *
 * @author liuzhen
 * @version 1.0.0 2020/7/28 17:25
 */
public class Del_last_N_Node {  // ???

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     *
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     *
     * 进阶：
     * 你能尝试使用一趟扫描实现吗？
     *
     */

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) {
            return null;
        }

        ListNode cur = head;

        int count = 1;
        // 找出链表size
        while (cur.next != null) {
            count++;
            cur = cur.next;
        }
        if (n > count) {
            return head;
        }

        int delNum = count - n + 1;
        int num = 1;
        ListNode pre = cur;
        while (cur.next != null) {
            cur = cur.next;
            num++;

            if (num == delNum) {
                pre.next = cur.next;
            }



        }

        if (cur.next != null) {
            cur.next = cur.next.next;
        }

        return head;
    }

}
