package chuji.class_04;

public class Code_02_PrintBinaryTree {	//直观的打印一颗二叉树

	public static class Node {		//可先看输出结果理解，思路：把二叉树逆时针旋转了90度，再打印踹表示
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	//"H"表示头节点，"^"表示左子节点，左上离最近的节点，"v"表示右子节点，右下离最近的节点
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
		String val = to + head.value + to;	//加上符号表示的二叉树的其中一个值
		int lenM = val.length();	//字符串所占的空格
		int lenL = (len - lenM) / 2;	//与子树左边界的空格个数
		int lenR = len - lenM - lenL;	//与子树右边界的的空格个数
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);	//为什么height+1？ 他的递归策略是什么？
	}

	public static String getSpace(int num) {	//打印父节点与子树之间的空格
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(-222222222);
		head.right = new Node(3);
		head.left.left = new Node(Integer.MIN_VALUE);
		head.right.left = new Node(55555555);
		head.right.right = new Node(66);
		head.left.left.right = new Node(777);
		printTree(head);

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.left = new Node(5);
		head.right.right = new Node(6);
		head.left.left.right = new Node(7);
		printTree(head);

		head = new Node(1);
		head.left = new Node(1);
		head.right = new Node(1);
		head.left.left = new Node(1);
		head.right.left = new Node(1);
		head.right.right = new Node(1);
		head.left.left.right = new Node(1);
		printTree(head);

	}

}
