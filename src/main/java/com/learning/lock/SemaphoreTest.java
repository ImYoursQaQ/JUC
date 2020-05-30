package com.learning.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for(int i = 0; i < 10; i ++) {
            final int j = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("T" + j + " running...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("T" + j + " ending...");
                }
            }).start();
        }
    }
}
