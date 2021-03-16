package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁-测试锁超时
 * 获取锁失败了、获取超时了或者被打断了，不再阻塞，直接停止运行。
 *
 * @author 53137
 */
@Slf4j(topic = "c.ReentrantLockTest1")
public class ReentrantLockTest2 {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            if (!lock.tryLock()) {
                log.debug("没拿到锁，结束");
                return;
            }
            try {
                log.debug("获得了可重入锁");
            } finally {
                lock.unlock();
            }
        });
        lock.lock();
        try {
            thread.start();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
