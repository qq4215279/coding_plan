package com.mumu.nuike.chuji.class_03;

import java.util.HashMap;

public class Code_13_CopyListWithRandom {

	/**
	 * 复制含有随机指针节点的链表
	 * 【题目】 一种特殊的链表节点类描述如下：
	 * public class Node {
	 * 	  public int value;
	 * 	  public Node next;
	 * 	  public Node rand;
	 * 	  public Node(int data) {
	 * 	  	  this.value = data;
	 * 	  }
	 * }
	 *
	 * Node类中的value是节点值，next指针和正常单链表中next指针的意义一样，都指向下一个节点，rand指针是Node类中新增的指针，
	 * 这个指针可能指向链表中的任意一个节点，也可能指向null。 给定一个由 Node节点类型组成的无环单链表的头节点head，
	 * 请实现一个 函数完成 这个链表中所有结构的复制，并返回复制的新链表的头节点。
	 * 进阶： 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N) 内完成原问题要实现的函数。
	 */

	public static class Node {
		public int value;
		public Node next;
		public Node rand;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node copyListWithRand1(Node head) {	// 方法一，用hash表
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		Node cur = head;
		while (cur != null) {	// 表示遍历所有节点，并在value中生成所有拷贝节点
			map.put(cur, new Node(cur.value));	// key表示当前节点，value表示当前节点的拷贝节点。
			cur = cur.next;
		}
		cur = head;
		while (cur != null) {
			map.get(cur).next = map.get(cur.next);	// 左边表示当前节点的拷贝节点，右边表示当前节点的next节点的拷贝节点。左边指向右边
			map.get(cur).rand = map.get(cur.rand);	// 表示（左边）拷贝节点的rand指针指向（右边）原来当原来连边的rand指针指向的拷贝节点
			cur = cur.next;
		}
		return map.get(head);
	}

	public static Node copyListWithRand2(Node head) {	// 方法二：
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next = null;
		// copy node and link to every node
		while (cur != null) {
			next = cur.next;
			cur.next = new Node(cur.value);
			cur.next.next = next;
			cur = next;
		}
		cur = head;
		Node curCopy = null;
		// set copy node rand 复制随机节点
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			curCopy.rand = cur.rand != null ? cur.rand.next : null;
			cur = next;
		}
		Node res = head.next;
		cur = head;
		// split	分离之前的原链表节点与在原链表中每个节点后面生成的拷贝节点
		while (cur != null) {
			next = cur.next.next;
			curCopy = cur.next;
			cur.next = next;
			curCopy.next = next != null ? next.next : null;
			cur = next;
		}
		return res;
	}

	public static void printRandLinkedList(Node head) {
		Node cur = head;
		System.out.print("order: ");
		while (cur != null) {
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
		cur = head;
		System.out.print("rand:  ");
		while (cur != null) {
			System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = null;
		Node res1 = null;
		Node res2 = null;
		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(6);

		head.rand = head.next.next.next.next.next; // 1 -> 6
		head.next.rand = head.next.next.next.next.next; // 2 -> 6
		head.next.next.rand = head.next.next.next.next; // 3 -> 5
		head.next.next.next.rand = head.next.next; // 4 -> 3
		head.next.next.next.next.rand = null; // 5 -> null
		head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

		printRandLinkedList(head);
		res1 = copyListWithRand1(head);
		printRandLinkedList(res1);
		res2 = copyListWithRand2(head);
		printRandLinkedList(res2);
		printRandLinkedList(head);
		System.out.println("=========================");

	}

}
