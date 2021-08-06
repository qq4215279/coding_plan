/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.geek.c6_tree;

import com.demo.common.entity.MultiTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * N叉树的前序，层次，后续遍历
 * @author liuzhen
 * @date 2021/8/6 18:13
 */
public class MultiTreeNodeOrder { // LeetCode T589:N叉树的前序遍历;  层次遍历：       T590: N叉树的后序遍历

    /**
     * N叉树的前序遍历
     * <p>
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     * <p>
     * 例如，给定一个 3叉树 :
     * <p>
     * 返回其前序遍历: [1,3,5,6,2,4]。
     */


    List<Integer> list = new ArrayList<>();

    public List<Integer> preOrder(MultiTreeNode root) {  // 前序递归遍历N叉树
        if (root == null)
            return list;

        if (root != null) {
            list.add(root.value);
            for (MultiTreeNode node : root.children) {
                preOrder(node);
            }
        }
        return list;
    }

    public List<Integer> postOrder(MultiTreeNode root) {  // 后序递归遍历N叉树
        if (root == null)
            return list;

        if (root != null) {
            for (MultiTreeNode node : root.children) {
                postOrder(node);
            }
            list.add(root.value);
        }
        return list;
    }

    public List<Integer> levelOrder(MultiTreeNode root) {  // 层次递归遍历N叉树


        return list;
    }


    public static void main(String[] args) {

//        Node Aa = new Node(5);
//        Node Ab = new Node(6);
//        Node B = new Node(2);
//        Node C = new Node(4);
//        List<Node> AList = new ArrayList<>();
//        AList.add(Aa);
//        AList.add(Ab);
//        Node A = new Node(3, AList);
//        List<Node> rootList = new ArrayList<>();
//        rootList.add(A);
//        rootList.add(B);
//        rootList.add(C);
//        Node root = new Node(1, rootList);

//        new Day03Demo03().preorder(root);


    }

}