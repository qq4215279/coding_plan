/*
 * Copyright 2020-2021, 木木996.
 * All Right Reserved.
 */

package com.mumu.nuike.chuji.class_01;

import com.mumu.common.utils.LogarithmUtil;

public class Code_12_SmallSum {	// 小河问题---归并排序：多了：返回值位int,在while中多了求小河过程。

	/**
	 * 小和问题和逆序对问题
	 * 小和问题
	 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
	 * 例子：
	 * [1,3,4,2,5] 1左边比1小的数，没有；
	 * 3左边比3小的数，1；
	 * 4左边比4小的数，1、3；
	 * 2左边比2小的数，1；
	 * 5左边比5小的数，1、3、4、2；
	 * 所以小和为1+1+3+1+1+3+4+2=16
	 *
	 * 逆序对问题
	 * 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印所有逆序对。
	 */

	public static int smallSum(int[] arr) {	// 1.小河方法
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return mergeSort(arr, 0, arr.length - 1);
	}

	public static int mergeSort(int[] arr, int l, int r) {	// 2.归并排序
		if (l == r) {
			return 0;
		}
		int mid = l + ((r - l) >> 1);
		return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r); // 左右两边排序过程产生小河，加merge过程产生小河
	}

	public static int merge(int[] arr, int l, int m, int r) {	// 3.外排融合的过程
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		int res = 0;
		while (p1 <= m && p2 <= r) {
			res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0; // 求小河过程。
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++]; // 后面于归并排序一样
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
		return res;
	}

	// for test
	public static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				res += arr[j] < arr[i] ? arr[j] : 0;
			}
		}
		return res;
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = LogarithmUtil.generateRandomArray(maxSize, maxValue);
			int[] arr2 = LogarithmUtil.copyArray(arr1);
			if (smallSum(arr1) != comparator(arr2)) {
				succeed = false;
				LogarithmUtil.printArray(arr1);
				LogarithmUtil.printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");
	}

}
