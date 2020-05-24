package com.learning.sync;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *  运行下面的程序，并分析结果
 */
public class VolatileNotSync {

    volatile int count = 0;

    public void m1() {
        for(int i = 0;i < 1000; i++) {
            count ++;
        }
    }

    public static void main(String[] args) {
        VolatileNotSync volatileNotSync = new VolatileNotSync();
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            threadList.add(new Thread(volatileNotSync::m1));
        }
        threadList.forEach(thread -> thread.start());
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(volatileNotSync.count);
    }
}
