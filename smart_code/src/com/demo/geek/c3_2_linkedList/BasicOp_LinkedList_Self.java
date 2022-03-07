/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.demo.geek.c3_2_linkedList;

import com.demo.common.entity.Node;

import java.util.Hashtable;

/**
 * 链表的基本操作
 * @author liuzhen
 * @date 2021/8/6 18:23
 */
public class BasicOp_LinkedList_Self {

    /**
     * 1.链表添加结点
     * @author liuzhen
     * @date 2021/9/13 17:14
     * @param head
     * @param value
     * @return void
     */
    public static void addNode(Node head, int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        }
        while (head.next != null) {
            head = head.next;
        }
        head.next = newNode;
    }

    /**
     * 2.删除链表指定索引结点
     * @author liuzhen
     * @date 2021/9/13 17:14
     * @param head
     * @param index
     * @return void
     */
    public static void deleteNode(Node head, int index) {
        if (index == 1) { //删除头结点      当index = 1，没有起作用
            head = head.next;
            return;
        }
        Node pre = head;
        Node cur = pre.next;
        int i = 2;
        while (cur != null) {
            if (i == index) {
                pre.next = cur.next; // 删除结点操作
            }
            pre = pre.next;
            cur = cur.next;
            i++;
        }
    }

    /**
     * 3.求链表长度
     * @author liuzhen
     * @date 2021/9/13 17:15
     * @param head
     * @return int
     */
    public static int nodeLength(Node head) {
        if (head == null) {
            System.out.println("链表的长度为：0");
            return 0;
        }
        int size = 1;
        while (head.next != null) {
            head = head.next;
            size++;
        }
        System.out.println("链表的长度为：" + size);
        return size;
    }

    /**
     * 4.排序: 并返回排序后的头结点。选择排序算法,即每次都选出未排序结点中最小的结点，与第一个未排序结点交换
     * @author liuzhen
     * @date 2021/9/13 17:15
     * @param head
     * @return com.demo.common.entity.Node
     */
    public static Node nodeSortASC(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            while (next != null) {
                if (cur.value >= next.value) {
                    int temp = cur.value;
                    cur.value = next.value;
                    next.value = temp;
                }
                next = next.next;
            }
            cur = cur.next;
        }
        return head;
    }

    /**
     * *5.去掉重复元素（即重复的值）: 需要额外的存储空间hashtable，调用hashtable.containsKey()来判断重复结点
     * @author liuzhen
     * @date 2021/9/13 17:15
     * @param head
     * @return void
     */
    public static void distinctNodeValue(Node head) {
        Node cur = head;
        Node pre = null;
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        while (cur != null) {
            if (hashtable.containsKey(cur.value)) {
                pre.next = cur.next;
            } else {
                hashtable.put(cur.value, 0);
            }
            pre = cur;
            cur = cur.next;
        }
    }

    /**
     * 6.返回倒数第 k 个结点
     * 技巧：两个指针，第一个指针向前移动k-1次，之后两个指针共同前进，当前面的指针到达末尾时，后面的指针所在的位置就是倒数第k个位置
     * @author liuzhen
     * @date 2021/9/13 17:15
     * @param head
     * @param k
     * @return com.demo.common.entity.Node
     */
    public static Node findReverseNode(Node head, int k) {
        Node first = head;
        Node second = head;
        for (int i = 0; i < k - 1; i++) { // 前移k-1步
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    /**
     * 7-1.反转链表
     * @author liuzhen
     * @date 2021/9/13 17:15
     * @param head
     * @return void
     */
    public static void nodeReverse1(Node head) {
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;
    }

    /**
     * 7-2.反转链表（递归方式：递归实质上就是系统帮你压栈的过程，系统在压栈的时候会保留现场。）
     * @author liuzhen
     * @date 2021/9/13 17:16
     * @param head
     * @return com.demo.common.entity.Node
     */
    public static Node nodeReverse2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head.next;
        Node newHead = nodeReverse2(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 8-1.判断链表是否有环：设置快指针和慢指针，慢指针每次走一步，快指针每次走两步。当快指针与慢指针相等时，就说明该链表有环
     * @author liuzhen
     * @date 2021/9/13 17:16
     * @param head
     * @return boolean
     */
    public static boolean isRinged(Node head) { //T 141
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (slow.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 8-2拓展:
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     * @author liuzhen
     * @date 2021/9/13 17:16
     * @param head
     * @return com.demo.common.entity.Node
     */
    public Node detectCycle(Node head) { // T 142
        return null;
    }

    /**
     * 9.判断两个链表是否相交
     * @author liuzhen
     * @date 2021/9/13 17:17
     * @param
     * @return boolean
     */
    public boolean checkIntersect() {

        return false;
    }

    /**
     * 打印链表
     * @author liuzhen
     * @date 2021/9/13 17:16
     * @param head
     * @return void
     */
    public static void printNodeList(Node head) {
        System.out.println("Print NodeList: ");
        if (head == null) {
            System.out.println("链表为空----");
            return;
        }
        while (head != null) {
            System.out.print(head.value + "  ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printNodeList(head);

        // 1.添加：
        addNode(head, 5);
        printNodeList(head);
        nodeLength(head);
        // 8.是否有环
        System.out.println("是否有环：" + isRinged(head));

        // 反转链表：
        nodeReverse1(head);
        System.out.println("反转后的链表为：----------------");
        printNodeList(head);
        System.out.println("----------------------------------");

        deleteNode(head, 3);
        printNodeList(head);
        nodeLength(head);

        Node head2 = new Node(10);
        head2.next = new Node(5);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(3);
        //排序：
        System.out.println("排序前：");
        printNodeList(head2);
        nodeSortASC(head2);
        System.out.println("排序后：");
        printNodeList(head2);

        //去重复
        System.out.println("去重复后：");
        distinctNodeValue(head2);
        printNodeList(head2);

        //返回倒数第 k 个几点
        Node reverseNode = findReverseNode(head2, 2);
        System.out.println("倒数第k个结点的值为：" + reverseNode.value);

    }
}
