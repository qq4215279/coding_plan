package chuji.class_04;

public class Code_08_CompleteTreeNodeNumber {	//求满二叉树结点个数   满二叉树节点个数公式：2的h次方-1 （h表示树的深度！）
		//时间复杂度：O(logn2)
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	//主函数
	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}				//当前层次为1
		return bs(head, 1, mostLeftLevel(head, 1));
	}
	//被主函数调用
	public static int bs(Node node, int l, int h) {//node:当前节点；l:当前节点所在层次；h：整棵树总共层次。返回值是总节点数
		if (l == h) {
			return 1;
		}								//l+1：是因为node右子树的第一个节点是算第二层次开始的
		if (mostLeftLevel(node.right, l + 1) == h) {//判断node的右子树的最左的深度到没到整体的深度。
			//到了：说明node左子树为满二叉树,而右子树不是满二叉树；没到：说明node右子树为满二叉树，左子树不是满二叉树。
			return (1 << (h - l)) + bs(node.right, l + 1, h);		//小机灵：1 << (h - l) 表示：2的（h-l）次方
			//到了：node左子树节点个数+node+递归遍历node右子树
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
			//没到：node右子树节点个数+node+递归遍历node左子树
		}
	}

	public static int mostLeftLevel(Node node, int level) {	//判断树总共有几层
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;	// -1是因为while中的level先进行++了，在进行判断子节点是否存在
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));

	}

}
