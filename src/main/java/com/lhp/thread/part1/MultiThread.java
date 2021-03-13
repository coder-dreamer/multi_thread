package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 演示多线程交替执行
 * 线程交替执行
 * 执行顺序不由我们控制
 *
 * @author 53137
 */
@Slf4j(topic = "c.MultiThread")
public class MultiThread {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                log.debug("线程t1");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                log.debug("线程t2");
            }
        }, "t2").start();
    }
}
