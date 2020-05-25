package com.learning.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 *  由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 *  这里是复习synchronized最原始的语义
 *
 *  使用reentrantlock可以完成同样的功能
 *  需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 *  使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
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
