package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试打断sleep方法
 *
 * @author 53137
 */
@Slf4j(topic = "c.TestInterruptSleep")
public class TestInterruptSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("开始打断");
        t1.interrupt();
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
