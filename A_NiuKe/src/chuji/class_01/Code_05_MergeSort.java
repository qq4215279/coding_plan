package chuji.class_01;

import java.util.Arrays;

public class Code_05_MergeSort {    //归并排序

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int l, int r) {//方法重载
		if (l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);                       //小机灵 等价于mid=l+(r-l)/2
		mergeSort(arr, l, mid);                             //调用子过程--左边排好序
		mergeSort(arr, mid + 1, r);                      //调用子过程--右边排好序
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int m, int r) {	//外排的方式填入辅助数组
		int[] help = new int[r - l + 1];        //准备辅助数组
		int i = 0;                              //用于给新数组赋值
		int p1 = l;                             // 指针一样（左）
		int p2 = m + 1;                         // 指针一样（右）
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];//重点！！
		}
		while (p1 <= m) {                       //把剩下排好顺序的直接赋值到辅助数组上
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {                       //把剩下排好顺序的直接赋值到辅助数组上
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {     //把排好序的辅助数组上的值从新赋给arr数组
			arr[l + i] = help[i];
		}
	}

	// for test 开始写绝对正确的数组（写5个方法）
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}   //1.随机发生器

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; //生成随机数组的大小
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()); //为随机数组赋随机值
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {  //2.数组复制，把随机产生的数组复制给一个新的数组res[]
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];        //生成一个res[]数组
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];			        //复制
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {//3.比较两个数组是否一样 ，一样就返回true,不一样就返回false
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) { //空计较
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {										//长度比较
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {									//值之间是否相等比较
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {  //4.打印Array数组方法
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");		//转换成字符串
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {    //5.主方法调用
		int testTime = 500000;				    //测试次数
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);   //生成一个有范围的随机数组
			int[] arr2 = copyArray(arr1);                          //将生成的arr1数组赋值给新生成的arr2数组
			mergeSort(arr1);                                       //arr1用自己写的排序方式排
			comparator(arr2);                                      //arr2用绝对正确的方式排
			if (!isEqual(arr1, arr2)) {                            //比较正确性
				succeed = false;
				printArray(arr1);                                  //打印输出
				printArray(arr2);                                  //打印输出
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
