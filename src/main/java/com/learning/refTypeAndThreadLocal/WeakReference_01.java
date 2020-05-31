package com.learning.refTypeAndThreadLocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用遭到gc就会回收
 */
public class WeakReference_01 {

    public static void main(String[] args) {
        WeakReference<M> weakReference = new WeakReference<>(new M());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
