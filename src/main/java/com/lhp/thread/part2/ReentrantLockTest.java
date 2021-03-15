package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁-测试可重入
 *
 * @author 53137
 */
@Slf4j(topic = "c.ReentrantLockTest")
public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            log.debug("enter main");
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1() {
        lock.lock();
        try {
            log.debug("enter m1");
            m2();
        } finally {
            lock.unlock();
        }

    }

    public static void m2() {
        lock.lock();
        try {
            log.debug("enter m2");
        } finally {
            lock.unlock();
        }

    }
}
