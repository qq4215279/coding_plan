package com.demo.nuike.chuji.class_01;

public class Code_08_NetherlandsFlag { // 荷兰国旗问题--快排问题  partition-分割
	/**
	 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
	 * 要求额外空间复杂度O(1)，时间复杂度O(N)
	 */

	public static int[] partition(int[] arr, int l, int r, int num) {//思路：与当前数比较，小于放左边，等于放中间，大于放右边
		// less表示小于区域，l表示左边界
		int less = l - 1;
		// more表示大于区域，r表示右边界
		int more = r + 1;
		int cur = l;
		// cur有点相当于指针，表示当前数的索引
		while (cur < more) {
			if (arr[cur] < num) {
				// ++less表示小于区域的后一个数；相当于小于区域向右扩一个位置，cur指针+1
				swap(arr, ++less, cur++);
			} else if (arr[cur] > num) {
				// --more表示大于区域的前一个数
				swap(arr, --more, cur);
			} else {	// 潜台词： 当前数arr[cur]==num
				cur++;
			}
		}
		return new int[] { less + 1, more - 1 }; // 最后返回等于区域下标（左边界到右边界）是从哪到哪
	}


	// for test
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// for test
	public static int[] generateArray() {
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 3);
		}
		return arr;
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

	public static void main(String[] args) {
		int[] test = generateArray();

		printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
