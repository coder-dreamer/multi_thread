package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 53137
 */
@Slf4j(topic = "c.WaitAndSleep")
public class WaitAndSleep {
    /**
     * 创建锁
     */
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("t1获得锁");
                //sleep不释放锁
                /*try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                //wait释放锁
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        Thread.sleep(1000);
        synchronized (lock) {
            log.debug("主线程获得锁");
            log.debug("主线程做其他事情");
        }
    }
}
