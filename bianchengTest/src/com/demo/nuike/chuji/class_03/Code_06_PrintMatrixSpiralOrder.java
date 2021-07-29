package com.demo.nuike.chuji.class_03;

public class Code_06_PrintMatrixSpiralOrder {	//转圈打印矩阵	t->start; d->end; R->row; C->cos 就是一个扣边界的过程...

	public static void spiralOrderPrint(int[][] matrix) {//二位数组表示
		int tR = 0;						//代表行，第一行第一个元素的位置为起点
		int tC = 0;						//代表列，第一列第一个元素的位置为起点
		int dR = matrix.length - 1;		//代表行，数组最后一行最后一个元素的位置为起点。数组的行长度-1。
		int dC = matrix[0].length - 1;	//代表列，数组最最后一行第一个元素的位置为起点.数组的列长度-1。
		while (tR <= dR && tC <= dC) {	//递归打印内圈矩阵
			printEdge(matrix, tR++, tC++, dR--, dC--);
		}

	}

	public static void printEdge(int[][] m, int tR, int tC, int dR, int dC) {//打印一圈
		if (tR == dR) {				//表示只有一行的情况
			for (int i = tC; i <= dC; i++) {
				System.out.print(m[tR][i] + " ");	//打印这一列
			}
		} else if (tC == dC) {		//表示只有一列的情况
			for (int i = tR; i <= dR; i++) {
				System.out.print(m[i][tC] + " ");	//打印这一行
			}
		} else {
			int curC = tC;
			int curR = tR;
			while (curC != dC) {		//当当前列不等于最后一列时
				System.out.print(m[tR][curC] + " ");	//打印，行元素不变列元素++
				curC++;	//当前列+1
			}
			while (curR != dR) {		//当当前行不等于最后一行时
				System.out.print(m[curR][dC] + " ");	//打印，列元素不变行元素++
				curR++;	//当前行+1
			}
			while (curC != tC) {		//当当前列不等于第一列时
				System.out.print(m[dR][curC] + " ");	//打印，行元素不变列元素--
				curC--;	//当前列-1
			}
			while (curR != tR) {		//当当前行不等于第一列时
				System.out.print(m[curR][tC] + " ");	//打印，列元素不变行元素--
				curR--;	//当前行-1
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		spiralOrderPrint(matrix);

	}

}
