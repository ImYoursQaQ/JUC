package com.learning.singleton;

import java.util.Arrays;

/**
 * 线程安全的单例模式：
 * 更好的是采用下面的方式，既不用加锁，也能实现懒加载
 */
public class Singleton5 {
    private Singleton5() {
        System.out.println("single");
    }

    public static Singleton5 getSingle() {
        return Inner.singleton5;
    }

    private static class Inner {
        private static Singleton5 singleton5 = new Singleton5();
    }

    public static void main(String[] args) {
        Thread[] ths = new Thread[200];
        for(int i = 0; i < ths.length; i ++) {
            ths[i] = new Thread(() -> {
                System.out.println(Singleton5.getSingle());
            });
        }
        Arrays.asList(ths).forEach(t -> t.start());
    }
}
