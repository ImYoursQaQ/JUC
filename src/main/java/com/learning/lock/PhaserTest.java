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
			switch (phase) {
				case 0 :
					System.out.println();
					return false;
				case 1 :
					System.out.println();
					return false;
				case 2 :
					System.out.println();
					return false;
				case 3 :
					System.out.println();
					return true;
				default:
					return true;
			}
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
			milliSleep(r.nextInt(1000));
			System.out.printf("%s 吃完!\n", name);
			phaser.arriveAndAwaitAdvance();
		}

		public void leave() {
			milliSleep(r.nextInt(1000));
			System.out.printf("%s 离开！\n", name);
			phaser.arriveAndAwaitAdvance();
		}

		public void hug() {
			if(name.equals("新郎") || name.equals("新娘")) {
				milliSleep(r.nextInt(1000));
				System.out.printf("%s 洞房！\n", name);
				phaser.arriveAndAwaitAdvance();
			} else {
				phaser.arriveAndDeregister();
				//phaser.register();
			}
		}

		@Override
		public void run() {
			arrive();
			eat();
			leave();
			hug();
		}
	}
}
