package com.mumu.nowcoder.chuji.class_04;

import com.mumu.common.entity.TreeNode;
import com.mumu.common.utils.BinaryTreeUtil;

import java.util.LinkedList;
import java.util.Queue;

public class Code_04_SerializeAndReconstructTree {

	/**
	 * 介绍二叉树先序与层次便利店的序列化和反序列化			# —>  读夏普，表示空
	 */

	/**
	 * 先序遍历字符串--序列化		#代表空，！作用是分隔符
	 * @author liuzhen
	 * @date 2021/8/12 22:49
	 * @param head
	 * @return java.lang.String
	 */
	public static String serialByPre(TreeNode head) {
		// 当子节点为空，左右两个子节点用"#!"表示，不能省略
		if (head == null) {
			// 加上空占位符能更好的区分树的结构
			return "#!";
		}
		// 为每一个节点加"!"，可以更好的区分每个值的之间的区别，比如：12!3!,1!23!
		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		// 最后返回序列完的字符串
		return res;
	}

	/**
	 * 先序遍历字符串的--反序列化
	 * @author liuzhen
	 * @date 2021/8/12 22:50
	 * @param preStr
	 * @return com.demo.common.entity.TreeNode
	 */
	public static TreeNode reconByPreString(String preStr) {
		// 将序列化的字符串通过分隔"!"，把分割的每个字符串装进数组里
		String[] values = preStr.split("!");
		Queue<String> queue = new LinkedList<>();
		for (int i = 0; i != values.length; i++) {
			// 再把该数组里的字符串装进队列里
			queue.offer(values[i]);
		}
		return reconPreOrder(queue);
	}

	/**
	 * do先序遍历字符串的--反序列化
	 * @author liuzhen
	 * @date 2021/8/12 22:50
	 * @param queue
	 * @return com.demo.common.entity.TreeNode
	 */
	private static TreeNode reconPreOrder(Queue<String> queue) {
		// 从队列里弹出一个值
		String value = queue.poll();
		// 判断是否有空值（"#"代表序列化时的空值）
		if (value.equals("#")) {
			// 有空，则跳出循环
			return null;
		}
		// 没空，将该值生成头节点
		TreeNode head = new TreeNode(Integer.valueOf(value));
		// 递归，生成头节点的左子节点
		head.left = reconPreOrder(queue);
		// 递归，生成头节点的右子节点
		head.right = reconPreOrder(queue);
		return head;
	}

	/**
	 * 分层遍历字符串--序列化
	 * @author liuzhen
	 * @date 2021/8/12 22:52
	 * @param head
	 * @return java.lang.String
	 */
	public static String serialByLevel(TreeNode head) {
		if (head == null) {
			return "#!";
		}
		String res = head.value + "!";
		// 用队列来装链表的节点
		Queue<TreeNode> queue = new LinkedList<>();
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

	/**
	 * 分层字符串的反序列化
	 * @author liuzhen
	 * @date 2021/8/12 22:53
	 * @param levelStr
	 * @return com.demo.common.entity.TreeNode
	 */
	public static TreeNode reconByLevelString(String levelStr) {
		// 层次遍历->生成字符串
		String[] values = levelStr.split("!");
		int index = 0;
		TreeNode head = generateNodeByString(values[index++]);
		Queue<TreeNode> queue = new LinkedList<>();
		if (head != null) {
			// 进队列
			queue.offer(head);
		}
		TreeNode treeNode = null;
		while (!queue.isEmpty()) {
			// 出队列
			treeNode = queue.poll();
			treeNode.left = generateNodeByString(values[index++]);
			treeNode.right = generateNodeByString(values[index++]);
			if (treeNode.left != null) {
				queue.offer(treeNode.left);
			}
			if (treeNode.right != null) {
				queue.offer(treeNode.right);
			}
		}
		return head;
	}

	/**
	 * 生成一个节点对象
	 * @author liuzhen
	 * @date 2021/8/12 22:53
	 * @param val
	 * @return com.demo.common.entity.TreeNode
	 */
	private static TreeNode generateNodeByString(String val) {
		if (val.equals("#")) {
			return null;
		}
		return new TreeNode(Integer.valueOf(val));
	}


	public static void main(String[] args) {
		TreeNode head = null;
		BinaryTreeUtil.printTree(head);

		String pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		BinaryTreeUtil.printTree(head);

		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		BinaryTreeUtil.printTree(head);

		System.out.println("====================================");

		head = new TreeNode(1);
		BinaryTreeUtil.printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		BinaryTreeUtil.printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		BinaryTreeUtil.printTree(head);

		System.out.println("====================================");

		head = new TreeNode(1);
		head.left = new TreeNode(2);
		head.right = new TreeNode(3);
		head.left.left = new TreeNode(4);
		head.right.right = new TreeNode(5);
		BinaryTreeUtil.printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		BinaryTreeUtil.printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		BinaryTreeUtil.printTree(head);

		System.out.println("====================================");

		head = new TreeNode(100);
		head.left = new TreeNode(21);
		head.left.left = new TreeNode(37);
		head.right = new TreeNode(-42);
		head.right.left = new TreeNode(0);
		head.right.right = new TreeNode(666);
		BinaryTreeUtil.printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		BinaryTreeUtil.printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		BinaryTreeUtil.printTree(head);

		System.out.println("====================================");

	}
}
