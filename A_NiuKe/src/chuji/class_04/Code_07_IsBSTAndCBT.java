package chuji.class_04;

import java.util.LinkedList;
import java.util.Queue;

public class Code_07_IsBSTAndCBT {	//判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBST(Node head) {	//判断搜索二叉树--->可以用中序遍历判断方法，不在本代码，
		if (head == null) {						//去之前中序遍历二叉树代码里参照改
			return true;
		}
		boolean res = true;
		Node pre = null;
		Node cur1 = head;
		Node cur2 = null;
		while (cur1 != null) {
			cur2 = cur1.left;
			if (cur2 != null) {
				while (cur2.right != null && cur2.right != cur1) {
					cur2 = cur2.right;
				}
				if (cur2.right == null) {
					cur2.right = cur1;
					cur1 = cur1.left;
					continue;
				} else {
					cur2.right = null;
				}
			}
			if (pre != null && pre.value > cur1.value) {
				res = false;
			}
			pre = cur1;
			cur1 = cur1.right;
		}
		return res;
	}

	public static boolean isCBT(Node head) {	//判断是否是完全二叉树：没左有右；有左没右；有左有右；共三种可能。============
		if (head == null) {		//判断逻辑：情况一：有右孩子没有左孩子，直接跳出
			return true;		//		   情况二：有左孩子没有右孩子，或者两个孩子都有，则后面遇到的所有节点都必须是叶节点。
		}
		Queue<Node> queue = new LinkedList<Node>();
		boolean leaf = false;	//用于判断是否开启叶节点阶段
		Node l = null;
		Node r = null;
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();//首先弹出头结点，(队列哪里来的元素?):通过头结点找到他的左右孩子，在后面判断：左右孩子进队列，
			l = head.left;		//代表左孩子		然后在通过while判断后，左右孩子陆续成为head结点；代码递归于此...
			r = head.right;		//代表右孩子		//判断逻辑:
			if ((leaf && (l != null || r != null))//开启后续节点都是叶节点判断，只要其中一个孩子不等于空就满足false（情况二）
					||							  //
					(l == null && r != null)) {  //如果一个节点左孩子为空，右孩子不为空，则直接false。（情况一）
				return false;
			}
			if (l != null) {
				queue.offer(l);	//便是左孩子进队列
			}
			if (r != null) {
				queue.offer(r);
			} else {
				leaf = true;	//如果左右两个孩子有一个为空，则直接开启后续叶节点判断
			}
		}
		return true;	//最后没有满足上面条件后，得出结论-->是完全二叉树
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {	//表示二叉树的方法
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(4);
		head.left = new Node(2);
		head.right = new Node(6);
		head.left.left = new Node(1);
		head.left.right = new Node(3);
		head.right.left = new Node(5);

		printTree(head);
		System.out.println(isBST(head));
		System.out.println(isCBT(head));

	}
}