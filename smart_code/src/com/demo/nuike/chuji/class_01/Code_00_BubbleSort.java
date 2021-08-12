package com.demo.nuike.chuji.class_01;

import com.demo.common.utils.ArrayUtil;
import com.demo.common.utils.LogarithmUtil;

public class Code_00_BubbleSort { // 冒泡排序（交换排序） 时间复杂度O(N^2)

	public static void bubbleSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		// 交换的最后一个元素
		for (int e = arr.length - 1; e > 0; e--) {
			// 相邻两个元素交换
			for (int i = 0; i < e; i++) {
				if (arr[i] > arr[i + 1]) {
					ArrayUtil.swap(arr, i, i + 1);
				}
			}
		}
	}

	// for test
	public static void main(String[] args) {
		int testTimes = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = LogarithmUtil.generateRandomArray(maxSize, maxValue);
			int[] arr2 = LogarithmUtil.copyArray(arr1);
			bubbleSort(arr1);
			LogarithmUtil.comparator(arr2);
			if (!LogarithmUtil.isEqual(arr1, arr2)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = LogarithmUtil.generateRandomArray(maxSize, maxValue);
		LogarithmUtil.printArray(arr);
		bubbleSort(arr);
		LogarithmUtil.printArray(arr);
	}

}
