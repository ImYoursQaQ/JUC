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
 *
 *  使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLockTest1 {

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
        boolean getLock = false;
        try{
            getLock = reentrantLock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 start ... " + getLock);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(getLock) {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest1 reentrantLockTest = new ReentrantLockTest1();
        new Thread(() -> reentrantLockTest.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> reentrantLockTest.m2()).start();
    }
}
