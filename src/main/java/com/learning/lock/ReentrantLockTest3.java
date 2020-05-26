package com.learning.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantlock用于替代synchronized
 *  * 由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 *  * 这里是复习synchronized最原始的语义
 *  *
 *  * 使用reentrantlock可以完成同样的功能
 *  * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 *  * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 *  *
 *  * 使用reentrantlock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 *  *
 *  * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
 *  * 在一个线程等待锁的过程中，可以被打断
 *  *
 *  * ReentrantLock还可以指定为公平锁
 */
public class ReentrantLockTest3 extends Thread {

    private static ReentrantLock reentrantLock = new ReentrantLock(false);

    @Override
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
        ReentrantLockTest3 reentrantLockTest2 = new ReentrantLockTest3();
        new Thread(reentrantLockTest2).start();
        new Thread(reentrantLockTest2).start();
    }
}
