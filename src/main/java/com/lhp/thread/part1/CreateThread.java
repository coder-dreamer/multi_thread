package com.lhp.thread.part1;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程
 *
 * @author 53137
 */
@Slf4j(topic = "c.CreateThread")
public class CreateThread {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("匿名内部类创建线程");
            }
        };
        t.setName("t1");
        t.start();
        log.debug("主线程");
    }
}
