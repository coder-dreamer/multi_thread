package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁
 *
 * @author 53137
 */
@Slf4j(topic = "c.DeadLock")
public class DeadLock {
    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        new Thread(() -> {
            synchronized (a) {
                log.debug("t1获得锁a");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    log.debug("t1获得锁b");
                }
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (b) {
                log.debug("t2获得锁b");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    log.debug("t2获得锁a");
                }
            }
        }, "t2").start();
    }
}
