/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c3_2_linkedList;

/**
 * 用链表实现栈的功能
 * @author liuzhen
 * @date 2021/8/6 18:26
 */
public class LinkedStack<T> {
    public static class Node<T> {
        T item;
        Node<T> nextNode;

        Node() {
            item = null;
            nextNode = null;
        }
        Node(T item, Node<T> nextNode) {
            this.item = item;
            this.nextNode = nextNode;
        }

        boolean end() {
            return item == null && nextNode == null;
        }
    }

    public Node<T> top = new Node<T>();
    public void push(T item) {
        top = new Node<T>(item, top);
    }

    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.nextNode;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> str = new LinkedStack<String>();
        for(String str1:"my name is eric".split(" ")){
            str.push(str1);
        }
        String string;
        while((string=str.pop())!=null){
            System.out.println(string);
        }
    }
}
