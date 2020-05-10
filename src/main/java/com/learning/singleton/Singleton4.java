package com.learning.singleton;

import java.util.Set;
import java.util.TreeSet;

/**
 * 懒汉式 + 双重检验锁
 *
 */
public class Singleton4 {
    private static volatile Singleton4 instance = null;
    public Singleton4() {

    }
    public synchronized static Singleton4 getInstance() {
        if(instance == null) {
            synchronized (Singleton4.class) {
                if(instance == null) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        for(int i = 0; i < 100; i ++) {
            new Thread(() -> {
                set.add(Singleton4.getInstance().hashCode());
            }).start();
        }
        System.out.println(set.size());
    }
}
