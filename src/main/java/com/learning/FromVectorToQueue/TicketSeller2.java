package com.learning.FromVectorToQueue;

import java.util.Vector;

/**
 * 有N张火车票，每张票都有一个编号
 *  * 同时有10个窗口对外售票
 *  * 请写一个模拟程序
 *  *
 *  * 分析下面的程序可能会产生哪些问题？
 *  *
 *  * 使用Vector或者Collections.synchronizedXXX
 *  * 分析一下，这样能解决问题吗?
 */
public class TicketSeller2 {

	static Vector<String> tickets = new Vector<String>();

	static {
		for(int i = 0; i < 10000; i ++) {
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; i ++) {
			new Thread(() -> {
				while (tickets.size() > 0) {
					System.out.println("销售了--" + tickets.remove(0));
				}
			}).start();
		}
	}
}
