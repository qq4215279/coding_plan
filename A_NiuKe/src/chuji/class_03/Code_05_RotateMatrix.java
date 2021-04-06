package chuji.class_03;

public class Code_05_RotateMatrix { //旋转正方形

	public static void rotate(int[][] matrix) {
		int tR = 0;			//t-->start   d-->end	R-->row		C-->col
		int tC = 0;
		int dR = matrix.length - 1;
		int dC = matrix[0].length - 1;
		while (tR < dR) {
			rotateEdge(matrix, tR++, tC++, dR--, dC--);
		}
	}

	public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
		int times = dC - tC; 		//表示一行有几个点，
		int tmp = 0;
		for (int i = 0; i != times; i++) {	//i就是出发点,4个边界的点依此交换交换
			tmp = m[tR][tC + i];			// tem = [0,0]
			m[tR][tC + i] = m[dR - i][tC];	//eg:[0,0]->[n,0]
			m[dR - i][tC] = m[dR][dC - i];	//[1,n]->[n-1,n-1]
			m[dR][dC - i] = m[tR + i][dC];	//[n-1,n-1]->[0,n-1]
			m[tR + i][dC] = tmp;			//[0,n-1]->[0,0]
		}
	}

	public static void printMatrix(int[][] matrix) {	//打印旋转后的矩阵
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
