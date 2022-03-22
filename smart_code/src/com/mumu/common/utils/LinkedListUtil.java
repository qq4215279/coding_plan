package com.mumu.common.utils;

import com.mumu.common.entity.Node;

/**
 * LinkedListUtil
 * 链表工具类
 * @author liuzhen
 * @version 1.0.0 2021/8/6 15:45
 */
public class LinkedListUtil {

    /**
     * 生成链表
     * @author liuzhen
     * @date 2021/9/14 13:54
     * @param arr
     * @return com.demo.common.entity.Node
     */
    public static Node generateNodeList(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }

        Node root = new Node();
        Node res = root;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            Node temp = new Node(value);
            root.next = temp;
            root = root.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3};
        Node node = generateNodeList(arr);
        node.print();
    }

}
