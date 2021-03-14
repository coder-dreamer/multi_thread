package com.lhp.thread.part2;

/**
 * 直接调用wait方法会抛异常
 * IllegalMonitorStateException
 *
 * @author 53137
 */
public class TestWaitThrowException {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        lock.wait();
    }
}
