package com.demo.nuike.chuji.class_07;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code_03_IPO {
	public static class Node {	//Node表示项目，每个项目里有两个点p,c
		public int p;	//p表示受益
		public int c;	//c表示花费

		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	public static class MinCostComparator implements Comparator<Node> {	//收益低的比较器，谁收益低放在上面

		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}

	}

	public static class MaxProfitComparator implements Comparator<Node> {//收益高的比较器，谁收益高谁放在上面

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

	}

	public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		Node[] nodes = new Node[Profits.length];
		for (int i = 0; i < Profits.length; i++) {
			nodes[i] = new Node(Profits[i], Capital[i]);
		}

		PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());//花费谁小放在上面的小根堆，
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());//收益谁大谁放在上面的大根堆
		for (int i = 0; i < nodes.length; i++) {	//第一步：把所有的项目加到小根堆里面去
			minCostQ.add(nodes[i]);
		}
		for (int i = 0; i < k; i++) {	//依次做项目，最多做k个
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {//我当前的钱是W,如果小根堆的钱小于我的钱且不为空。说明小根堆对顶的
				maxProfitQ.add(minCostQ.poll());//项目是可以做的。=>小根堆的堆顶弹出一个，进到按照利润谁大放到上面的大根堆里。
			}
			if (maxProfitQ.isEmpty()) {//表示没有项目可做的情况：比如启动资金不够做项目或者大根堆的项目做完了
				return W;
			}
			W += maxProfitQ.poll().p;	//获得大根堆解锁出来最高收益的项目的钱，加到自己总收益哪里去。
		}
		return W;
	}

}
