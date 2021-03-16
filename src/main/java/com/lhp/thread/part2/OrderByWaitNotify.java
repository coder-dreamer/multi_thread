package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 顺序执行，WaitNotify版,执行顺序：先2后1
 *
 * @author 53137
 */
@Slf4j(topic = "c.OrderByWaitNotify")
public class OrderByWaitNotify {
    /**
     * 锁
     */
    static final Object lock = new Object();
    /**
     * t2线程是否运行
     */
    static boolean t2Run = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while (!t2Run) {
                    try {
                        //释放锁
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }
                log.debug("1");
            }

        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                t2Run = true;
                lock.notifyAll();
                log.debug("2");
            }
        });
        t1.start();
        t2.start();
    }
}
