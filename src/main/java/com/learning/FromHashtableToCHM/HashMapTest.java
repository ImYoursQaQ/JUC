package com.learning.FromHashtableToCHM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class HashMapTest {

    static HashMap<UUID, UUID> hashMap = new HashMap<>();
    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for(int i = 0; i < count; i ++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = Constants.COUNT / Constants.THREAD_COUNT;
        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for(int i = start; i < start + gap; i ++) {
                hashMap.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[Constants.THREAD_COUNT];
        for(int i = 0; i < threads.length; i ++) {
            threads[i] = new MyThread(i *  (count/THREAD_COUNT));
        }
        Arrays.asList(threads).forEach(thread -> thread.start());
        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(hashMap.size());
    }
}
