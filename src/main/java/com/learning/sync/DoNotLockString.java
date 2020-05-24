package com.learning.sync;

import java.util.concurrent.TimeUnit;

/**
 * 要以字符串常量作为锁定对象
 *  在下面的例子中，m1和m2其实锁定的是同一个对象
 *  这种情况还会发生比较诡异的现象，比如你用到了一个类库，在该类库中代码锁定了字符串“Hello”，
 *  但是你读不到源码，所以你在自己的代码中也锁定了"Hello",这时候就有可能发生非常诡异的死锁阻塞，
 *  因为你的程序和你用到的类库不经意间使用了同一把锁
 */
public class DoNotLockString {

    String s1 = "hello";
    String s2 = "hello";

    public void m1() {
        synchronized (s1) {
            for(int i = 0; i < 1000; i ++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("m1: " + i);
            }

        }
    }

    public void m2() {
        synchronized (s2) {
            for(int i = 0; i < 1000; i ++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("m2: " + i);
            }
        }
    }

    public static void main(String[] args) {
        DoNotLockString doNotLockString = new DoNotLockString();
        new Thread(doNotLockString::m1).start();
        new Thread(doNotLockString::m2).start();
    }
}
