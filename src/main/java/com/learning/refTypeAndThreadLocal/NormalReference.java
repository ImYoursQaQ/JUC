package com.learning.refTypeAndThreadLocal;

import java.io.IOException;

/**
 * 强引用
 */
public class NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC

        System.in.read();//阻塞main线程，给垃圾回收线程时间执行
    }
}
