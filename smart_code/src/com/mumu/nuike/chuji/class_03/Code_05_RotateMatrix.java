package com.mumu.nuike.chuji.class_03;

public class Code_05_RotateMatrix { // 旋转正方形

	/**
	 * 旋转正方形矩阵
	 * 【题目】 给定一个整型正方形矩阵matrix，请把该矩阵调整成 顺时针旋转90度的样子。
	 * 【要求】 额外空间复杂度为O(1)。
	 */

	public static void rotate(int[][] matrix) {
		// t-->start   d-->end	R-->row		C-->col
		int tR = 0;
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR < dR) {
			rotateEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
		// 表示一行有几个点
		int times = dC - tC;
		int tmp = 0;
		// i就是出发点,4个边界的点依此交换交换
		for (int i = 0; i != times; i++) {
			tmp = m[tR][tC + i];			// tem = [0,0]
			m[tR][tC + i] = m[dR - i][tC];	// eg:[0,0]->[0,n]
			m[dR - i][tC] = m[dR][dC - i];	// [0,n]->[n,n]
			m[dR][dC - i] = m[tR + i][dC];	// [n,n]->[n,0]
			m[tR + i][dC] = tmp;			// [n,0]->[0,0]
		}
	}

	public static void printMatrix(int[][] matrix) {	// 打印旋转后的矩阵
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		printMatrix(matrix);
		rotate(matrix);
		System.out.println("=========");
		printMatrix(matrix);

	}

}
