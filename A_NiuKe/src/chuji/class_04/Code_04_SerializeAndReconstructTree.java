package chuji.class_04;

import java.util.LinkedList;
import java.util.Queue;

public class Code_04_SerializeAndReconstructTree {	//介绍二叉树的序列化和反序列化			#——》读夏普，表示空

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static String serialByPre(Node head) {	//先序遍历字符串序列化		#代表空，！作用是分隔符
		if (head == null) {		//当子节点为空，左右两个子节点用"#!"表示，不能省略
			return "#!";		//加上空占位符能更好的区分树的结构
		}
		String res = head.value + "!";	//为每一个节点加"!"，可以更好的区分每个值的之间的区别，比如：12！3！；1！23！
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;	//最后返回序列完的字符串
	}

	public static Node reconByPreString(String preStr) {	//先序遍历字符串的反序列化
		String[] values = preStr.split("!");	//将序列化的字符串通过分隔"!"，把分割的每个字符串装进数组里
		Queue<String> queue = new LinkedList<String>();
		for (int i = 0; i != values.length; i++) {
			queue.offer(values[i]);		//再把该数组里的字符串装进队列里
		}
		return reconPreOrder(queue);
	}

	public static Node reconPreOrder(Queue<String> queue) {
		String value = queue.poll();	//从队列里弹出一个值
		if (value.equals("#")) {	//判断是否有空值（"#"代表序列化时的空值）
			return null;	//有空，则跳出循环
		}
		Node head = new Node(Integer.valueOf(value));	//没空，将该值生成头节点
		head.left = reconPreOrder(queue);	//递归，生成头节点的左子节点
		head.right = reconPreOrder(queue);	//递归，生成头节点的右子节点
		return head;
	}

	public static String serialByLevel(Node head) {		//分层字符串的序列化============================================
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		Queue<Node> queue = new LinkedList<Node>();	//用队列来装链表的节点
		queue.offer(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			if (head.left != null) {
				res += head.left.value + "!";
				queue.offer(head.left);
			} else {
				res += "#!";
			}
			if (head.right != null) {
				res += head.right.value + "!";
				queue.offer(head.right);
			} else {
				res += "#!";
			}
		}
		return res;
	}

	public static Node reconByLevelString(String levelStr) {	//分层字符串的反序列化
		String[] values = levelStr.split("!");	//层次遍历->生成字符串
		int index = 0;
		Node head = generateNodeByString(values[index++]);
		Queue<Node> queue = new LinkedList<Node>();
		if (head != null) {
			queue.offer(head);	//进队列
		}
		Node node = null;
		while (!queue.isEmpty()) {
			node = queue.poll();	//出队列
			node.left = generateNodeByString(values[index++]);
			node.right = generateNodeByString(values[index++]);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
		return head;
	}

	public static Node generateNodeByString(String val) {	//去掉空的字符串(#)
		if (val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
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
		Node head = null;
		printTree(head);

		String pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

	}
}
