package com.learning.interview_01;

import java.util.ArrayList;
import java.util.List;

/**
 * 曾经的面试题：（淘宝？）
 *  * 实现一个容器，提供两个方法，add，size
 *  * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *  *
 *  * 分析下面这个程序，能完成这个功能吗？
 */
public class WithoutVolatile {

    List list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        WithoutVolatile withoutVolatile = new WithoutVolatile();
        new Thread(() -> {

        }).start();
    }
}
