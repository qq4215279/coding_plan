package com.demo.nuike.chuji.class_08;

import java.util.ArrayList;
import java.util.List;

public class Code_03_Print_All_Subsquences { // 打印所有子序列

	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	public static void process(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = 0;
		process(chs, i + 1);
		chs[i] = tmp;
	}

	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}

	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}

	public static void printList(List<Character> res) {
		// ...;
	}

	public static List<Character> copyList(List<Character> list){
		return null;
	}

	//递归写法，打印子序列。	思路：把需要打印字符串变成字节数组。按顺序遍历数组，分为两条路，一条是打印字节上的值，另一条是不打印为空情况
	public static void printAllSub(char[] str, int i, String res){
		if (i == str.length){	//当来到终止位置时，之前剩余的结果打印，直接return
			System.out.println(res);
			return;
		}
		printAllSub( str, i + 1,res );//不要当前的字符，就直接往下遍历字节数组，
		printAllSub( str, i+1,res + String.valueOf( str[i] ) );//如过要当前字符，则在当前字符的基础上加上当前的字符。然后再
	}			// 去下一个位置。		这样两条路都走全了

	public static void main(String[] args) {
		String test = "abc";
		printAllSubsquence(test);
		printAllSub( test.toCharArray(),0,"" );
	}

}
