package com.demo.nuike.chuji.class_06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Code_01_BFS {

	/**
	 * 图的存储方式 1）邻接表 2）邻接矩阵 如何表达图？生成图？
	 *
	 * 宽度优先遍历
	 * 1，利用队列实现
	 * 2，从源节点开始依次按照宽 度进队列，然后弹出
	 * 3，每弹出一个点，把该节点所有没有进 过队列的邻接点放入队 列
	 * 4，直到队列变空
	 *
	 * 广度优先遍历
	 * 1，利用栈实现
	 * 2，从源节点开始把节点按照深 度放入栈，然后弹出
	 * 3，每弹出一个点，把该节点下一个没有 进过栈的邻接点放入栈
	 * 4，直到栈变空
	 * @param node
	 */

	public static void bfs(Node node) {
		if (node == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> map = new HashSet<>();
		queue.add(node);
		map.add(node);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!map.contains(next)) {
					map.add(next);
					queue.add(next);
				}
			}
		}
	}

}
