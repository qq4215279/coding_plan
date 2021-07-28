/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.Geek.c3_array_linkedList;

public class LeetCode206 {  // LeetCode T206

    /**
     *反转链表
     *反转一个单链表。
     *
     *
     * 输入: 1->2->3->4->5->NULL
     * 输出: 5->4->3->2->1->NULL
     */

    //单链表定义
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode (int val) {
            this.val = val;
        }
    }

    public ListNode reverseList(ListNode head) {//方法一：迭代          时间复杂度是 O(n)    空间复杂度：O(1)O(1)。

        if(head == null || head.next == null)
            return head;

        ListNode pre = null; //前一个结点
        ListNode cur = head; //头结点
        while (cur != null) {
            ListNode nextNode = cur.next; //保留下一个结点
            cur.next = pre;//指针反转
            pre = cur;//前结点后移
            cur = nextNode;//当前结点后移

        }
        return cur;
    }

    public ListNode reverseList02(ListNode head) { //方法二：递归         时间复杂度是 O(n)    空间复杂度：O(1)O(1)。

        if(head == null || head.next == null)
            return head;

        ListNode temp = head.next;
        ListNode newHead = reverseList02(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }



    public static void main(String[] args) {

    }

}
