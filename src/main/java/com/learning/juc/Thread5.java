package com.learning.juc;

/**
 * synchronized方法与非synchronized方法是否可以同时执行
 */
public class Thread5 {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2");
    }

    public static void main(String[] args) {
        Thread5 thread5 = new Thread5();
        new Thread(() -> thread5.m1(), "t1").start();
        new Thread(() -> thread5.m2(), "t1").start();
        /*new Thread(thread5::m1, "t1").start();
        new Thread(thread5::m2, "t1").start();*/
    }
}
