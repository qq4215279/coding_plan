package com.demo.nuike.chuji.class_04;

public class Code_05_PaperFolding {	//折纸问题,给定一 个输入参数N，代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向

	public static void printAllFolds(int N) {
		printProcess(1, N, true);
	}

	public static void printProcess(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		printProcess(i + 1, N, true);
		System.out.println(down ? "down " : "up ");
		printProcess(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}
