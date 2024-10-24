package com.mumu.nowcoder.chuji.class_01;

import com.mumu.common.utils.ArrayUtil;
import com.mumu.common.utils.LogarithmUtil;

public class Code_03_HeapSort {	// 堆排序（选择排序） 时间复杂度O(N*logN)，额外空间复杂度O(1)

	/**
	 * 堆排序（选择排序） 时间复杂度O(N*logN)，额外空间复杂度O(1)
	 * 堆结构非常重要：
	 * 1，堆结构的heapInsert与heapify
	 * 2，堆结构的增大和减少
	 * 3，如果只是建立堆的过程，时间复杂度为O(N)
	 * 4，优先级队列结构，就是堆结构
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		// 1.遍历数组的每个元素，确保生成大根堆
		for (int i = 0; i < arr.length; i++) {
			heapInsert(arr, i);
		}

		// 2. 对数组遍历，每个元素与0位置上的数进行交换，再进行下沉排序
		int size = arr.length;
		ArrayUtil.swap(arr, 0, --size);
		while (size > 0) {
			heapify(arr, 0, size);
			ArrayUtil.swap(arr, 0, --size);
		}
	}

	/**
	 * 生成大根堆（层次遍历）
	 * 大根堆：父节点的值大于或等于子节点的值
	 * 小根堆：父节点的值小于或等于子节点的值
	 * @param arr
	 * @param index
	 */
	public static void heapInsert(int[] arr, int index) {
		// 当前位置的数与父位置的数比较，符合就交换
		while (arr[index] > arr[(index - 1) / 2]) {
			ArrayUtil.swap(arr, index, (index - 1) / 2);
			// 当前数来到父位置，继续while ,知道while不符合条件
			index = (index - 1) / 2;
		}
	}

	/**
	 * 作用：当前index节点下沉排序过程
	 * heapify思路：找自己的左右两个孩子，两个孩子先比较找出更大的那个与自己比较，比自己大就交换，且要继续重复heapify；否则直接结束
	 * @param arr
	 * @param index
	 * @param size
	 */
	public static void heapify(int[] arr, int index, int size) { // 2.解决堆上突然一个数变化了，让它重新形成大根堆的方法
		int left = index * 2 + 1;	// 左孩子
		while (left < size) { // size用来判断左孩子是否越界（是否存在左孩子）
			int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left; // 判断是否下沉，选出左右孩子中的较大者
			largest = arr[largest] > arr[index] ? largest : index; 	// 让自己和左右孩子中的较大者判断，孩子与自己比较是否下沉
			if (largest == index) {
				break;
			}
			ArrayUtil.swap(arr, largest, index);	// 潜台词表示 largest != index
			index = largest;		// 当前索引变成左右孩子较大者的索引
			left = index * 2 + 1;
		}
	}


	// for test
	public static void main(String[] args) {
//		int testTime = 500000;
//		int maxSize = 100;
//		int maxValue = 100;
//		boolean succeed = true;
//		for (int i = 0; i < testTime; i++) {
//			int[] arr1 = generateRandomArray(maxSize, maxValue);
//			int[] arr2 = copyArray(arr1);
//			heapSort(arr1);
//			comparator(arr2);
//			if (!isEqual(arr1, arr2)) {
//				succeed = false;
//				break;
//			}
//		}
//		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = LogarithmUtil.generateRandomArray(100, 20);
		LogarithmUtil.printArray(arr);
		heapSort(arr);
		LogarithmUtil.printArray(arr);
	}

}
