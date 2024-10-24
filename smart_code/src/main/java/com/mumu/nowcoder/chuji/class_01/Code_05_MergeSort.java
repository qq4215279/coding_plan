package com.mumu.nowcoder.chuji.class_01;

import com.mumu.common.utils.LogarithmUtil;

public class Code_05_MergeSort { // 归并排序 时间复杂度度O(N*logN)，额外空间复杂度O(N)

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		doMergeSort(arr, 0, arr.length - 1);
	}

	public static void doMergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return;
		}
		// 小机灵 等价于 mid = l + (r - l) / 2
		int mid = l + ((r - l) >> 1);
		// 调用子过程--左边排好序
		doMergeSort(arr, l, mid);
		// 调用子过程--右边排好序
		doMergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	/**
	 * 外排的方式填入辅助数组
	 * @param arr
	 * @param l
	 * @param m
	 * @param r
	 */
	public static void merge(int[] arr, int l, int m, int r) {
		// 准备辅助数组
		int[] help = new int[r - l + 1];
		// 用于给新数组赋值
		int i = 0;
		// 指针一样（左）
		int p1 = l;
		// 指针一样（右）
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++]; // 重点！！
		}
		// 把剩下排好顺序的直接赋值到辅助数组上
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		// 把剩下排好顺序的直接赋值到辅助数组上
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		// 把排好序的辅助数组上的值从新赋给arr数组
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i];
		}
	}

	// 5.主方法调用
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			// 生成一个有范围的随机数组
			int[] arr1 = LogarithmUtil.generateRandomArray(maxSize, maxValue);
			// 将生成的arr1数组赋值给新生成的arr2数组
			int[] arr2 = LogarithmUtil.copyArray(arr1);
			// arr1用自己写的排序方式排
			mergeSort(arr1);
			// arr2用绝对正确的方式排
			LogarithmUtil.comparator(arr2);
			// 比较正确性
			if (!LogarithmUtil.isEqual(arr1, arr2)) {
				succeed = false;
				// 打印输出
				LogarithmUtil.printArray(arr1);
				// 打印输出
				LogarithmUtil.printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = LogarithmUtil.generateRandomArray(maxSize, maxValue);
		LogarithmUtil.printArray(arr);
		mergeSort(arr);
		LogarithmUtil.printArray(arr);

	}

}
