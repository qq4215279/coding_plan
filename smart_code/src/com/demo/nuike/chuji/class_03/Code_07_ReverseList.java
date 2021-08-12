package com.demo.nuike.chuji.class_03;

import com.demo.common.entity.DoubleNode;
import com.demo.common.entity.Node;

public class Code_07_ReverseList {	// 反转单向和双向链表

	/**
	 * 反转单向链表
	 * @author liuzhen
	 * @date 2021/8/12 17:48
	 * @param head
	 * @return com.demo.common.entity.Node
	 */
	public static Node reverseList(Node head) {
		Node pre = null;
		Node next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
	}

	/**
	 * 反转双向链表
	 * @author liuzhen
	 * @date 2021/8/12 17:48
	 * @param head
	 * @return com.demo.common.entity.DoubleNode
	 */
	public static DoubleNode reverseList(DoubleNode head) {
		DoubleNode pre = null;
		DoubleNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			head.last = next;
			pre = head;
			head = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.print();
		head1 = reverseList(head1);
		head1.print();

		DoubleNode head2 = new DoubleNode(1);
		head2.next = new DoubleNode(2);
		head2.next.last = head2;
		head2.next.next = new DoubleNode(3);
		head2.next.next.last = head2.next;
		head2.next.next.next = new DoubleNode(4);
		head2.next.next.next.last = head2.next.next;
		head2.print();
		reverseList(head2).print();
	}

}
