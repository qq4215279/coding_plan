package com.demo.nuike.chuji.class_01;

import java.util.Arrays;

public class Code_04_QuickSort { // 快速排序（交换排序）   时间复杂度O(N*logN)额外空间复杂度O（logN）

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
			swap(arr, l + (int) (Math.random() * (r - l + 1)), r); // 表示从l到r上随机选择一个位置然后与r位置上的数进行交换。
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
				swap(arr, ++less, l++); // 表示less区域的数与less右边一个数交换，并且less区域+1，l指针+1
			} else if (arr[l] > arr[r]) {
				swap(arr, --more, l);
			} else {
				l++;
			}
		}

		// 最右边的r与more边界交换
		swap(arr, more, r);
		return new int[] { less + 1, more };//返回等于区域的左右两个边界，让这个两个边界去重复子过程
	}

	public static void swap(int[] arr, int i, int j) {//两个数交换
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
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
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			quickSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		quickSort(arr);
		printArray(arr);

	}

}
