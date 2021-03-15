package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁-测试可打断
 * 主线程获取锁，thread获取锁时被阻塞，主线程打断t1线程，thread等待锁的时候被打断
 *
 * @author 53137
 */
@Slf4j(topic = "c.ReentrantLockTest1")
public class ReentrantLockTest1 {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("等待锁的过程中被打断");
                return;
            }
            try {
                log.debug("获得了可重入锁");
            } finally {
                lock.unlock();
            }
        });
        try {
            lock.lock();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
