package com.learning.juc;

import java.util.concurrent.TimeUnit;

public class Thread1 implements Runnable{

    private int a;
    private int b;

    public Thread1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        sum(a, b);
    }

    public synchronized void sum(int a, int b) {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a:" + a + " b:" + b);
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread1(1, 2)).start();
        new Thread(new Thread1(3, 4)).start();

    }
}
