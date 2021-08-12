package com.demo.nuike.chuji.class_04;

import com.demo.common.entity.TreeNode;

public class Code_06_IsBalancedTree {

	/**
	 * 判断一棵二叉树是否是平衡二叉树
	 */

	public static boolean isBalance(TreeNode head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(TreeNode head, int level, boolean[] res) {		// 通过列出所有可能性
		if (head == null) {
			return level;
		}
		// 树的左边 获取左子树高度
		int lH = getHeight(head.left, level + 1, res);
		// 判断树的左子树是否存在，不存在返回树的高度
		if (!res[0]) {
			return level;
		}
		// 树的右边 获取右子树高度
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		// 判断左右子树高度差是否大于1
		if (Math.abs(lH - rH) > 1) {
			// 设置为false
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static void main(String[] args) {
		TreeNode head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.right = new TreeNode(3);
		head.left.left = new TreeNode(4);
		head.left.right = new TreeNode(5);
		head.right.left = new TreeNode(6);
		head.right.right = new TreeNode(7);

		System.out.println(isBalance(head));

	}

}
