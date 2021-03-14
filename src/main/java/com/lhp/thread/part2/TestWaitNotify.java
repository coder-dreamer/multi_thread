package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 53137
 */
@Slf4j(topic = "c.TestWaitNotify")
public class TestWaitNotify {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    log.debug("t1进入等待");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("t1执行其它代码");
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
                try {
                    log.debug("t2进入等待");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("t2执行其它代码");
            }
        }, "t2").start();
        Thread.sleep(2000);
        log.debug("开始唤醒");
        synchronized (lock) {
            //随机唤醒一个线程
            //lock.notify();
            //将线程全部唤醒
            lock.notifyAll();
        }

    }
}
