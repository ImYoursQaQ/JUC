package com.learning.sync;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 *  也就是说synchronized获得的锁是可重入的
 *  这里是继承中有可能发生的情形，子类调用父类的同步方法
 */
public class Sync2 {

    private static class S1 {

        public synchronized void m1() {
            System.out.println("s1 m1 start ...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s1 m1 end ...");
        }
    }

    private static class S2 extends S1 {

        @Override
        public synchronized void m1() {
            super.m1();
            System.out.println("s2 m1 start ...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s2 m1 end ...");
        }
    }

    public static void main(String[] args) {
        new S2().m1();
    }
}
