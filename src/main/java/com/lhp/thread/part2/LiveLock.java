package com.lhp.thread.part2;

import lombok.extern.slf4j.Slf4j;

/**
 * 活锁
 *
 * @author 53137
 */
@Slf4j(topic = "c.LiveLock")
public class LiveLock {
    static volatile int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count >= 0) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                log.debug("count{}", count);
            }
        }).start();

        new Thread(() -> {
            while (count < 30) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                log.debug("count{}", count);
            }
        }).start();
    }
}
