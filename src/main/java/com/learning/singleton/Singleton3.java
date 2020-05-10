package com.learning.singleton;

/**
 * 懒汉式 + synchronized
 *
 */
public class Singleton3 {
    private static Singleton3 instance = null;
    public Singleton3() {

    }
    public synchronized static Singleton3 getInstance() {
        if(instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
