package com.demo.nuike.chuji.class_05;

import java.util.HashMap;
import java.util.List;

public class Code_04_UnionFind {

	/**
	 * 认识并查集结构
	 */

	public static class Node {
		// whatever you like
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap; // 表示通过这个节点找其父节点，
		public HashMap<Node, Integer> sizeMap;	// 表示该表中的这个Node节点所在的集合一共有多少个节点

		public UnionFindSet() {	// 初始化，把每一个节点生成一个集合
			fatherMap = new HashMap<Node, Node>(); // 第一个Node表示自己，第二个Node表示其父节点（通过hash值来存父节点）
			sizeMap = new HashMap<Node, Integer>(); // 表示当前节点所在集合长度
		}

		public void makeSets(List<Node> nodes) { // 为每一个节点生成一个集合
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);	// 初始化，表示该节点父节点对应的是自己，有几个节点就有几个set集合
				sizeMap.put(node, 1);	// 该节点的对应的集合的长度为1
			}
		}

		private Node findHead(Node node) {	// 最下面一个节点找代表节点过程
			Node father = fatherMap.get(node); // 拿到该节点的父节点（既连着自己的上一个节点）
			if (father != node) {	// 父节点！=自己，说明还没拿到代表节点，那劫递归
				father = findHead(father); // 递归找代表节点
			}
			fatherMap.put(node, father);	//
			return father;	// 拿到代表节点
		}
		
		public boolean isSameSet(Node a, Node b) {//判断是否是相同节点
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

	public static void main(String[] args) {

	}

}
