package com.learning.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(20, () -> {
			System.out.println("人满发车");
		});

		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println(Thread.currentThread().getName() + " await start");
					barrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
