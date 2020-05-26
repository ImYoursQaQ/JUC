package com.learning.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

	public void usingCountDownLatch() {
		List<Thread> threadList = new ArrayList<>();
		CountDownLatch latch = new CountDownLatch(100);
		for(int i = 0; i < 100; i ++) {
			threadList.add(new Thread(() -> {
				int  result = 0;
				for(int j = 0; j < 10000; j ++) {
					result += j;
				}
				latch.countDown();
				System.out.println(latch.getCount());
			}));
		}
		threadList.forEach(thread -> thread.start());

		try {
			System.out.println("latch wait start ...");
			latch.await();
			System.out.println("latch wait end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		CountDownLatchTest countDownLatchTest = new CountDownLatchTest();
		countDownLatchTest.usingCountDownLatch();
	}
}
