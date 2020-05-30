package com.learning.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static void main(String[] args) {
        final Object o = new Object();
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10; i ++) {
                try {
                    if(i == 5) {
                        LockSupport.park();
                    }
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        //LockSupport.unpark(t1);

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LockSupport.unpark(t1);

    }
}
