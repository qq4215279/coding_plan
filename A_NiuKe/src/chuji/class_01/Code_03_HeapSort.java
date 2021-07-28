package chuji.class_01;

import java.util.Arrays;

public class Code_03_HeapSort {	// 堆排序（选择排序）

	/**
	 * 堆排序（选择排序）
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
		swap(arr, 0, --size);
		while (size > 0) {
			heapify(arr, 0, size);
			swap(arr, 0, --size);
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
		while (arr[index] > arr[(index - 1) / 2]) {		// 当前位置的数与父位置的数比较，符合就交换
			swap(arr, index, (index - 1) / 2);
			index = (index - 1) / 2;	// 当前数来到父位置，继续while ,知道while不符合条件
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
			swap(arr, largest, index);	// 潜台词表示 largest != index
			index = largest;		// 当前索引变成左右孩子较大者的索引
			left = index * 2 + 1;
		}
	}

	public static void swap(int[] arr, int i, int j) {	// 交换
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
//		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		int[] arr = new int[7];
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

		int[] arr = generateRandomArray(100, 20);
		printArray(arr);
		heapSort(arr);
		printArray(arr);
	}

}
