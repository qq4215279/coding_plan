package com.demo.nuike.chuji.class_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code_01_PreInPosTraversal { //实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	//递归的方式实现遍历二叉树
	public static void preOrderRecur(Node head) {	//先序遍历
		if (head == null) {
			return;
		}
		System.out.print(head.value + " ");
		preOrderRecur(head.left);
		preOrderRecur(head.right);
	}

	public static void inOrderRecur(Node head) {	//中序遍历
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static boolean inOrderRecurIsBST(Node head) {	//中序遍历判断是否是搜索二叉树
		if (head == null) {
			return true;
		}
		List<Integer> list = new ArrayList<Integer>();
		inOrder(head,list);

		for(int i=0;i<list.size()-1;i++){
			if(list.get(i) >= list.get(i+1))
				return false;
		}

		return true;
	}
	private static void inOrder(Node head, List<Integer> list){
		if(head == null){
			return;
		}
		inOrder(head.left,list);
		list.add(head.value);
		inOrder(head.right,list);
	}



	public static void posOrderRecur(Node head) {	//后续遍历
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	//非递归的方式实现遍历二叉树：
	public static void preOrderUnRecur(Node head) {	//非递归先序遍历
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();	//准备一个栈 ，因为需要从底层回到上层去，所有借用了栈技术。
			stack.add(head);
			while (!stack.isEmpty()) {
				head = stack.pop();		//弹出当前节点，即为头节点。右左孩子压完栈后，此时左节点来到栈顶，即为弹出当前节点了
				System.out.print(head.value + " ");
				if (head.right != null) {	//有右孩子，就先把右孩子压栈，
					stack.push(head.right);
				}
				if (head.left != null) {	//再把左孩子压栈。
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	public static void inOrderUnRecur(Node head) {	//非递归中序遍历（00：22）
		System.out.print("in-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			while (!stack.isEmpty() || head != null) {	//栈不为空且有当前节点时
				if (head != null) {		//当前节点不为空
					stack.push(head);	//则当前节点进栈
					head = head.left;	//有左孩子时，当前节点变为左孩子继续进栈
				} else {		//直到当前节点为空（即左孩子为空）时
					head = stack.pop();		//开始从栈中弹出当前孩子
					System.out.print(head.value + " ");	//弹出并且打印
					head = head.right;	//当前节点变为右孩子
				}
			}
		}			//总的思路为：当前节点为空，从栈中拿一个，打印，当前节点不为空当前节点压入栈，向右移动，当前节点为左。


		System.out.println();
	}

	public static void posOrderUnRecur1(Node head) {	//非递归后续遍历（00：38）
		System.out.print("pos-order: ");		//采用先中，再右，再左的思路；中用新生成的辅助栈存起来
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();	//
			Stack<Node> s2 = new Stack<Node>();	//辅助栈，存所有进去的"中"
			s1.push(head);
			while (!s1.isEmpty()) {		//此步跟非递归前序遍历很像
				head = s1.pop();	//弹出当前节点，即为头节点。右左孩子压完栈后，此时左节点来到栈顶，即为弹出当前节点了
				s2.push(head);		//辅助栈把当前节点（被认为是“中”）进入辅助栈
				if (head.left != null) {	//有左孩子，就先把左孩子压栈，
					s1.push(head.left);
				}
				if (head.right != null) {	//有右孩子，就先把右孩子压栈，
					s1.push(head.right);
				}
			}
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	public static void posOrderUnRecur2(Node h) {	//不用辅助栈的方式
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("pre-order: ");
		preOrderRecur(head);
		System.out.println();
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println();
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println();

		// unrecursive
		System.out.println("============unrecursive=============");
		preOrderUnRecur(head);
		inOrderUnRecur(head);
		posOrderUnRecur1(head);
		posOrderUnRecur2(head);

		System.out.println("------------------------------------------------------------------");
		System.out.println(inOrderRecurIsBST(head));

	}

}
