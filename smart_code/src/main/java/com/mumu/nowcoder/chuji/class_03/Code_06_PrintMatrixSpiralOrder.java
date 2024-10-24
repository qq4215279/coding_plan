package com.mumu.nowcoder.chuji.class_03;

public class Code_06_PrintMatrixSpiralOrder {

	/**
	 * 转圈打印矩阵
	 * 【题目】 给定一个整型矩阵matrix，请按照转圈的方式打印它。
	 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
	 * 打印结果为：1，2，3，4，8，12，16，15，14，13，9， 5，6，7，11， 10
	 * 【要求】 额外空间复杂度为O(1)。
	 */


	/**
	 * 转圈打印矩阵	t->start; d->end; R->row; C->cos 就是一个扣边界的过程...
	 * @param matrix
	 */

	public static void spiralOrderPrint(int[][] matrix) { // 二位数组表示
		// 代表行，第一行第一个元素的位置为起点
		int tR = 0;
		// 代表列，第一列第一个元素的位置为起点
		int tC = 0;
		// 代表行，数组最后一行最后一个元素的位置为起点。数组的行长度-1。
		int dR = matrix.length - 1;
		// 代表列，数组最最后一行第一个元素的位置为起点.数组的列长度-1。
		int dC = matrix[0].length - 1;
		// 递归打印内圈矩阵
		while (tR <= dR && tC <= dC) {
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	/**
	 * 打印一圈
	 * @param m
	 * @param tR
	 * @param tC
	 * @param dR
	 * @param dC
	 */
	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
		// 表示只有一行的情况
		if (tR == dR) {
			for (int i = tC; i <= dC; i++) {
				// 打印这一列
				System.out.print(m[tR][i] + " ");
			}
		} else if (tC == dC) { // 表示只有一列的情况
			for (int i = tR; i <= dR; i++) {
				// 打印这一行
				System.out.print(m[i][tC] + " ");
			}
		} else {
			int curC = tC;
			int curR = tR;
			// 当当前列不等于最后一列时
			while (curC != dC) {
				// 打印，行元素不变列元素++
				System.out.print(m[tR][curC] + " ");
				curC++;    // 当前列+1
			}
			// 当当前行不等于最后一行时
			while (curR != dR) {
				// 打印，列元素不变行元素++
				System.out.print(m[curR][dC] + " ");
				curR++;    //当前行+1
			}
			// 当当前列不等于第一列时
			while (curC != tC) {
				// 打印，行元素不变列元素--
				System.out.print(m[dR][curC] + " ");
				curC--;    //当前列-1
			}
			// 当当前行不等于第一列时
			while (curR != tR) {
				// 打印，列元素不变行元素--
				System.out.print(m[curR][tC] + " ");
				curR--;    // 当前行-1
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
