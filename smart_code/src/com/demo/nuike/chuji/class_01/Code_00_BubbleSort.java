package com.demo.nuike.chuji.class_01;

import java.util.Arrays;

public class Code_00_BubbleSort { // 冒泡排序（交换排序）

	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int e = arr.length - 1; e > 0; e--) {	// 交换的最后一个元素
			for (int i = 0; i < e; i++) {	// 相邻两个元素交换
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
				}
			}
		}
	}

	public static void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

	// 对数器:
	// for test  	开始写绝对正确的数组（写5个方法）
	public static void comparator(int[] arr) {	// 1.绝对正确的数组排序方法rightMethod
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {	// 2.随机数组发生器
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];	// 生成长度随机的数组
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {	// 3.数组复制，把随机产生的数组复制给一个新的数组res[]
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {	// 4.比较两个数组是否一样 ，一样就返回true,不一样就返回false
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {	//1.空计较false情况
			return false;
		}
		if (arr1 == null && arr2 == null) {										// 2.空比较true情况
			return true;
		}
		if (arr1.length != arr2.length) {										// 3.长度是否相等
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {									// 4.值之间是否相等比较(最关键)
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {	// 5.打印Array数组方法
		if (arr == null) {
			return ;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");		//以字符串进行打印
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {	// 主方法调用
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			bubbleSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		bubbleSort(arr);
		printArray(arr);
	}

}
