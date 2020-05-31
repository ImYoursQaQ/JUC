package com.learning.interview_01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Semaphore_01 {

    //添加volatile，使t2能够得到通知
    volatile List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        Semaphore_01 semaphore_01 = new Semaphore_01();
        Semaphore semaphore = new Semaphore(1);
        Thread t2 = new Thread(() -> {
            System.out.println("t2启动");
            try {
                semaphore.acquire();
                System.out.println("t2 结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }, "t2");

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 5; i++) {
                    semaphore_01.add(new Object());
                    System.out.println("add " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i = 5; i < 10; i ++) {
                semaphore_01.add(new Object());
                System.out.println("add " + i);
            }
        }, "t1");
        t1.start();
    }
}
