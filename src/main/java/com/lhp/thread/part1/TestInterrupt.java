package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试打断方法
 *
 * @author 53137
 */
@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        TimeUnit.SECONDS.sleep(1);
        log.debug("开始打断");
        t1.interrupt();
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
