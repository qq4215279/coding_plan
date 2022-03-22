/*
 * Copyright 2018-2020, 上海哈里奥科技有限公司
 * All Right Reserved.
 */

package com.mumu.nuike.test;

import java.util.Arrays;

public class MergeSortDemo {

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        mergeSort( arr,0,arr.length-1 );
    }

    public static void mergeSort(int[] arr,int l,int r){
        if (l == r){
            return;
        }
        int mid = l + (r-l)/2;
        mergeSort( arr,l,mid );
        mergeSort( arr,mid+1,r );
        merge( arr, l, mid, r);
    }

    public static void merge(int[] arr,int l,int m,int r){

        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        while (p1 < m+1 && p2 < r+1){

            help[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 < m+1){
            help[i++] = arr[p1++];
        }
        while (p2 < r+1){
            help[i++] = arr[p2++];
        }
        for (int k = 0; k < help.length; k++) { //把排好序的辅助数组上的值从新赋给arr数组
            arr[k+l] = help[k];
        }

    }


    public static void main(String[] args) {
        int arr1[]={20,5,15,10,35,100};
        mergeSort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
