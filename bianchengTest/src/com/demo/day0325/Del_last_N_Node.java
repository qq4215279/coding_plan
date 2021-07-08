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

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode c = head;
        ListNode cur = head;

        int count = 1;
        // 找出链表size
        while (c.next != null) {
            count++;
            c = c.next;
        }
        if (n > count) {
            return head;
        }

        int delNum = count - n;
        int num = 1;
        while (cur.next != null) {
            cur = cur.next;
            num++;

            if (num == delNum) {
                cur.next = cur.next.next;
                break;
            }
        }

        return head;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode quickNode = head;
        ListNode slowNode = head;

        for (int i = 0; i < n - 1; i++) {
            if (quickNode.next == null) {
                return head;
            }
            quickNode = quickNode.next;
        }

        ListNode pre = null;
        while (quickNode.next != null) {
            quickNode = quickNode.next;
            pre = slowNode;
            slowNode = slowNode.next;
        }

        // 删除节点
        pre.next = slowNode.next;

        return head;
    }

    public static void print(ListNode head) {
        if (head == null) {
            System.out.println("空。。。。。");
        }

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);

        ListNode head = removeNthFromEnd(node, 2);
//        ListNode head = removeNthFromEnd2(node, 2);
        print(head);

    }

}
