package com.demo.nuike.chuji.class_03;

import java.util.Stack;

public class Code_02_GetMinStack {	// 找出栈中的最小值	思路就是准备两个栈

	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {	//进栈
			if (this.stackMin.isEmpty()) {	//1.判断最小值栈是否为空
				this.stackMin.push(newNum);
			} else if (newNum <= this.getmin()) {	//2.判断newNum是否小于最小值栈的栈顶值
				this.stackMin.push(newNum);
			}
			this.stackData.push(newNum);	//其他情况进数据栈
		}

		public int pop() {	//出栈
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			int value = this.stackData.pop();	//先获取数据栈的值
			if (value == this.getmin()) {	//与最小值栈的值做比较
				this.stackMin.pop();
			}
			return value;
		}

		public int getmin() {		//弹出最小值
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();	//返回栈顶的值 不移除
		}
	}

	public static class MyStack2 {	//方法2
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {	//进栈--每一次操作，都是进入最小值栈
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {//比最小值栈小，进最小值栈
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();	//潜台词比最小值栈大，但是从最小值栈获取newMin（从最小值栈里弹出栈顶元素）
				this.stackMin.push(newMin);	//然后进入他自己本身
			}
			this.stackData.push(newNum);	//进入数据栈
		}

		public int pop() {		//出栈--有了上一步的进栈操作，直接可以从最小值栈取出最小值
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getmin());
		stack2.push(4);
		System.out.println(stack2.getmin());
		stack2.push(1);
		System.out.println(stack2.getmin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getmin());
	}

}
