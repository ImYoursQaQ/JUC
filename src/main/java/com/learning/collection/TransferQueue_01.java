package com.learning.collection;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;

public class TransferQueue_01 {

	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		strs.transfer("aaa");// 线程阻塞，等待消费者消费
		System.out.println("ccc");

		strs.put("aaab");


		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

	}
}
