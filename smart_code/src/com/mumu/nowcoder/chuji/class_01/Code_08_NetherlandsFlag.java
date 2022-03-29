package com.mumu.nowcoder.chuji.class_01;

import com.mumu.common.utils.ArrayUtil;
import com.mumu.common.utils.LogarithmUtil;

public class Code_08_NetherlandsFlag { // 荷兰国旗问题--快排问题  partition-分割

	/**
	 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，大于num的数放在数组的右边。
	 * 要求额外空间复杂度O(1)，时间复杂度O(N)
	 */

	/**
	 * 思路：与当前数比较，小于放左边，等于放中间，大于放右边
	 * @param arr
	 * @param l
	 * @param r
	 * @param num
	 * @return
	 */
	public static int[] partition(int[] arr, int l, int r, int num) {
		// less表示小于区域，l表示左边界
		int less = l - 1;
		// more表示大于区域，r表示右边界
		int more = r + 1;
		int cur = l;
		// cur有点相当于指针，表示当前数的索引
		while (cur < more) {
			if (arr[cur] < num) {
				// ++less表示小于区域的后一个数；相当于小于区域向右扩一个位置，cur指针+1
				ArrayUtil.swap(arr, ++less, cur++);
			} else if (arr[cur] > num) {
				// --more表示大于区域的前一个数
				ArrayUtil.swap(arr, --more, cur);
			} else {	// 潜台词： 当前数arr[cur]==num
				cur++;
			}
		}
		// 最后返回等于区域下标（左边界到右边界）是从哪到哪
		return new int[] { less + 1, more - 1 };
	}

	public static void main(String[] args) {
		int[] test = LogarithmUtil.generateRandomArray(100, 100);

		LogarithmUtil.printArray(test);
		int[] res = partition(test, 0, test.length - 1, 1);
		LogarithmUtil.printArray(test);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}
}
