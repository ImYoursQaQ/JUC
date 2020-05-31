package com.learning.interview_02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *  *
 *  * 使用wait和notify/notifyAll来实现
 *  *
 *  * 使用Lock和Condition来实现
 *  * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */
public class Container2<T> {
    private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (count == MAX) { //想想为什么用while而不是用if？
                condition1.await();
            }
            list.add(t);
            count ++;
            condition2.signalAll(); //通知消费者线程进行消费
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while(count == 0) {
                System.out.println(Thread.currentThread().getName() + "await");
                condition2.await();
            }
            t = list.removeFirst();
            count --;
            condition1.signalAll();//通知生产者进行生产
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        Container2 container2 = new Container2<String>();
        //启动消费者线程
        for(int i = 0; i < 10; i ++) {
            new Thread(() -> {
                for(int j = 0; j < 5; j ++) {
                    System.out.println(container2.get());
                }
            }, "C" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程
        for(int i = 0; i < 2; i ++) {
            new Thread(() -> {
                for(int j = 0; j < 25; j ++) {
                    container2.put(Thread.currentThread().getName() + " " + j);
                }
            }, "P" + i).start();
        }
    }
}
