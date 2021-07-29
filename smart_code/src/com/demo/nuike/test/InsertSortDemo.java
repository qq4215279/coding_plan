/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.demo.nuike.test;

import java.util.Arrays;

public class InsertSortDemo {   // 插入排序 --从小到大排

    public static void insertSort(int arr[]){       //测试失败？？？

        if (arr == null || arr.length <2){
            return;
        }
        for (int i=1;i<arr.length;i++){
            for (int j=i;j>0;j--){
                int minIndex = 0;
                 minIndex = arr[j] < arr[j-1] ? j : j-1;
                 swap( arr,j,minIndex);
            }
        }
    }

    public static void swap(int arr[],int i,int j){
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    //对数器：
    //1.绝对正确的数组排序：
    public static void rightMethod(int arr[]){
        Arrays.sort( arr );
    }

    //2.随机数组生成器：
    public static int[] generateRandomArray(int maxSize,int maxValue){
        /*int arr[] = new int[(int)(Math.random()*(maxSize+1))];
        for (int i=0;i<arr.length;i++){
            arr[i] = (int) (Math.random()*(maxValue+1));
        }
        return arr;*/
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];	//生成长度随机的数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    //3.复制数组：
    public static int[] copyArray(int[]arr){
        /*if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;*/
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //4.两个数组进行比较：
    public static boolean equalsArray(int[]arr1,int[]arr2){   // 相同->true;不相同->false
       /* if ((arr == null && res !=null) || (arr != null && res == null)){
            return false;
        }
        if (arr == null && res == null){
            return true;
        }
        if (arr.length != res.length){
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != res[i]){
                return false;
            }
        }
        return true;*/
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {	//1.空计较false情况
            return false;
        }
        if (arr1 == null && arr2 == null) {										//2.空比较true情况
            return true;
        }
        if (arr1.length != arr2.length) {										//3.长度是否相等
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {									//4.值之间是否相等比较(最关键)
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    //5.打印数组:
    public static void printArray(int arr[]){
        if (arr == null){
            return;
        }
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
       /* int arr[] = {3,5,15,101,20,2,155};
        insertSort( arr );
        printArray( arr );*/

       //测试数组生成器有没有用：
        int testTime = 2;
        int maxSize = 100;
        int maxValue = 100;
        boolean flag = true;
        /*for (int i = 1; i < testTime; i++) {
            int[]arr = generateRandomArray( maxSize,maxValue );
            int[]res = copyArray( arr );
            insertSort( arr );
            rightMethod( res );
            if (!equalsArray( arr,res )) {
                flag = false;
                break;
            }
        }*/
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray( maxSize, maxValue );
            int[] arr2 = copyArray( arr1 );
            insertSort( arr1 );
            rightMethod( arr2 );
            if (!equalsArray( arr1, arr2 )) {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "Nice!" : "Fucking fucked!");
        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        insertSort( arr );
        printArray(arr);
    }
}
