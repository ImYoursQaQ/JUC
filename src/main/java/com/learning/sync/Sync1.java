package com.learning.sync;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 *  也就是synchronized获取的锁是可重入的
 */
public class Sync1 {

    public synchronized void m1() {
        System.out.println("m1 start...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    public synchronized void m2() {
        System.out.println("m2 start ...");
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        new Sync1().m1();
    }
}
