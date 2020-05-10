package com.learning.juc;

import java.util.concurrent.TimeUnit;

public class Thread3 implements Runnable{

    private int a;
    private int b;
    private Object o = new Object();

    public Thread3(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        sum(a, b);
    }

    public void sum(int a, int b) {
        synchronized (o) {
            for(int i = 0; i < 10; i ++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a:" + a + " b:" +b);
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Thread3(1, 2)).start();
        new Thread(new Thread3(3, 4)).start();

    }
}
