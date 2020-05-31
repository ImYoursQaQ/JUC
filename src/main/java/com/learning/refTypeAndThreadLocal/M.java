package com.learning.refTypeAndThreadLocal;

public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
