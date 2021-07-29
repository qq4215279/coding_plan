package com.demo.nuike.chuji.class_03;

public class Code_01_Array_To_Stack_Queue {

	public static class ArrayStack {	//用数组结构实现大小固定的栈
		private Integer[] arr;
		private Integer size;

		public ArrayStack(int initSize) {	//用数组结构实现大小固定的栈
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
		}

		public Integer peek() {	//栈顶
			if (size == 0) {
				return null;
			}
			return arr[size - 1];
		}

		public void push(int obj) {	//入栈
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			arr[size++] = obj;	//给数组赋值，下标++
		}

		public Integer pop() {	//出栈（弹出）
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			return arr[--size];
		}
	}

	public static class ArrayQueue {	//用数组结构实现大小固定的队列
		private Integer[] arr;
		private Integer size;
		private Integer first;
		private Integer last;

		public ArrayQueue(int initSize) {
			if (initSize < 0) {
				throw new IllegalArgumentException("The init size is less than 0");
			}
			arr = new Integer[initSize];
			size = 0;
			first = 0;
			last = 0;
		}

		public Integer peek() {	//出队列（弹出）
			if (size == 0) {
				return null;
			}
			return arr[first];
		}

		public void push(int obj) {	//进队列
			if (size == arr.length) {
				throw new ArrayIndexOutOfBoundsException("The queue is full");
			}
			size++;	//用于记录队列有多少个数
			arr[last] = obj;
			last = last == arr.length - 1 ? 0 : last + 1;	//判断是否到数组最后一个元素
		}

		public Integer poll() {	//出队列
			if (size == 0) {
				throw new ArrayIndexOutOfBoundsException("The queue is empty");
			}
			size--;
			int tmp = first;
			first = first == arr.length - 1 ? 0 : first + 1;
			return arr[tmp];
		}
	}

	public static void main(String[] args) {

	}

}
