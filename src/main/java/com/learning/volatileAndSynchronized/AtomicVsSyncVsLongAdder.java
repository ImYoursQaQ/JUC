package com.learning.volatileAndSynchronized;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicXX与Synchronized与LongAdder的效率
 */
public class AtomicVsSyncVsLongAdder {
    public long count1 = 0;
    public AtomicLong count2 = new AtomicLong(0);
    public LongAdder count3 = new LongAdder();

    private final Object o = new Object();

    public void test1() throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for(int i = 0; i < threads.length; i ++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 100000; j ++) {
                    synchronized (o) {
                        count1 ++;
                    }
                }
            });
        }
        long start = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.println("sync:" + (end - start));
    }

    public void test2() throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for(int i = 0; i < threads.length; i ++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 100000; j ++) {
                    count2.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.println("atomicLong:" + (end - start));
    }

    public void test3() throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for(int i = 0; i < threads.length; i ++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 100000; j ++) {
                    count3.increment();
                }
            });
        }
        long start = System.currentTimeMillis();
        for(Thread t : threads) t.start();
        for(Thread t : threads) t.join();
        long end = System.currentTimeMillis();
        System.out.println("LongAdder:" + (end - start));
    }

    public static void main(String[] args) throws InterruptedException {

        AtomicVsSyncVsLongAdder atomicVsSyncVsLongAdder = new AtomicVsSyncVsLongAdder();
        atomicVsSyncVsLongAdder.test1();;
        atomicVsSyncVsLongAdder.test2();
        atomicVsSyncVsLongAdder.test3();
    }
}
