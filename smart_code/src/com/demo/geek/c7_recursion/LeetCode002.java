package com.demo.geek.c7_recursion;

import com.demo.common.entity.Node;

/**
 * LeetCode002
 * 两数相加
 * @author liuzhen
 * @version 1.0.0 2021/8/5 23:25
 */
public class LeetCode002 {

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     *  l1:  2 -> 4 -> 3
     *  l2:  5 -> 6 -> 4
     *  res: 7 -> 0 -> 8
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     *
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     */


    /**
     * 递归
     * @param note1
     * @param note2
     * @return
     */
    public static Node addTwoNumbersByRecursion(Node note1, Node note2) {
        if (note1 == null && note2 == null) {
            return null;
        }
        if (note1 == null) {
            note1 = new Node(0);
        }
        if (note2 == null) {
            note2 = new Node(0);
        }

        int total = note1.value + note2.value;
        // 进位操作
        if (total >= 10) {
            total = total % 10;
            if (note1.next != null) {
                note1.next.value += 1;
            } else {
                note1.next = new Node(1);
            }
        }

        return new Node(total, addTwoNumbersByRecursion(note1.next, note2.next));
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(4);
        node1.next.next = new Node(3);

        Node node2 = new Node(5);
        node2.next = new Node(6);
        node2.next.next = new Node(4);

//        Node node = addTwoNumbers(node1, node2);
        Node node = addTwoNumbersByRecursion(node1, node2);
        node.print();

    }

}
