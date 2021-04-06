package chuji.class_04;

public class Code_06_IsBalancedTree {	//判断一棵二叉树是否是平衡二叉树

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {		//通过列出所有可能性
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);	//树的左边 获取左子树高度
		if (!res[0]) {	//判断树的左子树是否存在，不存在返回树的高度
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);	//树的右边 获取右子树高度
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {	//判断左右子树高度差是否大于1
			res[0] = false;	//设置为false
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		System.out.println(isBalance(head));

	}

}
