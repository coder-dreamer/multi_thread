package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 多把锁
 *
 * @author 53137
 */
public class ManyLock {
    public static void main(String[] args) {
        //BigRoom bigRoom = new BigRoom();
        BigRoomV1 bigRoom = new BigRoomV1();
        new Thread(() -> {
            try {
                bigRoom.study();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                bigRoom.sleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom {
    public void study() throws InterruptedException {
        synchronized (this) {
            log.debug("学习");
            Thread.sleep(3000);
        }
    }

    public void sleep() throws InterruptedException {
        synchronized (this) {
            log.debug("睡觉");
            Thread.sleep(2000);
        }
    }
    //锁粒度太大，会导致两个线程不能同时进行
    //22:03:01.513 [t1] c.BigRoom - 学习
    //22:03:04.516 [t2] c.BigRoom - 睡觉
}

@Slf4j(topic = "c.BigRoom")
class BigRoomV1 {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public void study() throws InterruptedException {
        synchronized (lock1) {
            log.debug("学习");
            Thread.sleep(3000);
        }
    }

    public void sleep() throws InterruptedException {
        synchronized (lock2) {
            log.debug("睡觉");
            Thread.sleep(2000);
        }
    }
    //锁粒度变小后，不同业务同时进行
    //22:05:26.439 [t2] c.BigRoom - 睡觉
    //22:05:26.439 [t1] c.BigRoom - 学习
}
