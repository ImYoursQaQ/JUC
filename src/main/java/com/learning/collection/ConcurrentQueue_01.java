package com.learning.collection;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueue_01 {

	public static void main(String[] args) {
		Queue<String> queue = new ConcurrentLinkedQueue<>();
		for(int i = 0; i < 10; i ++) {
			queue.offer("a" + i);   // add
		}

		System.out.println(queue);

		System.out.println(queue.size());

		System.out.println(queue.poll());
		System.out.println(queue.size());

		System.out.println(queue.peek());
		System.out.println(queue.size());

		// 双端队列Deque
	}
}
