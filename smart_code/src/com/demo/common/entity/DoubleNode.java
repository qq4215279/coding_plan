package com.demo.common.entity;

/**
 * DoubleNode
 * 双向链表
 * @author liuzhen
 * @version 1.0.0 2021/8/12 17:45
 */
public class DoubleNode implements INode {
    public int value;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(int data) {
        this.value = data;
    }

    /**
     * 打印双向链表
     */
    @Override
    public void print() {
        DoubleNode head = this;
        System.out.println("Double Linked List: ");
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.print("| ");
        while (end != null) {
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }
}
