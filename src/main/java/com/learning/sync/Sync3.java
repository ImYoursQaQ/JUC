package com.learning.sync;

import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中，如果出现异常，默认情况下锁会释放，
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果处理异常不合适，
 * 在第一个线程抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据
 * 因此要非常小心的处理同步业务逻辑中的异常
 */
public class Sync3 {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "start");
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1/0;
        }
    }

    public static void main(String[] args) {
        Sync3 sync3 = new Sync3();
        new Thread(() -> {
            sync3.m1();
        }, "t1").start();
        new Thread(() -> {
            sync3.m1();
        }, "t2").start();
    }
}
