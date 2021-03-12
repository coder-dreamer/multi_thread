package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程
 *
 * @author 53137
 */
@Slf4j(topic = "c.CreateThread")
public class CreateThreadByRunning {
    public static void main(String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                log.debug("使用Runnable配合Thread创建线程");
            }
        };
        Thread t = new Thread(run);
        t.setName("t1");
        t.start();
        log.debug("主线程");
    }
}
