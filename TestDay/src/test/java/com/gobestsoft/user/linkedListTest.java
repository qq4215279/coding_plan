/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.gobestsoft.user;

/**
 * linkedTest
 * 两个链表，一个从小到大，一个从大到小，合成一个从小到大的链表
 * @author liuzhen
 * @version 1.0.0 2021/7/8 16:04
 */
public class linkedListTest {

    /**
     * 两个链表，一个从小到大，一个从大到小，合成一个从小到大的链表
     */

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node() {
        }
    }

    public static Node mergeLinkList(Node ascNode, Node descNode) {
        Node postSortNode = nodeSortAsc(descNode);

        Node newNode = new Node();
        Node head = newNode;

        while (ascNode != null && postSortNode != null) {
            if (ascNode.value >= postSortNode.value) {
                newNode.value = postSortNode.value;
                postSortNode = postSortNode.next;
            } else {
                newNode.value = ascNode.value;
                ascNode = ascNode.next;
            }

            if (ascNode != null && postSortNode != null) {
                newNode.next = new Node();
                newNode = newNode.next;
            }
        }

        while (ascNode != null) {
            newNode.value = ascNode.value;
            ascNode = ascNode.next;

            if (ascNode != null) {
                newNode.next = new Node();
                newNode = newNode.next;
            }
        }

        while (postSortNode != null) {
            newNode.value = postSortNode.value;
            postSortNode = postSortNode.next;

            if (postSortNode != null) {
                newNode.next = new Node();
                newNode = newNode.next;
            }
        }

        return head;
    }

    /**
     * 链表排序，使用冒泡法
     * @param head
     * @return
     */
    public static Node nodeSortAsc(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node cur = head;

        while (cur != null) {
            Node next = cur.next;
            while (next != null) {
                if (cur.value > next.value) {
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

    public static void print(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        Node ascNode = new Node(1);
        ascNode.next = new Node(3);
        ascNode.next.next = new Node(5);
        ascNode.next.next.next = new Node(7);
        ascNode.next.next.next.next = new Node(9);

        Node descNode = new Node(10);
        descNode.next = new Node(8);
        descNode.next.next = new Node(6);
        descNode.next.next.next = new Node(6);
        descNode.next.next.next.next = new Node(5);

        Node head = mergeLinkList(ascNode, descNode);
        print(head);

    }

}
