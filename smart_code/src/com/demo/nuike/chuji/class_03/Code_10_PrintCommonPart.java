package com.demo.nuike.chuji.class_03;

import com.demo.common.entity.Node;

public class Code_10_PrintCommonPart {

	/**
	 * 打印两个有序链表的公共部分	和mergesort类似，可以先理解排序那题
	 * 【题目】 给定两个有序链表的头指针head1和head2，打印两个链表的公共部分。
	 */

	public static void printCommonPart(Node head1, Node head2) { // 找出两个链表的公共部分函数。
		System.out.print("Common Part: ");
		while (head1 != null && head2 != null) {
			// 节点一的值小于节点二的值，则节点一指向下一个节点，节点二不变。
			if (head1.value < head2.value) {
				head1 = head1.next;
			} else if (head1.value > head2.value) {	// 节点二的值小于节点一的值，则节点二指向下一个节点，节点一不变。
				head2 = head2.next;
			} else { // 节点一与二的值相等，就打印公共部分
				System.out.print(head1.value + " ");
				head1 = head1.next;
				head2 = head2.next;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node node1 = new Node(2);
		node1.next = new Node(3);
		node1.next.next = new Node(5);
		node1.next.next.next = new Node(6);

		Node node2 = new Node(1);
		node2.next = new Node(2);
		node2.next.next = new Node(5);
		node2.next.next.next = new Node(7);
		node2.next.next.next.next = new Node(8);

		node1.print();
		node2.print();
		printCommonPart(node1, node2);

	}

}
