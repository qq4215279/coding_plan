package com.demo.nuike.chuji.class_04;

public class Code_03_SuccessorNode {

	/**
	 * 在二叉树中找到一个节点的后继节点
	 * 【题目】 现在有一种新的二叉树节点类型如下：
	 * public class Node {
	 *     public int value;
	 *     public Node left;
	 *     public Node right;
	 *     public Node parent;
	 *     public Node(int data) {
	 *         this.value = data;
	 *     }
	 *  }
	 *
	 *  该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
	 *  假设有一 棵Node类型的节点组成的二叉树，树中每个节点的parent指针 都正确地指向 自己的父节点，头节点的parent指向null。
	 *  只给一个在 二叉树中的某个节点 node，请实现返回node的后继节点的函数。
	 *  在二 叉树的中序遍历的序列中， node的下一个节点叫作node的后继节点。
	 */

	/**
	 * 题目：在二叉树中找到一个节点的后继节点,中序遍历的方式
	 * 注：我们说的二叉树结点的前继结点、后继结点是：在中序遍历这棵二叉树的结果中，该结点的前一结点是它的前继结点、后一结点是后继结点。
	 *        a
	 *   b         c
	 * d   e     f   g
	 *    h  i
	 *  中序遍历顺序是：d b h e i a f c g
	 *  思路：分成两种情况
	 *
	 * 1.一个节点有右子树，那么它的下一个节点就是它的右子树中的最左子节点。例如b的后继节点是h。
	 * 2.一个节点没有右子树时分两种情况：
	 *  （1）当前节点是它父节点的左子节点，那么它的下一个节点就是它的父节点。 例如节点f的后继节点是c，节点d的后继节点是b。
	 *  （2）当前节点是它父节点的右子节点，此时沿着指向父节点的指针一直向上遍历，直到找到一个是它父节点的左子节点的节点，如果这个节点存在，那么这个节点的父节点就是我们要找的下一个节点。 f的下一个节点是a。
	 *  注意：存在一个节点没有后继节点，如图1的g节点。找节点g的下一个节点的步骤类似，我们先沿着指向父节点的指针到达节点c，由于节点c是它父节点a的右子节点，我们继续向上遍历到达a，由于节点a是树的根节点，
	 *  它没有父节点，因此节点g没有下一个节点。
	 *   如下所示：
	 *  	 a
	 *	   b
	 *   c   d
	 *  	   e
	 *			 f
	 */

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
		// 情况1：当该节点X有右子树，
		if (node.right != null) {
			// 后继节点就是右子树上最左边的节点
			return getLeftMost(node.right);
		} else { // 情况2：当该节点X没有右子树，
			// 先找其父节点
			Node parent = node.parent;
			// 直到父节点的左子树节点=该节点时!!! 找到，跳出循环
			while (parent != null && parent.left != node) {
				// 没跳出之前，让改节点变成其父节点，即=>没有满足调节就向上找(令其父节点转为当前节点)
				node = parent;
				// 该节点的父节点成为该节点的父节点
				parent = node.parent;
			}
			// 找到后返回父节点就是后继节点！
			return parent;
		}
	}

	/**
	 * 找右子树上最左边的节点
	 * @param node
	 * @return
	 */
	public static Node getLeftMost(Node node) {
		if (node == null) {
			return null;
		}
		// 情况1：后继节点就是右子树上最左边的节点
		while (node.left != null) {
			// 子树存在左节点，就一直往下找左节点，直到左节点为空
			node = node.left;
		}

		return node;
	}

	/**
	 * 			    	   6
	 * 		       3                9
	 * 	        1	  4	        8      10
	 * 	         2     5      7
	 * 	中序遍历：1 2 3 4 5 6 7 8 9 10
	 * @param args
	 */
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
