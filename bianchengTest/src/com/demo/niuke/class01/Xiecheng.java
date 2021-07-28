/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.niuke.class01;

public class Xiecheng {

    public static class Node{
        public int start;
        public int end;
        public boolean flag;

        public Node(int start, int end, boolean flag){
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 6
     * 0,30    0
     * 0,50    1
     * 10,20   2
     * 15,30   3
     * 20,50   4
     * 20,65   5
     */

    public static int testMethod(Node[] nodes){
        int count = 0;
        for (int i = 0; i < nodes.length ; i++) {
            for (int j = 0; j < i; j++) {
                if (nodes[j].end > nodes[i].start && nodes[j].flag){
                    count++;
                    break;
                }
                if (nodes[j].end <= nodes[i].start && nodes[j].flag ){
                    nodes[j].flag = false;
                    nodes[j].end = nodes[i].end;
                }
            }
        }

        return count;
    }



    public static void main(String[] args) {
        Node[] nodes = new Node[]{new Node(0,30,true),new Node(0,50,true),new Node(10,20,true),new Node(15,30,true),new Node(20,50,true),new Node(20,65,true)};
        int i = testMethod(nodes);
        System.out.println(i);
    }

}
