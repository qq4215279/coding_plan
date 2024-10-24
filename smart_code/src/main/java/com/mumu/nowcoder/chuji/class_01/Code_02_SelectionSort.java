package com.mumu.nowcoder.chuji.class_01;

import com.mumu.common.utils.ArrayUtil;
import com.mumu.common.utils.LogarithmUtil;

public class Code_02_SelectionSort { // 选择排序--全局选择！  时间复杂度O(N^2)  空间复杂度O(1)

	public static void selectionSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			// j开始值由当前i值决定
			for (int j = i + 1; j < arr.length; j++) {
				minIndex = arr[j] < arr[minIndex] ? j : minIndex; // 相邻两个数比较，符合就记录该数为minIndex
			}
			ArrayUtil.swap(arr, i, minIndex);
		}
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
			selectionSort(arr1);
			LogarithmUtil.comparator(arr2);
			if (!LogarithmUtil.isEqual(arr1, arr2)) {
				succeed = false;
				LogarithmUtil.printArray(arr1);
				LogarithmUtil.printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = LogarithmUtil.generateRandomArray(maxSize, maxValue);
		LogarithmUtil.printArray(arr);
		selectionSort(arr);
		LogarithmUtil.printArray(arr);
	}

}
