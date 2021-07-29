package com.demo.nuike.chuji.class_04;

public class Code_03_SuccessorNode {	//在二叉树中找到一个节点的后继节点,中序遍历的方式

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {	//情况1：当该节点X有右子树，
			return getLeftMost(node.right);	//后继节点就是右子树上最左边的节点
		} else {					//情况2：当该节点X没有右子树，
			Node parent = node.parent;	//先找其父节点
			while (parent != null && parent.left != node) {	//直到父节点的左子树节点=该节点时!!! 找到，跳出循环
				node = parent;		//没跳出之前，让改节点变成其父节点，			即=>没有满足调节就向上找(令其父节点转为当前节点)
				parent = node.parent;	//该节点的父节点成为该节点的父节点
			}
			return parent;	//找到后返回父节点就是后继节点！
		}
	}

	public static Node getLeftMost(Node node) {	//找右子树上最左边的节点
		if (node == null) {
			return node;
		}
		while (node.left != null) {	//情况1：后继节点就是右子树上最左边的节点；
			node = node.left;	//子树存在左节点，就一直往下找左节点，直到左节点为空
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

}
