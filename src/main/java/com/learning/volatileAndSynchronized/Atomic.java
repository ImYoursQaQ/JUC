package com.learning.volatileAndSynchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是volatile不能替代synchronized,volatile并不能保证原子性
 * 需要添加synchronized保证原子性
 * 或者使用AtomicInteger
 */
public class Atomic {
    /*public volatile int count = 0;*/
    public AtomicInteger count = new AtomicInteger(0);
    public /*synchronized*/ void m() {
        for(int i = 0; i < 10000; i ++) {
            //count ++;
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        Atomic atomic = new Atomic();
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            threadList.add(new Thread(() -> atomic.m()));
        }
        threadList.forEach(thread -> thread.start());
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(atomic.count);
    }
}
