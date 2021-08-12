package com.demo.nuike.chuji.class_01;

import com.demo.common.utils.ArrayUtil;
import com.demo.common.utils.LogarithmUtil;

public class Code_04_QuickSort { // 快速排序（交换排序）  时间复杂度O(N*logN) 额外空间复杂度O（logN）

	public static void quickSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		quickSort(arr, 0, arr.length - 1);
	}

	/**
	 *  快速排序
	 * @param arr
	 * @param l 左指针
	 * @param r 右指针
	 */
	public static void quickSort(int[] arr, int l, int r) {
		// 加上下面这行代码变成随机快排：
		if (l < r) {
			ArrayUtil.swap(arr, l + (int) (Math.random() * (r - l + 1)), r); // 表示从l到r上随机选择一个位置然后与r位置上的数进行交换。
			int[] p = partition(arr, l, r);	 // 求出等于区域的左右边界
			quickSort(arr, l, p[0] - 1); // 递归子过程，等于区域的左边界
			quickSort(arr, p[1] + 1, r); // 递归子过程，等于区域的右边界
		}
	}

	/**
	 *  （...less区）l...r（ more区...） r
	 * @param arr
	 * @param l
	 * @param r
	 * @return
	 */
	public static int[] partition(int[] arr, int l, int r) { // 默认返回等于区域在哪个范围
		int less = l - 1;
		int more = r;
		while (l < more) {
			if (arr[l] < arr[r]) {
				ArrayUtil.swap(arr, ++less, l++); // 表示less区域的数与less右边一个数交换，并且less区域+1，l指针+1
			} else if (arr[l] > arr[r]) {
				ArrayUtil.swap(arr, --more, l);
			} else {
				l++;
			}
		}

		// 最右边的r与more边界交换
		ArrayUtil.swap(arr, more, r);
		return new int[] { less + 1, more };//返回等于区域的左右两个边界，让这个两个边界去重复子过程
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
			quickSort(arr1);
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
		quickSort(arr);
		LogarithmUtil.printArray(arr);

	}

}
