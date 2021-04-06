package chuji.class_03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code_03_StackAndQueueConvert {	//队列结构与栈结构的转换

	public static class TwoStacksQueue {	//栈结构实现队列结构
		private Stack<Integer> stackPush;	//进栈栈
		private Stack<Integer> stackPop;	//出栈栈

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		public void push(int pushInt) {	//进栈栈进栈
			stackPush.push(pushInt);
		}

		public int poll() {	//出栈栈出栈 -》思路：首先出栈栈需要进栈，从进栈栈里出栈出来的数直接进栈到出栈栈
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {	//如果出栈栈位空时
				while (!stackPush.empty()) {	//当进栈栈不为空时
					stackPop.push(stackPush.pop());	//出栈栈进栈，进的值是从出栈栈弹出的
				}
			}
			return stackPop.pop();	//出栈栈出栈
		}

		public int peek() {	//获取栈顶的值，但不移除栈顶的值。与上面一个方法类似，只是方法名称不同。
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			} else if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
			return stackPop.peek();
		}
	}

	public static class TwoQueuesStack {	//用队列结构实现栈结构
		private Queue<Integer> queue;		//思路：准备一个真队列，一个help队列，让前n-1个数进入help队里，最后一个数弹出,
		private Queue<Integer> help;		//在重新让help队列重新进入真队列，完成一个数弹出。重复刚刚操作...

		public TwoQueuesStack() {
			queue = new LinkedList<Integer>();
			help = new LinkedList<Integer>();
		}

		public void push(int pushInt) {//真队列-进队列
			queue.add(pushInt);
		}

		public int peek() {		//出队列
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() != 1) {//弹出n-1个数，留下最后一个数
				help.add(queue.poll());//进帮助队列里真进队列里的出的元素
			}
			int res = queue.poll(); //获取真队列的队尾元素
			help.add(res);	//比下面的方法就多了这个操作，这样就是把help队列完整的从真队列里复制过来
			swap();
			return res;	//返回该元素
		}

		public int pop() {	//与上面一个方法类似，只是方法名称不同。但是会移除栈顶元素
			if (queue.isEmpty()) {
				throw new RuntimeException("Stack is empty!");
			}
			while (queue.size() > 1) {
				help.add(queue.poll());
			}
			int res = queue.poll();
			swap();
			return res;
		}

		private void swap() {	//真队列与help交换
			Queue<Integer> tmp = help;
			help = queue;
			queue = tmp;
		}

	}

}
