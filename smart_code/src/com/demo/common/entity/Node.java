package com.demo.common.entity;

/**
 * Node
 * 链表节点
 * @author liuzhen
 * @version 1.0.0 2021/8/6 15:47
 */
public class Node implements INode {

    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public void print() {
        Node cur = this;
        if (cur == null) {
            return;
        }

        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
    }
}
