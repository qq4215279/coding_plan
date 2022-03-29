package com.mumu.nowcoder.chuji.class_01;

import com.mumu.common.utils.LogarithmUtil;

public class Code_06_BucketSort { // 桶排序  时间复杂度O(n),额外空间复杂度O(n)

	// only for 0~200 value

	/**
	 * 桶排序
	 * 重点：用arr[i]++;用++来记录数组中同一个值的词频
	 * @author liuzhen
	 * @date 2021/8/12 17:13
	 * @param arr
	 * @return void
	 */
	public static void bucketSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		// 找出最大值
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		// 要排序数组的最大值多少，则生成桶的大小就为多少+1（因为要包括0）
		int[] bucket = new int[max + 1];
		// 往每个桶里存一个值，每个桶的词频+1（即给桶中的每个元素赋值）；
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int i = 0;
		// j代表arr数组的值
		for (int j = 0; j < bucket.length; j++) {
			// 给arr数组赋值，每赋一个值，每个下标要+1；
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = LogarithmUtil.generateRandomArray(maxSize, maxValue);
			int[] arr2 = LogarithmUtil.copyArray(arr1);
			bucketSort(arr1);
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
		bucketSort(arr);
		LogarithmUtil.printArray(arr);

	}

}
