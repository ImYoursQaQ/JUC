package com.learning.lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class PhaserTest {

	private Random r = new Random();
	private MarriagePhaser phaser = new MarriagePhaser();

	public static void milliSleep(int milli) {
		try {
			TimeUnit.MILLISECONDS.sleep(milli);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class MarriagePhaser extends Phaser {
		@Override
		protected boolean onAdvance(int phase, int registeredParties) {
			return super.onAdvance(phase, registeredParties);
		}
	}

	class Person implements Runnable {

		private String name;

		public Person(String name) {
			this.name = name;
		}

		public void arrive() {
			milliSleep(r.nextInt(1000));
			System.out.printf("%s 到达现场！\n", name);
			phaser.arriveAndAwaitAdvance();
		}

		public void eat() {

		}

		public void leave() {

		}

		@Override
		public void run() {

		}
	}
}
