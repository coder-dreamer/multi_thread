package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * Synchronized解决线程安全问题
 *
 * @author 53137
 */
@Slf4j(topic = "c.ThreadSafeBySynchronized")
public class ThreadSafeBySynchronized {
    static int a = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    a++;
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    a--;
                }
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("a的结果：{}", a);
    }
}
