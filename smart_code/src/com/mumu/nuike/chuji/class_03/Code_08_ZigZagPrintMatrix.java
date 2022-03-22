package com.mumu.nuike.chuji.class_03;

public class Code_08_ZigZagPrintMatrix {

	/**
	 * “之”字形打印矩阵
	 * 【题目】 给定一个矩阵matrix，按照“之”字形的方式打印这 个矩阵，
	 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 “之”字形打印的结果为：1，2，5，9，6，3，4，7，10，11， 8，12
	 * 【要求】 额外空间复杂度为O(1)。
	 */

	/**
	 * “之”字形打印矩阵 =表示赋值，==表示比较
	 * @param matrix
	 */

	public static void printMatrixZigZag(int[][] matrix) { // a,b代表两个点
		int aR = 0;
		int aC = 0;
		int bR = 0;
		int bC = 0;
		int endR = matrix.length - 1;    // 最后一行
		int endC = matrix[0].length - 1;// 最后一列
		boolean fromUp = false;        // 用来判断左下打右上还是右上打左下
		while (aR != endR + 1) {        // 来到最后一行
			printLevel(matrix, aR, aC, bR, bC, fromUp); // 调用打印函数。。。下面是变量++操作
			aR = aC == endC ? aR + 1 : aR;    // 只有a的列来到最后一列，a的行才往下走，否则不变。
			aC = aC == endC ? aC : aC + 1;  // 如果a的列不是最后一列，则a列++，直到a的列时最后一列
			bC = bR == endR ? bC + 1 : bC;    // 只有b的行来到最后一行，b的列才往下走，否则不变。
			bR = bR == endR ? bR : bR + 1;  // 如果b的行不是最后一行，则b的行++,直到b的行是最后一行
			fromUp = !fromUp;
		}
		System.out.println();
	}

	/**
	 * 打印过程
	 * @param m
	 * @param aR
	 * @param aC
	 * @param bR
	 * @param bC
	 * @param f
	 */
	public static void printLevel(int[][] m, int aR, int aC, int bR, int bC, boolean f) {
		if (f) {
			// 从右上交往左下角打印(行++，列--)
			while (aR != bR + 1) {
				System.out.print(m[aR++][aC--] + " ");
			}
		} else {
			// 从左下角往右上角打印(行--，列++)
			while (bR != aR - 1) {
				System.out.print(m[bR--][bC++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		printMatrixZigZag(matrix);

	}

}
