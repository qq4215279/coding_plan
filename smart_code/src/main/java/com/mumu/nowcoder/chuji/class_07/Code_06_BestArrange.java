package com.mumu.nowcoder.chuji.class_07;

import java.util.Arrays;
import java.util.Comparator;

public class Code_06_BestArrange {	// 项目宣讲场次最多问题。思路：根据那个项目早结束来安排时间。

	/**
	 * 输入： 参数1，正数数组costs 参数2，正数数组profits 参数3， 正数k 参数4，正数m costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
	 * k表示你不能并行、只能串行的最多 做k个项目 m表示你初始的资金 说明：你每做完一个项目，马上获得的收益，可以支持你去做下 一个 项目。
	 * 输出： 你最后获得的最大钱数。
	 */

	public static class Program {	// 表示项目
		public int start;	// 开始时间
		public int end;		// 结束时间

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

	public static int bestArrange(Program[] programs, int cur) { // cur表示当前时刻
		// 在数组中排完序之后，
		Arrays.sort(programs, new ProgramComparator());

		// 一共要选的项目的数量，	根据结束时间早，那个项目就排在数组的前面。
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			// 如果这个数组中项目的开始时间是大于等于当前时间的，说明这个项目可以安排
			if (cur <= programs[i].start) {
				// 项目+1
				result++;
				// 当前时刻来到这个项目的结束时刻
				cur = programs[i].end;
			}
		}

		// 最后返回可执行项目的数量
		return result;
	}

	public static void main(String[] args) {

	}

}
