package com.learning.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 */
public class VolatileVsSync {
    int count = 0;

    public synchronized void m1() {
        for(int i = 0;i < 1000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) {
        VolatileVsSync volatileVsSync = new VolatileVsSync();
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            threadList.add(new Thread(volatileVsSync::m1));
        }
        threadList.forEach(thread -> thread.start());
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(volatileVsSync.count);
    }

}
