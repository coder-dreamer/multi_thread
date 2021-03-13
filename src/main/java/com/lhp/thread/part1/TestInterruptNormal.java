package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 测试打断正常线程
 *
 * @author 53137
 */
@Slf4j(topic = "c.TestInterruptNormal")
public class TestInterruptNormal {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("线程被打断了，退出循环");
                    break;
                }
            }
        }, "t1");
        t1.start();
        log.debug("开始打断");
        Thread.sleep(500);
        t1.interrupt();
        log.debug("打断标记：{}", t1.isInterrupted());
    }
}
