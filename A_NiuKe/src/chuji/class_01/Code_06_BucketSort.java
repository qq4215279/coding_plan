package chuji.class_01;

import java.util.Arrays;

public class Code_06_BucketSort {	//桶排序

	// only for 0~200 value
	public static void bucketSort(int[] arr) {	//重点：用arr[i]++;用++来记录数组中同一个值的词频
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {	//找出最大值
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1];	//要排序数组的最大值多少，则生成桶的大小就为多少+1（因为要包括0）
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;	//往每个桶里存一个值，每个桶的词频+1（即给桶中的每个元素赋值）；
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {	//j代表arr数组的值
			while (bucket[j]-- > 0) {	//
				arr[i++] = j;	//给arr数组赋值，每赋一个值，每个下标要+1；
			}
		}

	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
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
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			bucketSort(arr1);
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
		bucketSort(arr);
		printArray(arr);

	}

}
