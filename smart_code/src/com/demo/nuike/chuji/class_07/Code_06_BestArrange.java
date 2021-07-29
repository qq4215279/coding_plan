package com.demo.nuike.chuji.class_07;

import java.util.Arrays;
import java.util.Comparator;

public class Code_06_BestArrange {	//项目宣讲场次最多问题。思路：根据那个项目早结束来安排时间。

	public static class Program {	//表示项目
		public int start;	//开始时间
		public int end;		//结束时间

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {	//谁结束更早，谁排在上面
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int cur) {//cur表示当前时刻
		Arrays.sort(programs, new ProgramComparator());	//在数组中排完序之后，
		int result = 0;		//一共要选的项目的数量，	根据结束时间早，那个项目就排在数组的前面。
		for (int i = 0; i < programs.length; i++) {
			if (cur <= programs[i].start) {	//如果这个数组中项目的开始时间是大于等于当前时间的，说明这个项目可以安排
				result++;	//项目+1
				cur = programs[i].end;	//当前时刻来到这个项目的结束时刻
			}
		}
		return result;	//最后返回可执行项目的数量
	}

	public static void main(String[] args) {

	}

}
