package com.demo.common.entity;

import java.util.*;

/**
 * MultiTreeNode
 * N叉树定义
 * @author liuzhen
 * @version 1.0.0 2021/8/6 16:30
 */
public class MultiTreeNode implements INode {
    public int value;
    public List<MultiTreeNode> children;

    public MultiTreeNode(int value) {
        this.value = value;
        this.children = Collections.EMPTY_LIST;
    }

    public MultiTreeNode(int value, List<MultiTreeNode> children) {
        this.value = value;
        this.children = children;
    }

    /**
     * 层次遍历N叉树打印
     */
    @Override
    public void print() {
        MultiTreeNode cur = this;
        if (cur == null) {
            return;
        }

        Queue<MultiTreeNode> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            MultiTreeNode pollNode = queue.poll();

            System.out.println(pollNode.value);

            for (MultiTreeNode multiTreeNode : pollNode.children) {
                if (multiTreeNode != null) {
                    queue.offer(multiTreeNode);
                }
            }
        }
    }

    /**
     * test print()
     *                1
     *        100    178     10
     *     99    30
     *
     * @param args
     */
    public static void main(String[] args) {
        // 子节点
        List<MultiTreeNode> c1ChildNodes = new ArrayList<>();
        c1ChildNodes.add(new MultiTreeNode(99));
        c1ChildNodes.add(new MultiTreeNode(30));
        MultiTreeNode c1 = new MultiTreeNode(100, c1ChildNodes);
        // 父节点
        List<MultiTreeNode> headChildNodes = new ArrayList<>();
        headChildNodes.add(c1);
        headChildNodes.add(new MultiTreeNode(178));
        headChildNodes.add(new MultiTreeNode(10));
        MultiTreeNode head = new MultiTreeNode(1, headChildNodes);
        // print
        head.print();
    }

}
