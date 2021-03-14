package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 优雅使用wait与notify
 * 小王需要喝水才能干活，小李需要吃肉才能干活，送外卖的只负责送水
 *
 * @author 53137
 */
@Slf4j(topic = "c.UseWaitNotify")
public class UseWaitNotify {
    /**
     * 锁
     */
    static final Object lock = new Object();
    /**
     * 水
     */
    static boolean waterStatus = false;
    /**
     * 肉
     */
    static boolean meatStatus = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                while (!waterStatus) {
                    log.debug("{}没水，等待中", Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (waterStatus) {
                    log.debug("{}开始干活", Thread.currentThread().getName());
                } else {
                    log.debug("{}不干活", Thread.currentThread().getName());
                }

            }
        }, "小王").start();
        new Thread(() -> {
            synchronized (lock) {
                while (!meatStatus) {
                    log.debug("{}没肉，等待中", Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (meatStatus) {
                    log.debug("{}开始干活", Thread.currentThread().getName());
                } else {
                    log.debug("{}不干活", Thread.currentThread().getName());
                }
            }
        }, "小李").start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                waterStatus = true;
                log.debug("水到了");
                lock.notifyAll();
            }
        }, "送外卖").start();

    }
}
