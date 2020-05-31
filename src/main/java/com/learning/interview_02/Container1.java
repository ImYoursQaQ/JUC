package com.learning.interview_02;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 *  * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *  *
 *  * 使用wait和notify/notifyAll来实现
 */
public class Container1<T> {

    private final LinkedList<T> list = new LinkedList<T>();
    final private int MAX = 10; //最多10个元素
    private int count = 0;

    public synchronized void put(T o) {
        while (getCount() == MAX) { //想想为什么用while而不是用if？
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(o);
        count++;
        notifyAll();//通知消费者线程进行消费
    }

    public synchronized T get() {
        while (getCount() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = (T) list.removeFirst();
        count--;
        notifyAll();//通知生产者进行生产
        return t;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Container1 container1 = new Container1<String>();
        //启动消费者线程
        for(int i = 0; i < 10; i ++) {
            new Thread(() -> {
                for(int j = 0; j < 5; j ++) {
                    System.out.println(container1.get());
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
                    container1.put(Thread.currentThread().getName() + " " + j);
                }
            }, "P" + i).start();
        }
    }
}
