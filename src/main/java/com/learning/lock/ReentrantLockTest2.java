package com.learning.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 * tryLock
 */
public class ReentrantLockTest2 extends Thread {

    private static ReentrantLock reentrantLock = new ReentrantLock(false);

    public void run() {
        for(int i = 0; i < 100; i ++) {
            try{
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest2 reentrantLockTest2 = new ReentrantLockTest2();
        new Thread(reentrantLockTest2).start();
        new Thread(reentrantLockTest2).start();
    }
}
