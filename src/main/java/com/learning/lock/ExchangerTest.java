package com.learning.lock;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            String s = "s1";
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName + " exchange start ... " + s);
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " " + s);
        }).start();

        new Thread(() -> {
            String s = "s2";
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println(threadName + " exchange start ... " + s);
                TimeUnit.SECONDS.sleep(3);
                exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " " + s);
        }).start();
    }
}
