package com.learning.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock
 */
public class ReentrantLockTest {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void m1() {
        try{
            reentrantLock.lock();
            System.out.println("m1 start ...");
            for(int i = 0; i < 10; i ++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void m2() {
        try{
            reentrantLock.lock();
            System.out.println("m2 start ...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(() -> reentrantLockTest.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> reentrantLockTest.m2()).start();
    }
}
